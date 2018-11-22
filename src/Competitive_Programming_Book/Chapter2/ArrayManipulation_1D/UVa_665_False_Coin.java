package Competitive_Programming_Book.Chapter2.ArrayManipulation_1D;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_665_False_Coin {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int dataSets = sc.nextInt();

        for (int data = 1; data <= dataSets; data++) {
            int n = sc.nextInt(); int k = sc.nextInt();
            sc.nextLine();
//            Set<Integer> goodCoinsBigger = new HashSet<>();
//            Set<Integer> goodCoinsSmaller = new HashSet<>();
//            Set<Integer> bigger = new HashSet<>();
//            Set<Integer> smaller = new HashSet<>();
            Set<Integer> all = new HashSet<>();
            for (int i = 0 ;i < n; i++) all.add(i+1);

            Set<Integer> candidatesSmaller = new HashSet<>(all);
            Set<Integer> candidatesBigger = new HashSet<>(all);

            // 2 cases; 1. coin is smaller; 2. coin is bigger

            for (int i = 0; i < k; i++) {
                String coins = sc.nextLine();
                String output = sc.nextLine();

                String[] splitCoins = coins.split(" ");
                int noOfCoins = Integer.parseInt(splitCoins[0]);
                Set<Integer> left = new HashSet<>(noOfCoins);
                Set<Integer> right = new HashSet<>(noOfCoins);
                for (int j = 1; j <= noOfCoins; j++) left.add(Integer.parseInt(splitCoins[j]));
                for (int j = noOfCoins + 1; j <= 2 * noOfCoins; j++) right.add(Integer.parseInt(splitCoins[j]));

                // only 1 different coin
                if (output.charAt(0) == '=') {
                    candidatesSmaller.removeAll(left);
                    candidatesBigger.removeAll(left);
                    candidatesSmaller.removeAll(right);
                    candidatesBigger.removeAll(right);
                } else if (output.charAt(0) == '<') {
//                    System.out.println(candidatesSmaller);
                    candidatesSmaller.retainAll(left);
//                    System.out.println(candidatesSmaller);
//                    System.out.println(candidatesBigger);
                    candidatesBigger.retainAll(right);
//                    candidatesBigger.retainAll(right);                    System.out.println(candidatesBigger);

                } else if (output.charAt(0) == '>') {
                    candidatesSmaller.retainAll(right);
                    candidatesBigger.retainAll(left);
                }
            }
            candidatesBigger.addAll(candidatesSmaller);

            if (candidatesBigger.size() == 1) {
                int result = candidatesBigger.stream().findFirst().get();
                System.out.println(result);
                if (data < dataSets) System.out.println();
            } else {
                System.out.println(0);
                if (data < dataSets) System.out.println();
            }

        }
    }
}
