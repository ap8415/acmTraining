package CodeforcesContests.EducationalCodeforcesRounds.Educational_CF_54;

import java.util.*;
import java.lang.*;

public class B {

   static boolean[] primes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        setUpPrimes((int) (Math.sqrt(n)));

        long sub = 0; int biggestPrime = 0;
        while (n > 0) {
            for (int i = 2; i <= Math.max(Math.sqrt(n+1), biggestPrime); i++) {
                if (primes[i] && n % i == 0) {
                    biggestPrime = i;
                    break;
                }
            }

            if (biggestPrime == 0) {
                sub = 1; n = 0;
            } else if (biggestPrime == 2) {
                sub += n / 2; n = 0;
            } else {
                sub++; n = n - biggestPrime;
            }
        }


        System.out.println(sub);
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
