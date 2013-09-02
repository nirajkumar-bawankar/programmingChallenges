import java.util.Scanner;

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php
 * ?page=downloadproblem&probid=110101&format=html
 *
 * The following is a solution for the above problem.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sept 2, 2013
 */
public class ThreeNPlusOne {

    public static void main(String[] args) {
	runProblem();
    }

    // Now we're non-static
    public static void runProblem() {
	Scanner in = new Scanner(System.in);

	while (in.hasNextInt()) {
	    runOne(in.nextInt(), in.nextInt());
	}

	in.close();
    }

    public static void runOne(long i_short, long j_short) {
	// ---------------------------Solution--------------------------------
	long maximumCycleLength = 0;
	long from = 0;
	long to = 0;

	if (i_short < j_short) {
	    from = i_short;
	    to = j_short;
	} else {
	    from = j_short;
	    to = i_short;
	}

	for (long k = from; k <= to; k++) {
	    long currentCycleLength = calculateCycleLength(k);
	    if (currentCycleLength > maximumCycleLength) {
		maximumCycleLength = currentCycleLength;
	    }
	}
	//     1         2
	if (j_short < i_short) {
	    from = i_short;
	    to = j_short;
	}

	System.out.println(from + " " + to + " " + maximumCycleLength);
    }

    static long calculateCycleLength(long number) {
	long cycleLength = 1;

	while (number != 1) {
	    cycleLength++;

	    if (number % 2 == 0) {
		number = number / 2;
	    } else {
		number = number * 3 + 1;
	    }
	}
	return cycleLength;
    }
}
