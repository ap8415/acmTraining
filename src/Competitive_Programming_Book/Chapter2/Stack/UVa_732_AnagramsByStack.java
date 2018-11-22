package Competitive_Programming_Book.Chapter2.Stack;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.stream.Collectors;

public class UVa_732_AnagramsByStack {

    static char[] in, out;
    static String IN, OUT;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            IN = sc.next(); OUT = sc.next();
            in = IN.toCharArray();
            out = OUT.toCharArray();

            Stack<Character> state = new Stack<>();
            List<String> outputs = back(0, 0, state).stream().map(
                    out -> out.substring(0, out.length() - 1)
            ).collect(Collectors.toList());
            Collections.sort(outputs);
            System.out.println("[");
            for (String s : outputs) System.out.println(s);
            System.out.println("]");
        }
    }

    private static List<String> back(int inIndex, int outIndex, Stack<Character> state) {

        List<String> outputs = new ArrayList<>();

//        System.out.println(inIndex +" " + outIndex + " " +
//                IN.substring(inIndex, in.length) + " "
//                + OUT.substring(outIndex, out.length) + " " +
//                state.toString());

        List<Integer> notPushedIndices = new ArrayList<>();

        for (int i = inIndex; i < in.length; i++) {
            if (in[i] == out[outIndex]) notPushedIndices.add(i);
        }

        List<Integer> depthOfStack = new ArrayList<>();
        if (!state.isEmpty() && state.peek() == out[outIndex]) depthOfStack.add(0);

        //System.out.println(notPushedIndices);
        //System.out.println(depthOfStack);

        if (notPushedIndices.isEmpty() && depthOfStack.isEmpty()) {
            if (inIndex == in.length && outIndex == out.length) {
                List<String> l = new ArrayList<>();
                l.add(""); return l;
            } else return new ArrayList<>();
        }
        else {
            for (int index : notPushedIndices) {
                Stack<Character> copyState = (Stack<Character>) state.clone();
                StringBuilder appendedOutput = new StringBuilder();
                for (int i = inIndex; i <= index; i++) {
                    copyState.push(in[i]);
                    appendedOutput.append("i ");
                }

                appendedOutput.append("o ");
                copyState.pop();

                outputs.addAll(back(index + 1, outIndex+1, copyState)
                        .stream()
                        .map(out -> appendedOutput.toString() + out)
                        .collect(Collectors.toList()));
            }
            for (int index : depthOfStack) {
                Stack<Character> copyState = (Stack<Character>) state.clone();
                StringBuilder appendedOutput = new StringBuilder();
                for (int i = 0; i <= index; i++) {
                    copyState.pop();
                    appendedOutput.append("o ");
                }

                outputs.addAll(back(inIndex, outIndex+1, copyState)
                        .stream()
                        .map(out -> appendedOutput.toString() + out)
                        .collect(Collectors.toList()));

            }
        }

        return outputs;
    }

}
