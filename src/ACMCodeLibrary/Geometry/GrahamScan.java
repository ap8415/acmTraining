package ACMCodeLibrary.Geometry;

import ACMCodeLibrary.Geometry.Geometry.PointDouble;

import java.util.*;

public class GrahamScan {
    private Stack<PointDouble> hull = new Stack<PointDouble>();

    /**
     * Computes the convex hull of the specified array of points.
     *
     * @param  points the array of points
     * @throws IllegalArgumentException if {@code points} is {@code null}
     * @throws IllegalArgumentException if any entry in {@code points[]} is {@code null}
     * @throws IllegalArgumentException if {@code points.length} is {@code 0}
     */
    public GrahamScan(List<PointDouble> points) {
        if (points == null) throw new IllegalArgumentException("argument is null");
        if (points.size() == 0) throw new IllegalArgumentException("array is of length 0");

        // copy, or just pass as arrays
        int n = points.size();
        PointDouble[] a = (PointDouble[]) points.toArray();

        // preprocess so that a[0] has lowest y-coordinate; break ties by x-coordinate
        // a[0] is an extreme point of the convex hull
        // (alternatively, could do easily in linear time)
        Arrays.sort(a);

        // sort by polar angle with respect to base point a[0],
        // breaking ties by distance to a[0]
        Arrays.sort(a, 1, n, Geometry.polarOrder(points.get(0)));

        hull.push(a[0]);       // a[0] is first extreme point

        // find index k1 of first point not equal to a[0]
        int k1;
        for (k1 = 1; k1 < n; k1++)
            if (!a[0].equals(a[k1])) break;
        if (k1 == n) return;        // all points equal

        // find index k2 of first point not collinear with a[0] and a[k1]
        int k2;
        for (k2 = k1+1; k2 < n; k2++)
            if (!Geometry.collinear(a[0], a[k1], a[k2])) break;
        hull.push(a[k2-1]);    // a[k2-1] is second extreme point

        // Graham scan; note that a[n-1] is extreme point different from a[0]
        for (int i = k2; i < n; i++) {
            PointDouble top = hull.pop();
            while (!Geometry.ccw(hull.peek(), top, a[i])) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(a[i]);
        }
    }
}
