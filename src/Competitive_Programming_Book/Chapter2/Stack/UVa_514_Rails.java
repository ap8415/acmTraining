package Competitive_Programming_Book.Chapter2.Stack;

import java.util.*;
import java.lang.*;
import java.io.*;

public class UVa_514_Rails {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int coaches = sc.nextInt(); if (coaches == 0) return;

            Stack<Integer> station = new Stack<>();

            while (true) {
                int lastPushed = 0;

                boolean possible = true;
                int current = sc.nextInt();
                if (current == 0) break;

                for (int i = 0; i < coaches; i++) {
                    if (possible) {
                        try {
                            for (int j = lastPushed + 1; j <= current; j++) station.push(j);
                            if (current > lastPushed) lastPushed = current;
                            if (station.pop() != current) throw new Exception();
                        } catch (Exception e) {
                            possible = false;
                        }
                    }
                    if (i < coaches - 1) current = sc.nextInt();
                }
                System.out.println(possible ? "Yes" : "No");
            }
            System.out.println();
        }
    }

}
