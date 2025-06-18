class Solution {
    public String crackSafe(int n, int k) {
        int t = (int) Math.pow(k, n-1);
        int[] seen = new int[t];

        StringBuilder s = new StringBuilder();
    
        Runnable dfs = new Runnable() {
            
        void bldSeq(int u) {
            while (seen[u] < k) {
                int d = seen[u]++;
                bldSeq((u * k + d) % t);
                s.append((char)('0' + d));
            }
        }
        public void run() { bldSeq(0); }
        };
        dfs.run();
    
        for (int i = 0; i < n-1; i++) s.append('0');
        return s.reverse().toString();
    }

}