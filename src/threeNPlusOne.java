import java.util.Scanner;

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php
 * ?page=downloadproblem&probid=110101&format=html
 *
 * The following is a solution for the above problem.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Aug 28, 2013
 */
public class threeNPlusOne {

    public static void main(String[] args) {
	threeNPlusOne me = new threeNPlusOne();
	me.runProblem();

    }

    // Now we're non-static
    public void runProblem() {
	Scanner in = new Scanner(System.in);

	while (in.hasNextInt()) {
	    runOne(in.nextInt(), in.nextInt());
	}

	in.close();
    }

    public void runOne(int i, int j) {
	// ---------------------------Solution--------------------------------
	int maximumCycleLength = 0;
	for (int k = i; k <= j; k++) {
	    int currentCycleLength = 0;
	    int currentNumber = k;
	    while (currentNumber != 1 && currentNumber > 0 && currentNumber < 1000000) {
		if (currentNumber % 2 == 0) {
		    currentNumber = currentNumber / 2;
		    currentCycleLength++;
		} else {
		    currentNumber = currentNumber * 3 + 1;
		    currentCycleLength++;
		}
	    }
	    if (currentCycleLength > maximumCycleLength) {
		maximumCycleLength = currentCycleLength;
	    }
	    currentCycleLength = 0;
	}
	maximumCycleLength += 1;
	System.out.println(i + " " + j + " " + maximumCycleLength);
    }
}
