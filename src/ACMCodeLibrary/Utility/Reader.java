package ACMCodeLibrary.Utility;

import java.io.*;
import java.util.*;

/**
 * Reasonably fast I/O class.
 *
 * All methods except hasNext() assume that there is input still to be read.
 * We should always use hasNext() to ensure that this is the case.
 */
public class Reader {
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