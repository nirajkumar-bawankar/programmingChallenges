import java.util.StringTokenizer;
import java.util.Scanner;

public class HappyCamper {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);

	boolean isNotFirstLine = false;

	while (scanner.hasNextLine()) {
	    StringTokenizer stringTokenizer = new StringTokenizer(
		    scanner.nextLine());

	    while (stringTokenizer.hasMoreTokens()) {
		int nextInteger = Integer.parseInt(stringTokenizer.nextToken());

		if (isNotFirstLine) {
		    printOddOrEven(nextInteger);
		}
		isNotFirstLine = true;
	    }
	}
    }

    public static void printOddOrEven(int integer) {
	if (integer == 0) {
	    System.out.println(integer + " is even");
	} else {
	    int positiveInteger = Math.abs(integer);
	    if (isEven(positiveInteger)) {
		System.out.println(integer + " is even");
	    } else {
		System.out.println(integer + " is odd");
	    }
	}
    }

    public static boolean isEven(int integer) {
	if (integer % 2 == 0) {
	    return true;
	} else {
	    return false;
	}
    }
}
