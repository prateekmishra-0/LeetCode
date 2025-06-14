class Solution {
    private boolean isPalindrome(int i, int j, String str) {
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    private int recursion(int i, int n, String str) {
        // Base case
        if (i >= n)
            return 0;

        int mini = (int) (1e9);

        // Explore all paths
        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j, str)) {
                int cost = 1 + recursion(j + 1, n, str);
                mini = Math.min(mini, cost);
            }
        }

        return mini;
    }

    private int memoization(int i, int n, String str, int[] dp) {
        // Base case
        if (i >= n)
            return 0;

        // Overlapped subproblem
        if (dp[i] != 0)
            return dp[i];

        int mini = (int) (1e9);

        // Explore all paths
        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j, str)) {
                int cost = 1 + memoization(j + 1, n, str, dp);
                mini = Math.min(mini, cost);
            }
        }

        return dp[i] = mini;
    }

    private int tabulation(String str, int[] dp) {
        int n = str.length();
        for (int i = n - 1; i >= 0; i--) {
            int mini = (int) (1e9);

            // Explore all paths
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, str)) {
                    mini = Math.min(mini, 1 + dp[j + 1]);
                }
            }
            dp[i] = mini;
        }

        return dp[0] - 1; // -1 because the code considers the end as one more partition, which is not correct.
    }

    public int minCut(String s) {
        int n = s.length();

        int[] dp = new int[n + 1];

        return tabulation(s, dp);

        // return memoization(0, n, s, dp) - 1; // -1 because the code considers the end as one more partition, which is not correct.
        // return recursion(0, n, s) - 1; // -1 because the code considers the end as one more partition, which is not correct.
    }
}