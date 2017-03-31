import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphProblems {

    public static boolean connected(Graph g, int v, int u) {
        ArrayList<Integer> visited = new ArrayList<>();
        depthFirstSearch(g, v, visited);
        return visited.contains(u);
    }

    public static void depthFirstSearch(Graph g, int v, ArrayList<Integer> visited){
        visited.add(v);
        for(int neighbor : g.getNeighbors(v)){
            if(!visited.contains(neighbor)){
                depthFirstSearch(g, neighbor, visited);
            }
        }
    }


    public static List<Integer> topologicalOrder(Digraph g) {
        LinkedList<Integer> ordered = new LinkedList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        for(int vertex : g.vertices()){
            if(!visited.contains(vertex)){
                depthFirstSearchTop(g, vertex, ordered, visited);
            }
        }
        return ordered;
    }

    public static void depthFirstSearchTop(Digraph g, int v, LinkedList<Integer> ordered, ArrayList<Integer> visited) {
        visited.add(v);
        for (int n : g.getNeighbors(v)) {
            if (!visited.contains(n)) {
                depthFirstSearchTop(g, n, ordered, visited);
            }
        }
        ordered.addFirst(v);
    }

    public static boolean hasCycle(UndirectedGraph g) {
        ArrayList<Integer> visited = new ArrayList<>();
        //Loop through each vertex
        for(int vertex : g.vertices()){
            if(!visited.contains(vertex)){
               if(depthFirstSearchCycle(g, vertex, visited, Integer.MIN_VALUE)){
                   return true;
               }
            }
        }
        //Made it through
        return false;
    }

    public static boolean depthFirstSearchCycle(Graph g, int v, ArrayList<Integer> visited, int parent){
        visited.add(v);

        //Normal loop case
        for(int neightbor : g.getNeighbors(v)){
            if(!visited.contains(neightbor)){
                if(depthFirstSearchCycle(g, neightbor, visited, v)){
                    return true;
                }
            }

            //End of path
            else if(neightbor != parent){
                return true;
            }
        }

        //Made it through
        return false;
    }


}