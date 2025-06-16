class Solution {

    List <int[]> dividedRange;
    int numberOfDivisions = 0;
    Random rand;
    public Solution(int n, int[] arr) {
        Arrays.sort(arr);
        rand = new Random();
        dividedRange = new ArrayList <>();
        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            end = arr[i] - 1;
            if (start > end) {
                start = arr[i] + 1;
                continue;
            }
            dividedRange.add(new int[]{start, end});
            start = arr[i] + 1;
        }
        if (start < n) {
            dividedRange.add(new int[]{start, n-1});
        } 
        numberOfDivisions = dividedRange.size();
    }
    
    public int pick() {
        int pickedDivision = rand.nextInt(numberOfDivisions);
        int start = dividedRange.get(pickedDivision)[0];
        int end = dividedRange.get(pickedDivision)[1];
        return rand.nextInt(end - start + 1) + start;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */