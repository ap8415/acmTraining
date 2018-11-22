package ACM_Contests.NWERC2017;

import java.util.*;
import java.lang.*;
import java.io.*;

public class A {

    static class Height implements Comparable<Height> {
        int height, index;

        boolean shouldSkip =false;
        public Height(int height, int index) {
            this.height = height;
            this.index = index;
        }

        @Override
        public int compareTo(Height o) {
            return (height != o.height) ? height - o.height : index - o.index; // earlier indices first
        }
    }

    static class Count {
        int height, count;

        public Count(int height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "Count{" +
                    "height=" + height +
                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int n = Reader.nextInt();
        Height[] heights = new Height[n];
        Height[] ordered = new Height[n];
        for (int i = 0; i < n; i++) {
            int h = Reader.nextInt();
            Height height = new Height(h, i);
            heights[i] = height;
            ordered[i] = height;
        }
        Arrays.sort(ordered);

        List<Count> uniqueOrdered = new ArrayList<>();
        int k = 0; Height curr;
        while (k < n) {
            curr = ordered[k]; Count c = new Count(curr.height);
            int l = k;
            while (k < n && curr.height == ordered[k].height) k++;
            c.count = k - l;
            uniqueOrdered.add(c);
        }

        BitSet cuts = new BitSet(n+1);
        cuts.set(0); cuts.set(n); // by default these cuts already made

        int index = 0;
        int skipUntil = -1;

//        //System.out.println(uniqueOrdered);

        int orderedHeightsIndex = 0;
        while (orderedHeightsIndex < uniqueOrdered.size()) {
            Count c = uniqueOrdered.get(orderedHeightsIndex);
            int nextIndex = index + c.count;

            int noCut = nextIndex - 1;
            boolean foundExcess = false; int whatToSkip = 0;
            // noCut is where we go as far as possible before cutting.
            // by default, do this for last element, if not possible anywhere.

            int maxBeta = -1;
            int maxAlfa = -1;

            for (int i = index; i < nextIndex; ) {
                Height h = ordered[i];
                //System.out.println(i);
                if (h.shouldSkip) {
                    System.out.println("skipped " + h.index);
                    i++;
                    continue;
                }
                int nexti = i;
                int alfa = h.index;
                while (alfa < n && heights[alfa].height == h.height) {
                    alfa++;
                    nexti++; // because these heights are already equal and next to eachother so we skip them
                }
                int beta = alfa;
//                //System.out.println(alfa);
                if (orderedHeightsIndex + 1 < uniqueOrdered.size()) {
                    Count c1 = uniqueOrdered.get(orderedHeightsIndex+1);
                    while (beta < n && heights[beta].height == c1.height) {
                        beta++;
                    }
                    // if 13 and we have a 2, alfa never increases

                    if (beta - alfa == c1.count) {
                        // replace old noCut and maxAlfa since they won't get cut
                        if (maxAlfa != -1) {
//                            //System.out.println("HERE5");
                            cuts.set(noCut);
                            cuts.set(maxAlfa);
                        }

                        // then this one we'll never cut ever
                        noCut = ordered[i].index;
                        foundExcess = true;
                        for (int q = alfa; q < beta; q++) heights[q].shouldSkip = true;

                        maxBeta = beta;
                        maxAlfa = alfa; // condition below will never happen
                        i = nexti;
                        continue; // to avoid cutting in this case
                    }
                }

                //System.out.println(i + " outside " + beta + " " + alfa);

                if (beta - alfa > maxBeta - maxAlfa) {
                    // biggest ending sequence of 2's.
                    // previous noCut will now get for sure cut, so cut there.
                    if (maxAlfa != -1) {
                        //System.out.println("HERE4");
                        cuts.set(noCut);
                        cuts.set(maxAlfa);
                    }
                    maxAlfa = alfa;
                    maxBeta = beta;
                    //System.out.println(" AHHAAH " + maxAlfa + " " + maxBeta);
                    noCut = i;
                } else {
                    //System.out.println("HERE3");
                    //System.out.println(cuts);
                    cuts.set(ordered[i].index);
                    //System.out.println(i + " PULA");
                    cuts.set(alfa);
                    //System.out.println(cuts);
                }

                i = nexti;
                // otherwise we have a sequence 11112222 with not all of the 2's (say, there's one
                // more 2 somewhere else). we count this.
            }

            if (foundExcess) {
                // look at maxBeta now
                // should be at uniqueOrdered.get(orderedHeightsIndex + 2)'s first element, or at end if that doesnt exit
                if (orderedHeightsIndex + 2 < uniqueOrdered.size()) {
                    int currIndex = orderedHeightsIndex + 2;
                    while (maxBeta < n && currIndex < uniqueOrdered.size()) {
                        Count x = uniqueOrdered.get(currIndex);
                        int xcount = x.count;
                        while (maxBeta < n && heights[maxBeta].height == x.height) {
                            maxBeta++; xcount--; whatToSkip++;
                        }
                        if (xcount == 0) {
                            currIndex++;
                        } else break;
                    }
                    // maxBeta is at maximum
                }
            }

            //System.out.println(index + " WHERE R WE");
            //System.out.println(cuts);
            if (maxBeta != -1) {
                cuts.set(noCut);
                cuts.set(maxBeta);
            }

            for (int i = maxAlfa; i < maxBeta; i++) {
                // if -1, -1, then nothing happens
                // if set I make them skippable
                heights[i].shouldSkip = true;
            }

            index = nextIndex;
//            //System.out.println(uniqueOrdered.get(orderedHeightsIndex).height);
            System.out.println(cuts);
            orderedHeightsIndex++;
        }

        System.out.println(cuts);
        System.out.println(cuts.cardinality() - 2);

    }

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

}
