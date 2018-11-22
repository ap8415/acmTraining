/*
 * ACM ICPC - CERC 2011
 *
 * Sample solution: Stack Machine Programmer (program)
 * Author: Martin Kacer
 * 
 * Idea: generate a polynomial that gives the given results.
 * Coefficients are computer from determinant by the Cramer's rule.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class program {
	StringTokenizer st = new StringTokenizer("");
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		program instance = new program();
		while (instance.run()) {/*repeat*/}
	}
	String nextToken() throws Exception {
		while (!st.hasMoreTokens()) st = new StringTokenizer(input.readLine());
		return st.nextToken();
	}
	int nextInt() throws Exception {
		return Integer.parseInt(nextToken());
	}
	
	static final int MAXINP = 5;

	int[][] detcnt = new int[2][MAXINP+1];
	int[][][][] detq = new int[2][MAXINP+1][60][MAXINP];
	{
		for (int i = 0; i <= MAXINP; ++i) {
			detcnt[0][i] = detcnt[1][i] = 0;
			int[] pool = new int[i];
			for (int j = 0; j < i; ++j) pool[j] = j;
			shuffle(0, pool, 0);
		}
	}
	void swap(int[] array, int i1, int i2) {
		int x = array[i1]; array[i1] = array[i2]; array[i2] = x;
	}
	void shuffle(int idx, int[] pool, int sgn) {
		if (idx == pool.length) {
			int cnt = detcnt[sgn][idx]++;
			for (int i = 0; i < idx; ++i)
				detq[sgn][idx][cnt][i] = pool[i];
			return;
		}
		shuffle(idx+1, pool, sgn);
		for (int i = idx+1; i < pool.length; ++i) {
			swap(pool, idx, i);
			shuffle(idx+1, pool, 1-sgn);
			swap(pool, idx, i);
		}
	}

	int determinant(int[][] matrix, int size) {
		int sum = 0;
		if (size == 0) return 0;
		for (int sgn = 0; sgn <= 1; ++sgn)
		for (int i = 0; i < detcnt[sgn][size]; ++i) {
			int x = (sgn == 0) ? 1 : -1;
			for (int j = 0; j < size; ++j) x *= matrix[j][ detq[sgn][size][i][j] ];
			sum += x;
		}
		return sum;
	}
	int pow(int x, int p) {
		int res = 1;
		while (p-->0) res *= x;
		return res;
	}
	
	int[] xinput = new int[MAXINP], xoutput = new int[MAXINP];
	int mat[][] = new int[MAXINP][MAXINP];
	
	void pushnum(int x) {
		if (x < 0) {
			System.out.println("NUM " + (-x));
			System.out.println("INV");
		} else {
			System.out.println("NUM " + x);
		}
	}

	boolean run() throws Exception {
		int size = nextInt();
		if (size == 0) return false;
		for (int i = 0; i < size; ++i) {
			xinput[i] = nextInt() - 5; xoutput[i] = nextInt();
		}
		int[] det = new int[size+1];
		for (int x = 0; x <= size; ++x) {
			for (int i = 0; i < size; ++i)
				for (int j = 0; j < size; ++j) {
					mat[i][j] = (j == x) ? xoutput[i] : pow(xinput[i], j);
				}
			det[x] = determinant(mat, size);
		}
		if (det[size] == 0) throw new IllegalStateException("zero determinant?");
		System.out.println("NUM 5");
		System.out.println("SUB");
		pushnum(det[0]);
		System.out.println("SWP");
		int nonzero = 1;
		for (int x = 1; x < size; ++x) {
			if (det[x] == 0) continue;
			for (int i = 0; i < x; ++i) System.out.println("DUP");
			for (int i = 1; i < x; ++i) System.out.println("MUL");
			pushnum(det[x]);
			System.out.println("MUL");
			System.out.println("SWP");
			++nonzero;
		}
		System.out.println("POP");
		for (int i = 1; i < nonzero; ++i) System.out.println("ADD");
		pushnum(det[size]);
		System.out.println("DIV");
		System.out.println("END");
		System.out.println();
		return true;
	}
}
