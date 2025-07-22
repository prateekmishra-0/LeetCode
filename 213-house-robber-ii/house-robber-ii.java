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
        return Math.max(helper(temp1,n-2, dp),helper(temp2,n-2, dp2));
    }
    public int helper(int[] nums, int n, int[] dp){
        if(n == 0) return nums[n];
        if(n<0) return 0;
        if(dp[n] != -1) return dp[n];
        int pick = nums[n] + helper(nums, n-2, dp);
        int notPick = 0 + helper(nums, n-1, dp);
        return dp[n] = Math.max(pick,notPick);

    }
}