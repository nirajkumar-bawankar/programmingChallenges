#include <vector>
#include <queue>
#include <iostream>

// in the spirit of Christmas
#define GREEN 1
#define RED (-1)

using namespace std;

// contains no self loops (edges from a vertex to itself)
typedef vector<vector<int> > undirectedConnectedGraph;

bool isBicolorable(const undirectedConnectedGraph undirectedConnectedGraph) {
	bool isBicolorable = true;

	vector<int> colorsOfEachElement(undirectedConnectedGraph.size()); // construct a vector with the size of the graph

	queue<int> queueOfAdjacentVertices;

	colorsOfEachElement[0] = GREEN; // initial color
	queueOfAdjacentVertices.push(0);

	while (!queueOfAdjacentVertices.empty()) {
		int vertex = queueOfAdjacentVertices.front();
		int colorOfVertex = colorsOfEachElement[vertex];

		for (int i = 0; i < undirectedConnectedGraph[vertex].size(); i++) {
			int adjacentVertex = undirectedConnectedGraph[vertex][i];
			int & colorOfAdjacentVertex = colorsOfEachElement[adjacentVertex];

			if (!colorsOfEachElement[adjacentVertex]) {
				colorOfAdjacentVertex = colorOfVertex * -1;
				queueOfAdjacentVertices.push(adjacentVertex);
			} else if (colorOfAdjacentVertex * colorOfVertex != -1) {
				isBicolorable = false;
				break;
			}
		}
		queueOfAdjacentVertices.pop();
	}
	return isBicolorable;
}

int main() {
	int numberOfVertices = 0; // set the default number of vertices to be 0

	while ((cin >> numberOfVertices) && numberOfVertices) {

		undirectedConnectedGraph undirectedConnectedGraph(numberOfVertices);

		int numberOfEdges = 0;
		cin >> numberOfEdges;

		// build undirected connected graph
		for (int i = 0; i < numberOfEdges; i++) {
			int v1, v2;
			cin >> v1 >> v2;
			undirectedConnectedGraph[v1].push_back(v2);
			undirectedConnectedGraph[v2].push_back(v1);
		}

		if (isBicolorable(undirectedConnectedGraph)) {
			cout << "BICOLORABLE." << endl;
		} else {
			cout << "NOT BICOLORABLE." << endl;
		}
	}
	return 0;
}
