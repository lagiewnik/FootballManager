package footballmanager;

import java.util.ArrayList;

public class Scheduler
{
    private ArrayList<Round> league;
    public ArrayList<Match> round;

    //  round robin schedule method
    public ArrayList<String> schedule(ArrayList<FootballClub> list) {

        this.league = null;
        //this.rounds = new String[(list.size()-1)*2][(list.size() / 2)];

        for (int roundNumber = 0; roundNumber < (list.size() - 1) * 2; roundNumber++) {
            round = null;
            for (int matchNumber = 0; matchNumber < (list.size() / 2); matchNumber++) {
                Match game = new Match();

                game.setTeamA(list.get((roundNumber + matchNumber) % (list.size() - 1)));
                game.setTeamB(list.get((list.size() - 1 - matchNumber + roundNumber) % (list.size() - 1)));

                // Last team stays in the same place while the others rotate around it.
                if (matchNumber == 0) {
                    game.setTeamB(list.get(list.size() - 1));
                }

                // from rounds half interchange the position of teams in rounds, to get both home and away matches
                String mixedRounds;
                if (roundNumber < (list.size() - 1)) {
                    mixedRounds = (game.getTeamA().getName() + " vs " + game.getTeamB().getName());

                } else
                //interchange the place of teams from half ((teamList.size() - 1)
                {
                    mixedRounds = (game.getTeamB().getName() + " vs " + game.getTeamA().getName());
                }

                round.set(matchNumber, game);
            }
            league.set(roundNumber, round);
        }
        return league;
    }
}

class Round{
    public Round() {
        this.round = null;
    }

    public ArrayList<Match> round;


}

