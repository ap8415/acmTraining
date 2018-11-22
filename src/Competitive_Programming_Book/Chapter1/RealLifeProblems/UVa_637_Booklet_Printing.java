package Competitive_Programming_Book.Chapter1.RealLifeProblems;

import java.io.*;
import java.lang.*;
import java.util.*;

public class UVa_637_Booklet_Printing {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int noOfPages = sc.nextInt(); if (noOfPages == 0) return;

            int noOfSheets = noOfPages/4 + ((noOfPages % 4 == 0) ? 0 : 1);
            Sheet[] sheets = new Sheet[noOfSheets]; for (int i = 0; i < noOfSheets; i++) sheets[i] = new Sheet(i+1);

            boolean first = true;
            boolean ret = false;
            int ctr = 0;
            for (int i = 1; i <= noOfPages; i++) {

                if (!ret) {
                    if (first) {
                        sheets[ctr].frontRight = i; first = false;
                    } else {
                        sheets[ctr].backLeft = i; first = true; ctr++;
                    }

                    if (ctr == noOfSheets) {
                        ret = true; ctr--;
                    }
                } else {
                    if (first) {
                        sheets[ctr].backRight = i; first = false;
                    } else {
                        sheets[ctr].frontLeft = i; first = true; ctr--;
                    }
                }
            }

            System.out.println("Printing order for " + noOfPages + " pages:");
            for (int i = 0; i < noOfSheets; i++) {
                System.out.println(sheets[i]);
            }
        }
    }

    public static class Sheet {
        int frontLeft, frontRight, backLeft, backRight;
        int index;

        public Sheet(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            String front = String.format("Sheet %d, front: %s, %s", index, frontLeft != 0 ? frontLeft+"" : "Blank", frontRight);
            if (backLeft != 0 || backRight != 0) {
                String back = String.format("Sheet %d, back : %s, %s", index,
                        backLeft != 0 ? backLeft+"" : "Blank",
                        backRight != 0 ? backRight+"" : "Blank");
                return front + '\n' + back;
            } else {
                return front;
            }
        }
    }

}
