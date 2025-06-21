class Solution {
    public int divide(int dividend, int divisor) {
        
        // Handle overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

        // Convert to long to avoid overflow, and use absolute values
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        // Fast path for divisor == 1 or -1
        if (ldivisor == 1) {
            return sign * (int) ldividend;
        }

        int quotient = 0;
        while (ldividend >= ldivisor) {
            ldividend -= ldivisor;
            quotient++;
        }

        return sign * quotient;
    }
}