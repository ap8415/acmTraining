package cf_round_520;

import java.util.Arrays;
import java.util.Scanner;

public class B {

    static boolean[] primes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        setUpPrimes(n+1);
        int leastPrime = 2;
        int factorProd = 1;
        int biggestPower = 0; boolean notAllEqual = false;
        while (n > 1) {
//            System.out.println("PULA " + n + " pulica " + leastPrime);
            if (primes[leastPrime]){
                if (n % leastPrime == 0) {
                    factorProd *= leastPrime; int currentPower = 0;
                    while (n % leastPrime == 0) {
                       n = n / leastPrime;
                       currentPower++;
                    }
                    if (biggestPower != 0) {
                        if (biggestPower != currentPower) {
                            notAllEqual = true;
                            biggestPower = Math.max(currentPower, biggestPower);
                        }
                    } else {
                        biggestPower = currentPower;
                    }
                }
                leastPrime++;
            } else {
                leastPrime++;
            }
        }

        System.out.print(factorProd + " ");
        for (int i = 0; i < 7; i++) {
            if (biggestPower > Math.pow(2, i) && biggestPower <= Math.pow(2, i+1)) {
                if (biggestPower == (int) Math.pow(2, i+1) && !notAllEqual) {
                    System.out.println(i+1); return;
                } else {
                    System.out.println(i + 2); return;
                }
            }
        }
        System.out.println("0");
    }


    static void setUpPrimes(int maxPrime) {
        primes = new boolean[Math.max(10, maxPrime + 1)];
        Arrays.fill(primes, false);

        primes[2] = true; primes[3] = true; primes[5] = true;
        outer:
        for (int i = 7; i <= maxPrime; i+= 2) {
            for (int j = 3; j <= Math.sqrt(i); j++) {
                if (primes[j] && i % j == 0) continue outer;
            }
            primes[i] = true;
        }
    }

}
