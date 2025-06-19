class Solution {
    public int sumSubseqWidths(int[] nums) {
        int mod=1000000007;
        Arrays.sort(nums);
        int n=nums.length;
        int ans=0;
        long[] mul=new long[n];
        mul[0]=1;
        for(int i=1;i<n;i++){
            mul[i]=(2*mul[i-1])%mod;
        }
        for(int i=0;i<n;i++){
            ans=(int)(ans%mod+((mul[i]-mul[n-i-1])*nums[i])%mod)%mod;
        }
        return ans;
    }
}