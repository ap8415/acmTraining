package Competitive_Programming_Book.Chapter2.ArrayManipulation_1D;

import java.io.*;
import java.util.*;
import java.lang.*;

public class UVa_12356_Army_Buddies {

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

        StringBuilder output = new StringBuilder();

        while(true) {
            int s = Reader.nextInt(); int b = Reader.nextInt();
            if (s == 0 && b == 0) {
                BufferedWriter out =
                        new BufferedWriter(new OutputStreamWriter(System.out));
                out.write(output.toString());
                out.flush();
                return;
            }

            int[] soldiersLeft = new int[s+2];
            int[] soldiersRight = new int[s+2];

            for (int i = 0; i <= s+1; i++) {
                soldiersLeft[i] = i;
                soldiersRight[i] = i;
            }

            for (int i = 1; i <= b; i++) {
                int l = Reader.nextInt(); int r = Reader.nextInt();
                soldiersLeft[l] = soldiersLeft[l-1]; soldiersRight[r] = soldiersRight[r+1];
                soldiersLeft[r] = soldiersLeft[l-1]; soldiersRight[l] = soldiersRight[r+1];

                int first = soldiersLeft[l-1];
                if (soldiersLeft[first] != first) {
                    soldiersRight[first] = soldiersRight[r];
                }

                soldiersRight[soldiersLeft[l] + 1] = soldiersRight[r+1];
                soldiersLeft[soldiersRight[r] - 1] = soldiersLeft[l-1];

                first = soldiersRight[r+1];
                if (soldiersRight[first] != first)
                    soldiersLeft[first] = soldiersLeft[l];

                int x = soldiersLeft[l];
                if (x > 0) output.append(x); else output.append("*");
                output.append(" ");
                x = soldiersRight[r];
                if ( x <= s) output.append(x); else output.append("*");
                output.append('\n');

//                System.out.println(Arrays.toString(soldiersLeft));
//                System.out.println(Arrays.toString(soldiersRight));
            }
            output.append("-\n");
        }
    }

}
