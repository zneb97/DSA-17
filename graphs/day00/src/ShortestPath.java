import java.util.*;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Integer> parent = new ArrayList<>();
        for (int i = 0; i < g.numVertices(); i++) {
            parent.add(Integer.MIN_VALUE);
        }

        queue.add(w);
        visited.add(w);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int neighbor : g.getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    parent.set(neighbor,vertex);
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        if (!visited.contains(v)) {
            return null;
        }

        //Build it back
        List<Integer> path = new LinkedList<>();
        int current = v;
        path.add(v);
        while (current != w) {
            current = parent.get(current);
            path.add(current);
        }
        return path;

    }

    public static int distanceBetween(Graph g, int v, int w) {
        List<Integer> go = shortestPath(g, v, w);
        if (go != null) {
            return go.size() - 1;
        }
        return -1;
    }

}