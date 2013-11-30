#include <vector>
#include <queue>
#include <iostream>

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=110901&format=html
 *
 * The following is a solution for the above problem.
 */
// in the spirit of Christmas
#define GREEN 1
#define RED (-1)

using namespace std;

// contains no self loops (edges from a vertex to itself)
typedef vector<vector<int> > undirectedConnectedGraph;

bool isBicolorable(const undirectedConnectedGraph undirectedConnectedGraph) {
	bool isBicolorable = true;

	vector<int> colorOfNodes(undirectedConnectedGraph.size()); // construct a vector with the size of the graph

	queue<int> queueOfAdjacentVertices;

	colorOfNodes[0] = GREEN; // initial color
	queueOfAdjacentVertices.push(0);

	while (!queueOfAdjacentVertices.empty()) {
		int vertex = queueOfAdjacentVertices.front();
		int colorOfVertex = colorOfNodes[vertex];

		for (int i = 0; i < undirectedConnectedGraph[vertex].size(); i++) {
			int adjacentVertex = undirectedConnectedGraph[vertex][i];
			int &colorOfAdjacentVertex = colorOfNodes[adjacentVertex];

			if (!colorOfNodes[adjacentVertex]) {
				// if color is GREEEN (1)
				colorOfAdjacentVertex = colorOfVertex * -1; // make adjacent vertex color RED (-1)
				queueOfAdjacentVertices.push(adjacentVertex);
			} else if (colorOfAdjacentVertex * colorOfVertex != -1) {
				// found an adjacent vertex with the same color
				isBicolorable = false;
				break;
			}
		}
		queueOfAdjacentVertices.pop();
	}
	return isBicolorable;
}

int main() {
	int numberOfVertices = 0;

	while ((cin >> numberOfVertices) && numberOfVertices) {

		undirectedConnectedGraph undirectedConnectedGraph(numberOfVertices);

		int numberOfEdges = 0;
		cin >> numberOfEdges;

		// build undirected connected graph
		for (int i = 0; i < numberOfEdges; i++) {
			int vertex1, vertex2;
			cin >> vertex1 >> vertex2;
			undirectedConnectedGraph[vertex1].push_back(vertex2);
			undirectedConnectedGraph[vertex2].push_back(vertex1);
		}

		if (isBicolorable(undirectedConnectedGraph)) {
			cout << "BICOLORABLE." << endl;
		} else {
			cout << "NOT BICOLORABLE." << endl;
		}
	}
	return 0;
}
