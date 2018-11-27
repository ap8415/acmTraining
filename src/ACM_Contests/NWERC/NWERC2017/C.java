package ACM_Contests.NWERC.NWERC2017;

import java.util.*;

public class C {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Geometry.PointDouble[] points = new Geometry.PointDouble[16];
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                points[sc.nextInt() - 1] = new Geometry.PointDouble(i, j);
            }
        }

//        System.out.println(Arrays.toString(points));

        int segments = 0;
        Geometry.PointDouble curr = points[0];
        int next = 1;
        while (next < 16) {
            System.out.println(next + 1);
            System.out.println(segments + " segmentele");
            Geometry.Line line = Geometry.pointsToLine(curr, points[next]);
            segments++;
            int i = next + 1;
            while(i < 16 && Math.abs(Geometry.angle(curr, points[next], points[i]) - Math.PI) < Geometry.EPS)
            {
                i++;
            }
//            System.out.println(" i aici e " + i);
//            System.out.println("Junghiu " + Math.abs(Geometry.angle(curr, points[next], points[i]) - Math.PI));
//            System.out.println(curr);
//            System.out.println(points[next]);
//            System.out.println(points[i]);

            if (i == 16) {
                // we're done
                System.out.println(segments);
                return;
            } else if (i == 15) {
                // need one more seg for 15
                System.out.println(segments + 1);
                return;
            } else {
                Geometry.PointDouble intersection = Geometry.intersection(line, Geometry.pointsToLine(points[i], points[i+1]));
                if (intersection != null) {
                    if (Math.abs(Geometry.angle(curr, points[i-1], intersection) - Math.PI) < Geometry.EPS){
                        curr = intersection;
//                        System.out.println(intersection);
                    } else {
                        curr = points[i-1];
                    }
                } else {
                    // line(curr, p[next]) and line (p[i], p[i+1]) are parallel
                    if (i <= 13) {
                        // atleast3 points not done
                        if (!Geometry.collinear(points[i], points[i+1], points[i+2])) {
//                            System.out.println("here");
                            segments += 1;
                            curr = Geometry.translate(points[i+1], Geometry.vector(points[i+2], points[i+1]));
                            i++;
                        } else {
                            curr = points[i-1];
                        }
                    } else {
                        System.out.println(segments + 2);
                        return;
                    }
                }
            }
            next = i;
        }

    }








    static class Geometry {
        static final double INF = 1e9;
        static final double EPS = 1e-5; // changed from 1e-9
        // we will use constant Math.PI in Java

        static double DEG_to_RAD(double d) {
            return d * Math.PI / 180.0;
        }

        static double RAD_to_DEG(double r) {
            return r * 180.0 / Math.PI;
        }

        // basic integer PointDouble
        static class Point {
            int x, y;

            Point() {
                x = y = 0;
            }

            Point(int x, int y) {
                this.x = x; this.y = y;
            }
        }

        // Used if ints are not sufficient
        static class PointDouble implements Comparable<PointDouble>{
            double x, y;                   // only used if more precision is needed

            PointDouble() {
                x = y = 0.0;
            }

            PointDouble(double x, double y) {
                this.x = x; this.y = y;
            }

            // returns the point from the complex nr notation
            static PointDouble complex(double r, double arg) {
                return new PointDouble(r * Math.cos(arg), r * Math.sin(arg));
            }

            // use EPS (1e-9) when testing equality of two floating points
            public int compareTo(PointDouble other) {      // override less than operator
                if (Math.abs(x - other.x) > EPS)                // useful for sorting
                    return (int)Math.ceil(x - other.x);       // first: by x-coordinate
                else if (Math.abs(y - other.y) > EPS)
                    return (int)Math.ceil(y - other.y);      // second: by y-coordinate
                else
                    return 0; // they are equal
            }

            @Override
            public String toString() {
                return "PointDouble{" +
                        "x=" + x +
                        ", y=" + y +
                        '}';
            }
        }

        // Euclidean distance
        static double dist(PointDouble p1, PointDouble p2) {
            // Math.hypot(dx, dy) returns sqrt(dx * dx + dy * dy)
            return Math.hypot(p1.x - p2.x, p1.y - p2.y);
        }

        // rotate p by theta degrees CCW w.r.t origin (0, 0)
        static PointDouble rotate(PointDouble p, double theta) {
            double rad = DEG_to_RAD(theta); // get angle in radians
            return new PointDouble(
                    p.x * Math.cos(rad) - p.y * Math.sin(rad),
                    p.x * Math.sin(rad) + p.y * Math.cos(rad)
            );
        }

        // returns the complex argument of the point
        static double arg(PointDouble p) {
            PointDouble origin = new PointDouble(0, 0);
            PointDouble ox = new PointDouble(1, 0);
            if (p.y == 0) {
                return p.x >= 0 ? 0.0 : Math.PI;
            } else if (p.y <= 0) {
                return 2 * Math.PI - angle(ox, origin, p);
            } else {
                return angle(ox, origin, p);
            }
        }

        // Line is described by its equation ax+by+c = 0
        static class Line {
            double a, b, c;
        }

        // the answer is stored in the third parameter
        static Line pointsToLine(PointDouble p1, PointDouble p2) {
            Line line = new Line();
            if (Math.abs(p1.x - p2.x) < EPS) {
                // Vertical Line through both points
                line.a = 1.0;   line.b = 0.0;   line.c = -p1.x;
            } else {
                // Non-vertical Line through the points.
                // Since the Line eq. is homogeneous we fix the scaling by setting b = 1.0.
                line.a = -(p1.y - p2.y) / (p1.x - p2.x);
                line.b = 1.0;
                line.c = -(line.a * p1.x) - p1.y;
            }
            return line;
        }

        // Line in the form mx + c = 0 (where m is a PointDouble)
        // not needed since we will use the more robust form: ax + by + c = 0 (see above)
        static class Line2 { double m, c; };          // another way to represent a Line

        static Line2 pointsToLine2(PointDouble p1, PointDouble p2) {
            Line2 line = new Line2();
            if (Math.abs(p1.x - p2.x) < EPS) {
                // Vertical Line through both points
                line.m = INF;                       // l contains m = INF and c = x_value
                line.c = p1.x;                     // to denote vertical Line x = x_value
            }
            else {
                // Non-vertical Line through the points.
                line.m = (p1.y - p2.y) / (p1.x - p2.x);
                line.c = p1.y - line.m * p1.x;
            }
            return line;
        }

        // Lines are parallel if their equations differ only in the constant c.
        static boolean areParallel(Line l1, Line l2) {
            return (Math.abs(l1.a - l2.a) < EPS) && (Math.abs(l1.b-l2.b) < EPS);
        }

        // Lines are identical if their equations are identical
        static boolean areIdentical(Line l1, Line l2) {
            return areParallel(l1, l2) && (Math.abs(l1.c - l2.c) < EPS);
        }

        // Return null if lines don't intersect, or the PointDouble of intersection if they do
        static PointDouble intersection(Line l1, Line l2) {
            if (areParallel(l1, l2)) return null;               // no intersection
            PointDouble p = new PointDouble();
            // solve system of 2 linear algebraic equations with 2 unknowns
            p.x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);
            // In one of the lines, the b in the equation will be 1, since if both are 0
            // the lines are parallel. We calculate y from that Line's equation
            if (Math.abs(l1.b) > EPS) {
                p.y = -(l1.a * p.x + l1.c);
            } else {
                p.y = -(l2.a * p.x + l2.c);
            }
            return p;
        }

        static class Vec {
            double x, y;     // name: `Vec' is different from Java Vector
            Vec(double x, double y) {
                this.x = x; this.y = y;
            }
        };

        // Get the vector representing 2 points
        static Vec vector(PointDouble a, PointDouble b) {
            return new Vec(b.x - a.x, b.y - a.y);
        }

        // Return a new vector, equal to the vector v scaled by a factor s.
        static Vec scale(Vec v, double s) {
            return new Vec(v.x * s, v.y * s);
        }

        // Return a new PointDouble, equal to PointDouble p translated by vector v
        static PointDouble translate(PointDouble p, Vec v) {
            return new PointDouble(p.x + v.x , p.y + v.y);
        }


        // Returns the slope of a Line.
        // All vertical lines are taken to have slope +infinity
        // (differentiation between vertical lines can only happen when we have orientation)
        static double slope(Line line) {
            return (line.b == 1) ? -line.a : INF;
        }

        // convert PointDouble and gradient/slope to Line
        static Line pointSlopeToLine(PointDouble p, double slope) {
            Line line = new Line();
            line.a = -slope; // always -slope TODO: solve case slope=INFINITY
            line.b = 1;      // always 1
            line.c = -((line.a * p.x) + (line.b * p.y));
            return line;
        }

        // Returns the projection of p on Line.
        static PointDouble projection(Line line, PointDouble p) {
            if (Math.abs(line.b) < EPS) {
                // special case 1: vertical Line
                return new PointDouble(-line.c, p.y);
            }

            if (Math.abs(line.a) < EPS) {
                // special case 2: horizontal Line
                return new PointDouble(p.x, -line.c);
            }

            // perpendicular to l, passing through p
            // slope guaranteed to not be edge-case since we dealt with those already
            Line perpendicular = pointSlopeToLine(p, 1 / line.a);
            // intersect Line with perpendicular
            return intersection(line, perpendicular);
        }

        // Returns the reflection of p on Line.
        static PointDouble reflectionPoint(Line l, PointDouble p) {
            PointDouble b = projection(l, p);      // get projection b, of p onto l
            Vec v = vector(p, b);                  // create a vector v = vector(pb)
            return translate(translate(p, v), v);  // return p translated twice by v
        }

        // dot product of 2 vectors
        static double dot(Vec a, Vec b) {
            return (a.x * b.x + a.y * b.y);
        }

        // squared norm of vector
        static double squaredNorm(Vec v) {
            return v.x * v.x + v.y * v.y;
        }

        // norm of vector
        static double norm(Vec v) {
            return Math.sqrt(squaredNorm(v));
        }

        // returns the distance from p to the Line defined by (a, b)
        // two points a and b (a and b must be different)
        // the closest PointDouble is stored in the 4th parameter
        static double distToLine(PointDouble p, PointDouble a, PointDouble b, PointDouble c) {
            // formula: c = a + u * ab
            Vec ap = vector(a, p), ab = vector(a, b);
            double u = dot(ap, ab) / squaredNorm(ab);
            c = translate(a, scale(ab, u));                     // translate a to c
            return dist(p, c);
        }              // Euclidean distance between p and c

        // returns the distance from p to the Line segment ab defined by
        // two points a and b (still OK if a == b)
        // the closest PointDouble is stored in the 4th parameter
        static double distToLineSegment(PointDouble p, PointDouble a, PointDouble b, PointDouble c) {
            Vec ap = vector(a, p), ab = vector(a, b);
            double u = dot(ap, ab) / squaredNorm(ab);
            if (u < 0.0) { c = new PointDouble(a.x, a.y);                  // closer to a
                return dist(p, a); }            // Euclidean distance between p and a
            if (u > 1.0) { c = new PointDouble(b.x, b.y);                  // closer to b
                return dist(p, b); }            // Euclidean distance between p and b
            return distToLine(p, a, b, c); }             // run distToLine as above

        // Returns angle <AOB in radians.
        // Uses angle formula for dot product: a dot b = |a| |b| cos(angle btw a,b)
        static double angle(PointDouble a, PointDouble o, PointDouble b) {
            Vec oa = vector(o, a), ob = vector(o, b);
            return Math.acos(dot(oa, ob) / (norm(oa) * norm(ob)));
        }

        // Cross product of 2 vectors
        static double cross(Vec a, Vec b) {
            return a.x * b.y - a.y * b.x;
        }


        // Returns true if PointDouble r is on the left side of Line (p, q)
        // Note: If points are collinear, this returns false.
        // Use notClockwise(p,q,r) if you want that case to return true.
        static boolean ccw(PointDouble p, PointDouble q, PointDouble r) {
            return cross(vector(p, q), vector(p, r)) > 0;
        }

        // Returns true if PointDouble r is on the right side of Line (p, q)
        // Note: If points are collinear, this returns false.
        // Use notCcw(p,q,r) if you want that case to return true.
        static boolean clockwise(PointDouble p, PointDouble q, PointDouble r) {
            return !notClockwise(p, q, r);
        }

        // Returns true if p, q, r are collinear.
        static boolean collinear(PointDouble p, PointDouble q, PointDouble r) {
            return Math.abs(cross(vector(p, q), vector(p, r))) < EPS;
        }

        static boolean notClockwise(PointDouble p, PointDouble q, PointDouble r) {
            return ccw(p, q, r) || collinear(p, q, r);
        }

        static boolean notCcw(PointDouble p, PointDouble q, PointDouble r) {
            return !ccw(p, q, r);
        }

        // Perimeter of triangle with sides AB, BC, CA.
        static double perimeter(double AB, double BC, double CA) {
            return AB + BC + CA;
        }

        // Perimeter of triangle abc.
        static double perimeter(PointDouble a, PointDouble b, PointDouble c) {
            return dist(a, b) + dist(b, c) + dist(c, a);
        }

        // Area of triangle with sides AB, BC, CA. Uses Heron's formula.
        static double area(double AB, double BC, double CA) {
            double s = 0.5 * perimeter(AB, BC, CA); // semiperimeter
            return Math.sqrt(s) * Math.sqrt(s - AB) * Math.sqrt(s - BC) * Math.sqrt(s - CA);
        }

        // Area of triangle abc.
        static double area(PointDouble a, PointDouble b, PointDouble c) {
            return area(dist(a, b), dist(b, c), dist(c, a));
        }

        static double radiusOfIncircle(double ab, double bc, double ca) {
            return area(ab, bc, ca) / (0.5 * perimeter(ab, bc, ca)); }

        static double radiusOfIncircle(PointDouble a, PointDouble b, PointDouble c) {
            return radiusOfIncircle(dist(a, b), dist(b, c), dist(c, a)); }

        // assumption: the required points/lines functions have been written
        // returns the incircle center if it exists, else returns null
        static PointDouble incircleCenter(PointDouble p1, PointDouble p2, PointDouble p3) {
            // if points are collinear, triangle is degenerate => no incircle center
            if (collinear(p1, p2, p3)) return null;

            // Compute the angle bisectors in l1, l2
            double ratio = dist(p1, p2) / dist(p1, p3);
            PointDouble p = translate(p2, scale(vector(p2, p3), ratio / (1 + ratio)));
            Line l1 = pointsToLine(p1, p);

            ratio = dist(p2, p1) / dist(p2, p3);
            p = translate(p1, scale(vector(p1, p3), ratio / (1 + ratio)));
            Line l2 = pointsToLine(p2, p);

            // Return the intersection of the bisectors
            return intersection(l1, l2);
        }

        // Radius of circumcircle for triangle with sides AB, BC, CA
        static double radiusOfCircumcirle(double AB, double BC, double CA) {
            return AB * BC * CA / (4.0 * area(AB, BC, CA));
        }

        // Radius of circumcircle for triangle abc
        static double radiusOfCircumcirle(PointDouble a, PointDouble b, PointDouble c) {
            return radiusOfCircumcirle(dist(a, b), dist(b, c), dist(c, a));
        }

        // assumption: the required points/lines functions have been written
        // returns r, the radius of the circumCircle if there is a circumCenter center,
        // and set ctr to be the circumCircle center
        // returns 0 otherwise
        static PointDouble circumCircle(PointDouble p1, PointDouble p2, PointDouble p3) {
            // if points are collinear, triangle is degenerate => no circumcircle center
            if (collinear(p1, p2, p3)) return null;

            // computation
            double a = p2.x - p1.x, b = p2.y - p1.y;
            double c = p3.x - p1.x, d = p3.y - p1.y;
            double e = a * (p1.x + p2.x) + b * (p1.y + p2.y);
            double f = c * (p1.x + p3.x) + d * (p1.y + p3.y);
            double g = 2.0 * (a * (p3.y - p2.y) - b * (p3.x - p2.x));

            PointDouble ctr = new PointDouble();
            ctr.x = (d*e - b*f) / g;
            ctr.y = (a*f - c*e) / g;
            return ctr;
        }

        // returns true if PointDouble p is inside the circumcircle defined by a,b,c
        static boolean pointInCircumcircle2(PointDouble a, PointDouble b, PointDouble c, PointDouble p) {
            return ((a.x - p.x) * (b.y - p.y) * ((c.x - p.x) * (c.x - p.x) + (c.y - p.y) * (c.y - p.y)) +
                    (a.y - p.y) * ((b.x - p.x) * (b.x - p.x) + (b.y - p.y) * (b.y - p.y)) * (c.x - p.x) +
                    ((a.x - p.x) * (a.x - p.x) + (a.y - p.y) * (a.y - p.y)) * (b.x - p.x) * (c.y - p.y) -
                    ((a.x - p.x) * (a.x - p.x) + (a.y - p.y) * (a.y - p.y)) * (b.y - p.y) * (c.x - p.x) -
                    (a.y - p.y) * (b.x - p.x) * ((c.x - p.x) * (c.x - p.x) + (c.y - p.y) * (c.y - p.y)) -
                    (a.x - p.x) * ((b.x - p.x) * (b.x - p.x) + (b.y - p.y) * (b.y - p.y)) * (c.y - p.y)) > 0.0;
        }

        // returns true if PointDouble p is inside the circumcircle defined by a,b,c
        // first method works 100% (copied from library), but is more clunky
        // this one is easier to write bugfree but is mine (not tested)
        static boolean pointInCircumcircle(PointDouble a, PointDouble b, PointDouble c, PointDouble p) {
            PointDouble circumcenter = circumCircle(a, b, c);
            return circumcenter != null &&
                    dist(p, circumcenter) < radiusOfCircumcirle(a, b, c);
        }

        // Returns whether lengths a,b,c can form a nondegenerate triangle
        // i.e. satisfy the triangle inequality.
        boolean canFormTriangle(double a, double b, double c) {
            return (a + b > c) && (a + c > b) && (b + c > a);
        }


    }

}
