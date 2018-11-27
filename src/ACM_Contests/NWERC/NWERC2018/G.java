package ACM_Contests.NWERC.NWERC2018;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class G {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static String input;
    static int len;

    static class Point {

        static Point origin;
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
            x= y = 0;
        }

        public int manhattan(Point p) {
            return Math.abs(p.x - x) + Math.abs(p.y  - y);
        }

        @Override
        public String toString() {
            return (x - origin.x) + " " + (y- origin.y);
        }
    }

    static HashMap<Character, Integer> encoding;

    public static void main(String[] args) throws FileNotFoundException {


//        InputStream testIn = new FileInputStream("test.in");
//        System.setIn(testIn);
        Point.origin = new Point();

         encoding = new HashMap<>();
        encoding.put('U', 3);
        encoding.put('D', 1);
        encoding.put('L', 0);
        encoding.put('R', 2);

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        len = input.length();

        String[] nasty = {"UDU", "DUD", "LRL", "RLR"};
        for (String bad : nasty) {
            if (input.substring(len - 3, len).equals(bad)) {
                System.out.println("impossible");
                return;
            }
        }

        Point result = bruteForce(0, -1, new Point(), null, null);
        System.out.println(result);
        Point.origin = result;
        System.out.println(blocks.size());
        for (Point block : blocks) System.out.println(block);

    }

    static List<Point> blocks = new ArrayList<>();

    private static Point bruteForce(int iteration, int direction, Point curr, Point lower, Point upper) {

//        System.out.println("At iter " + iteration);
        if (iteration >= len) return curr;

        int nextDir = encoding.get(input.charAt(iteration));
//        System.out.println("NExt direction is: " + nextDir);
        if (direction == -1 || (nextDir - direction) % 2 == 1) {
            // verticals are == 1, horizontals are == 0, so this ensures we switch verticals
            Point next = new Point();
            next.x = curr.x; next.y = curr.y;
            next.x += (int) (Math.pow(2, len - iteration - 1)) * dx[nextDir];
            next.y += (int) (Math.pow(2, len - iteration - 1)) * dy[nextDir];
//            System.out.println(next);
            Point block = new Point();
            block.x = next.x + dx[nextDir];
            block.y = next.y + dy[nextDir];
            blocks.add(block);
            return bruteForce(iteration + 1, nextDir, next,
                    dx[nextDir] < 0 || dy[nextDir] < 0 ? block : null,
                    dy[nextDir] > 0 || dx[nextDir] > 0 ? block : null
                    );
        } else if (nextDir == direction) {
            // do nothing
            return bruteForce(iteration+1, direction, curr, lower, upper);
        } else {
        // opposite direction
            if (dx[nextDir] < 0 || dy[nextDir] < 0) {
                if (lower != null) {
                    Point next = new Point(lower.x - dx[nextDir], lower.y - dy[nextDir]);
                    return bruteForce(iteration+1,nextDir, next, lower, upper);
                } else {
                    Point next = new Point();
                    next.x = curr.x; next.y = curr.y;
                    next.x += (int) (Math.pow(2, len - iteration - 1) + Math.pow(2, len - iteration)) * dx[nextDir];
                    next.y += (int) (Math.pow(2, len - iteration - 1) + Math.pow(2, len - iteration)) * dy[nextDir];
                    Point block = new Point();
                    block.x = next.x + dx[nextDir];
                    block.y = next.y + dy[nextDir];
                    blocks.add(block);
                    return bruteForce(iteration+1,nextDir, next, lower, upper);
                }
            } else {
                if (upper != null) {
                    curr = new Point(upper.x - dx[nextDir], upper.y - dy[nextDir]);
                    return bruteForce(iteration + 1, nextDir, curr, lower, upper);
                } else {
                    Point next = new Point();
                    next.x = curr.x; next.y = curr.y;
                    next.x += (int) (Math.pow(2, len - iteration - 1) + Math.pow(2, len - iteration)) * dx[nextDir];
                    next.y += (int) (Math.pow(2, len - iteration - 1) + Math.pow(2, len - iteration)) * dy[nextDir];
                    Point block = new Point();
                    block.x = next.x + dx[nextDir];
                    block.y = next.y + dy[nextDir];
                    blocks.add(block);
                    return bruteForce(iteration+1,nextDir, next, lower, upper);
                }
            }

        }
    }

}
