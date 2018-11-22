package ACM_Contests.SEERC_2014;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class A {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(new FileInputStream("A.in"));

        int n = scan.nextInt();
        Queue<Bank> negatives = new LinkedList<>();
        Bank first; first = new Bank(scan.nextInt());
        Bank curr = null, prev = first;
        for (int i = 1; i < n; i++) {
            curr = new Bank(scan.nextInt());
            curr.left = prev; prev.right = curr;
            prev = curr;
            if (curr.capital < 0) negatives.add(curr);
        }
        first.left = curr; if (curr!=null) curr.right = first;

        int ctr = 0;
        while (!negatives.isEmpty()) {
            Bank x = negatives.poll();
            x.left.capital += x.capital;
            x.right.capital += x.capital;
            x.capital = -x.capital;
            if (x.left.capital + x.capital >= 0 && x.left.capital < 0) negatives.add(x.left);
            if (x.right.capital + x.capital >= 0 && x.right.capital < 0) negatives.add(x.right);
            ctr++;
        }

        System.out.println(ctr);

    }

    // maybe make comparable, use prio queue to take smallest elem?

    static class Bank {
        int capital;
        Bank left, right;

        Bank(int cap) {
            capital = cap;
        }

        @Override
        public String toString() {
            return "Bank{" +
                    "capital=" + capital +
                    '}';
        }
    }

}
