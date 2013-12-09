import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class GoldbachConjecture {
        public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                int num_inputs = sc.nextInt();
                for (int input = 0; input < num_inputs; ++input) {
                        int target = sc.nextInt();
                        List<Integer> primes = getListOfPrimes(target);
                        StringBuilder out = new StringBuilder();
                        int found = 0;
                        for (int prime : primes) {
                                if (prime > target / 2) {
                                        break;
                                }
                                if (primes.contains(target - prime)) {
                                        out.append(prime + "+" + (target - prime) + "\n");
                                        ++found;
                                }
                        }
                        System.out.println(target + " has " + found + " representation(s)");
                        System.out.print(out);
                        if (input != num_inputs - 1) {
                                System.out.println();
                        }
                }
                sc.close();
        }

        public static boolean isPrime(long n) {
                if (n < 2)
                        return false;
                if (n == 2 || n == 3)
                        return true;
                if (n % 2 == 0 || n % 3 == 0)
                        return false;
                long sqrtN = (long) Math.sqrt(n) + 1;
                for (long i = 6L; i <= sqrtN; i += 6) {
                        if (n % (i - 1) == 0 || n % (i + 1) == 0)
                                return false;
                }
                return true;
        }

        public static List<Integer> getListOfPrimes(int max) {
                BitSet sieve = new BitSet((max + 2) / 2);
                for (int i = 3; i * i <= max; i += 2) {
                        if (sieve.get((i - 3) / 2))
                                continue;

                        // We increment by 2*i to skip even multiples of i
                        for (int multiple_i = i * i; multiple_i <= max; multiple_i += 2 * i)
                                sieve.set((multiple_i - 3) / 2);
                }

                List<Integer> primes = new ArrayList<Integer>();
                primes.add(2);
                for (int i = 3; i <= max; i += 2)
                        if (!sieve.get((i - 3) / 2))
                                primes.add(i);
                return primes;
        }
}