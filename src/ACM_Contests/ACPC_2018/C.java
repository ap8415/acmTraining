package ACM_Contests.ACPC_2018;

import java.io.*;
import java.lang.*;
import java.util.*;

public class C {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("coffee.in"));
        int tests = sc.nextInt();
        Map<String, Integer> types = new HashMap<>();
        types.put("small", 0);
        types.put("medium", 1);
        types.put("large", 2);
        for (int cases = 1; cases <= tests; cases++) {
            int C = sc.nextInt(); int P = sc.nextInt();
            int[][] coffees = new int[C][3];
            Map<String, Integer> coffeeTypes = new HashMap<>();
            for (int i = 0; i < C; i++) {
                coffeeTypes.put(sc.next(), i);
                for (int j = 0; j < 3; j++) coffees[i][j] = sc.nextInt();
            }

            int delivery = 100 / P;


            for (int i = 0; i < P; i++) {
                String name = sc.next();
                int j = types.get(sc.next());
                int k = coffeeTypes.get(sc.next());
                System.out.println(name + " " + round(delivery + coffees[k][j]));
            }
        }

    }


    static int round(int sum) {
        switch (sum % 5) {
            case 1: return sum -1;
            case 4: return sum+1;
            default: return sum;
        }
    }

}
