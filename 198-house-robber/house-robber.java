class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int prev = nums[0];
        int prev2 = nums[0];
        int curri = 0;
        for(int i = 1;i<n;i++){
            int pick = nums[i];
            if(i!=1) pick+=prev2;
            int notPick = prev;
            curri = Math.max(pick,notPick);
            prev2 = prev;
            prev = curri;
        }
        return prev;
    }
}