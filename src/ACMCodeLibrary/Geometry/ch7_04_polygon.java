package ACMCodeLibrary.Geometry;

import ACMCodeLibrary.Geometry.Geometry.PointDouble;
import ACMCodeLibrary.Geometry.Geometry.Vec;

import java.util.*;

import static ACMCodeLibrary.Geometry.Geometry.EPS;
import static ACMCodeLibrary.Geometry.Geometry.dist;
import static ACMCodeLibrary.Geometry.Geometry.vector;

class ch7_04_polygon {

    // returns the perimeter of the polygon
    double perimeter(List<PointDouble> points) {
        double result = 0.0;
        int n = points.size();
        for (int i = 0; i < n; i++)
            result += dist(points.get(i % n), points.get((i + 1) % n));
        return result;
    }

    // returns the area, which is half the determinant
    // works for both convex and concave polygons
    double area(List<PointDouble> points) {
        double result = 0.0, x1, y1, x2, y2;
        int n = points.size();
        for (int i = 0; i < n; i++) {
            x1 = points.get(i % n).x; x2 = points.get((i + 1) % n).x;
            y1 = points.get(i % n).y; y2 = points.get((i + 1) % n).y;
            result += (x1 * y2 - x2 * y1);
        }
        return Math.abs(result) / 2.0;
    }

    double dot(Vec a, Vec b) { return (a.x * b.x + a.y * b.y); }

    double norm_sq(Vec v) { return v.x * v.x + v.y * v.y; }

    double angle(PointDouble a, PointDouble o, PointDouble b) {     // returns angle aob in rad
        Vec oa = vector(o, a), ob = vector(o, b);
        return Math.acos(dot(oa, ob) / Math.sqrt(norm_sq(oa) * norm_sq(ob))); }

    double cross(Vec a, Vec b) { return a.x * b.y - a.y * b.x; }

    // note: to accept collinear points, we have to change the `> 0'
    // returns true if PointDouble r is on the left side of line pq
    boolean ccw(PointDouble p, PointDouble q, PointDouble r) {
        return cross(vector(p, q), vector(p, r)) > 0; }

    // returns true if PointDouble r is on the same line as the line pq
    boolean collinear(PointDouble p, PointDouble q, PointDouble r) {
        return Math.abs(cross(vector(p, q), vector(p, r))) < EPS;
    }

    // returns true if we always make the same turn while examining
    // all the edges of the polygon one by one
    boolean isConvex(List<PointDouble> points) {
        int sz = points.size();
        if (sz <= 3) return false;
        boolean isLeft =
                ccw(points.get(0), points.get(1), points.get(2)); // remember one result

        for (int i = 1; i < sz; i++)            // then compare with the others
            if (ccw(points.get(i % sz),
                    points.get((i + 1) % sz),
                    points.get((i+2) % sz)) != isLeft) {
                return false;            // different sign -> this polygon is concave
            }
        return true;
    }

    // returns true if PointDouble p is in either convex/concave polygon P
    boolean inPolygon(PointDouble pt, List<PointDouble> P) {
        int sz = P.size();
        if (sz == 0) return false;
        double sum = 0; // assume first vertex = last vertex
        for (int i = 0; i < sz; i++) {
            PointDouble first = P.get(i % sz);
            PointDouble second = P.get((i + 1) % sz);

            if (ccw(pt, first, second)) {
                sum += angle(first, pt, second);   // left turn/ccw
            } else {
                sum -= angle(first, pt, second);  // right turn/cw
            }
        }
        return Math.abs(Math.abs(sum) - 2*Math.PI) < EPS; // angles must sum to 2*pi to be inside the polygon
    }

    // line segment p-q intersect with line A-B.
    PointDouble lineIntersectSeg(PointDouble p, PointDouble q, PointDouble A, PointDouble B) {
        double a = B.y - A.y;
        double b = A.x - B.x;
        double c = B.x * A.y - A.x * B.y;
        double u = Math.abs(a * p.x + b * p.y + c);
        double v = Math.abs(a * q.x + b * q.y + c);
        return new PointDouble((p.x * v + q.x * u) / (u+v), (p.y * v + q.y * u) / (u+v)); }

    // cuts polygon Q along the line formed by PointDouble a -> PointDouble b
    // (note: the last PointDouble must be the same as t`he first PointDouble)
    List<PointDouble> cutPolygon(PointDouble a, PointDouble b, List<PointDouble> Q) {
        List<PointDouble> P = new ArrayList<>(); // the new polygon
        for (int i = 0; i < (int)Q.size(); i++) {
            double left1 = cross(vector(a, b), vector(a, Q.get(i))), left2 = 0;
            if (i != (int)Q.size()-1) left2 = cross(vector(a, b), vector(a, Q.get(i+1)));
            if (left1 > -EPS) P.add(Q.get(i)); // Q[i] is on the left of ab
            if (left1 * left2 < -EPS) // edge (Q[i], Q[i+1]) crosses line ab
                P.add(lineIntersectSeg(Q.get(i), Q.get(i+1), a, b));
        }
        if (!P.isEmpty() && P.get(P.size()-1).compareTo(P.get(0)) != 0)
            P.add(P.get(0)); // make P's first PointDouble = P's last PointDouble
        return P;
    }

    PointDouble pivot = new PointDouble();
    List<PointDouble> CH(List<PointDouble> P) {
        int i, j, n = (int)P.size();
        if (n <= 3) {
            if (P.get(0).compareTo(P.get(n-1)) != 0) P.add(P.get(0)); // safeguard from corner case
            return P; // special case, the CH is P itself
        }

        // first, find P0 = PointDouble with lowest Y and if tie: rightmost X
        int P0 = 0;
        for (i = 1; i < n; i++)
            if (P.get(i).y  < P.get(P0).y ||
                    (P.get(i).y == P.get(P0).y && P.get(i).x > P.get(P0).x))
                P0 = i;

        PointDouble temp = P.get(0); P.set(0, P.get(P0)); P.set(P0 ,temp); // swap P[P0] with P[0]

        // second, sort points by angle w.r.t. P0
        pivot = P.get(0); // use this global variable as reference
        Collections.sort(P, new Comparator<PointDouble>(){
            public int compare(PointDouble a, PointDouble b) { // angle-sorting function
                if (collinear(pivot, a, b))
                    return dist(pivot, a) < dist(pivot, b) ? -1 : 1; // which one is closer?
                double d1x = a.x - pivot.x, d1y = a.y - pivot.y;
                double d2x = b.x - pivot.x, d2y = b.y - pivot.y;
                return (Math.atan2(d1y, d1x) - Math.atan2(d2y, d2x)) < 0 ? -1 : 1;
            }
        });

        // third, the ccw tests
        List<PointDouble> S = new ArrayList<PointDouble>();
        S.add(P.get(n-1)); S.add(P.get(0)); S.add(P.get(1)); // initial S
        i = 2; // then, we check the rest
        while (i < n) { // note: n must be >= 3 for this method to work
            j = S.size() - 1;
            if (ccw(S.get(j-1), S.get(j), P.get(i))) S.add(P.get(i++)); // left turn, accept
            else S.remove(S.size() - 1); // or pop the top of S until we have a left turn
        }
        return S; } // return the result

    void run() {
        // 6 points, entered in counter clockwise order, 0-based indexing
        List<PointDouble> P = new ArrayList<PointDouble>();
        P.add(new PointDouble(1, 1));
        P.add(new PointDouble(3, 3));
        P.add(new PointDouble(9, 1));
        P.add(new PointDouble(12, 4));
        P.add(new PointDouble(9, 7));
        P.add(new PointDouble(1, 7));
        P.add(P.get(0)); // loop back

        System.out.printf("Perimeter of polygon = %.2f\n", perimeter(P)); // 31.64
        System.out.printf("Area of polygon = %.2f\n", area(P)); // 49.00
        System.out.printf("Is convex = %b\n", isConvex(P)); // false (P1 is the culprit)

        //// the positions of P6 and P7 w.r.t the polygon
        //7 P5--------------P4
        //6 |                  \
        //5 |                    \
        //4 |   P7                P3
        //3 |   P1___            /
        //2 | / P6    \ ___    /
        //1 P0              P2
        //0 1 2 3 4 5 6 7 8 9 101112

        PointDouble P6 = new PointDouble(3, 2); // outside this (concave) polygon
        System.out.printf("Point P6 is inside this polygon = %b\n", inPolygon(P6, P)); // false
        PointDouble P7 = new PointDouble(3, 4); // inside this (concave) polygon
        System.out.printf("Point P7 is inside this polygon = %b\n", inPolygon(P7, P)); // true

        // cutting the original polygon based on line P[2] -> P[4] (get the left side)
        //7 P5--------------P4
        //6 |               |  \
        //5 |               |    \
        //4 |               |     P3
        //3 |   P1___       |    /
        //2 | /       \ ___ |  /
        //1 P0              P2
        //0 1 2 3 4 5 6 7 8 9 101112
        // new polygon (notice the index are different now):
        //7 P4--------------P3
        //6 |               |
        //5 |               |
        //4 |               |
        //3 |   P1___       |
        //2 | /       \ ___ |
        //1 P0              P2
        //0 1 2 3 4 5 6 7 8 9

        P = cutPolygon(P.get(2), P.get(4), P);
        System.out.printf("Perimeter of polygon = %.2f\n", perimeter(P)); // smaller now 29.15
        System.out.printf("Area of polygon = %.2f\n", area(P)); // 40.00

        // running convex hull of the resulting polygon (index changes again)
        //7 P3--------------P2
        //6 |               |
        //5 |               |
        //4 |   P7          |
        //3 |               |
        //2 |               |
        //1 P0--------------P1
        //0 1 2 3 4 5 6 7 8 9

        P = CH(P); // now this is a rectangle
        System.out.printf("Perimeter of polygon = %.2f\n", perimeter(P)); // precisely 28.00
        System.out.printf("Area of polygon = %.2f\n", area(P)); // precisely 48.00
        System.out.printf("Is convex = %b\n", isConvex(P)); // true
        System.out.printf("Point P6 is inside this polygon = %b\n", inPolygon(P6, P)); // true
        System.out.printf("Point P7 is inside this polygon = %b\n", inPolygon(P7, P)); // true
    }

    public static void main(String[] args){
        new ch7_04_polygon().run();
    }
}
