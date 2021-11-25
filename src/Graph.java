import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple directed graph with weighted edges. There are no negative cycles, and edges
 * are allowed to be negatively weighted.
 */
public class Graph {

  int numberOfNodes;
  int numberOfEdges;
  List<ArrayList<Pair<Integer, Integer>>> outgoingEdges; // each pair represents neighbor, weight
  List<ArrayList<Pair<Integer, Integer>>> incomingEdges;

  /**
   * Constructs an empty graph with no nodes
   */
  public Graph() {
    this.numberOfNodes = 0;
    this.numberOfEdges = 0;
    this.outgoingEdges = new ArrayList<ArrayList<Pair<Integer, Integer>>>();
    this.incomingEdges = new ArrayList<ArrayList<Pair<Integer, Integer>>>();
  }

  /**
   * Constructs a graph with {@code numberOfNodes} nodes. This simply allocates sufficient memory
   * for the specified number of nodes
   *
   * @param numberOfNodes The initial number of nodes in the graph
   * @throws IllegalArgumentException if the given number of nodes is negative
   */
  public Graph(int numberOfNodes) throws IllegalArgumentException {
    this.numberOfNodes = InputValidation
        .ensureGreaterThan(numberOfNodes, -1, "Number of nodes must be nonnegative");
    this.numberOfEdges = 0;
    this.outgoingEdges = new ArrayList<ArrayList<Pair<Integer, Integer>>>(this.numberOfNodes);
    this.incomingEdges = new ArrayList<ArrayList<Pair<Integer, Integer>>>(this.numberOfNodes);

    for (int index = 0; index < this.numberOfNodes; index++) {
      this.outgoingEdges.add(new ArrayList<Pair<Integer, Integer>>());
      this.incomingEdges.add(new ArrayList<Pair<Integer, Integer>>());
    }
  }

  /**
   * Obtains the current number of nodes in the graph
   *
   * @return The current number of nodes in the graph
   */
  public int getNumberOfNodes() {
    return this.numberOfNodes;
  }

  /**
   * Allocates sufficient space for one additional node in the graph
   */
  public void addNode() {
    this.numberOfNodes += 1;
    this.outgoingEdges.add(new ArrayList<Pair<Integer, Integer>>());
    this.incomingEdges.add(new ArrayList<Pair<Integer, Integer>>());
  }

  /**
   * Allocates sufficient space for the given number of nodes in the graph
   *
   * @param numberOfNodes The number of nodes to be added
   * @throws IllegalArgumentException if the number of nodes to be added is not positive
   */
  public void addNodes(int numberOfNodes) throws IllegalArgumentException {
    InputValidation
        .ensureGreaterThan(numberOfNodes, 0, "Number of nodes added must be a positive integer");

    this.numberOfNodes += numberOfNodes;
    for (int index = 0; index < numberOfNodes; index++) {
      this.outgoingEdges.add(new ArrayList<Pair<Integer, Integer>>());
      this.incomingEdges.add(new ArrayList<Pair<Integer, Integer>>());
    }
  }

  /**
   * Adds a directed edge from the {@code from} node to the {@code to} with a weight of {@code
   * weight}
   *
   * @param from   The integer representing the node the edge comes from
   * @param to     The integer representing the node the edges goes to
   * @param weight The weight of the edge
   * @throws IllegalArgumentException if either node is not contained in the graph, or if there
   *                                  already exists an edge from the {@code from} node to the
   *                                  {@code to} node
   */
  public void addEdge(int from, int to, int weight) throws IllegalArgumentException {
    InputValidation
        .ensureWithin(from, -1, this.numberOfNodes, "Node is not contained in the graph");
    InputValidation.ensureWithin(to, -1, this.numberOfNodes, "Node is not contained in the graph");

    List<Pair<Integer, Integer>> currentOutgoingEdges = this.outgoingEdges.get(from);
    List<Pair<Integer, Integer>> currentIncomingEdges = this.incomingEdges.get(to);
    List<Integer> currentOutgoingNeighbors = ListUtilities
        .map(currentOutgoingEdges, (element) -> element.getFirst());
    List<Integer> currentIncomingNeighbors = ListUtilities
        .map(currentIncomingEdges, (element) -> element.getFirst());
    if (currentOutgoingNeighbors.contains(to) || currentIncomingNeighbors.contains(from)) {
      throw new IllegalArgumentException("An edge between these two nodes already exists");
    }

    this.numberOfEdges += 1;
    currentOutgoingEdges.add(new Pair<Integer, Integer>(to, weight));
    currentIncomingEdges.add(new Pair<Integer, Integer>(from, weight));
  }

  /**
   * Obtains a copy of the list of all edges leaving the given node
   *
   * @param from The integer representing the node whose outgoing edges we want
   * @return A copy of the list of all edges leaving the given node
   * @throws IllegalArgumentException if the given node is not contained in the graph
   */
  public List<Pair<Integer, Integer>> getOutgoingEdges(int from) throws IllegalArgumentException {
    List<Pair<Integer, Integer>> outgoingEdgesCopy = new ArrayList<Pair<Integer, Integer>>(
        this.numberOfNodes);
    for (int index = 0; index < this.outgoingEdges.get(from).size(); index++) {
      outgoingEdgesCopy.add(this.outgoingEdges.get(from).get(index));
    }
    return outgoingEdgesCopy;
  }

  /**
   * Obtains a copy of the list of all edges arriving at the given node
   *
   * @param to The integer representing the node whose incoming edges we want
   * @return A copy of the list of all edges arriving the given node
   * @throws IllegalArgumentException if the given node is not contained in the graph
   */
  public List<Pair<Integer, Integer>> getIncomingEdges(int to) throws IllegalArgumentException {
    List<Pair<Integer, Integer>> incomingEdgesCopy = new ArrayList<Pair<Integer, Integer>>(
        this.numberOfNodes);
    for (int index = 0; index < this.incomingEdges.get(to).size(); index++) {
      incomingEdgesCopy.add(this.incomingEdges.get(to).get(index));
    }
    return incomingEdgesCopy;
  }
}
