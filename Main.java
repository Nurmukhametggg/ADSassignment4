// =============================================================================
// ASSIGNMENT 4 — Main.java
// Student ID : 251612
// Graph      : 7 vertices (A–G), undirected weighted
// Task 2     : BFS and DFS starting from node  C
// Task 3     : Dijkstra source node            D
// =============================================================================

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ── YOUR EXACT GRAPH DATA (from Student ID 251612) ──────────────── //
        //  Vertices : A, B, C, D, E, F, G
        //  Edges read from the Gemini-generated assignment page:
        //
        //    B-A:9   C-A:7   D-A:6   E-B:2   C-B:6
        //    D-C:2   E-C:3   F-C:6   E-D:4   A-E:7
        //    A-G:14  F-E:3
        // ────────────────────────────────────────────────────────────────── //

        Graph g = new Graph();

        // Add vertices explicitly (satisfies add_vertex requirement)
        for (String v : new String[]{"A", "B", "C", "D", "E", "F", "G"}) {
            g.addVertex(v);
        }

        // Add all edges
        g.addEdge("B", "A",  9);
        g.addEdge("C", "A",  7);
        g.addEdge("D", "A",  6);
        g.addEdge("E", "B",  2);
        g.addEdge("C", "B",  6);
        g.addEdge("D", "C",  2);
        g.addEdge("E", "C",  3);
        g.addEdge("F", "C",  6);
        g.addEdge("E", "D",  4);
        g.addEdge("A", "E",  7);
        g.addEdge("A", "G", 14);
        g.addEdge("F", "E",  3);

        // ================================================================= //
        //  TASK 1 — Print adjacency list
        // ================================================================= //
        printBanner("TASK 1: Graph Representation (Adjacency List)");
        g.printAdjacencyList();

        // ================================================================= //
        //  TASK 2 — BFS and DFS from node C
        // ================================================================= //
        printBanner("TASK 2: Graph Traversal — BFS & DFS from 'C'");

        List<String> bfsResult = Traversal.bfs(g, "C");
        List<String> dfsResult = Traversal.dfs(g, "C");

        System.out.println("-".repeat(60));
        System.out.println("  SUMMARY");
        System.out.println("  BFS order : " + bfsResult);
        System.out.println("  DFS order : " + dfsResult);
        System.out.println();
        System.out.println("  REPORT ANSWER:");
        System.out.println("  +-------------------------------------------------------+");
        System.out.println("  | BFS is better for shortest paths in UNWEIGHTED graphs.|");
        System.out.println("  | It explores level-by-level (1 hop, then 2 hops...),   |");
        System.out.println("  | so the FIRST time it reaches a vertex it has used     |");
        System.out.println("  | the fewest possible edges = the shortest path.        |");
        System.out.println("  | DFS gives no such guarantee.                          |");
        System.out.println("  +-------------------------------------------------------+");

        // ================================================================= //
        //  TASK 3 — Dijkstra from node D
        // ================================================================= //
        printBanner("TASK 3: Dijkstra's Shortest Path from 'D'");

        Dijkstra.run(g, "D");

        System.out.println("  Shortest paths from 'D':");
        Dijkstra.printShortestPaths("D");
    }

    // ── helper: print a section banner ─────────────────────────────────── //
    private static void printBanner(String title) {
        String line = "=".repeat(60);
        System.out.println("\n" + line);
        System.out.println("  " + title);
        System.out.println(line);
    }
}
