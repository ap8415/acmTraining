package ACM_Contests.SEERC.SEERC_2018;

import java.util.*;
import java.io.*;
import java.lang.*;

public class E {

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

        FileOutputStream testOut = new FileOutputStream("test.out");
        PrintStream out = new PrintStream(testOut);
        System.setOut(out);

        InputStream testIn = new FileInputStream("test.in");
        System.setIn(testIn);

Reader.init(System.in);

        int n = Reader.nextInt(); int m = Reader.nextInt();
        int l = Reader.nextInt();

        long[] xmin = new long[n]; long[] xmax = new long[n];
        long[] fishermen = new long[m];

        for (int i = 0; i < n; i++) {
            int x = Reader.nextInt(); int y = Reader.nextInt();
           xmin[i] = l >= y ? x + y - l : 1_000_000_1; xmin[i] *= 2;
           xmax[i] = l >= y ? l + x - y : 1_000_000_1; xmax[i] *= 2;
        }

        Arrays.sort(xmin); Arrays.sort(xmax);

        for (int i = 0 ; i < m; i++) {
            fishermen[i] = Reader.nextInt(); fishermen[i] *= 2;
            long stgX = 0;

            long minIndex = Arrays.binarySearch(xmin, fishermen[i] + 1);
            if (minIndex >= 0) {
                stgX = 1 + minIndex; /// include everythijng
            } else {
                stgX = -(1 + minIndex);
            }

            long drpX = 0;

            long maxIndex = Arrays.binarySearch(xmax, fishermen[i] - 1);
            if (maxIndex >= 0 ){

                drpX = maxIndex;
            } else {
                drpX = -(1 + maxIndex);
            }

//            System.out.println("stg " + stgX + " drpta " + drpX);

            System.out.println(stgX - drpX);
        }
    }

}
