// =============================================================================
// ASSIGNMENT 4 — Task 2: BFS and DFS Traversal
// Student ID: 251612
// =============================================================================
//
// BFS (Breadth-First Search)
//   Data structure : QUEUE (LinkedList used as FIFO queue)
//   Strategy       : explore all neighbours before going deeper
//   Time           : O(V + E)  — every vertex and edge visited exactly once
//   Space          : O(V)      — queue holds at most V vertices
//
// DFS (Depth-First Search)
//   Data structure : RECURSION (implicit call stack)
//   Strategy       : dive as deep as possible, then backtrack
//   Time           : O(V + E)  — every vertex and edge visited exactly once
//   Space          : O(V)      — call stack depth is at most V
//
// ─────────────────────────────────────────────────────────────────────────────
// REPORT ANSWER — Which is better for shortest path in an UNWEIGHTED graph?
//
//   BFS guarantees the shortest path (fewest edges) in an unweighted graph.
//   It explores level-by-level (1 hop, then 2 hops, …), so the FIRST time
//   it reaches any vertex it has used the minimum number of edges.
//
//   DFS does NOT guarantee this — it may arrive via a long detour first.
//   DFS suits: cycle detection, topological sort, connected components.
// =============================================================================

import java.util.*;

public class Traversal {

    // ====================================================================== //
    //  BFS
    // ====================================================================== //
    public static List<String> bfs(Graph graph, String start) {
        if (!graph.hasVertex(start)) {
            System.out.println("[BFS] Vertex '" + start + "' not in graph.");
            return Collections.emptyList();
        }

        Set<String>    visited = new LinkedHashSet<>();
        Queue<String>  queue   = new LinkedList<>();
        List<String>   order   = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        System.out.println("\n==================================================");
        System.out.println("  BFS — starting from '" + start + "'");
        System.out.println("==================================================");
        System.out.println("  Init : enqueue '" + start + "'");

        while (!queue.isEmpty()) {
            String u = queue.poll();          // dequeue from front
            order.add(u);
            System.out.println("  Visit: " + u + "  |  queue -> " + queue);

            for (Graph.Edge e : graph.getNeighbours(u)) {
                if (!visited.contains(e.to)) {
                    visited.add(e.to);
                    queue.add(e.to);
                    System.out.println("           enqueue '" + e.to
                            + "'  (edge " + u + "-" + e.to + ", w=" + e.weight + ")");
                }
            }
        }

        System.out.println("\n  BFS order : " + order + "\n");
        return order;
    }

    // ====================================================================== //
    //  DFS  (public entry point)
    // ====================================================================== //
    public static List<String> dfs(Graph graph, String start) {
        if (!graph.hasVertex(start)) {
            System.out.println("[DFS] Vertex '" + start + "' not in graph.");
            return Collections.emptyList();
        }

        Set<String>  visited = new LinkedHashSet<>();
        List<String> order   = new ArrayList<>();

        System.out.println("\n==================================================");
        System.out.println("  DFS — starting from '" + start + "'");
        System.out.println("==================================================");

        dfsVisit(graph, start, visited, order, 0);

        System.out.println("\n  DFS order : " + order + "\n");
        return order;
    }

    // ====================================================================== //
    //  DFS  (recursive helper)
    // ====================================================================== //
    private static void dfsVisit(Graph graph, String u,
                                 Set<String> visited,
                                 List<String> order,
                                 int depth) {
        visited.add(u);
        order.add(u);

        String pad = "  ".repeat(depth + 1);
        System.out.println(pad + "Visit '" + u + "'");

        for (Graph.Edge e : graph.getNeighbours(u)) {
            if (!visited.contains(e.to)) {
                System.out.println(pad + "  -> descend to '"
                        + e.to + "'  (edge " + u + "-" + e.to + ", w=" + e.weight + ")");
                dfsVisit(graph, e.to, visited, order, depth + 1);
            } else {
                System.out.println(pad + "  x  skip '" + e.to + "' (already visited)");
            }
        }
    }
}