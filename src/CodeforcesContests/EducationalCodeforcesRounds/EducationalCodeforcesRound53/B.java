package CodeforcesContests.EducationalCodeforcesRounds.EducationalCodeforcesRound53;


import java.util.*;
import java.io.*;
import java.lang.*;

public class B {

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

        static int nextInt() throws IOException {
            return Integer.parseInt( next() );
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble( next() );
        }
    }

    public static void main(String[] args) throws IOException {
               Reader.init(System.in);


        Queue<Integer> initial = new LinkedList<>(); // use queue because stuff is given in reverse order
        Set<Integer> removed = new HashSet<>();

        int n = Reader.nextInt();
        for (int i = 0; i < n; i++) initial.offer(Reader.nextInt());

        for (int i = 0; i < n; i++) {
            int x = Reader.nextInt();
            if (removed.remove(x)) {
                System.out.print("0 ");
            } else {
                int ctr = 0;
                while (initial.element() != x) {
                    ctr++;
                    int y = initial.poll(); removed.add(y);
                }
                ctr++; initial.poll();
                System.out.print(ctr + " ");
            }
        }

    }

}
