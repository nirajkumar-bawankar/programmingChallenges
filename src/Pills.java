import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Problem statement can be viewed at:
 * https://theta.cs.vt.edu/acm/shared/fall2013
 * /handouts-week9/rockymountain2012problems/pills.pdf
 *
 * The following is a solution for the above problem.
 */
public class Pills {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);

	int numberOfPills = scanner.nextInt();

	BigInteger[] concatinatedValidStrings = new BigInteger[34];

	for (int i = 0; i < concatinatedValidStrings.length; i++) {
	    concatinatedValidStrings[i] = BigInteger.ZERO;
	}

	int[] concatinatedValidOptions = new int[34];
	concatinatedValidOptions = getDifferentValidStrings1(concatinatedValidOptions);
	concatinatedValidStrings = getDifferentValidStrings2(concatinatedValidStrings);

	while (numberOfPills != 0) {
	    System.out.println(concatinatedValidStrings[numberOfPills + 1]);

	    numberOfPills = scanner.nextInt();
	}
    }

    /**
     * @param concatinatedString
     * @return
     */
    public static int[] getDifferentValidStrings1(int[] concatinatedString) {
	concatinatedString[1] = 1;

	for (int i = 2; i <= 31; i++) {
	    for (int j = 1; j <= (i - 1); j++) {
		int product = concatinatedString[j] * concatinatedString[i - j];
		concatinatedString[i] = concatinatedString[i] + product;
	    }
	}
	return concatinatedString;
    }

    /**
     * @param concatinatedString
     * @return
     */
    public static BigInteger[] getDifferentValidStrings2(
	    BigInteger[] concatinatedString) {
	concatinatedString[1] = (BigInteger.valueOf(1));

	for (int i = 2; i <= 31; i++) {
	    for (int j = 1; j <= i - 1; j++) {
		BigInteger product = concatinatedString[j]
			.multiply(concatinatedString[i - j]);
		concatinatedString[i] = ((concatinatedString[i]).add(product));
	    }
	}
	return concatinatedString;
    }
}