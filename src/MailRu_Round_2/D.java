//package MailRu_Round_2;
//
//import java.util.*;
//import java.io.*;
//import java.lang.*;
//
//public class D {
//
//    static String[] initial;
//    static String[] finals;
//    static int n;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//
//        initial = new String[n];
//        finals = new String[n];
//
//        for (int i = 0; i < n; i++) initial[i] = sc.next();
//        for (int i = 0; i < n; i++) finals[i] = sc.next();
//
//        int firstDiff = -1;
//
//        int q = 0;
//        while (initial[q].equals(finals[q])) q++;
//
//        for (int j = 0; j < initial[q].length(); j++) {
//            if (initial[q].charAt(j) != finals[q].charAt(j)) {
//                firstDiff = j; break;
//            }
//        }
//
//        String s1 = ""; String s2 = "";
//        int secondDiff = -1;
//
//        if (firstDiff != -1) {
//
//            secondDiff = -1;
//
//            for (int j = initial[q].length() - 1; j >= 0; j--) {
//                if (initial[q].charAt(j) != finals[q].charAt(j)) {
//                    secondDiff = j; break;
//                }
//            }
//
//            s1 = initial[q].substring(firstDiff, secondDiff + 1);
//            s2 = finals[q].substring(firstDiff, secondDiff + 1);
//        }
//
//        boolean canReplace = true;
//
//        // So we have the string.
//        // Case 1: initial.replacefirst(s1,s2) = final => OK.
//        // Case 2: initial = final but initial.replacefirst(s1, s2) != final.
//        // Case 3: initial != final.
//
//        // so the pairing (s1, s2) that solves the problem must have that:
//        // if
//
//
//        for (int i = 0; i < n; i++) {
//            if (!(initial[i].replaceFirst(s1, s2).equals(finals[i]))) {
//                if (initial[i].equals(finals[i])) {
//
//                } else {
//                    canReplace = false;
//                }
//            }
//        }
//
//        if (canReplace) {
//            System.out.println("YES");
//            System.out.println(s1);
//            System.out.println(s2);
//        } else {
//            System.out.println("NO");
//        }
//
//
//    }
//
//    static class Pair {
//        String from, to;
//
//        public Pair(String from, String to) {
//            this.from = from;
//            this.to = to;
//        }
//    }
//
//
//    Pair<String> delta(String s1, String s2) {
//
//        int firstDiff = -1;
//        int q = 0;
//        while (initial[q].equals(finals[q])) q++;
//
//        for (int j = 0; j < s1.length(); j++) {
//            if (s1.charAt(j) != s2.charAt(j)) {
//                firstDiff = j; break;
//            }
//        }
//
//        String s1prim = ""; String s2prim = "";
//        int secondDiff = -1;
//
//        if (firstDiff != -1) {
//
//            secondDiff = -1;
//
//            for (int j = s1.length() - 1; j >= 0; j--) {
//                if (s1.charAt(j) != s2.charAt(j)) {
//                    secondDiff = j; break;
//                }
//            }
//
//            s1prim = s1.substring(firstDiff, secondDiff + 1);
//            s2prim = s2.substring(firstDiff, secondDiff + 1);
//        }
//
//        return  new Pair(s1prim, s2prim);
//    }
//
//}
