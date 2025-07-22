class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        int[] temp1 = new int[n-1];
        int[] temp2 = new int[n-1];
        for(int i = 0;i<n-1;i++) temp1[i] = nums[i];
        for(int i = 1;i<n;i++) temp2[i-1] = nums[i];
        return Math.max(helper(temp1,n-1),helper(temp2,n-1));
    }
    public int helper(int[] nums, int n){
        int prev = nums[0];
        int prev2 = 0;
        int curri = 0;
        for(int i = 1;i<n;i++){
            int pick = nums[i];
            if(i!=1) pick+=prev2;
            int notPick = prev;
            curri = Math.max(pick, notPick);
            prev2 = prev;
            prev = curri;
        }
        return prev;

    }
}