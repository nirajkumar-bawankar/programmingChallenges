import java.util.Scanner;
import java.io.*;
import java.util.*;

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
    static BufferedReader bufferedReader = new BufferedReader(
	    new InputStreamReader(System.in));
    static Scanner scanner = new Scanner(bufferedReader);

    public static void main(String[] args) {
	for (int numberOfTestCases = scanner.nextInt(); numberOfTestCases-- > 0;) {

	    int weightOfObjectOnLeftScale = scanner.nextInt();

	    ArrayList<Integer> weightsToAddToLeftScale = new ArrayList<Integer>(), weightsToAddToRightScale = new ArrayList<Integer>();

	    for (int p = 1; weightOfObjectOnLeftScale > 0; weightOfObjectOnLeftScale /= 3, p *= 3) {
		switch (weightOfObjectOnLeftScale % 3) {

		case 1:
		    weightsToAddToRightScale.add(p);
		    break;
		case 2:
		    weightsToAddToLeftScale.add(p);
		    weightOfObjectOnLeftScale++;
		    break;
		default:
		    break;

		}
	    }

	    printOutToConsoleWeightsToAdd("left", weightsToAddToLeftScale);
	    printOutToConsoleWeightsToAdd("right", weightsToAddToRightScale);

	    if (numberOfTestCases > 0) {
		System.out.println();
	    }
	}
    }

    static void printOutToConsoleWeightsToAdd(String leftOrRight,
	    ArrayList<Integer> weightsToAddToSide) {
	Collections.reverse(weightsToAddToSide);

	System.out.printf("%s pan:", leftOrRight);

	for (int currentWeight : weightsToAddToSide) {
	    System.out.print(" " + currentWeight);
	}

	System.out.println();
    }
}
