package ACM_Contests.ACM_CERC_2011_12;

import java.util.Scanner;

public class E_StackMachineProgrammer_Lagrange {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            System.out.println(n);
            if (n == 0) return;
            long[] v = new long[n]; long[] r = new long[n];
            for (int i = 0; i < n; i++) {
                v[i] = sc.nextInt(); r[i] = sc.nextInt();
            }

            long[] divizori = new long[n];
            for (int i = 0; i < n; i++) divizori[i] = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) divizori[i] *= (v[i] - v[j]);
                }
            }

            long[][] coef = new long[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    coef[i][n-1-j] = r[i] * polysum(v, j, i);
                }
            }

            long div_mare = 1;
            for (int i = 0; i < n; i++) div_mare *= divizori[i];

//            System.out.println("ACESTA ESTE CAZUL CU: ");
//            for (int i = 0; i < n; i++) {
//                System.out.println(v[i] + "--->" + r[i]+ "; DIVIZOR" + divizori[i]);
//            }
//            System.out.println();
            // polin e de forma (x^i * coef_finali[i] ) / div_mare

            for (int bucata = 0; bucata < n; bucata++) {
                System.out.println("DUP");
                for (int i = 0; i < n; i++) {
                    // il facem pe x^i, si dupa ii dam swap
                    // x e at the top mereu
                    for (int j = 0; j < i; j++) System.out.println("DUP");
                    for (int j = 0; j < i - 1; j++) System.out.println("MUL");


                    System.out.println("NUM " + Math.abs(coef[bucata][i]));
                    if (coef[bucata][i] < 0) System.out.println("INV");
                    if (i != 0) System.out.println("MUL");
                    // il avem pe x^i acum!!!
                    System.out.println("SWP"); // x back on top!!!
                }
                System.out.println("POP"); // remove redundant X

                for (int i = 1; i < n; i++) {
                    System.out.println("ADD");
                }

                System.out.println("NUM " + Math.abs(divizori[bucata]));
                if (divizori[bucata] < 0) System.out.println("INV");
                System.out.println("DIV");

                System.out.println("SWP"); // stacku e BUCATILE --- x
            }

            // avem <n> bucati de polinoame, trb acu impartite; plus un x la misto sus
            //
            System.out.println("POP");
                for (int i = 1; i < n; i++) {
                    System.out.println("ADD");
                }

            // GG!
            System.out.println("END");
            System.out.println();
        }
    }


    public static long polysum(long[] v, long deg, int not_present) {
        if (deg == 0) return 1;
        long sum = 0;
        if (deg == 1) {
            for (int i = 0; i < v.length; i++) {
                if (i != not_present) sum += v[i];
            }
        }
        if (deg == 2) {
            for (int i = 0; i < v.length; i++) {
                for (int j = 0; j < v.length; j++) {
                    if ((i != not_present) && (j != not_present) && (i != j) ) {
                        sum += v[i] * v[j];
                    }
                }
            }
            sum /= 2;
        }
        if (deg == 3) {
            for (int i = 0; i < v.length; i++) {
                for (int j = 0; j < v.length; j++) {
                    if (i != j) {
                        for (int k = 0; k < v.length; k++) {
                            if (i != k && j != k) {
                                if (i != not_present) {
                                    if (j != not_present) {
                                        if (k != not_present) {
                                            sum += v[i] * v[j] * v[k];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            sum /= 6;
        }
        if (deg == 4) {
            sum = 1;
            for (int i = 0; i < v.length; i++) {
                if (i != not_present) sum *= v[i];
            }
        }
        return sum * (deg % 2 == 0 ? 1 : -1);
    }

}
