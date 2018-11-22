package Competitive_Programming_Book.Chapter3.CompleteSearch.Iterative_TwoLoops;

import java.util.*;
import java.lang.*;
import java.io.*;

public class UVa_1260_Sales {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T  = sc.nextInt();
        for (int T1 = 1; T1 <= T; T1++){
            int A = sc.nextInt();

            long sum = 0;
            List<Integer> a = new ArrayList<>();
            for (int i = 0; i < A; i++) {
                int next = sc.nextInt();
                sum += a.stream().filter(x -> x <= next).count();
                a.add(next);
            }
            System.out.println(sum);
        }
    }

}
