package Competitive_Programming_Book.Chapter2.ArrayManipulation_1D;

import java.io.*;
import java.lang.*;
import java.util.*;

public class UVa_11093_JustFinishItUp {

    /** Class for buffered reading int and double values */
    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        /** call this method to initialize reader for InputStream */
        static void init(InputStream input) {
            reader = new BufferedReader(
                    new InputStreamReader(input) );
            tokenizer = new StringTokenizer("");
        }

        /** get next word */
        static String next() throws IOException {
            while ( ! tokenizer.hasMoreTokens() ) {
                //TODO add check for eof if necessary
                tokenizer = new StringTokenizer(
                        reader.readLine() );
            }
            return tokenizer.nextToken();
        }

        static String nextLine() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                return reader.readLine(); // if at end of a line, read the next one.
            } else {
                tokenizer = new StringTokenizer(""); // skip to the end of line.
                return null;
            }
        }

        static int nextInt() throws IOException {
            return Integer.parseInt( next() );
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble( next() );
        }
    }

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);

        int tests = Reader.nextInt();

        testCase:
        for (int testCases = 1; testCases <= tests; testCases++) {
            int n = Reader.nextInt();
            int[] p = new int[n];
            int[] q = new int[n];

            for (int i = 0; i < n; i++) {
                p[i] = Reader.nextInt();
            }
            for (int i = 0; i < n; i++) {
                q[i] = Reader.nextInt();
            }

            int currentStartPoint = 0; int currentGas = 0; int currentStation = 0;


            int increment = 0;

            while(increment <= 3 * n) {
                currentGas += (p[currentStation] - q[currentStation]);
                if (currentGas < 0) {
                    // we can't get from cS to cS + 1, so we reset
                    currentStartPoint = currentStation + 1; currentStation = currentStartPoint;
                    currentGas = 0;

                    if (currentStartPoint >= n) {
                        currentStartPoint = 0; currentStation = 0;
                    }
                } else {
                    currentStation++; // we can get from cS to cS + 1
                    if (currentStation >= n) currentStation = 0;
                    if (currentStation == currentStartPoint) {
                        // done a loop
                        System.out.println(String.format("Case %d: Possible from station %d", testCases, currentStartPoint + 1));
                        continue testCase;
                    }
                }
                increment++;
            }

            System.out.println(String.format("Case %d: Not possible", testCases));

        }
    }

}
