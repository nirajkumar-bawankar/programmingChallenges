#include <vector>
#include <queue>
#include <iostream>

#define RED 1
#define BLACK (-1)

using namespace std;

// contains no self loops (edges from a vertex to itself)
typedef vector<vector<int> > undirectedConnectedGraph;

bool isBicolorable(const undirectedConnectedGraph graph) {
    bool isBicolorable = true;

    vector<int> colorsOfEachElement(graph.size()); // construct a vector with the size of the graph

    queue<int> queue;

    colorsOfEachElement[0] = RED; // initial color
    queue.push(0);

    while (!queue.empty()) { // While the queue is not empty
        int v = queue.front();  // Get the front item
        int c = colorsOfEachElement[v];

        for (int i = 0; i < graph[v].size(); i++) {
            int v2 = graph[v][i];
            int & c2 = colorsOfEachElement[v2];

            if (!colorsOfEachElement[v2]) {
                c2 = c * -1;
                queue.push(v2);
            } else if (c2 * c != -1) {
                isBicolorable = false;
                break;
            }
        }
        queue.pop();
    }
    return isBicolorable;
}

int main() {
    int numberOfVertices = 0; // set the default number of vertices to be 0

    while ((cin >> numberOfVertices) && numberOfVertices) {

        undirectedConnectedGraph graph(numberOfVertices);

        int numberOfEdges = 0;
        cin >> numberOfEdges;

        for (int i = 0; i < numberOfEdges; i++) {
            int v1, v2;
            cin >> v1 >> v2; // first get v1 then get v2
            graph[v1].push_back(v2);
            graph[v2].push_back(v1);
        }

        if (isBicolorable(graph)) {
            cout << "BICOLORABLE." << endl;
        } else {
            cout << "NOT BICOLORABLE." << endl;
        }
    }
    return 0;
}
