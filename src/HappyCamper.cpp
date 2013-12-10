#include <algorithm>
#include <iostream>

int main() {
	int id = 1;
	int L;
	int P;
	int V;

	while (std::cin >> L >> P >> V && (L || P || V)) {
		std::cout << "Case " << id++ << ": ";

		int result = V / P * L + std::min(V % P, L);

		std::cout << result << std::endl;
	}

	return 0;
}
