package ACM_Contests.SEERC_2014;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class E {

    static Point left;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream("E.in"));

        int N = scan.nextInt();


        int[][] coords = new int[N][2];
        // [i][0] is X, [i][1] is Y

        for (int i = 0; i < N; i++) {
            coords[i][0] = scan.nextInt();
            coords[i][1] = scan.nextInt();
        }

        // Generate points for convex hull
        Set<Point> pointFilterer = new HashSet<>();
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int a = coords[i][0];
            int b = coords[i][1];
            pointFilterer.add(new Point(a, b + 1));
            pointFilterer.add(new Point(a, b - 1));
            pointFilterer.add(new Point(a + 1, b));
            pointFilterer.add(new Point(a - 1, b));
        }

        points.addAll(pointFilterer);

        Comparator<Point> leftmost = Comparator.comparingInt(o -> o.x);

        left = points.stream().min(leftmost).get();
        points.remove(left);

        // Sort points according to comparator
        mergeSort(points, 0, points.size() - 1);


        List<PolygonEdge> hull = new LinkedList<>();
        hull.add(new PolygonEdge(left, points.get(0)));

        for (int i = 0; i < points.size() - 1; i++) {
            hull.add(new PolygonEdge(points.get(i), points.get(i + 1)));
        }

        hull.add(new PolygonEdge(points.get(points.size() - 1), left));

//        System.out.println("First is "+ left);

        // Pennies step
//        System.out.println(hull);

        PolygonEdge first = hull.get(0);
        PolygonEdge second;
        ListIterator<PolygonEdge> curr = hull.listIterator();
        curr.next();

        Point p1, p2, p3;
        do {
            second = curr.next();

            p1 = first.begin;
            p2 = first.end; // = second.begin
            p3 = second.end;

//            System.out.println("Points are " + p1 + ", " + p2 + ", " + p3);
            long val = ((long)(p2.y - p1.y) * (p3.x - p1.x)) - ((long)(p3.y - p1.y) * (p2.x - p1.x));

//            System.out.println(val);

            if (val < 0 || (val == 0 && ((p1.x > p2.x && p2.x > p3.x) || (p1.x < p2.x && p2.x < p3.x)) )) {
                first = second;
//                System.out.println("CCW");
            } else {
                first.end = second.end;
//                System.out.println("NOT CCW");
                curr.remove();
                curr.previous();
                curr.previous();
                first = curr.next();
            }

        } while (curr.hasNext());

        double periPeri = 0.0;

        // have hull, print perimeter
        for (PolygonEdge e : hull) {
            periPeri += e.begin.distance(e.end);
        }

//        System.out.println(" \n\n\n HULL IS " + hull + " \n\n\n");



        System.out.println(periPeri);

    }

    private static void mergeSort(List<Point> points, int begin, int end) {
        if (begin == end) return;
        else {
            int mid = (begin+ end)/2;
            mergeSort(points, begin, mid);
            mergeSort(points, mid+1, end);

            Point[] sorted = new Point[end - begin + 1]; int ctr = 0;
            int index1 = begin; int index2 = mid+1;
            while (index1 <= mid || index2 <= end) {
                if (index2 > end) {
                    sorted[ctr] = points.get(index1);
                    index1++;
                } else if (index1 > mid) {
                    sorted[ctr] = points.get(index2);
                    index2++;
                } else if (comparePts(points.get(index1), points.get(index2)) > 0) {
                    sorted[ctr] = points.get(index1);
                    index1++;
                } else {
                    sorted[ctr] = points.get(index2);
                    index2++;
                }
                ctr++;
            }

            for (int i = begin; i <= end; i++) {
                points.set(i, sorted[i-begin]);
            }
        }

    }

    static class PolygonEdge {
        Point begin, end;

        public PolygonEdge(Point begin, Point end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            return "PolygonEdge{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}'+'\n';
        }
    }

    public static int comparePts(Point o1, Point o2) {
        // left = (a,b), o1 = (c,d), o2 = (e,f) NOT SURE

        if (o1.x < left.x) return -1;
        if (o2.x < left.x) return 1;

        long abc = ((long)(o2.y - left.y)) * (o1.x - left.x);
        long def = ((long)(o1.y - left.y)) * (o2.x - left.x);

        if (def - abc < 0) return 1;
        else if (def - abc > 0) return -1;
        else return 0;
    }
}