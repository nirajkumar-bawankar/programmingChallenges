package problems;

import java.util.List;
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
 * @version Sept 4, 2013
 */
public class JollyJumpers {
    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	while (scanner.hasNextLine()) {
	    StringTokenizer stringTokenizer = new StringTokenizer(
		    scanner.nextLine());
	    while (stringTokenizer.hasMoreTokens()) {
		int nextInt = Integer.parseInt(stringTokenizer.nextToken());
		numbers.add(nextInt);
	    }
	    isNumbersJolly(numbers);
	}
    }

    public static void isNumbersJolly(List<Integer> numbers) {
	boolean[] differences = new boolean[numbers.size() - 1];

	for (int i = 0; i < numbers.size() - 1; i++) {
	    int difference = Math.abs(numbers.get(i) - numbers.get(i + 1));
	    if (difference != 0 && difference < differences.length) {
		differences[difference] = true;
	    }
	}

	boolean isJolly = true;

	for (int i = 1; i < differences.length; i++) {
	    if (differences[i] == false) {
		isJolly = false;
	    }
	}

	if (isJolly) {
	    System.out.println("Jolly");
	} else {
	    System.out.println("Not jolly");
	}
	numbers.removeAll(numbers);
    }
}