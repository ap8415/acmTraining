package CodeforcesContests.MailRuCup.Round1_2018;

import java.time.Instant;
import java.util.*;
import java.io.*;
import java.lang.*;

public class E_MailRu {


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(); int n = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        Queue<Integer>[][] inputTable = new Queue[m][n];
        Stack<Integer>[][] outputTable = new Stack[m][n];

        Queue<Integer>[][] problemaDeCacat = new Queue[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String inp = sc.next();
                inputTable[i][j] = new LinkedList<>();
                problemaDeCacat[i][j] = new LinkedList<>();
                for (int k = inp.length() - 1; k >=0; k--) {
                    inputTable[i][j].add(inp.charAt(k) - '0');
                    problemaDeCacat[i][j].add(inp.charAt(k) - '0');
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String inp = sc.next();
                outputTable[i][j] = new Stack();
                for (int k = 0; k < inp.length(); k++) {
                    outputTable[i][j].add(inp.charAt(k) - '0');
                }
            }
        }

        long xd = Instant.now().toEpochMilli();

        // Step 1: diff 1s from 0s
        Queue<Integer>[][] separated = new Queue[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                separated[i][j] = new LinkedList<>();
            }
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                while (!inputTable[i][j].isEmpty()) {
                    int x = inputTable[i][j].poll(); ans += 2;
                    if (x == 0) {
                        // 0 at the bottom; row 0
                        if (i == 0) {
                            if (j == 0) {
                                separated[0][1].add(x);
                            } else {
                                separated[0][0].add(x);
                            }
                        } else {
                            separated[0][j].add(x);
                        }
                    } else {
                        if (i == m-1) {
                            if (j == 0) {
                                separated[1][1].add(x);
                            } else {
                                separated[1][0].add(x);
                            }
                        } else {
                            separated[1][j].add(x);
                        }
                    }
                }
            }
        }

        // Step 2: calculate solvable.
        int[] onesNeeded = new int[n];

        Set<Integer> needMoreOnes = new HashSet<>();
        Set<Integer> tooManyOnes = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = m-2; j >=0; j--) {
                onesNeeded[i] += outputTable[j][i].stream().filter(x -> x == 1).count();
            }
            int ps;
            if (i == 0 ) ps = n - 1;
            else ps = i-1;
            onesNeeded[i] += outputTable[m-1][ps].stream().filter(x -> x == 1).count();
            if (onesNeeded[i] > separated[1][i].size()) {
                needMoreOnes.add(i);
            } else if (onesNeeded[i] < separated[1][i].size()) {
                tooManyOnes.add(i);
            }
        }

        int[] zerosNeeded = new int[n];
        Set<Integer> needMoreZeros = new HashSet<>();
        Set<Integer> tooManyZeros = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = m-1; j > 0; j--) {
                zerosNeeded[i] += outputTable[j][i].stream().filter(x -> x == 0).count();
            }
            int ps;
            if (i == 0) ps = n - 1;
            else ps = i-1;
            zerosNeeded[i] += outputTable[0][ps].stream().filter(x -> x == 0).count();
            if (zerosNeeded[i] > separated[0][i].size()) {
                needMoreZeros.add(i);
            } else if (zerosNeeded[i] < separated[0][i].size()) {
                tooManyZeros.add(i);
            }
        }

        Iterator<Integer> tooManyZerosIterator = tooManyZeros.iterator();
        Iterator<Integer> needMoreZerosIterator = needMoreZeros.iterator();

        int curr;

        if (needMoreZerosIterator.hasNext()) {
            curr = needMoreZerosIterator.next();

            while (tooManyZerosIterator.hasNext()) {
                int x = tooManyZerosIterator.next();
                while (separated[0][x].size() != zerosNeeded[x]) {
                    if (separated[0][curr].size() == zerosNeeded[curr]) curr = needMoreZerosIterator.next();
                    separated[0][curr].add(separated[0][x].poll());
                    ans++;
                }
            }
        }

        Iterator<Integer> tooManyOnesIterator = tooManyOnes.iterator();
        Iterator<Integer> needMoreOnesIterator = needMoreOnes.iterator();

        if (needMoreOnesIterator.hasNext()) {
            curr = needMoreOnesIterator.next();

            while (tooManyOnesIterator.hasNext()) {
                int x = tooManyOnesIterator.next();
                while (separated[1][x].size() != onesNeeded[x]) {
                    if (separated[1][curr].size() == onesNeeded[curr]) curr = needMoreOnesIterator.next();
                    separated[1][curr].add(separated[1][x].poll());
                    ans++;
                }
            }
        }

        separated = new Queue[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                separated[i][j] = new LinkedList<>();
            }
        }

        sb.append(ans + "\n");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                while (!problemaDeCacat[i][j].isEmpty()) {
                    int x = problemaDeCacat[i][j].poll(); // answer is 2 * chars in table + changes that need to be made
//                    sb.append(i + " " + j + " " + x);
                    if (x == 0) {
                        // 0 at the bottom; row 0
                        if (i == 0) {
                            if (j == 0) {
                                separated[0][1].add(x);
                                sb.append("1 1 1 2"); sb.append('\n');
                            } else {
                                separated[0][0].add(x);
                                sb.append(1 + " " + (j+1) + " 1 1");
                                sb.append('\n');
                            }
                        } else {
                            separated[0][j].add(x);
                            sb.append((i+1) + " " + (j+1) + " " + 1 + " " + (j+1));
                            sb.append('\n');
                        }
                    } else {
                        if (i == m-1) {
                            if (j == 0) {
                                separated[1][1].add(x);
                                sb.append((m) + " " + (1) + " " + m + " " + (2));
                                sb.append('\n');
                            } else {
                                separated[1][0].add(x);
                                sb.append((i+1) + " " + (j+1) + " " + m + " " + (1));sb.append('\n');
                            }
                        } else {
                            separated[1][j].add(x);
                            sb.append((i+1) + " " + (j+1) + " " + m + " " + (j+1));sb.append('\n');
                        }
                    }
                }
            }
        }

        tooManyZerosIterator = tooManyZeros.iterator();
        needMoreZerosIterator = needMoreZeros.iterator();

        if (needMoreZerosIterator.hasNext()) {
            curr = needMoreZerosIterator.next();

            while (tooManyZerosIterator.hasNext()) {
                int x = tooManyZerosIterator.next();
                while (separated[0][x].size() != zerosNeeded[x]) {
                    if (separated[0][curr].size() == zerosNeeded[curr]) curr = needMoreZerosIterator.next();
                    separated[0][curr].add(separated[0][x].poll());
                    sb.append(1 + " " + (x + 1) + " " + 1 + " " + (curr + 1)); sb.append('\n');
                }
            }
        }

        tooManyOnesIterator = tooManyOnes.iterator();
        needMoreOnesIterator = needMoreOnes.iterator();

        if (needMoreOnesIterator.hasNext()) {
            curr = needMoreOnesIterator.next();

            while (tooManyOnesIterator.hasNext()) {
                int x = tooManyOnesIterator.next();
                while (separated[1][x].size() != onesNeeded[x]) {
                    if (separated[1][curr].size() == onesNeeded[curr]) curr = needMoreOnesIterator.next();
                    separated[1][curr].add(separated[1][x].poll());
                    sb.append(m + " " + (x + 1) + " " + m + " " + (curr + 1)); sb.append('\n');
                }
            }
        }

        // Step 3: Do the solving. At this point just printout the moves it's fine.

        for (int i = 0; i < n; i++) {
            int ps;
            if (i == n-1) ps = 0; else ps = i+1;
            while (!outputTable[0][i].isEmpty()) {
                if (outputTable[0][i].pop() == 0) {
                    sb.append(1 + " ").append(ps + 1).append(" ").append(1).append(" ").append(i + 1);sb.append('\n');
                } else {
                    sb.append(m).append(" ").append(i + 1).append(" ").append(1).append(" ").append(i + 1);sb.append('\n');
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int ps;
            if (i == n-1) ps = 0; else ps = i+1;
            while (!outputTable[m-1][i].isEmpty()) {
                if (outputTable[m-1][i].pop() == 1) {
                    sb.append(m).append(" ").append(ps + 1).append(" ").append(m).append(" ").append(i + 1);sb.append('\n');
                } else {
                    sb.append(1 + " ").append(i + 1).append(" ").append(m).append(" ").append(i + 1);sb.append('\n');
                }
            }
        }

        for (int i = 1; i < m-1; i++) {
            for (int j = 0; j < n; j++) {
                while (!outputTable[i][j].isEmpty()) {
                    if (outputTable[i][j].pop() == 1) {
                        sb.append(m).append(" ").append(j + 1).append(" ").append(i+1).append(" ").append(j + 1);sb.append('\n');
                    } else {
                        sb.append(1 + " ").append(j + 1).append(" ").append(i+1).append(" ").append(j + 1);sb.append('\n');
                    }
                }
            }
        }


        System.out.println(sb.toString());

    }
}
