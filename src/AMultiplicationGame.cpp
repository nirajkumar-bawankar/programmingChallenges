#include <cmath>
#include <iostream>

using namespace std;

static bool willStanWin(unsigned int n);

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=110505&format=html
 *
 * The following is a solution for the above problem.
 */
int main(void) {
	unsigned int n;
	while (cin >> n) {
		if (willStanWin(n)) {
			cout << "Stan wins." << endl;
		} else {
			cout << "Ollie wins." << endl;
		}
	}
	return 0;
}

/**
 * Return true if Stan will win; otherwise return false if Ollie will win.
 */
bool willStanWin(unsigned int n) {
	bool stanWins = true;
	// if the multiplying number is less than 9 then Stan automatically wins

	// the strategy to find who wins is the continuously decrease the range
	// of possible values
	if (n > 9) {
		// for Stan to win, the result of Ollie's multiplication must be
		// equal to or bigger than lowerBound and less than upperBound
		unsigned int lowerBound = ceil(n / 9.0);
		unsigned int upperBound = n;

		// keeps track of which person the current result is for
		stanWins = !stanWins;

		while (true) {

			// base case
			if (stanWins && lowerBound <= 9) {
				if (!(lowerBound >= 2 && upperBound > lowerBound)) {
					stanWins = false;
				}
				break;
			}

			// keeps track of which person the current result is for
			stanWins = !stanWins;

			if (stanWins) {
				lowerBound = ceil(lowerBound / 2.0);
				upperBound = ceil(upperBound / 9.0);
			} else {
				lowerBound = ceil(lowerBound / 9.0);
				upperBound = ceil(upperBound / 2.0);
			}
		}
	}
	return stanWins;
}
