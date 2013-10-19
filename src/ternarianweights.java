import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 5, 2013
 */
public class ternarianweights {
    /**
     * Represents all needed ternarian weights to compute the weight of an
     * object of weight between 0 and 10^9. Create an array of size 20 where the
     * index of the array holds the power of 3 to that index for example, index
     * at 5 holds the value of 3^5.
     */
    private static long[] powersOf3AtIndex = new long[42];

    static long leftScaleWeight = 0;
    static long rightScaleWeight = 0;

    private static ArrayList<Long> weightsAddedToLeftSide = new ArrayList<Long>();
    private static ArrayList<Long> weightsAddedToRightSide = new ArrayList<Long>();

    private static boolean isNotFirstLine = false;
    private static boolean isNotFirstNumber = false;

    private static long numberOfExecutions = 0;

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);

	constructPowerOf3Array();

	while (scanner.hasNextLine()) {
	    StringTokenizer stringTokenizer = new StringTokenizer(
		    scanner.nextLine());

	    while (stringTokenizer.hasMoreTokens()) {
		long nextInteger = Long.parseLong(stringTokenizer.nextToken());

		if (isNotFirstLine) {
		    if (numberOfExecutions > 0) {
			solveForOneObjectWeight(nextInteger);
			numberOfExecutions--;
		    }
		} else {
		    isNotFirstLine = true;
		    numberOfExecutions = nextInteger;
		}
	    }
	}
    }

    public static void constructPowerOf3Array() {
	for (int i = 0; i < powersOf3AtIndex.length; i++) {
	    powersOf3AtIndex[i] = (long) Math.pow(3, i);
	    // System.out.println(powersOf3AtIndex[i]);
	}
    }

    /**
     * The highest power of 3 needed is 19 since 3^19 > 10^9(the maximum weight
     * of the object)
     *
     * @param objectWeight
     *            ranges from 0 to 10^9
     */
    public static void solveForOneObjectWeight(long objectWeight) {
	leftScaleWeight = objectWeight;
	long closestWeightToObject = get3ToThePowerOf(findIndexOfClosestPowerOf3To(objectWeight));
	rightScaleWeight = closestWeightToObject;
	weightsAddedToRightSide.add(closestWeightToObject);

	recursiveWeightAdd();

	printOutScaleInfo();

	weightsAddedToLeftSide.clear();
	weightsAddedToRightSide.clear();
    }

    /**
     * Print out each weight that was added to each side.
     */
    public static void printOutScaleInfo() {
	if (isNotFirstNumber) {
	    System.out.print("\nleft pan:");
	} else { // it is the first line
	    System.out.print("left pan:");
	    isNotFirstNumber = true;
	}
	for (Long weight : weightsAddedToLeftSide) {
	    System.out.print(" " + weight);
	}

	System.out.print("\nright pan:");
	for (Long weight : weightsAddedToRightSide) {
	    System.out.print(" " + weight);
	}
    }

    /**
     * Now recursively add smaller and smaller weights to balance the scale.
     */
    public static void recursiveWeightAdd() {
	if (leftScaleWeight == rightScaleWeight) {
	    long left = leftScaleWeight;
	    long right = rightScaleWeight;
	    // done
	    return;
	} else if (leftScaleWeight > rightScaleWeight) {
	    long left = leftScaleWeight;
	    long right = rightScaleWeight;
	    // add weight to right side to make right side as close as possible
	    // to left side
	    long difference = Math.abs(leftScaleWeight - rightScaleWeight);
	    long closestWeight = get3ToThePowerOf(findIndexOfClosestPowerOf3To(difference));
	    rightScaleWeight += closestWeight;
	    weightsAddedToRightSide.add(closestWeight);
	    recursiveWeightAdd();
	} else {
	    long left = leftScaleWeight;
	    long right = rightScaleWeight;
	    // leftScaleWeight < rightScaleWeight
	    // add weight to left side to make left side as close as
	    // possible to right side
	    long difference = Math.abs(leftScaleWeight - rightScaleWeight);
	    long closestWeight = get3ToThePowerOf(findIndexOfClosestPowerOf3To(difference));
	    leftScaleWeight += closestWeight;
	    weightsAddedToLeftSide.add(closestWeight);
	    recursiveWeightAdd();
	}
    }

    public static long get3ToThePowerOf(int indexOfClosestPowerOf3) {
	return powersOf3AtIndex[indexOfClosestPowerOf3];
    }

    /**
     * Value at index of closest power of 3 may be smaller or larger than given
     * weight.
     */
    public static int findIndexOfClosestPowerOf3To(long weight) {
	int indexOfSmallestDifference = -1;
	long smallestDifference = 1000000000;
	for (int i = 0; i < powersOf3AtIndex.length; i++) {
	    long difference = Math.abs(powersOf3AtIndex[i] - weight);
	    if (difference <= smallestDifference) {
		smallestDifference = difference;
		indexOfSmallestDifference = i;
	    }
	}
	return indexOfSmallestDifference;
    }
}
