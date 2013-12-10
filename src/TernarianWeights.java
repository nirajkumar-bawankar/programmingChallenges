import java.util.Scanner;

/**
 * Problem statement can be viewed at:
 * https://icpc-qual-13.contest.scrool.se/problems/ternarianweights
 * 
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Jason Riddle (jr1285@vt.edu)
 *
 * The following is a solution for the above problem.
 */
public class TernarianWeights {
    private static final int NUMBER_OF_POWERS = 21;
    private static long[] powersOfNumbers;

    /**
     * @param args
     */
    public static void main(String[] args) {
	powersOfNumbers = new long[NUMBER_OF_POWERS];

	long currentPower = 1;
	for (int i = 0; i < NUMBER_OF_POWERS; ++i) {
	    powersOfNumbers[i] = currentPower;
	    currentPower *= 3;
	}

	Scanner scanner = new Scanner(System.in);

	long numberOfInputs = scanner.nextInt();
	for (long input = 0; input < numberOfInputs; ++input) {

	    long target = scanner.nextLong();
	    if (target == 0) { // end of input stream
		System.out.println("left pan: ");
		System.out.println("right pan: ");
		if (input != numberOfInputs - 1) {
		    // next input
		    System.out.println();
		}
		continue;
	    }

	    boolean[] leftSide = new boolean[NUMBER_OF_POWERS];

	    long copyOfTarget;
	    for (long i = 0; i < Math.pow(2, NUMBER_OF_POWERS); ++i) {

		copyOfTarget = target;

		for (int j = 0; j < NUMBER_OF_POWERS; ++j) {
		    leftSide[NUMBER_OF_POWERS - 1 - j] = (i & (1 << j)) != 0;

		    if ((i & (1 << j)) != 0) {
			copyOfTarget += powersOfNumbers[NUMBER_OF_POWERS - 1
				- j];
		    }
		}

		String weightsToAddToRight = weightsToAddToRight(copyOfTarget,
			leftSide);

		if (weightsToAddToRight != null) {
		    StringBuilder weightsToAddToLeft = new StringBuilder();
		    for (int theCurrentPower = NUMBER_OF_POWERS - 1; theCurrentPower >= 0; --theCurrentPower) {
			if (leftSide[theCurrentPower]) {
			    weightsToAddToLeft.append(" ");
			    weightsToAddToLeft
				    .append(powersOfNumbers[theCurrentPower]);
			}
		    }

		    System.out.println("left pan:" + weightsToAddToLeft);
		    System.out.println("right pan:" + weightsToAddToRight);

		    if (input != numberOfInputs - 1) {
			// space between inputs
			System.out.println();
		    }
		    break;
		}
	    }
	}
	scanner.close();
    }

    private static String weightsToAddToRight(long leftSideWeight,
	    boolean[] used) {
	boolean[] weightsUsed = new boolean[NUMBER_OF_POWERS];

	StringBuilder weightsToAddToRightSid = new StringBuilder();

	while (leftSideWeight > 0) {
	    int largest = 0;
	    for (int i = 0; i < NUMBER_OF_POWERS; ++i) {

		if (powersOfNumbers[i] > leftSideWeight) {
		    largest = i - 1;
		    break;
		}
	    }

	    if (used[largest] || weightsUsed[largest]) {
		break;
	    }

	    leftSideWeight -= powersOfNumbers[largest];

	    weightsUsed[largest] = true;
	    weightsToAddToRightSid.append(" ");
	    weightsToAddToRightSid.append(powersOfNumbers[largest]);
	}

	if (leftSideWeight != 0) {
	    return null;
	}

	return weightsToAddToRightSid.toString();
    }
}
