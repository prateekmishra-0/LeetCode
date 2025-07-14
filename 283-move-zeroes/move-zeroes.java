class Solution {
    public void moveZeroes(int[] nums) {
        int l = nums.length;
        int[] ans = new int[l];
        Arrays.fill(ans,0);
        int j = 0;
        for(int i = 0;i<l;i++){
            if(nums[i]!=0){
                ans[j] = nums[i];
                j++;
            }
        }
        for(int k = 0;k<l;k++){
            nums[k] = ans[k];
        }
    }
}