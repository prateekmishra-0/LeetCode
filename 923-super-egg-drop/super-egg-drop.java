class Solution {
    int[][] memo;
    
    public int superEggDrop(int k, int n) {
        memo = new int[k + 1][n + 1];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        return solve(k, n);
    }

    private int solve(int eggs, int floors) {
        if (floors == 0 || floors == 1)
            return floors;
        if (eggs == 1)
            return floors;
        if (memo[eggs][floors] != -1)
            return memo[eggs][floors];
        
        int moves = floors;
        int left = 1, right = floors;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int breaks = solve(eggs - 1, mid - 1);
            int noBreaks = solve(eggs, floors - mid);
            int worst = 1 + Math.max(breaks, noBreaks);
            
            if (breaks > noBreaks)
                right = mid - 1;
            else
                left = mid + 1;
            
            moves = Math.min(moves, worst);
        }
        
        return memo[eggs][floors] = moves;
    }
}