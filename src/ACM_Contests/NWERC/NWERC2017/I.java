package ACM_Contests.NWERC.NWERC2017;

import java.util.*;
import java.lang.*;

public class I {

    static class App {
        int s, d;
        boolean used = false;
        int index;

        public App(int s, int d, int index) {
            this.s = s;
            this.d = d;
            this.index = index;
        }

        @Override
        public String toString() {
            return "App{" +
                    "s=" + s +
                    ", d=" + d +
                    '}';
        }
    }

    static Comparator<App> byStorageSize = new Comparator<App>() {
        @Override
        public int compare(App o1, App o2) {
            return o1.s - o2.s;
        }
    };

    static Comparator<App> byInstallSize = new Comparator<App>() {
        @Override
        public int compare(App o1, App o2) {
            return -(Math.max(o1.s, o1.d) - Math.max(o2.s, o2.d));
        }
    };

    static App[] byStorage;
    static App[] byInstall;
    static int n, totalSpace;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); totalSpace = sc.nextInt();
        App[] apps = new App[n];
        for (int i = 0; i < n; i++) {
            int d = sc.nextInt(); int s = sc.nextInt();
            apps[i] = new App(s, d, i);
        }

        byStorage = Arrays.copyOf(apps, n);
        byInstall = Arrays.copyOf(apps, n);

        Arrays.sort(byStorage, byStorageSize);
        Arrays.sort(byInstall, byInstallSize);

        System.out.println(Arrays.toString(byStorage));
        System.out.println(Arrays.toString(byInstall));

        solve(new BitSet(n), totalSpace, 0);
        BitSet optimal = new BitSet(n);
        for (BitSet candidate: best) {
//            System.out.println(candidate);
            if (candidate.cardinality() > optimal.cardinality()) optimal = candidate;
        }

        List<App> bestApps = new ArrayList<>();
        int nextBit = optimal.nextSetBit(0);
        while (nextBit != -1) {
            bestApps.add(apps[nextBit]);
            nextBit = optimal.nextSetBit(nextBit + 1);
        }
        Collections.sort(bestApps, byInstallSize);

        System.out.println(optimal.cardinality());
        for(App a : bestApps) System.out.print((1 + a.index) + " ");
        System.out.println();
    }

    static Set<BitSet> best = new HashSet<>();

    private static void solve(BitSet sol, int spaceLeft, int storageIndex) {
        System.out.println(spaceLeft + "  " + storageIndex);
        if (storageIndex >= n || spaceLeft < byStorage[storageIndex].s) {
            System.out.println("here");
            best.add(sol);
            return;
        }

        int spaceLeftNext = spaceLeft - byStorage[storageIndex].s;
        if (spaceLeft >= byStorage[storageIndex].d
                && !sol.get(byStorage[storageIndex].index)) {
            System.out.println("should get here " + storageIndex);
            BitSet solcp = new BitSet(n);
            solcp.or(sol); solcp.set(byStorage[storageIndex].index);
            solve(solcp, spaceLeftNext, storageIndex + 1);
        }

        int leftIndex = Arrays.binarySearch(byInstall, new App(0, spaceLeft, -1), byInstallSize);
        if (leftIndex < 0) {
            leftIndex = -(leftIndex + 1);
            // byInstall[left index] is < spaceLeft+1 =<=> <= spaceLeft
        }

        int rightIndex = Arrays.binarySearch(byInstall, new App(0, spaceLeftNext, -1), byInstallSize);

        if (rightIndex < 0) {
            rightIndex = -(rightIndex + 1);
            rightIndex--;
        } else {
            rightIndex--;
        }

//        System.out.println(spaceLeft + "  " + storageIndex + " " + sol);
//        System.out.println(String.format("Indices: %d %d", leftIndex, rightIndex));

        int best = -1; int min_s = 50000;
        for (int i = leftIndex; i <= rightIndex; i++) {
            if (byInstall[i].s < min_s) {
                min_s = byInstall[i].s;
                best = i;
            }
        }

//        System.out.println(best);

        if (best != -1 && !sol.get(best)) {
            BitSet solcp = new BitSet(n);
            solcp.or(sol);
            solcp.set(byInstall[best].index);
            solve(solcp, spaceLeftNext, storageIndex);
        }
    }
}
