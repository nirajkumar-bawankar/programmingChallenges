import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php
 * ?page=downloadproblem&probid=110201&format=html
 *
 * The following is a solution for the above problem.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sept 2, 2013
 */
public class Main {

    public static void main(String[] args) {
	Main me = new Main();
	me.runProblem();

    }

    // Now we're non-static
    public void runProblem() {
	Scanner in = new Scanner(System.in);
	StringTokenizer tokenizer;
	int numberOfItems;

	while (in.hasNextInt()) {
	    tokenizer = new StringTokenizer(in.nextLine());
	    numberOfItems = Integer.parseInt(tokenizer.nextToken());

//	    int[] numbers = new int[numberOfItems];
//
//	    for (int i = 0; i < numberOfItems; i++) {
//		numbers[i] = Integer.parseInt(tokenizer.nextToken());
//	    }

	    int[] numbers = {1, 4, 2, 1};

	    runOnce(numbers);
	}
    }

    public void runOnce(int[] numbers) {
	// ---------------------------Solution--------------------------------

	// store input into an array1
	// make another boolean array2 of size minus one

	// check the difference of each succesive number in array1
	// and make it true in array2 at the correct index

	// for loop through array2 to see if any element is false
	// if any element is false jolly jumper is false
	// otherwise jollyjumper is true
	boolean isJollyJumper = false;

	ArrayList differences = new ArrayList();

	for (int i = 0; i < numbers.length - 1; i++) {
	    int difference = Math.abs(numbers[i] - numbers[i+1]);

	    if (difference == 0) {
		isJollyJumper = false;
	    } else if (difference >= numbers.length) {
		isJollyJumper = false;
	    } else if (differences.contains(difference)) {
		isJollyJumper = false;
	    }
	    differences.add(difference);
	}

	if (isJollyJumper) {
	    System.out.println("Jolly");
	} else {
	    System.out.println("Not jolly");
	}
    }
}