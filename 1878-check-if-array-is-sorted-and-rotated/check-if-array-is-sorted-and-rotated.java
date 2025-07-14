class Solution {
    public boolean check(int[] nums) {
        int c = 0;
        int l = nums.length;
        if(nums[l-1]>nums[0]) c++;
        for(int i = 0;i<l-1;i++){
            if(nums[i+1]<nums[i]) c++;
        }
        return c<2;
    }
}