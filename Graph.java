// =============================================================================
// ASSIGNMENT 4 — Task 1: Graph Representation (Adjacency List)
// Student ID: 251612
// =============================================================================
// Complexity:
//   addVertex      : O(1)  — single HashMap put
//   addEdge        : O(1)  — two list add() calls
//   printAdjList   : O(V + E) — iterate every vertex and every edge
//   Space          : O(V + E) — V keys, 2E Edge objects (undirected)
// =============================================================================

import java.util.*;

public class Graph {

    // Inner class to represent a weighted edge to a neighbour
    static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to     = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return to + "(w=" + weight + ")";
        }
    }

    // Adjacency list: vertex -> list of edges
    private Map<String, List<Edge>> adjList;

    // ------------------------------------------------------------------ //
    public Graph() {
        adjList = new LinkedHashMap<>();   // LinkedHashMap preserves insertion order
    }

    // ------------------------------------------------------------------ //
    // ADD VERTEX
    // Time: O(1)  |  Space: O(1)
    // ------------------------------------------------------------------ //
    public void addVertex(String v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    // ------------------------------------------------------------------ //
    // ADD EDGE  (undirected — adds both directions)
    // Time: O(1)  |  Space: O(1)
    // ------------------------------------------------------------------ //
    public void addEdge(String v, String w, int weight) {
        addVertex(v);
        addVertex(w);
        adjList.get(v).add(new Edge(w, weight));   // v --> w
        adjList.get(w).add(new Edge(v, weight));   // w --> v
    }

    // ------------------------------------------------------------------ //
    // PRINT ADJACENCY LIST
    // Time: O(V + E)
    // ------------------------------------------------------------------ //
    public void printAdjacencyList() {
        System.out.println("\n========== Adjacency List ==========");
        for (String vertex : adjList.keySet()) {
            List<Edge> neighbours = adjList.get(vertex);
            StringBuilder sb = new StringBuilder();
            for (Edge e : neighbours) {
                sb.append(e).append("  ");
            }
            System.out.println("  " + vertex + "  -->  " + sb.toString().trim());
        }
        System.out.println("=====================================\n");
    }

    // ------------------------------------------------------------------ //
    // HELPERS used by BFS / DFS / Dijkstra
    // ------------------------------------------------------------------ //
    public List<Edge> getNeighbours(String v) {
        return adjList.getOrDefault(v, Collections.emptyList());
    }

    public Set<String> getVertices() {
        return adjList.keySet();
    }

    public boolean hasVertex(String v) {
        return adjList.containsKey(v);
    }
}
