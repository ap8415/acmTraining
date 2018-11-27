package ACM_Contests.SWERC.SWERC2014;

import java.util.*;
import java.lang.*;

public class A {

   static List<char[]> words;
   static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        words  = new ArrayList<>();

        for (int i = 0 ; i < n; i++) {
            words.add(new StringBuilder(sc.next()).reverse().toString().toCharArray());
        }

        int[] currentValues = new int[10];
        Arrays.fill(currentValues, -1);

        back(0, 10, new HashMap<>(), currentValues);


    }

    private static void back(int currWord, int currentIndex, Map<Character, Integer> givenValues, int[] current) {
        if (currWord == n-1) {
            if (givenValues.get(words.get(currWord)[currentIndex]) != null) {
                current[currentIndex] += givenValues.get(words.get(currWord)[currentIndex]);
                for (int i = 0; i <= n-2; i++) {
                    // i is carry
                    if (current[currentIndex] == -i) {
                        current[currentIndex - 1] += 10 * i;
                        back(0, currentIndex-1, givenValues, current);
                    }
                }
                return;
            } else {

                for (int i = 0; i <= n-2; i++) {
                    // i is carry
                    for (int j = current[currentIndex] - i; j <= 9; j++) {
                        Map<Character, Integer> mapcp = new HashMap<>();
                        mapcp.putAll(givenValues);
                        mapcp.put(words.get(currWord)[currentIndex], j);
                        int[] currentCP = Arrays.copyOf(current, 10);
                        currentCP[currentIndex-1] += 10 * i;
                        back(0, currentIndex-1, mapcp, currentCP);
                    }
                }
                return;
            }
        }
        if (currentIndex == -1) {
            System.out.println(givenValues); return;
        }

        if (givenValues.get(words.get(currWord)[currentIndex]) != null) {
            current[currentIndex] -= givenValues.get(words.get(currWord)[currentIndex]);
        } else {
            for (int i = 0; i < 9; i++) {
                Map<Character, Integer> mapcp = new HashMap<>();
                mapcp.putAll(givenValues);
                mapcp.put(words.get(currWord)[currentIndex], i);
                int[] currentCP = Arrays.copyOf(current, 10);
                currentCP[currentIndex-1] -= i;
                if (current[currentIndex-1] >= 0) System.out.println("XD");
            }
        }


    }


}
