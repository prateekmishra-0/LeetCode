class Solution {
    private int n;
    private int m;
    private int[][] mat;
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {0, 0}};
    
    private record BfsNode(int state, int distance) {}

    public int minFlips(int[][] mat) {
        n = mat.length;
        m = mat[0].length;
        this.mat = mat;
        
        Queue<BfsNode> queue = new LinkedList<>();
        int start = matrixToInt();
        queue.offer(new BfsNode(start, 0));
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        
        while (!queue.isEmpty()) {
            var bfsNode = queue.poll();
            int state = bfsNode.state;
            int distance = bfsNode.distance;
            if (state == 0) return distance;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int nextState = applyPatch(state, i, j);
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.offer(new BfsNode(nextState, distance + 1));
                    }
                }
            }
        }
        
        return -1;
    }
    
    private int matrixToInt() {
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = (result << 1) | mat[i][j];
            }
        }
        return result;
    }
    
    private void restoreMatrix(int value) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                mat[i][j] = value & 1;
                value >>= 1;
            }
        }
    }
    
    private int applyPatch(int state, int i, int j) {
        restoreMatrix(state);
        for (int[] dir : directions) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                mat[ni][nj] = mat[ni][nj] == 0 ? 1 : 0;
            }
        }
        return matrixToInt();
    }
}