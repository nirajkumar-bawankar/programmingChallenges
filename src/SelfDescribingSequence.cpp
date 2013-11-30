#include <vector>
#include <iostream>

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=110607&format=html
 *
 * The following is a solution for the above problem.
 *
 * To learn more about the Golomb sequence visit:
 * http://en.wikipedia.org/wiki/Golomb_sequence
 */
using namespace std;

vector<int> GolombSequence;

vector<int> sumOfNumbersWithinGolombSequence;

int indexOfElementInGolombSequence;

void constructInitialGolombSequence() {
	GolombSequence.reserve(100000);

	// initial Golomb sequence numbers
	GolombSequence.push_back(0);
	GolombSequence.push_back(1);
	GolombSequence.push_back(2);
	GolombSequence.push_back(2);

	sumOfNumbersWithinGolombSequence.push_back(0);
	sumOfNumbersWithinGolombSequence.push_back(1);
	sumOfNumbersWithinGolombSequence.push_back(3);
	sumOfNumbersWithinGolombSequence.push_back(5);

	indexOfElementInGolombSequence = 2;
}

int computeNthGolumbNumber(int nthIndexWithinGolumbSequence) {
	int nthGolombNumber;
	if (GolombSequence.size() > nthIndexWithinGolumbSequence) {
		nthGolombNumber = GolombSequence[nthIndexWithinGolumbSequence];
	} else {
		// compute the sum up to the input index n
		while (sumOfNumbersWithinGolombSequence.back()
				< nthIndexWithinGolumbSequence) {

			int numberOfElementsToSum =
					GolombSequence[++indexOfElementInGolombSequence];

			int sumUpToNthIndexWithinGolumbSequence =
					sumOfNumbersWithinGolombSequence.back();

			for (int i = 0; i < numberOfElementsToSum; i++) {
				GolombSequence.push_back(indexOfElementInGolombSequence);
				// update the current sum of elements
				sumUpToNthIndexWithinGolumbSequence +=
						indexOfElementInGolombSequence;
				// update the sum vector
				sumOfNumbersWithinGolombSequence.push_back(
						sumUpToNthIndexWithinGolumbSequence);
			}
		}

		for (nthGolombNumber = sumOfNumbersWithinGolombSequence.size() - 1;
				nthGolombNumber >= 0; nthGolombNumber--) {

			if (nthIndexWithinGolumbSequence
					> sumOfNumbersWithinGolombSequence[nthGolombNumber - 1]
					&& nthIndexWithinGolumbSequence
							<= sumOfNumbersWithinGolombSequence[nthGolombNumber]) {
				break;
			}
		}
	}
	return nthGolombNumber;
}

int main() {
	constructInitialGolombSequence();

	// read in input n to calculate the value of f(n) where f(n) is a
	// function that generates the nth Golomb number given the index n
	int indexWithinGolombSequence;

	while ((cin >> indexWithinGolombSequence) && indexWithinGolombSequence != 0) { // stop if input is 0
		cout << computeNthGolumbNumber(indexWithinGolombSequence) << endl;
	}

	return 0;
}
