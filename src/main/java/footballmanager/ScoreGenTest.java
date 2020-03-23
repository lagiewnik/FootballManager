package footballmanager;

import java.util.Random;

public class ScoreGenTest {


    public static void main(String[] args) {
        //int [] weight = {600,1,1,1,1};
        int[] x;
        int countX=0;
        for (int i = 0; i < 1000000 ; i++) {
            x= ScoreGen.simulateScoreMatch();
            System.out.println(x[0] + " : " + x[1]);
            }


        }
    }