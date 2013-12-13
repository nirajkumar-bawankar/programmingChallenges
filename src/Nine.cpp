#include <iostream>
#include <iomanip>
#include <cstdlib>

using namespace std;

/**
 * Find problem statement at: https://theta.cs.vt.edu/acm/shared/fall2013/handouts-week9/rockymountain2012problems/nine.pdf
 *
 * Below is a solution to this problem.
 *
 * Problem: Given a desired cooking time, find a sequence of keys with the greatest
 * number of 9's such that the resulting time has less than 10% error compared to
 * the desired cooking time. In other words, if T is the desired cooking time in seconds,
 * and T9 is the cooking time specified by the found sequence, then 10*|T - T9| < T.
 * If there are multiple possibilities, choose the one that has the smallest error
 * (in magnitude). If there are still ties, choose the one that is lexicographically smallest.
 */
int main() {
	int milliseconds;
	int seconds;
	char colon;

	int nines[100];
	for (int i = 0; i <= 99; i++) {
		nines[i] = 0;

		if (i % 10 == 9) {
			nines[i]++;
		}

		if (i / 10 == 9) {
			nines[i]++;
		}
	}

	while (cin >> milliseconds >> colon >> seconds && (milliseconds || seconds)) {
		int T = 60 * milliseconds + seconds;
		int smallestErrorOutOfAllPossibilities = 1000000;

		int bestSeconds = 0;
		int best9 = -1;
		int bestMilliseconds = 0;

		// output format forces milliseconds to display only in 2 digits
		for (int millisecondsExpressedAs2Digits = 0;
				millisecondsExpressedAs2Digits <= 99;
				millisecondsExpressedAs2Digits++) {

			// output format forces seconds to display only in 2 digits
			for (int secondsExpressedAs2Digits = 0;
					secondsExpressedAs2Digits <= 99;
					secondsExpressedAs2Digits++) {

				int T9 = 60 * millisecondsExpressedAs2Digits
						+ secondsExpressedAs2Digits;

				int currentError = abs(T9 - T);

				if (currentError * 10 >= T) {
					continue;
				}

				int numberOf9s = nines[millisecondsExpressedAs2Digits]
						+ nines[secondsExpressedAs2Digits];

				if (numberOf9s > best9
						|| (numberOf9s == best9
								&& currentError
										< smallestErrorOutOfAllPossibilities)
						|| (numberOf9s == best9
								&& currentError
										== smallestErrorOutOfAllPossibilities
								&& millisecondsExpressedAs2Digits
										< bestMilliseconds)
						|| (numberOf9s == best9
								&& currentError
										== smallestErrorOutOfAllPossibilities
								&& millisecondsExpressedAs2Digits
										== bestMilliseconds
								&& secondsExpressedAs2Digits < bestSeconds)) {

					bestMilliseconds = millisecondsExpressedAs2Digits;
					bestSeconds = secondsExpressedAs2Digits;
					best9 = numberOf9s;
					smallestErrorOutOfAllPossibilities = currentError;
				}
			}
		}
		// printout output in the format mm:ss
		cout << setw(2) << setfill('0') << bestMilliseconds << ":" << setw(2)
				<< setfill('0') << bestSeconds << endl;
	}

	return 0;
}
