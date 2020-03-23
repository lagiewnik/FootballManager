package example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scheduler {

    public void listMatches(List<String> ListTeam)
    {


        if (ListTeam.size() % 2 != 0)
        {
            ListTeam.add("Empty"); // If odd number of teams add a dummy
        }
        int numTeams = ListTeam.size();

        int numDays = (numTeams - 1); // Days needed to complete tournament
        int halfSize = numTeams / 2;

        List<String> teams = new ArrayList<String>();

        teams.addAll(ListTeam); // Add teams to List and remove the first team
        teams.remove(0);

        int teamsSize = teams.size();

        for (int day = 0; day < numDays; day++)
        {
            System.out.println("Day: " + (day + 1));

            int teamIdx = day % teamsSize;

            System.out.println(teams.get(teamIdx) + " vs. " +  ListTeam.get(0));

            for (int idx = 1; idx < halfSize; idx++)
            {
                int firstTeam = (day + idx) % teamsSize;
                int secondTeam = (day  + teamsSize - idx) % teamsSize;
                System.out.println( teams.get(firstTeam) + " vs. "+ teams.get(secondTeam));
            }
        }
    }
    public static void main(String[] args) {
        List<String> ekstraklapa = new ArrayList<String>(Arrays.asList("legia", "Wisła", "Korona", "Pogoń", "Lechia", "Zagłębie", "śląsk", "Cracovia"));
        Scheduler ligaExstraklapa = new Scheduler();
        ligaExstraklapa.listMatches(ekstraklapa);

    }

}

