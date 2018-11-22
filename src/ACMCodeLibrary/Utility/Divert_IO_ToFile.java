package ACMCodeLibrary.Utility;

import java.io.*;

/** Utility bit of code to divert input/output streams to test.in and test.out. */
public class Divert_IO_ToFile {

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream testOut = new FileOutputStream("test.out");
        PrintStream out = new PrintStream(testOut);
        System.setOut(out);

        InputStream testIn = new FileInputStream("test.in");
        System.setIn(testIn);
    }
}
