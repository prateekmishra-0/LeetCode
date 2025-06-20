class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length,n = grid[0].length();

        int startRow = -1,startCol = -1;
        int allKeys = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                char ch = grid[i].charAt(j);
                if(ch == '@') {
                    startRow = i;
                    startCol = j;
                }
                if(ch >= 'a' && ch <= 'f') allKeys |= (1 << (ch - 'a'));
            }
        }
        
        boolean[][][] visited = new boolean[m][n][64];
        visited[startRow][startCol][0] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startRow,startCol,0,0});

        int[] dRow = {0,-1,0,1};
        int[] dCol = {-1,0,1,0};

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0],c = curr[1],dis = curr[2],keys = curr[3];

            if(keys == allKeys) return dis;

            for(int d=0;d<4;d++) {
                int nRow = r + dRow[d];
                int nCol = c + dCol[d];
                int newKeys = keys;

                if(nRow < 0 || nRow >= m || nCol < 0 || nCol >= n) continue;

                char ch = grid[nRow].charAt(nCol);
                if(ch == '#') continue;

                if(ch >= 'A' && ch <= 'F') {
                    if((keys & (1 << (ch - 'A'))) == 0) continue;
                }

                if(ch >= 'a' && ch <= 'f') {
                    newKeys |= (1 << (ch - 'a'));
                }

                if(!visited[nRow][nCol][newKeys]) {
                    visited[nRow][nCol][newKeys] = true;
                    q.offer(new int[]{nRow,nCol,dis + 1,newKeys});
                }
            }
        }

        return -1;
    }
}