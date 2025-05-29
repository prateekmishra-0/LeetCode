class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n==1) return s;
        String sb = s.substring(0,1);
        for(int i = 0;i<n;i++){
            String odd = expand(s,i,i);
            String even = expand(s,i,i+1);
            if(odd.length()>=sb.length()) sb = odd;
            if(even.length()>=sb.length()) sb = even;
        }
        return sb;
    }
    public String expand(String s, int l,int r){
        int n = s.length();
        while(l>=0 && r<n && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return s.substring(l+1,r);
    }
}