class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0;i<n;i++){
            sum+=nums[i];
        }
        int[][] dp = new int[n][sum];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        if(sum%2 == 0) return helper(nums,n-1,sum/2,dp);
        else return false;
    }
    public boolean helper(int[] nums,int n,int target, int[][] dp){
        if(target == 0) return true;
        if(n == 0) return nums[n] == target;
        if(dp[n][target]!= -1) return dp[n][target] == 1 ? true : false;
        boolean notTaken = helper(nums,n-1,target,dp);
        boolean taken = false;
        if(nums[n]<=target){
            taken = helper(nums,n-1,target-nums[n],dp);
        }
        dp[n][target] = taken||notTaken ? 1:0;
        return taken || notTaken;
    }
}