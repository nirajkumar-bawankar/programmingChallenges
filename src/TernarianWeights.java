import java.util.Scanner;

public class TernarianWeights {
    private static long[] powers;
    private static final int NUM_POWERS = 21;

    public static void main(String[] args) {
	powers = new long[NUM_POWERS];
	long power = 1;
	for (int i = 0; i < NUM_POWERS; ++i) {
	    powers[i] = power;
	    power *= 3;
	}
	Scanner sc = new Scanner(System.in);
	long num_inputs = sc.nextInt();
	for (long input = 0; input < num_inputs; ++input) {
	    long target = sc.nextLong();
	    if (target == 0) {
		System.out.println("left pan: ");
		System.out.println("right pan: ");
		if (input != num_inputs - 1) {
		    System.out.println();
		}
		continue;
	    }
	    boolean[] leftSide = new boolean[NUM_POWERS];
	    long target_copy;
	    for (long i = 0; i < Math.pow(2, NUM_POWERS); ++i) {
		target_copy = target;
		for (int j = 0; j < NUM_POWERS; ++j) {
		    leftSide[NUM_POWERS - 1 - j] = (i & (1 << j)) != 0;
		    if ((i & (1 << j)) != 0) {
			target_copy += powers[NUM_POWERS - 1 - j];
		    }
		}
		String right = rightSideWeights(target_copy, leftSide);
		if (right != null) {
		    StringBuilder left = new StringBuilder();
		    for (int sad = NUM_POWERS - 1; sad >= 0; --sad) {
			if (leftSide[sad]) {
			    left.append(" ");
			    left.append(powers[sad]);
			}
		    }
		    System.out.println("left pan:" + left);
		    System.out.println("right pan:" + right);
		    if (input != num_inputs - 1) {
			System.out.println();
		    }
		    break;
		}
	    }
	}
	sc.close();
    }

    private static String rightSideWeights(long leftSideWeight, boolean[] used) {
	boolean[] myused = new boolean[NUM_POWERS];
	StringBuilder out = new StringBuilder();
	while (leftSideWeight > 0) {
	    int largest = 0;
	    for (int i = 0; i < NUM_POWERS; ++i) {
		if (powers[i] > leftSideWeight) {
		    largest = i - 1;
		    break;
		}
	    }
	    if (used[largest] || myused[largest]) {
		break;
	    }
	    leftSideWeight -= powers[largest];
	    myused[largest] = true;
	    out.append(" ");
	    out.append(powers[largest]);
	}
	if (leftSideWeight != 0) {
	    return null;
	}
	return out.toString();
    }
}
