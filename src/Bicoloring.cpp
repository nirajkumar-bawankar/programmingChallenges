
#include <iostream>
#include <vector>
#include <queue>

#define RED 1
#define BLACK (-1)

using namespace std;

typedef vector<vector<int>> Graph;

// TODO Bicoloring still needs more refractoring

bool bicolorable(const Graph graph) {
    bool answer = true;

    vector<int> color(graph.size()); // Construct a vector with the size of the graph

    queue<int> single_queue;
    color[0] = RED;
    single_queue.push(0);

    while (! single_queue.empty()) { // While the queue is not empty

        int v = single_queue.front();  // Get the front item
        int c = color[v];

        for (int i = 0; i < graph[v].size(); i++) {

            int v2 = graph[v][i];
            int & c2 = color[v2];

            if (! color[v2]) {
              c2 = c * -1;
              single_queue.push(v2);
            }

            else if (c2 * c != -1) {
                answer = false;
                break;
            }

        }

      single_queue.pop();

    }

    return anwser;
}

int main() {
    int numberOfVertices = 0; // Set the default number of vertices to be 0

    while ((cin >> numberOfVertices) && numberOfVertices) {
      
        Graph graph(numberOfVertices);

        int numberOfEdges = 0;
        cin >> numberOfEdges;
        
        for (int i = 0; i < numberOfEdges; i++) {
            int v1, v2;
            cin >> v1 >> v2; // First get v1 then get v2
            graph[v1].push_back(v2); // Add V2 at the end
            graph[v2].push_back(v1); // Add V1 at the end
        }
        
        if (bicolorable(graph)) {
            cout << "BICOLORABLE." << endl;
        }
        
        else {
            cout << "NOT BICOLORABLE." << endl;
        }

    }

  return 0;
}