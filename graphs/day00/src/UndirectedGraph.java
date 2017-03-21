import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UndirectedGraph implements Graph {

    private Map<Integer, List<Integer>> adj;  // Represent the graph using an adjacency list
    private List<Integer> vertices;  // all vertices in the graph
    private int numEdges;

    public UndirectedGraph(int n) {
        // TODO: Your code here
    }

    @Override
    public void addEdge(int v, int w) {
        // TODO: Your code here
    }

    @Override
    public List<Integer> vertices() {
    	// TODO: Your code here
        return null;
    }

    @Override
    public int numVertices() {
    	// TODO: Your code here
        return 0;
    }

    @Override
    public int numEdges() {
    	// TODO: Your code here
        return 0;
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
    	// TODO: Your code here
        return null;
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
    	// TODO: Your code here
        return false;
    }

}
