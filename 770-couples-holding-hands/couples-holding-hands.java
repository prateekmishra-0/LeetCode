class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[row[i]] = i;

        int swaps = 0;
        for (int i = 0; i < n; i += 2) {
            int x = row[i];
            int y = row[i + 1];
            int want = x ^ 1; 

            if (y != want) {
                swaps++;
                int p = pos[want];
                pos[y] = p;
                row[p] = y;

                row[i + 1] = want;
                pos[want] = i + 1;
            }
        }
        return swaps;
    }
}