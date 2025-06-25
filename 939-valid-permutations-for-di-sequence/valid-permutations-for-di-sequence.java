class Solution {
    boolean[] vis;
    Integer[][] memo;
    int mod = (int)1e9+7;
    public int numPermsDISequence(String s) {
        vis = new boolean[s.length()+2];
        memo = new Integer[s.length()][s.length()+3];
        return solve(s,0,-1);
    }
    private int solve(String s,int idx,int prev){
        if(idx >= s.length()){
            return 1;
        } else if(memo[idx][prev+1] != null){
            return memo[idx][prev+1];
        }

        int cnt = 0;
        for(int i=0;i<=s.length();i++){
            if(!vis[i]){
                vis[i] = true;
                if(prev == -1){
                    cnt = (cnt + solve(s,idx,i)) % mod;
                } else if((s.charAt(idx) == 'D' && i < prev) 
                    || s.charAt(idx) == 'I' && i > prev){
                        
                    cnt = (cnt + solve(s,idx+1,i)) % mod;
                }
                vis[i] = false;
            }
        }

        return memo[idx][prev+1] = cnt;
    }
}