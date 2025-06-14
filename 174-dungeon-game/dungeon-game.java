class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = dungeon[n - 1][m - 1] < 0 ?dungeon[n - 1][m - 1]:0;
        for(int i = n - 1;i >= 0;i--) {
            for(int j = m-1;j >= 0;j--) {
                if(i == n- 1 && j == m - 1) {
                    continue;
                }
                int value = dungeon[i][j];
                if(i == n - 1) {
                    int value1 = value +dp[i][j + 1];
                    if(value1 > 0) {
                        dp[i][j] = 0;
                    }
                    else {
                        dp[i][j] = value + dp[i][j + 1];
                    }
                    continue;
                }
                if(j == m - 1) {
                    int value1 = value + dp[i + 1][j];
                    if(value1 > 0) {
                        dp[i][j] = 0;
                    }
                    else {
                        dp[i][j] = value + dp[i + 1][j];
                    }
                    continue;
                }
                int first1 = value + dp[i + 1][j];
                int first2 = value + dp[i][j + 1];
                if(first1 >= 0) {
                    first1 = 0;
                }
                if(first2 >= 0) {
                    first2 = 0;
                }
                dp[i][j] = Math.max(first1,first2);
            }
            
        }
        return (int)Math.abs(dp[0][0]) + 1;
    }
}