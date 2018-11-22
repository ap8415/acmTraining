package Educational_CF_54;

import java.util.*;
import java.io.*;
import java.lang.*;


public class A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();

        for (int i = 0; i < n-1; i++) {
            if (s.charAt(i) > s.charAt(i+1)) {
                System.out.println(s.substring(0, i) + s.substring(i+1, n));
                return;
            }
        }

        System.out.println(s.substring(0, n-1));
    }
}
