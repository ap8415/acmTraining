package Competitive_Programming_Book.Chapter2.Stack;

import java.util.*;
import java.io.*;
import java.lang.*;


public class UVa_1062_Containers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int caseNo = 1;

        while (sc.hasNext()) {
            String containers = sc.nextLine();
            if (containers.equals("end")) return;

            Stack<Character> stack = new Stack<>();
            Map<Character, Integer> possibleContainers = new HashMap<>();
            List<Character> orderedContainers = new ArrayList<>();

            for (int i = 0; i < containers.length(); i++) {
                stack.push(containers.charAt(i));
                possibleContainers.put(containers.charAt(i), 1 +  possibleContainers.getOrDefault(containers.charAt(i), 0));
                if (!orderedContainers.contains(containers.charAt(i))) {
                    orderedContainers.add(containers.charAt(i));
                }
            }

            Collections.sort(orderedContainers);

            Stack<Character> aux = new Stack<>();

            int total = 1;
            int currCharIndex = 0;

            while (!stack.isEmpty()) {
                Character c = next(stack);
                Character x = stack.pop();
                if (x == c) {
                    possibleContainers.put(c, possibleContainers.get(c) - 1);
                } else {
                    aux.push(x);
                }
                if (stack.isEmpty()) {
                    if (!aux.isEmpty()) {
                        total++;
                        while (!aux.isEmpty()) stack.push(aux.pop());
                    }
                }
            }
            System.out.println("Case " + caseNo + ": " + total);
            caseNo++;
        }
    }

    public static Character next(Stack<Character> stack) {
        return stack.stream().min(Character::compareTo).get();
    }

}
