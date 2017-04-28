import java.util.*;

public class UndirectedGraph implements Graph {
    ArrayList<Integer> vertices;
    ArrayList<List<Integer>> edges;

    public UndirectedGraph(int n) {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(i);
        }
    }

    @Override
    public void addEdge(int v, int w) {
        //O(1), just add another edge to the edge arraylist
        ArrayList<Integer> newEdge = new ArrayList<>();
        newEdge.add(v);
        newEdge.add(w);
        edges.add(newEdge);
    }

    @Override
    public List<Integer> vertices() {
    	//O(1), returns vertex array
        return vertices;
    }

    @Override
    public int numVertices() {
    	//O(1), return vertex array size
        return vertices.size();
    }

    @Override
    public int numEdges() {
    	//O(1), return edge array size
        return edges.size();
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
    	//O(e), iterates through edge array
        ArrayList<Integer> neighbors = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).get(0) == v){
                neighbors.add(edges.get(i).get(1));
            }
            if(edges.get(i).get(1) == v){
                neighbors.add(edges.get(i).get(0));
            }
        }

        Iterable<Integer> result = neighbors;
        return result;
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
    	//O(e), iterates through edge array.
        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).get(0) == v){
                if(edges.get(i).get(1) == w){
                    return true;
                }
            }

            else if(edges.get(i).get(0) == w){
                if(edges.get(i).get(1) == v){
                    return true;
                }
            }
        }
        return false;
    }

}
