class Solution {
    public int rob(int[] nums) {
        int l = nums.length;
        int[] dp = new int[l];
        Arrays.fill(dp,-1);
        return helper(nums,l-1,dp);
    }
    public int helper(int[] nums, int l,int[] dp){
        if(l == 0) return nums[0];
        if(l<0) return 0;
        if(dp[l]!=-1) return dp[l];
        int pick = nums[l] + helper(nums, l-2, dp);
        int notPick = 0+ helper(nums, l-1, dp);
        return dp[l] = Math.max(pick,notPick);
    }
}