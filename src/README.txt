welcome to my implementation of bellman-ford's single source shortest path algorithm.
this is a rather straightforward algorithm to implement. the only challenges present themselves in
the appropriate representation for the graph. in this implementation, i used two adjacency lists
in order to efficiently retrieve the edges leaving but also arriving at any given node. in addition
to this, however, i did encounter difficulties in representing infinity as an integer. this is a
java-specific limitation, so an implementation in another language would not experience this issue.
as a workaround, i just chose a large value, 9999, and ensured that no addition or subtraction was
done to this value as this value does not have the same behavior with binary operations as infinity
does. for example infinity +/- c for any real c is still infinity. in order to work around this, i
just performed a quick constant time check to see if the previous shortest distance is equal to this
value.

in order to use this algorithm, once can construct a graph in the main method by calling the graph
constructor, the addNode/addNodes, and addEdge methods, and call the bellmanFord method with the
constructed graph and a starting node. this algorithm will output to console a list of distances
from the starting node to every node in the graph and a list of the parent pointers for each node
in order to reconstruct the graph.