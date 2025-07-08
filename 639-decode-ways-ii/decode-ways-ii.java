public class Solution {
    private static final int MOD = 1000000007;

    public static int numDecodings(String s) {
        if (s == null || s.isEmpty()) return 0;

        long prev = 1; 
        long curr = ways1(s.charAt(0)); 
        for (int i = 1; i < s.length(); i++) {
            char ch1 = s.charAt(i - 1), ch2 = s.charAt(i);
            long next = (curr * ways1(ch2) + prev * ways2(ch1, ch2)) % MOD;
            prev = curr;
            curr = next;
        }
        return (int) curr;
    }
    private static int ways1(char ch) {
        if (ch == '*') return 9;
        if (ch == '0') return 0;
        return 1;
    }
    private static int ways2(char ch1, char ch2) {
        if (ch1 == '*' && ch2 == '*') {
            return 15;
        } else if (ch1 == '*') {
            if (ch2 >= '0' && ch2 <= '6') return 2;
            else return 1;
        } else if (ch2 == '*') {
            if (ch1 == '1') return 9; 
            else if (ch1 == '2') return 6; 
            else return 0;
        } else {
            int num = (ch1 - '0') * 10 + (ch2 - '0');
            return (num >= 10 && num <= 26) ? 1 : 0;
        }
    }
}