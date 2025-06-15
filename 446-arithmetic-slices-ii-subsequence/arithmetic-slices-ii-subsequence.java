class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] mp = new HashMap[n];
        int result = 0;

        for(int i = 0; i < n; i++){
            mp[i] = new HashMap<>();
            for(int j = 0; j < i; j++){
               long diff = (long)nums[i] - nums[j];

                int counter_j = mp[j].getOrDefault(diff, 0); 
                result += counter_j;

                mp[i].put(diff, mp[i].getOrDefault(diff, 0) + counter_j + 1);  
            }
        }
        return result;
    }
}