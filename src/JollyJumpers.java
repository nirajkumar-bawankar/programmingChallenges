import java.util.Scanner;

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php
 * ?page=downloadproblem&probid=110201&format=html
 *
 * The following is a solution for the above problem.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Aug 28, 2013
 */
public class JollyJumpers {

    public static void main(String[] args) {
	JollyJumpers me = new JollyJumpers();
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

	// store input into an array1
	// make another boolean array2 of size minus one

	// check the difference of each succesive number in array1
	// and make it true in array2 at the correct index

	// for loop through array2 to see if any element is false
	// if any element is false jolly jumper is false
	// otherwise jollyjumper is true
	boolean isJollyJumper = false;




	if (isJollyJumper) {
	    System.out.println("Jolly");
	} else {
	    System.out.println("Note jolly");
	}
    }
}