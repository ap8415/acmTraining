package ACMCodeLibrary;

public class NextPermutation {

    // modifies c to next permutation or returns null if such permutation does not exist
    static Comparable[] nextPermutation(final Comparable[] c) {
        // 1. finds the largest k, that c[k] < c[k+1]
        int first = getFirst(c);
        if (first == -1) return null; // no greater permutation
        // 2. find last index toSwap, that c[k] < c[toSwap]
        int toSwap = c.length - 1;
        while (c[first].compareTo(c[toSwap]) >= 0) {
            --toSwap;
        }
        // 3. swap elements with indexes first and last
        swap(c, first++, toSwap);
        // 4. reverse sequence from k+1 to n (inclusive)
        toSwap = c.length - 1;
        while (first < toSwap)
            swap(c, first++, toSwap--);
        return c;
    }

    // finds the largest k, that c[k] < c[k+1]
    // if no such k exists (there is not greater permutation), return -1
    static int getFirst(final Comparable[] c) {
        for (int i = c.length - 2; i >= 0; --i)
            if (c[i].compareTo(c[i+1]) < 0)
                return i;
        return -1;
    }

    // swaps two elements (with indexes i and j) in array
    static void swap(final Comparable[] c, int i, int j) {
        final Comparable tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }

    static char[] toPrimitive(Character[] arr) {
        char[] primitive = new char[arr.length];
        for (int i = 0; i < arr.length; i++) primitive[i] = arr[i];
        return primitive;
    }

    static Character[] toClass(char[] arr) {
        Character[] classArr = new Character[arr.length];
        for (int i = 0; i < arr.length; i++) classArr[i] = arr[i];
        return classArr;
    }

}
