package Competitive_Programming_Book.Chapter3.CompleteSearch.Iterative_OneLoop_LinearScan;

import java.util.*;
import java.lang.*;
import java.io.*;

public class UVa_1237_ExpertEnough {

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

        int T = Reader.nextInt();
        for (int T1 = 1; T1 <= T; T1++) {
            int D = Reader.nextInt();
            String[] M = new String[D];
            int[] L = new int[D]; int[] H = new int[D];

            for (int i = 0; i < D; i++) {
                M[i] = Reader.next(); L[i] = Reader.nextInt(); H[i] = Reader.nextInt();
            }

            int Q = Reader.nextInt();
            for (int i = 0; i < Q; i++) {
                int P = Reader.nextInt();
                String found = null;
                for (int j = 0; j < D; j++) {
                    if (P >= L[j] && P <= H[j]) {
                        if (found == null) {
                            found = M[j];
                        } else {
                            found = null; break;
                        }
                    }
                }
                System.out.println(found != null ? found : "UNDETERMINED");
            }

            if (T1 < T) System.out.println();
        }
    }

}
