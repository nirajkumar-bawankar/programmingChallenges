#include <iostream>
#include <sstream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;
static vector<int> getAllFlipLocations(
		vector<int> & pancakesRepresentedByDiameter);

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=110402&format=html
 *
 * The following is a solution for the above problem.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Jason Riddle
 */
int main(void) {
	string stackOfPancakes;

	while (getline(cin, stackOfPancakes)) {
		istringstream is(stackOfPancakes);

		vector<int> pancakesRepresentedByDiameter;

		int diameterOfPancake;
		while (is >> diameterOfPancake) {
			pancakesRepresentedByDiameter.push_back(diameterOfPancake);
		}
		reverse(pancakesRepresentedByDiameter.begin(),
				pancakesRepresentedByDiameter.end());

		vector<int> orderOfFlipLocations = getAllFlipLocations(
				pancakesRepresentedByDiameter);

		// first print original stack of pancakes
		cout << stackOfPancakes << endl;

		// now print location within stack to flip pancakes to get a stack
		// of pancakes where the pancake diameters decrease as they move
		// from the bottom to the top
		for (int i = 0; i < orderOfFlipLocations.size(); i++) {
			if (i != 0) {
				cout << ' ';
			}
			cout << orderOfFlipLocations[i];
		}
		cout << endl;
	}
}

/**
 * Return the order of the locations to flip pancakes in the pancake stack.
 */
vector<int> getAllFlipLocations(vector<int> &pancakesRepresentedByDiameter) {
	vector<int> orderOfFlipLocations;

	vector<int>::iterator beginIndex = pancakesRepresentedByDiameter.begin();
	vector<int>::iterator endIndex = pancakesRepresentedByDiameter.end();

	for (int i = 0; i < pancakesRepresentedByDiameter.size(); i++) {
		vector<int>::iterator currentIndex = beginIndex + i;
		vector<int>::iterator maximumIndex = max_element(currentIndex,
				endIndex);

		// iterate through the stack of pancakes
		if (currentIndex != maximumIndex) {

			// true for all elements except the element before the endIndex
			if (maximumIndex + 1 != endIndex) {
				// adds value of (maximumIndex - beginIndex + 1) to the end of the vector
				orderOfFlipLocations.push_back(maximumIndex - beginIndex + 1);

				reverse(maximumIndex, endIndex);
			}
			orderOfFlipLocations.push_back(i + 1);
			reverse(currentIndex, endIndex);
		}
	}
	orderOfFlipLocations.push_back(0); // add 0 to represent flipping the entire stack over
	return orderOfFlipLocations;
}
