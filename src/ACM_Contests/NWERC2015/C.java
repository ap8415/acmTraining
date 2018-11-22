package ACM_Contests.NWERC2015;

import java.util.*;
import java.lang.*;
import java.awt.geom.*;

public class C {

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner s = new Scanner(System.in);
        int w = s.nextInt();
        int p = s.nextInt();

        int[][] wells = new int[w][2];
        for (int i = 0; i < w; i++) {
            wells[i][0] = s.nextInt(); wells[i][1] = s.nextInt();
        }

        Set<Pipe> pipes = new HashSet<>();
        for (int i = 0; i < p; i++) {
            int alpha = s.nextInt();
            pipes.add(new Pipe(wells[alpha - 1][0], wells[alpha - 1][1], s.nextInt(), s.nextInt(), i));
        }

        for (Pipe p1: pipes) {
            for (Pipe p2: pipes) {
                if (p1 != p2) {
                    if (intersects(p1, p2)) {
                        p1.badPipes.add(p2); p2.badPipes.add(p1);
                    }
                }
            }
        }

        Pipe x = null;
        Iterator<Pipe> it = pipes.iterator();
        while (it.hasNext()) {
            x = it.next();
            if (x.good == null) {
                if (x.badPipes.isEmpty()) {
                    x.good = true;
                }
                else {
                    try {
                        recurse(x, true);
                    } catch (IllegalArgumentException e) {
                        try {
                            recurse (x, false);
                        } catch (IllegalArgumentException e1) {
                            System.out.println("impossible");
                            return;
                        }
                    }
                }
            }
        }

        System.out.println("possible");

    }

    static boolean intersects(Pipe p1, Pipe p2) {
        Line2D line1 = new Line2D.Float(p1.x1, p1.y1, p1.x2, p1.y2);
        Line2D line2 = new Line2D.Float(p2.x1, p2.y1, p2.x2, p2.y2);
        return line2.intersectsLine(line1) && (p1.x1 != p2.x1 || p1.y1 != p2.y1);
    }

    static void recurse(Pipe x, boolean p) {
        if (x.good == null) {
            x.good = p;
        } else if (x.good != p) {
            throw new IllegalArgumentException("am belit pula");
        } else {
            return;
        }
        for (Pipe pip: x.badPipes) {
            recurse(pip, !p);
        }
    }



    static class Pipe {
        int index;

        Boolean good = null;

        int x1, y1, x2, y2;

        Pipe(int x1, int y1, int x2, int y2, int index) {
            this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2; this.index = index;
        }

        List<Pipe> badPipes = new LinkedList<>();

        public boolean equals(Object obj)
        {
            return (this == obj);
        }

        public int hashCode() {
            return 31*(x1 + 31 *(x2 + 31 *(y1 + 31 * y2)));
        }

    }
}