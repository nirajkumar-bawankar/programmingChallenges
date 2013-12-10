#include <iostream>
#include <cmath>
#include <vector>
#include <queue>
#include <climits>
#include <utility>
#include <algorithm>

using namespace std;

// used to store the input information about capacity for each edge within the graph
typedef vector<vector<pair<int, int> > > UndirectedGraph;

static int minimumNumberOfEdgesToGoThrough(const UndirectedGraph & undirectedGraph, int sourceVertex,
		int destinationVertex, int initialFlowRate);

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=110903&format=html
 * 
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Jason Riddle (jr1285@vt.edu)
 *
 * The following is a solution for the above problem.
 */
int main() {
	int numberOfVertices;
	int numberOfEdges;
	int scenarioNumber = 1;

	// when numberOfVertices == 0 && numberOfEdges == 0 the while loop will be existed
	while ((cin >> numberOfVertices) && (cin >> numberOfEdges) && numberOfVertices) {
		UndirectedGraph graphOfCityAndBusRoutes(numberOfVertices + 1);

		for (int currentEdgeIndex = 0; currentEdgeIndex < numberOfEdges;
				currentEdgeIndex++) {
			int firstCityNumber;
			int secondCityNumber;
			int maximumNumberOfPassengers;

			cin >> firstCityNumber >> secondCityNumber
					>> maximumNumberOfPassengers;

			graphOfCityAndBusRoutes[firstCityNumber].push_back(
					make_pair(secondCityNumber, maximumNumberOfPassengers));

			graphOfCityAndBusRoutes[secondCityNumber].push_back(
					make_pair(firstCityNumber, maximumNumberOfPassengers));
		}
		int startingCityNumber;
		int destinationCityNumber;
		int numberOfPassengers;
		cin >> startingCityNumber >> destinationCityNumber
				>> numberOfPassengers;

		cout << "Scenario #" << scenarioNumber++ << endl;
		cout << "Minimum Number of Trips = "
				<< minimumNumberOfEdgesToGoThrough(graphOfCityAndBusRoutes,
						startingCityNumber, destinationCityNumber,
						numberOfPassengers) << endl;
		cout << endl;
	}
	return 0;
}

/**
 * The shortest path is found by using Dijkstra's Algorithm.
 */
int minimumNumberOfEdgesToGoThrough(const UndirectedGraph & undirectedGraph, int sourceVertex,
		int destinationVertex, int initialFlowRate) {
	queue<int> queueOfAllUnvisitedVerticesInGraph;
	queueOfAllUnvisitedVerticesInGraph.push(sourceVertex);

	vector<int> flowInEachEdge(undirectedGraph.size());
	flowInEachEdge[sourceVertex] = INT_MAX; // simulate setting edge flow to infinity

	while (!queueOfAllUnvisitedVerticesInGraph.empty()) {

		int vertex1 = queueOfAllUnvisitedVerticesInGraph.front();
		int edgeCapacity1 = flowInEachEdge[vertex1];
		queueOfAllUnvisitedVerticesInGraph.pop();

		for (int i = 0; i < undirectedGraph[vertex1].size(); i++) {
			// vertex2 eventually becomes the destinationVertex
			int vertex2 = undirectedGraph[vertex1][i].first;
			int edgeCapacity2 = undirectedGraph[vertex1][i].second;

			int smallerEdgeCapacity = min(edgeCapacity1, edgeCapacity2);

			if (flowInEachEdge[vertex2] < smallerEdgeCapacity) {
				flowInEachEdge[vertex2] = smallerEdgeCapacity;
				queueOfAllUnvisitedVerticesInGraph.push(vertex2);
			}
		}
	}
	return ceil(1.0 * initialFlowRate / (flowInEachEdge[destinationVertex] - 1));
}
