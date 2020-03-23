package footballmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EkstraklasaManager implements LeagueManager {

    private final int numberOfClubs;
    private final ArrayList<FootballClub> league;
    private final Scanner scanner;
    private final ArrayList<Match> matches;

    public EkstraklasaManager(int numberOfClubs) {
        this.numberOfClubs = numberOfClubs;
        league = new ArrayList<FootballClub>();
        matches = new ArrayList<Match>();
        scanner = new Scanner(System.in);
        displayMenu();
    }

    private void displayMenu() {
        while (true) {
            System.out.println("League Menu: ");
            System.out.println("1- Create new team and add it to league");
            System.out.println("2- Delete team from league");
            System.out.println("3- Display statistic of team");
            System.out.println("4- Display table of league");
            System.out.println("5- Add played match league");
            System.out.println("6- Display calendar and find match");
            String line = scanner.nextLine();

            int command = 0;
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {

            }

            switch (command) {
                case 1:
                    System.out.println("It will add new team");
                    addTeam();
                    break;
                case 2:
                    deleteTeam();
                    break;
                case 3:
                    displayStatistics();
                    break;
                case 4:
                    displayLeageTable();
                    break;
                case 5:
                    addPlayedMatch();
                    break;
                case 6:
                   // displayCalendar();
                    break;


                default:
                    System.out.println("Wrong command");
            }
        }


    }

    /*private void displayCalendar() {
        System.out.println("Enter year: ");
        String line = scanner.nextLine();
        int y = -7777;
        try{
            y=Integer.parseInt(line);
        }
        catch (Exception e){

        }
        if (y==-7777){
            System.out.println("You have to enter a year");
            return;
        }

        System.out.println("Enter month: ");
        String line = scanner.nextLine();
        int m = 0;
        try{
            m=Integer.parseInt(line);
        }
        catch (Exception e){

        }
        if (m==0){
            System.out.println("You have to enter a ,onth");
            return;
        }

        String[] month =
    }*/

    private void addPlayedMatch() {
        System.out.println("Enter the date of match (dd-mm-yyyy)");
        String line = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(line);
        } catch (ParseException ex) {
            System.out.println("You have to enter date in format: (dd-mm-yyyy)");
            return;
        }
        System.out.println("Enter home team: ");
        line = scanner.nextLine();
        FootballClub home = null;
        Iterator itr = league.iterator();

        while (itr.hasNext()) {
            FootballClub club = (FootballClub) itr.next();
            if (club.getName().equalsIgnoreCase(line)) {
                home = club;
            }
        }
        if (home == null){
            System.out.println("No such club in league");
            return;
        }

        System.out.println("Enter away team: ");
        line = scanner.nextLine();
        FootballClub away = null;
        itr = league.iterator();

        while (itr.hasNext()) {
            FootballClub club = (FootballClub) itr.next();
            if (club.getName().equalsIgnoreCase(line)) {
                away = club;
            }
        }
        if (away == null){
            System.out.println("No such club in league");
            return;
        }

        System.out.println("Enter home team goals: ");
        line = scanner.nextLine();
        int homeGoals = -1;
        try{
            homeGoals = (int) Integer.parseInt(line);
        } catch (Exception e){

        }
        if (homeGoals == -1){
            System.out.println("you have to enter number of goals");
            return;
        }

        System.out.println("Enter home away goals: ");
        line = scanner.nextLine();
        int awayGoals = -1;
        try{
            awayGoals = (int) Integer.parseInt(line);
        } catch (Exception e){

        }
        if (awayGoals == -1){
            System.out.println("you have to enter number of goals");
            return;
        }

        Match match = new Match();
        match.setDate(date);
        match.setTeamA(home);
        match.setTeamB(away);
        match.setTeamAScore(homeGoals);
        match.setTeamBScore(awayGoals);
        matches.add(match);
        home.addMatchGoal(homeGoals,awayGoals);
        away.addMatchGoal(awayGoals,homeGoals);


        if(homeGoals> awayGoals){
            home.addMatchPoint(3);
            home.addWin();
            away.addLoose();
        }
        else if(awayGoals> homeGoals){
            away.addMatchPoint(3);
            away.addWin();
            home.addLoose();
        }
        else{
            away.addMatchPoint(1);
            home.addMatchPoint(1);
            away.addDraw();
            home.addDraw();
        }
        home.addMatchPlayed();
        away.addMatchPlayed();

    }

    private void displayLeageTable() {
        Collections.sort(league, new CustomComparator());
        for (FootballClub club : league
        ) {
            System.out.println(club.getName() + " " + club.getMatchesPlayed() + " " + club.getPoints() + " " + club.getScoredGoalsCount() + " : " + club.getReceivedGoalsCount());

        }
    }

    private void displayStatistics() {
        System.out.println("Insert club name ");
        String line = scanner.nextLine();
        Iterator itr = league.iterator();

        while (itr.hasNext()) {
            FootballClub club = (FootballClub) itr.next();
            if (club.getName().equalsIgnoreCase(line)) {

                System.out.format("Club: %s matches won: %d , drawed: %d,  loosed: %d, goals: %d : %d , points: %d %n", club.getName(), club.getWinCount(), club.getDrawCount(), club.getDefeatCount(), club.getScoredGoalsCount(), club.getReceivedGoalsCount(), club.getPoints());
                return;
            }
        }
        System.out.println("No such club: " + line + " in league");
    }

    private void deleteTeam() {
        System.out.println("Insert club name ");
        String line = scanner.nextLine();
        Iterator itr = league.iterator();

        while (itr.hasNext()) {
            FootballClub club = (FootballClub) itr.next();
            if (club.getName().equalsIgnoreCase(line)) {
                itr.remove();
                System.out.println("Club: " + club.getName() + " removed!!!");
                return;
            }
        }
        System.out.println("No such club: " + line + " in league");
    }

    private void addTeam() {
        if (league.size() == numberOfClubs) {
            System.out.println("Can't add more clubs to league");
            return;
        }

        FootballClub club = new FootballClub();
        System.out.println("Insert Club name: ");
        String line = scanner.nextLine();
        club.setName(line);

        if (league.contains(club)) {
            System.out.println("This club is already in the league");
            return;
        }
        System.out.println("Insert club location: ");
        line = scanner.nextLine();
        club.setLocation(line);
        league.add(club);
    }


}
