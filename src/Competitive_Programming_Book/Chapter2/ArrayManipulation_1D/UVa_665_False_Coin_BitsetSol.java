package Competitive_Programming_Book.Chapter2.ArrayManipulation_1D;

import java.util.*;
import java.io.*;
import java.lang.*;

public class UVa_665_False_Coin_BitsetSol {

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
            BitSet all = new BitSet(n);
            all.set(0, n);

            BitSet candidatesSmaller = new BitSet(n);
            candidatesSmaller.set(0, n);

            BitSet candidatesBigger = new BitSet(n);
            candidatesBigger.set(0, n);

            // 2 cases; 1. coin is smaller; 2. coin is bigger

            for (int i = 0; i < k; i++) {
                String coins = sc.nextLine();
                String output = sc.nextLine();

                String[] splitCoins = coins.split(" ");
                int noOfCoins = Integer.parseInt(splitCoins[0]);
                BitSet left = new BitSet(n);
                BitSet right = new BitSet(n);
                for (int j = 1; j <= noOfCoins; j++) left.set(Integer.parseInt(splitCoins[j]) - 1);
                for (int j = noOfCoins + 1; j <= 2 * noOfCoins; j++) right.set(Integer.parseInt(splitCoins[j]) - 1);

                // only 1 different coin
                if (output.charAt(0) == '=') {
                    candidatesSmaller.andNot(left);
                    candidatesBigger.andNot(left);
                    candidatesSmaller.andNot(right);
                    candidatesBigger.andNot(right);
                } else if (output.charAt(0) == '<') {
                    candidatesSmaller.and(left);
                    candidatesBigger.and(right);
                } else if (output.charAt(0) == '>') {
                    candidatesSmaller.and(right);
                    candidatesBigger.and(left);
                }
            }
            candidatesBigger.or(candidatesSmaller);

            if (candidatesBigger.cardinality() == 1) {
                int result = candidatesBigger.nextSetBit(0);
                System.out.println(result+1);
                if (data < dataSets) System.out.println();
            } else {
                System.out.println(0);
                if (data < dataSets) System.out.println();
            }

        }
    }
}