package ACM_Contests.ACPC_2018;

import java.io.*;
import java.lang.*;
import java.util.*;

public class L {

    /**
     * Reasonably fast I/O class.
     *
     * All methods except hasNext() assume that there is input still to be read.
     * We should always use hasNext() to ensure that this is the case.
     */
    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;
        static String nextLine;

        /** call this method to initialize reader for InputStream */
        static void init(InputStream input) {
            reader = new BufferedReader(
                    new InputStreamReader(input) );
            tokenizer = new StringTokenizer("");
        }

        static boolean hasNext() throws IOException {
            if (tokenizer.hasMoreTokens()) {
                return true;
            } else {
                nextLine = reader.readLine();
                return nextLine == null;
            }
        }

        /** get next word */
        static String next() throws IOException {
            while ( ! tokenizer.hasMoreTokens() ) {
                if (nextLine != null) {
                    tokenizer = new StringTokenizer(nextLine);
                    nextLine = null;
                } else {
                    tokenizer = new StringTokenizer(reader.readLine());
                }
            }
            return tokenizer.nextToken();
        }

        static String nextLine() throws IOException {
            // if there's a next line already we return it
            if (nextLine != null) {
                String copy = nextLine;
                nextLine = null;
                return copy;
            }
            // otherwise we either pick up a new line, or skip to the end of the line.
            else {
                if (!tokenizer.hasMoreTokens()) {
                    return reader.readLine();
                } else {
                    tokenizer = new StringTokenizer(""); // skip to the end of line.
                    return null;
                }
            }
        }

        static int nextInt() throws IOException {
            return Integer.parseInt( next() );
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble( next() );
        }
    }

    static int availTastes;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        Reader.init(new FileInputStream(new File("looking.in")));

        int tests = Reader.nextInt();
        for (int cases = 1 ; cases <= tests; cases++) {
            int n = Reader.nextInt(); int k = Reader.nextInt();
            availTastes = 0;
            for (int i = 0; i < n; i++) {
                availTastes = availTastes | Reader.nextInt();
            }
            System.out.println(availTastes);
        }
    }

}
