package Competitive_Programming_Book.Chapter2.ArrayManipulation_1D;

import java.io.*;
import java.util.*;
import java.lang.*;

public class UVa_11340_Newspapers {

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
        int n = Reader.nextInt();
        for(int test = 1; test <= n; test++) {
            int[] centsValue = new int[300];

            long totalValue = 0;

            int k = Reader.nextInt();
            for (int i = 1; i <= k; i++) {
                centsValue[Reader.next().charAt(0)] = Reader.nextInt();
            }
            int m = Reader.nextInt();
            for (int i = 1; i <= m; i++) {
                String nextLine = Reader.nextLine();

                for (int j = 0; j < nextLine.length(); j++) {
                    if (nextLine.charAt(j) < 300) totalValue += centsValue[nextLine.charAt(j)];
                }
            }

            System.out.println(String.format("%d.%02d$", totalValue / 100, totalValue % 100));
        }
    }

}
