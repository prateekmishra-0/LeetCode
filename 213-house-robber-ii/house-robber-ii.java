class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int[] temp1 = new int[n-1];
        int[] temp2 = new int[n-1];
        int[] dp = new int[n-1];
        Arrays.fill(dp, -1);
        int[] dp2 = new int[n-1];
        Arrays.fill(dp2, -1);
        for(int i = 0;i<n-1;i++) temp1[i] = nums[i];
        for(int i = 1;i<n;i++) temp2[i-1] = nums[i];
        return Math.max(helper(temp1,n-1, dp),helper(temp2,n-1, dp2));
    }
    public int helper(int[] nums, int n, int[] dp){
        dp[0] = nums[0];
        for(int i = 1;i<n;i++){
            int pick = nums[i];
            if(i!=1) pick+=dp[i-2];
            int notPick = dp[i-1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n-1];

    }
}