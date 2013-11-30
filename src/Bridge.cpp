#include <vector>
#include <algorithm>
#include <iostream>
#include <string>

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=110403&format=html
 *
 * The following is a solution for the above problem.
 */
using namespace std;

int simulate2PeopleCrossingBridge(
		vector<vector<int> > & sequenceOfPeopleCrossingTime,
		int fasterPersonTimeToCross, int slowerPersonTimeToCross = 0) {

	if (!slowerPersonTimeToCross) {
		sequenceOfPeopleCrossingTime.push_back(
				vector<int>(1, fasterPersonTimeToCross));
		// if no slower Person that is crossing
		return fasterPersonTimeToCross;
	} else {
		vector<int> twoPeople(2);

		twoPeople[0] = fasterPersonTimeToCross;
		twoPeople[1] = slowerPersonTimeToCross;

		sequenceOfPeopleCrossingTime.push_back(twoPeople);
		// since this the total time for the 2 people to cross the bridge
		return slowerPersonTimeToCross;
	}
}

int computeMinimumTimeToCrossBridge(vector<int> & timeToCrossForAllPeople,
		vector<vector<int> > & sequenceOfPeopleCrossingTime) {
	if (timeToCrossForAllPeople.size() == 1) {
		sequenceOfPeopleCrossingTime.push_back(
				vector<int>(1, timeToCrossForAllPeople[0]));
		return timeToCrossForAllPeople[0];
	}
	// the people that will cross the bridge fastest are at the beginning
	// of the vector timeToCrossForAllPeople
	sort(timeToCrossForAllPeople.begin(), timeToCrossForAllPeople.end());

	int totalTimeToCross = 0;

	// at most only 2 people can cross the bridge each time
	int currentPersonCrossing1 = timeToCrossForAllPeople[0];
	int currentPersonCrossing2 = timeToCrossForAllPeople[1];

	while (!timeToCrossForAllPeople.empty()) {

		int size = timeToCrossForAllPeople.size();

		if (size == 2) { // only 2 people left to cross the bridge
			totalTimeToCross += simulate2PeopleCrossingBridge(
					sequenceOfPeopleCrossingTime, currentPersonCrossing1,
					currentPersonCrossing2);
			// no more people to cross the bridge
			timeToCrossForAllPeople.clear();
		} else if (size == 3) { // only 3 people left to cross the bridge
			totalTimeToCross += simulate2PeopleCrossingBridge(
					sequenceOfPeopleCrossingTime, currentPersonCrossing1,
					timeToCrossForAllPeople[2]);

			totalTimeToCross += simulate2PeopleCrossingBridge(
					sequenceOfPeopleCrossingTime, currentPersonCrossing1);
			totalTimeToCross += simulate2PeopleCrossingBridge(
					sequenceOfPeopleCrossingTime, currentPersonCrossing1,
					currentPersonCrossing2);

			timeToCrossForAllPeople.clear();
		} else { // more than 3 people left to cross the bridge
			if (currentPersonCrossing2 - currentPersonCrossing1
					< timeToCrossForAllPeople[size - 2]
							- currentPersonCrossing2) {
				totalTimeToCross += simulate2PeopleCrossingBridge(
						sequenceOfPeopleCrossingTime, currentPersonCrossing1,
						currentPersonCrossing2);
				totalTimeToCross += simulate2PeopleCrossingBridge(
						sequenceOfPeopleCrossingTime, currentPersonCrossing1);

				totalTimeToCross += simulate2PeopleCrossingBridge(
						sequenceOfPeopleCrossingTime,
						timeToCrossForAllPeople[size - 2],
						timeToCrossForAllPeople.back());

				totalTimeToCross += simulate2PeopleCrossingBridge(
						sequenceOfPeopleCrossingTime, currentPersonCrossing2);

				timeToCrossForAllPeople.pop_back();
				timeToCrossForAllPeople.pop_back();
			} else {
				totalTimeToCross += simulate2PeopleCrossingBridge(
						sequenceOfPeopleCrossingTime, currentPersonCrossing1,
						timeToCrossForAllPeople.back());
				totalTimeToCross += simulate2PeopleCrossingBridge(
						sequenceOfPeopleCrossingTime, currentPersonCrossing1);

				timeToCrossForAllPeople.pop_back();
			}
		}
	}
	return totalTimeToCross;
}

int main() {
	int numberOfPeople = 0;
	cin >> numberOfPeople;
	for (int i = 0; i < numberOfPeople; i++) {
		int indexOfCurrentPerson = 0;

		cin >> indexOfCurrentPerson;

		vector<int> timeToCrossForAllPeople(indexOfCurrentPerson);

		int index;
		for (index = 0; index < indexOfCurrentPerson; index++) {
			cin >> timeToCrossForAllPeople[index];
		}

		vector<vector<int> > sequenceOfPeopleCrossingTime;

		int minimumTimeForEveryoneToCrossBridge =
				computeMinimumTimeToCrossBridge(timeToCrossForAllPeople,
						sequenceOfPeopleCrossingTime);

		if (i != 0) {
			cout << endl;
		}
		cout << minimumTimeForEveryoneToCrossBridge << endl;

		for (index = 0; index < sequenceOfPeopleCrossingTime.size(); index++) {
			for (int k = 0; k < sequenceOfPeopleCrossingTime[k].size(); k++) {
				if (k != 0) {
					cout << ' ';
				}
				cout << sequenceOfPeopleCrossingTime[k][k];
			}
			cout << endl;
		}
	}
}
