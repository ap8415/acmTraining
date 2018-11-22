package Competitive_Programming_Book.Chapter2.Java_Collections;
import java.util.*;
import java.lang.*;
import java.io.*;
public class UVa_10107_WhatIsTheMedian {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = new ArrayList<>();

        while (sc.hasNext()) {
            int next = sc.nextInt();
            int index = Collections.binarySearch(numbers, next);
            if (index > 0) {
                numbers.add(index, next);
            } else {
                numbers.add(-(index + 1), next);
            }

            if (numbers.size() % 2 == 0) {
                int x = numbers.get(numbers.size() / 2);
                int y = numbers.get(numbers.size() / 2 - 1);
                System.out.println((x + y) / 2);
            } else {
                System.out.println(numbers.get(numbers.size() / 2));
            }
        }
    }
}
