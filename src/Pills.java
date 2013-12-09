import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Pills {

    public static void main(String[] args) throws IOException {
	Scanner scanner = new Scanner(System.in);

	int numberOfPills = scanner.nextInt();

	BigInteger[] catB = new BigInteger[34];

	for (int i = 0; i < catB.length; i++) {
	    catB[i] = BigInteger.ZERO;
	}

	int[] cat = new int[34];
	cat = solve(cat);
	catB = solve3(catB);

	while (numberOfPills != 0) {
	    System.out.println(catB[numberOfPills + 1]);

	    numberOfPills = scanner.nextInt();
	}
    }

    public static int[] solve(int[] cat) {
	cat[1] = 1;

	for (int i = 2; i <= 31; i++) {
	    for (int j = 1; j <= i - 1; j++) {
		int mult = cat[j] * cat[i - j];
		cat[i] = cat[i] + mult;
	    }
	}
	return cat;

    }

    public static BigInteger[] solve3(BigInteger[] cat) {
	cat[1] = (BigInteger.valueOf(1));

	for (int i = 2; i <= 31; i++) {
	    for (int j = 1; j <= i - 1; j++) {
		BigInteger mult = cat[j].multiply(cat[i - j]);
		cat[i] = ((cat[i]).add(mult));
	    }
	}
	return cat;

    }

    public static ArrayList solve2(ArrayList<BigInteger> cat2) {
	cat2.add(BigInteger.valueOf(0));
	cat2.add(BigInteger.valueOf(1));

	for (int i = 2; i <= 31; i++) {
	    for (int j = 1; j <= i - 1; j++) {
		BigInteger val = cat2.get(i).add(
			(cat2.get(j).multiply(cat2.get(i - j))));
		cat2.set(i, val);
	    }
	}
	return cat2;
    }
}