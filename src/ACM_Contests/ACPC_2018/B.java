package ACM_Contests.ACPC_2018;

import java.io.*;
import java.lang.*;
import java.util.*;

public class B {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("baklava.in"));

        int tests = sc.nextInt();
        for (int cases = 1; cases<=tests; cases++) {
            int N = sc.nextInt();
            double fraction = f(N);
            double ans = (double)10000 / (1 - fraction);
            System.out.println(ans);
        }
    }

    // innerPolyArea / outerPolyArea
    private static double f(int n) {
        double polygon_angle = ((n - 2) * Math.PI) / n;

        double inner_angle = 2 * Math.PI / n;
        double half_angle = (Math.PI - inner_angle) / 2;
        double side = Math.sin(inner_angle) / Math.sin(half_angle);

        double poly_area = n * Math.sin(inner_angle) / 2;
        double triangles_area = (n * side * side * Math.sin(polygon_angle)) / 8;

        double inner_poly_area = poly_area - triangles_area;
        return inner_poly_area / poly_area;
    }

}
