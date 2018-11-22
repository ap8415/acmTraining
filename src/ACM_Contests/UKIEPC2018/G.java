package ACM_Contests.UKIEPC2018;

import java.awt.geom.Point2D;
import java.util.*;
import java.lang.*;

public class G {

    static int n;
    static int[] treeRadii;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point2D p1 = new Point2D.Double(sc.nextInt(), sc.nextInt());
        Point2D p2 = new Point2D.Double(sc.nextInt(), sc.nextInt());
        Point2D p3 = new Point2D.Double(sc.nextInt(), sc.nextInt());

        n = sc.nextInt();
        treeRadii = new int[n];
        for (int i = 0; i < n; i++) treeRadii[i] = sc.nextInt();

        // chose p2 as midpoint. Then p1, then p3.
        if (run(p1, p2, p3)) return;
        Point2D p4;
        p4 = p1;
        p1 = p2;
        p2 = p3;
        p3 = p4;

        if (run(p1, p2, p3)) return;
        p4 = p1;
        p1 = p2;
        p2 = p3;
        p3 = p4;

        if (run(p1, p2, p3)) return;
        System.out.println("impossible");
    }

    private static boolean run(Point2D p1, Point2D p2, Point2D p3) {
        for (int i = 0; i < 1<<n; i++) {
            Set<Integer> ones = new HashSet<>();
            Set<Integer> zeroes = new HashSet<>();

            int index = 0;
            int k = i; while (index < n) {
                if (k%2==0) zeroes.add(index); else ones.add(index);

                index++;
                k = k / 2;
            }

            for (Integer zero: zeroes) {
                // ones is p1-p2, zeroes is p3-p2, Zero is circle around p2
                int p1p2 = 0;
                for (Integer one : ones) {
                    p1p2 += treeRadii[one] * 2;
                }
                int p3p2 = 0;
                for (Integer normalZero : zeroes) {
                    if (!Objects.equals(normalZero, zero)) p3p2 += treeRadii[normalZero] * 2;
                }

                double P1Dist = Math.max(0, p1.distance(p2) - p1p2);
                double P1Raport = P1Dist / p2.distance(p1);

                Point2D newP1 = new Point2D.Double(p1.getX() * P1Raport + p2.getX() * (1 - P1Raport), p1.getY() * P1Raport + p2.getY() * (1 - P1Raport)) ;

                double P3Dist = Math.max(0, p3.distance(p2) - p3p2);
                double P3Raport = P3Dist / p2.distance(p3);
//                System.out.println(p1p2 +  " SI CU PULA " + p3p2);

                Point2D newP3 = new Point2D.Double(
                        p3.getX() * P3Raport + p2.getX() * (1 - P3Raport),
                        p3.getY() * P3Raport + p2.getY() * (1 - P3Raport));
                if (razaCerculuiCircumscris(newP1, newP3, p2) <= treeRadii[zero]) {
                    System.out.println("possible");return true;
                }
            }
        }
        return false;
    }

    private static double heron(Point2D a, Point2D b, Point2D c) {
        double p = (a.distance(b) + b.distance(c) + c.distance(a)) / 2;
        return Math.sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)));
    }

    private static double razaCerculuiCircumscris(Point2D a, Point2D b, Point2D c) {
        // if infinitesimal, most likely equal and then we dont do heron as its a bug we just return distance.
        if (a.distance(b) < 0.00001 || b.distance(c) < 0.00001 || c.distance(a) < 0.00001) {
            return Math.max(Math.max(a.distance(b), b.distance(c)), c.distance(a)) / 2;
        }
        return a.distance(b) * b.distance(c) * c.distance(a) / (4 * heron(a, b, c));
    }

}
