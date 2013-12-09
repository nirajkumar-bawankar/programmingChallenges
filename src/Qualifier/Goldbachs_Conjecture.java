import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        runProblem();
    }

    public static void runProblem() {


        Scanner in = new Scanner(System.in);

        ArrayList<Integer> nums = new ArrayList<Integer>();

        int problems = in.nextInt();

        while (problems > 0) {
            nums.add(in.nextInt());
            problems--;
        }

        for (Integer num: nums) {
            runOne(num);
        }

        in.close();
    }

    public static void runOne(int number) {

        ArrayList<Integer> printed = new ArrayList<Integer>();

        ArrayList<String> answer = new ArrayList<String>();

        int count = 0;

        for (int i = 0; i < number; i++) {

            if (isPrime(i) && isPrime(number - i) && !printed.contains(number - i)) {
                answer.add("" + i + "+" + (number - i));
                printed.add(i);
                count++;
            }
        }

        System.out.println(number + " has " + count + " representation(s)");

        for (String element: answer) {
            System.out.println(element);
        }

        System.out.println("");

    }

    static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        int q = (int) Math.sqrt(n);

        for (int i = 2; i <= q; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

}
