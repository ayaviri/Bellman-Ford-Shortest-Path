import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents a suite of tests for the {@code Graph} class
 */
public class TestGraph {

  private Graph exampleGraph;

  // initializes data for testing purposes
  @Before
  public void initializeData() {
    exampleGraph = new Graph();
  }

  // tests the addNode method
  @Test
  public void testAddNode() {
    assertEquals(0, exampleGraph.getNumberOfNodes());
    exampleGraph.addNode();
    assertEquals(1, exampleGraph.getNumberOfNodes());
    exampleGraph.addNode();
    exampleGraph.addNode();
    exampleGraph.addNode();
    assertEquals(4, exampleGraph.getNumberOfNodes());
  }

  // ensures that the addNodes method throws an IllegalArgumentException when given a negative
  // number of nodes to add
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeNodesAdded() {
    exampleGraph.addNodes(-1);
  }

  // ensures that the addNodes method throws an IllegalArgumentException when given zero nodes
  // to add
  @Test(expected = IllegalArgumentException.class)
  public void testZeroNodesAdded() {
    exampleGraph.addNodes(0);
  }

  // tests the addNodes method
  @Test
  public void testAddNodes() {
    assertEquals(0, exampleGraph.getNumberOfNodes());
    exampleGraph.addNodes(3);
    assertEquals(3, exampleGraph.getNumberOfNodes());
    exampleGraph.addNodes(5);
    assertEquals(8, exampleGraph.getNumberOfNodes());
  }

  // ensures that the addEdge method throws an IllegalArgumentException when given a from node that
  // is negative
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeFromNode() {
    exampleGraph.addNodes(3); // now, nodes 0, 1, and 2 have been added to the graph
    exampleGraph.addEdge(-1, 2, 20);
  }

  // ensures that the addEdge method throws an IllegalArgumentException when given a from node that
  // is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsFromNode() {
    exampleGraph.addNodes(3); // now, nodes 0, 1, and 2 have been added to the graph
    exampleGraph.addEdge(3, 2, 20);
  }

  // ensures that the addEdge method throws an IllegalArgumentException when given a to node that
  // is negative
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeToNode() {
    exampleGraph.addNodes(3); // now, nodes 0, 1, and 2 have been added to the graph
    exampleGraph.addEdge(2, -1, 20);
  }

  // ensures that the addEdge method throws an IllegalArgumentException when given a to node that
  // is out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsToNode() {
    exampleGraph.addNodes(3); // now, nodes 0, 1, and 2 have been added to the graph
    exampleGraph.addEdge(1, 3, 20);
  }

  // ensures that the addEdge method throws an IllegalArgumentException when attempting to add an
  // edge between two nodes that are already connected by an edge
  @Test(expected = IllegalArgumentException.class)
  public void testAddDuplicateEdge() {
    exampleGraph.addNodes(4);
    exampleGraph.addEdge(1, 2, 10);
    exampleGraph.addEdge(1, 2, 30);
  }

  // tests the addEdge method
  @Test
  public void testAddEdge() {
    exampleGraph.addNodes(3);
    // node 1 has no outgoing edges and node 2 has no incoming edges
    assertEquals(new ArrayList<Pair<Integer, Integer>>(),
        exampleGraph.getOutgoingEdges(1));
    assertEquals(new ArrayList<Pair<Integer, Integer>>(), exampleGraph.getIncomingEdges(2));
    // adding an edge from node 1 to node 2 with a weight of 20
    exampleGraph.addEdge(1, 2, 10);
    assertEquals(
        new ArrayList<Pair<Integer, Integer>>(Arrays.asList(new Pair<Integer, Integer>(2, 10))),
        exampleGraph.getOutgoingEdges(1));
    assertEquals(
        new ArrayList<Pair<Integer, Integer>>(Arrays.asList(new Pair<Integer, Integer>(1, 10))),
        exampleGraph.getIncomingEdges(2));
  }

  // ensures that the bellmanFord method throws an IllegalArgumentException if the starting node
  // is negative
  @Test(expected = IllegalArgumentException.class)
  public void testBellmanFordWithNegativeStartingNode() {
    exampleGraph.addNodes(5);
    exampleGraph.addEdge(0, 1, -1);
    exampleGraph.addEdge(0, 2, 4);
    exampleGraph.addEdge(1, 2, 3);
    exampleGraph.addEdge(1, 3, 2);
    exampleGraph.addEdge(1, 4, 2);
    exampleGraph.addEdge(3, 1, 1);
    exampleGraph.addEdge(3, 2, 5);
    exampleGraph.addEdge(4, 3, -3);
    Main.bellmanFord(exampleGraph, -1);
  }

  // ensures that the bellmanFord method throws an IllegalArgumentException if the starting node is
  // out of bounds
  @Test(expected = IllegalArgumentException.class)
  public void testBellmanFordWithOutOfBoundsStartingNode() {
    exampleGraph.addNodes(5);
    exampleGraph.addEdge(0, 1, -1);
    exampleGraph.addEdge(0, 2, 4);
    exampleGraph.addEdge(1, 2, 3);
    exampleGraph.addEdge(1, 3, 2);
    exampleGraph.addEdge(1, 4, 2);
    exampleGraph.addEdge(3, 1, 1);
    exampleGraph.addEdge(3, 2, 5);
    exampleGraph.addEdge(4, 3, -3);
    Main.bellmanFord(exampleGraph, 5);
  }

  // tests the bellmanFord method
  @Test
  public void testBellmanFord() {
    exampleGraph.addNodes(5);
    exampleGraph.addEdge(0, 1, -1);
    exampleGraph.addEdge(0, 2, 4);
    exampleGraph.addEdge(1, 2, 3);
    exampleGraph.addEdge(1, 3, 2);
    exampleGraph.addEdge(1, 4, 2);
    exampleGraph.addEdge(3, 1, 1);
    exampleGraph.addEdge(3, 2, 5);
    exampleGraph.addEdge(4, 3, -3);
    assertEquals(
        new Pair<ArrayList<Integer>, ArrayList<Integer>>(
            new ArrayList<Integer>(Arrays.asList(0, -1, 2, -2, 1)),
            new ArrayList<Integer>(Arrays.asList(0, 0, 1, 4, 1))),
        Main.bellmanFord(exampleGraph, 0));
  }
}
