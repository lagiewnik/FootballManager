package footballmanager;

import java.util.Random;

public class ScoreGen {

    final private int[] footballScoreWeightGoals = {25303, 25599, 16353, 7979, 3792, 1550, 628, 480, 260, 153, 82, 20, 15, 11, 6, 3, 1, 1, 1};

    private int randomIndex() {
        Random generator = new Random();
        int sum = 0;
        for (int x : footballScoreWeightGoals )
            sum += x;
        int r = generator.nextInt(sum);
        for (int i = 0; i < footballScoreWeightGoals.length; ++i) {
            r -= footballScoreWeightGoals[i];
            if (r < 0)
                return i;
        }
        assert r >= 0;
        return -1;
    }


   public static int [] simulateScoreMatch(){
        ScoreGen goalGenerator = new ScoreGen();
        int[] scoreMatch = new int[]{goalGenerator.randomIndex(), goalGenerator.randomIndex()};
        return scoreMatch;
    }


}