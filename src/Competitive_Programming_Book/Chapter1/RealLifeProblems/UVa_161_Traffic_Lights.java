package Competitive_Programming_Book.Chapter1.RealLifeProblems;

import java.lang.*;
import java.io.*;
import java.util.*;

public class UVa_161_Traffic_Lights {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int[] signalTimes = new int[101];
            int noOfSignals = 2;
            signalTimes[0] = sc.nextInt();
            signalTimes[1] = sc.nextInt();
            signalTimes[2] = sc.nextInt();
            // if all 0 we're done
            if (signalTimes[0] == 0 && signalTimes[1] == 0 && signalTimes[2] == 0) {
                return;
            }

            while (signalTimes[noOfSignals] != 0) {
                noOfSignals++;
                signalTimes[noOfSignals] = sc.nextInt();
            }
            noOfSignals--;
            int ctr = -1;
            boolean stillGreen = true;
            while (stillGreen) {
                ctr++;
                for (int i = 0; i <=noOfSignals; i++) {
                    if (whatColor(ctr, signalTimes[i]) != Color.GREEN) stillGreen = false;
                }
            }
            // at this instant no longer green
            while (true) {
                boolean isGreen = true;
                ctr++;
                for (int i = 0; i <=noOfSignals; i++) {
                    if (whatColor(ctr, signalTimes[i]) != Color.GREEN) isGreen = false;
                }
                if (isGreen) {
                    System.out.println(format(ctr)); break;
                }
                if (ctr > 5 * 60 * 60) {
                    System.out.println("Signals fail to synchronise in 5 hours");
                    break;
                }
            }
        }
    }

    private static String format(int ctr) {
        return String.format("%02d:%02d:%02d", ctr / (60 * 60), (ctr % (60 * 60))/ 60, (ctr % 60));
    }

    public static Color whatColor(int currentTime, int cycleLength) {
        Color x;
        if (currentTime % (2 * cycleLength) < cycleLength - 5) x = Color.GREEN;
        else if (currentTime % (2 * cycleLength) >= cycleLength) x = Color.RED;
        else x = Color.ORANGE;
//        System.out.println(currentTime + " and " + cycleLength+ " = " + x.toString() );
        return x;
    }

    enum Color {
        GREEN, ORANGE, RED;
    }
}
