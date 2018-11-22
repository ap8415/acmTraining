package Competitive_Programming_Book.Chapter2.Java_Collections;
import java.util.*;
import java.lang.*;
import java.io.*;

public class UVa_10258_ContestScoreboard {

    static class Contestant implements Comparable<Contestant> {
        int index;
        int noOfProblems;
        int timePenalty;
        boolean active;

        public Contestant(int index) {
            this.index= index; noOfProblems = 0; timePenalty = 0; active = false;
        }


        @Override
        public int compareTo(Contestant o) {
            return -((noOfProblems != o.noOfProblems) ? noOfProblems - o.noOfProblems : (
                            (timePenalty != o.timePenalty) ? o.timePenalty - timePenalty : (
                                    (index != o.index) ? o.index - index : 0
                            )
                    ));
        }

        @Override
        public String toString() {
            return String.format("%d %d %d", index, noOfProblems, timePenalty);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        int cases = sc.nextInt(); sc.nextLine(); sc.nextLine();
        for (int tests = 1; tests <= cases; tests++) {
            int[][] time =  new int[100][9];
            int[][] tries = new int[100][9];
            int[][] yes = new int[100][9];


            for (String line = sc.nextLine(); !line.equals(""); line = sc.hasNextLine() ? sc.nextLine() : "") {
                String[] parsed = line.split(" ");
                int contestant = Integer.parseInt(parsed[0]);
                int problem = Integer.parseInt(parsed[1]);
                int TIME = Integer.parseInt(parsed[2]);
                char response = parsed[3].charAt(0);
                if (response == 'C' && time[contestant-1][problem-1] == 0) {
                    time[contestant-1][problem-1] = TIME + 20 * tries[contestant-1][problem-1];
                } else if (response == 'I') {
                    tries[contestant-1][problem-1]++;
                }
                yes[contestant-1][problem-1]++;
            }

            List<Contestant> contestants = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                Contestant newC = new Contestant(i+1);
                for (int j = 0; j < 9; j++) {
                    if (time[i][j] != 0) {
//                        System.out.println("PLM " + i + " " + j);
                        newC.noOfProblems++;
                        newC.timePenalty += time[i][j];
                        newC.active = true;
                    } else if (yes[i][j] != 0) newC.active = true;
                }
                if (newC.active) contestants.add(newC);
            }

            Collections.sort(contestants);

            for (Contestant c : contestants) {
                System.out.println(c);
            }

            if (tests < cases) System.out.println();



        }
    }

}
