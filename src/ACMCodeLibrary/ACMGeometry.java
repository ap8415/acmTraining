package ACMCodeLibrary;

import java.awt.geom.Point2D;

// Use these for PointDouble and vector operations.
// A vector (a,b) -> (c,d) is represented as the equivalent PointDouble (c-a, d-b).
public class ACMGeometry {

    static double DEG_to_RAD(double d) { return d * Math.PI / 180.0; }

    public static double RAD_to_DEG(double r) { return r * 180.0 / Math.PI; }

    // The (0, 0) PointDouble, referred to as the ORIGIN, or simply O.
    static final Point2D.Double ORIGIN = new Point2D.Double(0, 0);

    public boolean isEqualTo(double x, double y) {
        return Math.abs(x-y) < 0.000000001; // if delta < 10^-9, then numbers should be equal.
    }

    public double magnitude(Point2D.Double X) {
        return X.distance(ORIGIN);
    }

    // If two vectors are perpendicular, their dot product is 0.
    public double dotProduct(Point2D.Double X, Point2D.Double Y) {
        return X.x * Y.x + X.y * Y.y;
    }

    // Cosinus of the angle between OX and OY.
    public double cosAngle(Point2D.Double X, Point2D.Double Y) {
        return dotProduct(X,Y) / (magnitude(X) * magnitude(Y));
    }

    // Returns the value of the cross product of OX and OY.
    public double crossProduct2D(Point2D.Double X, Point2D.Double Y) {
        return X.x * Y.y - X.y * Y.x;
    }

    // Returns the PointDouble representing a vector from X to Y.
    public Point2D.Double vector(Point2D.Double X, Point2D.Double Y) {
        return new Point2D.Double(Y.x - X.x, Y.y - X.y);
    }

    // Can also use Heron's formula here, if we know magnitude(X, Y, Z) beforehand.
    public double area(Point2D.Double X, Point2D.Double Y, Point2D.Double Z) {
        return Math.abs(areaSigned(X, Y, Z));
    }

    public double areaSigned(Point2D.Double X, Point2D.Double Y, Point2D.Double Z) {
        return 0.5 * crossProduct2D(vector(X, Y), vector(X, Z));
    }

    // Checks if X1X2 || Y1Y2.
    // To check if two lines, l1 and l2, are parallel, take points X1,X2 on l1, Y1,Y2 on l2, and apply this.
    public boolean parallel(Point2D.Double X1, Point2D.Double X2, Point2D.Double Y1, Point2D.Double Y2) {
        return isEqualTo(crossProduct2D(vector(X1, X2), vector(Y1, Y2)), 0);
    }

    // Returns the distance from P to Line AB.
    public double distance(Point2D.Double P, Point2D.Double A, Point2D.Double B) {
        return Math.abs(crossProduct2D(vector(A, P), vector(A, B))) / magnitude(vector(A, B));
    }

    // Returns the distance from P to segment AB:
    // If angle PAB is obtuse, then returns min(PA, PB).
    // Else returns the distance from P to Line AB.
    public double distanceToSegment(Point2D.Double P, Point2D.Double A, Point2D.Double B) {
        if (cosAngle(vector(A, P), vector(A, B)) < 0) {
            return Math.min(magnitude(vector(A, P)), magnitude(vector(B, P)));
        } else {
            return distance(P, A, B);
        }
    }

    // Checks if P lies on Line AB.
    public boolean onLine(Point2D.Double P, Point2D.Double A, Point2D.Double B) {
        return isEqualTo(distance(P, A, B), 0);
    }

    // Checks if P lies on segment AB.
    public boolean onSegment(Point2D.Double P, Point2D.Double A, Point2D.Double B) {
        return isEqualTo(distanceToSegment(P, A, B), 0);
    }

    // Checks if C and D are on the same side of Line AB.
    // Edge case: if C or D lie on AB, the product will be 0 and this returns false.
    public boolean onSameSide(Point2D.Double C, Point2D.Double D, Point2D.Double A, Point2D.Double B) {
        return areaSigned(A, B, C) * areaSigned(A, B, D) > 0;
    }

    // Checks if A, B and C are in counter-clockwise (CCW) order.
    // Returns 1 for CCW, 0 for collinear, -1 for Clockwise.
    public int ccw(Point2D.Double A, Point2D.Double B, Point2D.Double C) {
        double signed = areaSigned(A, B, C);

        if (isEqualTo(signed, 0)) return 0;
        else if (signed > 0) return 1;
        else return -1;
    }

    // Checks if segments AB and CD intersect.
    public boolean segmentsIntersect(Point2D.Double A, Point2D.Double B, Point2D.Double C, Point2D.Double D) {
        return !onSameSide(C, D, A, B) && !onSameSide(A, B, C, D);
    }

}
