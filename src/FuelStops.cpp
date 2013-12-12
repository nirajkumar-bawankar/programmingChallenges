#include <algorithm>
#include <iostream>
#include <numeric>

/**
 * Find problem statement at:
 * https://theta.cs.vt.edu/acm/shared/fall2013/handouts-week9/rockymountain2012problems/fuel.pdf
 *
 * Below is a solution to this problem.
 */
int MAXIMUM_NUMBER_OF_CITIES = 100000;

bool printOutValidStartCityNumbers(int testCaseNumber) {
	int numberOfTestCases;
	std::cin >> numberOfTestCases;

	if (numberOfTestCases == 0) {
		return false; // program is over
	}

	std::cout << "Case " << testCaseNumber << ":";

	int amountOfFuelAtEachStation[MAXIMUM_NUMBER_OF_CITIES];

	for (int i = 0; i < numberOfTestCases; i++) {
		std::cin >> amountOfFuelAtEachStation[i];
	}

	for (int i = 0; i < numberOfTestCases; i++) {
		int quantityOfFuelToGetToNextFuelingStationInNextCity;
		std::cin >> quantityOfFuelToGetToNextFuelingStationInNextCity;
		amountOfFuelAtEachStation[i] = amountOfFuelAtEachStation[i]
				- quantityOfFuelToGetToNextFuelingStationInNextCity;
	}

	int partialSums[MAXIMUM_NUMBER_OF_CITIES];

	// partial_sum is a function within imported numeric file
	std::partial_sum(amountOfFuelAtEachStation,
			amountOfFuelAtEachStation + numberOfTestCases, partialSums);

	// min_element is a function within imported algorithms file
	int fuelingStationWithEnoughFuel = *std::min_element(partialSums,
			partialSums + numberOfTestCases);

	for (int indexOfSuccessfulStartingCity = 0;
			indexOfSuccessfulStartingCity < numberOfTestCases;
			indexOfSuccessfulStartingCity++) {

		if (fuelingStationWithEnoughFuel >= 0) {
			std::cout << ' ' << indexOfSuccessfulStartingCity + 1;
		}

		fuelingStationWithEnoughFuel = fuelingStationWithEnoughFuel
				- amountOfFuelAtEachStation[indexOfSuccessfulStartingCity];
	}

	std::cout << std::endl;
	return true;
}

int main() {
	int testCaseNumber = 1;
	while (printOutValidStartCityNumbers(testCaseNumber++))
		;
	return 0;
}
