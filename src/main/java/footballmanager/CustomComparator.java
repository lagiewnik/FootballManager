package footballmanager;

import java.util.Comparator;

public class CustomComparator implements Comparator<FootballClub> {


    public int compare(FootballClub t1, FootballClub t2) {
        if (t1.getPoints() > t2.getPoints())
            return -1;
        else if (t1.getPoints() < t2.getPoints())
            return 1;
        else {
            int goalDifT1 = t1.getScoredGoalsCount() - t1.getReceivedGoalsCount();
            int goalDifT2 = t2.getScoredGoalsCount() - t2.getReceivedGoalsCount();
            if (goalDifT1 > goalDifT2)
                return -1;
            else if (goalDifT1 < goalDifT2)
                return 1;
            else return 0;

        }

    }
}
