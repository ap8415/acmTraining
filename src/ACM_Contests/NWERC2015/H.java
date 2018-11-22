package ACM_Contests.NWERC2015;

import java.util.*;

public class H {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = scan.nextInt(); int y = scan.nextInt();

        Set<Wall> wallSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            wallSet.add(new Wall(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }

        solve(0,0,x,y, wallSet, null);
    }

    private static int solve(int Ox, int Oy, int x, int y, Set<Wall> wallSet, Angle angle) {
        List<Integer> results = new LinkedList<>();

        // For each wall, we check whether we can hit it. If we can, recurse.
        Iterator<Wall> it = wallSet.iterator();
        while (it.hasNext()) {
            Wall curr = it.next();

            Set<Wall> remainingWalls = new HashSet<>();
            remainingWalls.addAll(wallSet);
            remainingWalls.remove(curr);

            // Check if we can hit wall CURR directly.
            // If angle is NULL, then angle is not yet set, and we get to try and choose it.
            if (angle == null) {
                // determine possible angles. Must be integers.
                // Also means Ox = 0, Oy = 0.
            }

        }


        return results.stream().max(Integer::compareTo).get();
    }

    static class Angle {
        int xAngle, yAngle;

        public Angle(int xAngle, int yAngle) {
            this.xAngle = xAngle;
            this.yAngle = yAngle;
        }
    }

    static class Wall {
        int x1, y1, x2, y2;

        public Wall(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Wall wall = (Wall) o;

            if (x1 != wall.x1) return false;
            if (y1 != wall.y1) return false;
            if (x2 != wall.x2) return false;
            return y2 == wall.y2;
        }

        @Override
        public int hashCode() {
            int result = x1;
            result = 31 * result + y1;
            result = 31 * result + x2;
            result = 31 * result + y2;
            return result;
        }
    }

}
