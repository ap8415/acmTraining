package CodeforcesContests.CodeforcesRounds.cf_round_520;

import java.util.*;
import java.io.*;
import java.lang.*;

public class C {

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

        int n = Reader.nextInt(); int q = Reader.nextInt();

        String shaorma = Reader.next();
        int[][] dp = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + (shaorma.charAt(i-1) == '0' ? 1 : 0);
//            System.out.println(dp[i][1] + "LM");
            dp[i][1] = dp[i-1][1] + (shaorma.charAt(i-1) == '1' ? 1 : 0);
//            System.out.println(dp[i][1] + "LM");
        }

        for (int queries = 1; queries <= q; queries++) {
            int from = Reader.nextInt() - 1; int to = Reader.nextInt() - 1;
            int ones = dp[to+1][1] - dp[from][1];
            int zeros = dp[to+1][0] - dp[from][0];
            System.out.println((pow(ones + zeros) + BIG - pow(zeros)) % BIG);
        }
    }


    static int BIG = 1000000007;
   static long pow(int x) {
        if (x <= 3) {
            if ( x == 0 ) return 1;
            if (x == 1) return 2;
            if (x == 2 )return 4;
            if (x == 3) return 8;
        }
        long prev = pow(x / 2);
        if ( x % 2 == 0) {
            return (prev * prev) % BIG;
        } else {
            return (prev * prev * 2) % BIG;
        }
    }

}
