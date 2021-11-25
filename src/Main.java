import java.util.ArrayList;
import java.util.List;

// the runner class
public class Main {

  public static Pair<List<Integer>, List<Integer>> bellmanFord(Graph graph, int startingNode)
      throws IllegalArgumentException {
    // ensuring that the starting node is contained in the graph
    InputValidation.ensureWithin(startingNode, -1, graph.getNumberOfNodes(),
        "Starting node is not contained in the graph");

    List<ArrayList<Integer>> DP = new ArrayList<ArrayList<Integer>>(graph.getNumberOfNodes());
    List<Integer> parent = new ArrayList<Integer>(graph.getNumberOfNodes());
    for (int nodeIndex = 0; nodeIndex < graph.getNumberOfNodes(); nodeIndex++) {
      if (nodeIndex == startingNode) {
        parent.add(startingNode);
      } else {
        // indicates no parent as -1 is an invalid node
        parent.add(-1);
      }
      ArrayList<Integer> currentNodeDistances = new ArrayList<Integer>(graph.getNumberOfNodes());
      DP.add(currentNodeDistances);
      for (int numberOfHops = 0; numberOfHops < graph.getNumberOfNodes(); numberOfHops++) {
        if (nodeIndex == startingNode) {
          currentNodeDistances.add(0);
        } else {
          // this is hacky, but i don't like dealing with java's implementation of infinity
          currentNodeDistances.add(9999);
        }
      }
    }

    for (int numberOfHops = 1; numberOfHops < graph.getNumberOfNodes(); numberOfHops++) {
      for (int nodeIndex = 0; nodeIndex < graph.getNumberOfNodes(); nodeIndex++) {
        int currentDistance = DP.get(nodeIndex).get(numberOfHops - 1);
        DP.get(nodeIndex).set(numberOfHops, currentDistance);
        List<Pair<Integer, Integer>> currentIncomingEdges = graph.getIncomingEdges(nodeIndex);
        for (int incomingEdgeIndex = 0; incomingEdgeIndex < currentIncomingEdges.size();
            incomingEdgeIndex++) {
          Pair<Integer, Integer> currentIncomingEdge = currentIncomingEdges.get(incomingEdgeIndex);
          int inNeighbor = currentIncomingEdge.getFirst();
          int edgeWeight = currentIncomingEdge.getSecond();
          int distanceToNeighbor = DP.get(inNeighbor).get(numberOfHops - 1);
          if (distanceToNeighbor != 9999 && distanceToNeighbor + edgeWeight < currentDistance) {
            DP.get(nodeIndex).set(numberOfHops, distanceToNeighbor + edgeWeight);
            parent.set(nodeIndex, inNeighbor);
          }
        }
      }
    }

    List<Integer> distances = ListUtilities.map(DP, (row) -> row.get(row.size() - 1));
    return new Pair<List<Integer>, List<Integer>>(distances, parent);
  }

  public static void main(String[] args) {
    System.out.println("Hello world!");
    Graph exampleGraph = new Graph(5);
    exampleGraph.addEdge(0, 1, -1);
    exampleGraph.addEdge(0, 2, 4);
    exampleGraph.addEdge(1, 2, 3);
    exampleGraph.addEdge(1, 3, 2);
    exampleGraph.addEdge(1, 4, 2);
    exampleGraph.addEdge(3, 1, 1);
    exampleGraph.addEdge(3, 2, 5);
    exampleGraph.addEdge(4, 3, -3);

    Pair<List<Integer>, List<Integer>> distancesAndParents = bellmanFord(exampleGraph, 0);
    System.out.println("distances: " + distancesAndParents.getFirst());
    System.out.println("parents: " + distancesAndParents.getSecond());
  }
}
