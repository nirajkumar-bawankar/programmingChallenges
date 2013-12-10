#include <algorithm>
#include <iostream>
#include <numeric>

int MAXIMUM_NUMBER_OF_CITIES = 100000;

bool printOutValidStartCityNumbers(int quantityOfFuel) {
	int numberOfTestCases;
	std::cin >> numberOfTestCases;

	if (!numberOfTestCases) {
		return false;
	}

	std::cout << "Case " << quantityOfFuel << ":";

	int amountOfFuelAtAllStations[MAXIMUM_NUMBER_OF_CITIES];

	for (int i = 0; i < numberOfTestCases; i++) {
		std::cin >> amountOfFuelAtAllStations[i];
	}

	for (int i = 0; i < numberOfTestCases; i++) {
		int x;
		std::cin >> x;
		amountOfFuelAtAllStations[i] = amountOfFuelAtAllStations[i] - x;
	}

	int sum[MAXIMUM_NUMBER_OF_CITIES];

	// partial_sum is a function within imported numeric file
	std::partial_sum(amountOfFuelAtAllStations,
			amountOfFuelAtAllStations + numberOfTestCases, sum);

	// min_element is a function within imported algorithms file
	int worst = *std::min_element(sum, sum + numberOfTestCases);

	for (int i = 0; i < numberOfTestCases; i++) {
		if (worst >= 0) {
			std::cout << ' ' << i + 1;
		}
		worst = worst - amountOfFuelAtAllStations[i];
	}

	std::cout << std::endl;
	return true;
}

int main() {
	int quantityOfFuel = 1;
	while (printOutValidStartCityNumbers(quantityOfFuel++))
		;
	return 0;
}
