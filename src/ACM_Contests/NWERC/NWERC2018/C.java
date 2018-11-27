package ACM_Contests.NWERC.NWERC2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {

    static class PointDouble {
        double x, y;

        public PointDouble(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public PointDouble() {
            x = y=  0.0;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    static class Vec {
        double x, y;

        public Vec(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static Vec vector(PointDouble a, PointDouble b) {
        return new Vec(b.x - a.x, b.y - a.y);
    }

    static double dot(Vec a, Vec b) {
        return (a.x * b.x + a.y * b.y);
    }

    static double norm(Vec v) {
        return Math.sqrt(v.x * v.x + v.y * v.y);
    }

    static PointDouble rotate(PointDouble p, double theta) {
        return new PointDouble(
                p.x * Math.cos(theta) - p.y * Math.sin(theta),
                p.x * Math.sin(theta) + p.y* Math.cos(theta)
        );
    }

    static double angle(PointDouble a, PointDouble o, PointDouble b) {
        Vec oa = vector(o, a), ob = vector(o , b);
        return Math.acos(dot(oa, ob) / (norm(oa) * norm(ob)));
    }
    static int n;

    static List<Integer>[] adjList;
    static PointDouble[] setPoints;
    static int[] childrenNo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); adjList = new List[n];
        for (int i = 0 ; i < n; i++ ) adjList[i] = new ArrayList<>();

        for (int i = 0; i < n-1; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;
            adjList[from].add(to); adjList[to].add(from);
        }

        setPoints = new PointDouble[n];
        childrenNo = new int[n];
        dfs(0, -1);
        constructValidTree(0, -1, 2 * Math.PI, new PointDouble(), new PointDouble(1, 0), new PointDouble(1, 0));

        for (int i = 0; i < n; i++) {
            System.out.println(setPoints[i]);
        }
    }

    private static void constructValidTree(int i, int p, double angle,
                                           PointDouble origin, PointDouble left, PointDouble right) {
        int children= adjList[i].size() - (p == -1 ? 0 : 1);
        PointDouble[] succ = new PointDouble[2 * children + 1];
        succ[0] = left;
        int index = 0;
        double currAngle = 0.0;
        double[] recursiveAngles = new double[children];
        for (int j : adjList[i]) {
            if (j != p) {
                Vec oLeft = vector(origin, left);
                PointDouble oLeftP = new PointDouble(oLeft.x, oLeft.y);
                double quantity = (angle * childrenNo[j]) / childrenNo[i];

                PointDouble rotated = rotate(oLeftP, currAngle + quantity /2 );
                rotated.x += origin.x; rotated.y += origin.y;
                succ[2*index+1 ] = rotated;

                rotated = rotate(oLeftP, currAngle + quantity );
                rotated.x += origin.x; rotated.y += origin.y;
                succ[2*index+2 ] = rotated;
                recursiveAngles[index] = quantity;
                currAngle += quantity;
                index++;
            }
        }

        index = 0;
        for (int j : adjList[i]) {
            if (j != p) {
                PointDouble nextOrigin = succ[index * 2 + 1];
                PointDouble nextLeft = romb(origin, succ[index * 2], nextOrigin);
                PointDouble nextRight = romb(origin, nextOrigin, succ[index * 2  + 2]);
                constructValidTree(j, i, recursiveAngles[index], nextOrigin, nextLeft, nextRight);
                index++;
            }
        }

        setPoints[i] = origin;
    }

    private static void dfs(int i, int p) {
        for (int j : adjList[i]) {
            if (j != p) {
                dfs(j, i);
                childrenNo[i] += childrenNo[j];
            }
        }
        if (childrenNo[i] == 0) childrenNo[i] = 1;
    }

    static PointDouble romb(PointDouble o, PointDouble x, PointDouble y) {
        PointDouble result = new PointDouble();
        result.x = x.x + y.x - o.x;
        result.y = x.y + y.y - o.y;
        return result;
    }

}
