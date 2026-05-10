// =============================================================================
// ASSIGNMENT 4 — Task 3: Dijkstra's Shortest Path Algorithm
// Student ID: 251612
// =============================================================================
//
// Finds the shortest (minimum-weight) path from source S to all other vertices.
//
// Core idea (greedy):
//   Always expand the unsettled vertex with the smallest known distance.
//   A PriorityQueue (min-heap) makes this O(log V) instead of O(V).
//
// Time  : O((V + E) log V)
//           — each vertex pushed/popped at most once  -> O(V log V)
//           — each edge relaxation may push to heap   -> O(E log V)
// Space : O(V + E)  — dist[], prev[], and heap entries
//
// WARNING: edge weights must be NON-NEGATIVE.
//          Use Bellman-Ford O(V * E) for negative weights.
// =============================================================================

import java.util.*;

public class Dijkstra {

    // Inner class for priority queue entries
    static class Node implements Comparable<Node> {
        String vertex;
        int dist;

        Node(String vertex, int dist) {
            this.vertex = vertex;
            this.dist   = dist;
        }

        // Min-heap: smaller distance = higher priority
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    // ====================================================================== //
    //  DIJKSTRA
    // ====================================================================== //
    public static Map<String, Integer> dist = new LinkedHashMap<>();
    public static Map<String, String>  prev = new LinkedHashMap<>();

    public static void run(Graph graph, String source) {
        if (!graph.hasVertex(source)) {
            System.out.println("[Dijkstra] Source '" + source + "' not in graph.");
            return;
        }

        final int INF = Integer.MAX_VALUE;

        // Initialise distances to infinity, predecessor to null
        for (String v : graph.getVertices()) {
            dist.put(v, INF);
            prev.put(v, null);
        }
        dist.put(source, 0);

        // Min-heap priority queue
        PriorityQueue<Node> heap = new PriorityQueue<>();
        heap.add(new Node(source, 0));

        Set<String> settled = new HashSet<>();

        System.out.println("\n=======================================================");
        System.out.println("  Dijkstra — source node '" + source + "'");
        System.out.println("=======================================================");
        System.out.println("  Init: dist[" + source + "] = 0,  all others = INF\n");

        while (!heap.isEmpty()) {
            Node curr = heap.poll();
            String u  = curr.vertex;

            if (settled.contains(u)) continue;   // stale entry — skip
            settled.add(u);

            System.out.println("  Settle '" + u + "'  (dist = " + dist.get(u) + ")");

            for (Graph.Edge e : graph.getNeighbours(u)) {
                String v = e.to;
                if (settled.contains(v)) continue;

                int newDist = dist.get(u) + e.weight;
                if (newDist < dist.get(v)) {
                    dist.put(v, newDist);
                    prev.put(v, u);
                    heap.add(new Node(v, newDist));
                    System.out.println("    update '" + v + "': "
                            + dist.get(u) + " + " + e.weight + " = " + newDist
                            + "  (via '" + u + "')");
                }
            }
        }
        System.out.println();
    }

    // ====================================================================== //
    //  PATH RECONSTRUCTION
    // ====================================================================== //
    public static List<String> reconstructPath(String source, String target) {
        List<String> path = new ArrayList<>();
        String cur = target;

        while (cur != null) {
            path.add(cur);
            cur = prev.get(cur);
        }

        Collections.reverse(path);

        // If path doesn't start at source, target is unreachable
        if (path.isEmpty() || !path.get(0).equals(source)) {
            return Collections.emptyList();
        }
        return path;
    }

    // ====================================================================== //
    //  PRINT RESULTS TABLE
    // ====================================================================== //
    public static void printShortestPaths(String source) {
        System.out.printf("  %-10s %10s    %s%n", "Vertex", "Distance", "Path");
        System.out.println("  " + "-".repeat(52));

        for (String v : new TreeSet<>(dist.keySet())) {
            int d = dist.get(v);
            if (d == Integer.MAX_VALUE) {
                System.out.printf("  %-10s %10s    (unreachable)%n", v, "INF");
            } else {
                List<String> path = reconstructPath(source, v);
                String pathStr = String.join(" -> ", path);
                System.out.printf("  %-10s %10d    %s%n", v, d, pathStr);
            }
        }
        System.out.println();
    }
}
