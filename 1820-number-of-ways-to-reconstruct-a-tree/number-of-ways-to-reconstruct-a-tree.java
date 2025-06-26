import java.util.*;

class Solution {
    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        // Build the graph
        for (int[] pair : pairs) {
            graph.computeIfAbsent(pair[0], k -> new HashSet<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new HashSet<>()).add(pair[0]);
        }
        
        // Sort nodes by the size of their adjacency lists in descending order
        List<Integer> nodes = new ArrayList<>(graph.keySet());
        nodes.sort((a, b) -> graph.get(b).size() - graph.get(a).size());
        
        // The root node must be the one with the largest adjacency list
        int root = nodes.get(0);
        if (graph.get(root).size() != graph.size() - 1) {
            return 0;
        }
        
        int res = 1;
        
        // Check the validity of the tree
        for (int node : nodes) {
            if (node == root) continue;
            
            int currDegree = graph.get(node).size();
            int parent = -1;
            int parentDegree = Integer.MAX_VALUE;
            
            // Find the parent of the current node
            for (int neighbor : graph.get(node)) {
                int degree = graph.get(neighbor).size();
                if (degree < parentDegree && degree >= currDegree) {
                    parentDegree = degree;
                    parent = neighbor;
                }
            }
            
            if (parent == -1) {
                return 0;
            }
            
            // Verify that the current node's neighbors are a subset of its parent's neighbors
            for (int neighbor : graph.get(node)) {
                if (neighbor == parent) continue;
                if (!graph.get(parent).contains(neighbor)) {
                    return 0;
                }
            }
            
            // Check if there might be multiple valid trees
            if (parentDegree == currDegree) {
                res = 2;
            }
        }
        
        return res;
    }
}