class Solution {
    private class Node {
        int dest;
        double probability;

        public Node(int dest, double probability) {
            this.dest = dest;
            this.probability = probability;
        }
    }

    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<List<Integer>> tree = buildTree(n, edges);

        Set<Integer> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 1.0));
        visited.add(1);

        while (!queue.isEmpty() && t >= 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                int numNeighbors = getUnvisitedNeighbors(tree.get(curr.dest), visited);

                if (curr.dest == target) {
                    if (numNeighbors == 0 || t == 0) {
                        return curr.probability;
                    }

                    return 0.0;
                }

                for (int next : tree.get(curr.dest)) {
                    if (!visited.contains(next)) {
                        double currProbability =  1.0 / ((double)numNeighbors);
                        queue.offer(new Node(next, curr.probability / numNeighbors));
                        visited.add(next);
                    }
                }
            }
            t--;
        }    

        return 0.0;
    }

    private int getUnvisitedNeighbors(List<Integer> neighbors, Set<Integer> visited) {
        int count = 0;
        for (int n : neighbors) {
            if (!visited.contains(n)) {
                count++;
            }
        }

        return count;
    }

    private List<List<Integer>> buildTree(int n, int[][] edges) {
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        return tree;
    }
}