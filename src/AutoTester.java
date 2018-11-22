import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.*;
import java.io.*;
import java.lang.*;

public class AutoTester {

    public static void main(String[] args) throws Exception {
        FileWriter outputStream = new FileWriter(new File("testOutputs.txt"));

        Class<?> clazz = Class.forName(args[0]);

        for (int i = 1; i < args.length; i++) {
            String testFileName = args[i];

            InputStream testIn = new FileInputStream(testFileName + ".in") ;
            System.setIn(testIn);

            FileOutputStream testOut = new FileOutputStream("tmp.txt");
            PrintStream out = new PrintStream(testOut);
            System.setOut(out);

            try {
                clazz.getMethod("main", String[].class).invoke(clazz.getDeclaredConstructor().newInstance(), (Object) null);
            } catch (Throwable t) {}
            byte[] f1 = Files.readAllBytes(new File(testFileName + ".out").toPath());
            byte[] f2 = Files.readAllBytes(new File("tmp.txt").toPath());
            if (!Arrays.equals(f1, f2)) {
                outputStream.write(String.format("Test %s: FAILED, output does not match expected output!\n" +
                        "Your output was %d bits long; the accepted output was %d bits long", testFileName, f1.length, f2.length));
            } else {
                outputStream.write(String.format("Test %s: PASSED\n", testFileName));
            }
            outputStream.write('\n');
        }

        outputStream.flush();

    }

}

