class Solution {
    public int swimInWater(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distance = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Pair> min_heap = new PriorityQueue<>((a, b) -> a.time - b.time);
        min_heap.offer(new Pair(grid[0][0], 0, 0));
        distance[0][0] = grid[0][0];

        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!min_heap.isEmpty()) {
            Pair node = min_heap.poll();
            int t = node.time;
            int r = node.row;
            int c = node.col;

            if (r == rows - 1 && c == cols - 1) {
                return t;
            }

            for (int[] dir : direction) {
                int row = r + dir[0];
                int col = c + dir[1];

                if (row >= 0 && row < rows && col >= 0 && col < cols) {
                    int max_time = Math.max(t, grid[row][col]);
                    if (distance[row][col] > max_time) {
                        distance[row][col] = max_time;
                        min_heap.offer(new Pair(max_time, row, col));
                    }
                }
            }
        }

        return distance[rows - 1][cols - 1];
    }
}

class Pair {
    int time;
    int row;
    int col;

    Pair(int time, int row, int col) {
        this.time = time;
        this.row = row;
        this.col = col;
    }
}