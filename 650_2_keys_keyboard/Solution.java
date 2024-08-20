/*
 * Problem:
 * There is only one character 'A' on the screen of a notepad. You can perform
 * one of two operations on this notepad for each step:
 * 
 * 1. Copy All: You can copy all the characters present on the screen (a partial
 * copy is not allowed).
 * 2. Paste: You can paste the characters which are copied last time.
 * 
 * Given an integer n, return the minimum number of operations to get the
 * character 'A' exactly n times on the screen.
 * 
 * Example 1:
 * Input: n = 3
 * Output: 3
 * Explanation: Initially, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 * 
 * Example 2:
 * Input: n = 1
 * Output: 0
 * 
 * Constraints:
 * 1 <= n <= 1000
 */

public class Solution {

    public int minSteps(int n) {
        if (n == 1) {
            return 0; // No operation needed if we only need 1 'A'
        }

        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i; // Initialize with a high number (worst case is i operations)
            // Check all factors of i
            for (int j = 1; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    int factor = j;
                    if (factor != i) {
                        dp[i] = Math.min(dp[i], dp[factor] + (i / factor));
                    }
                    factor = i / j;
                    if (factor != i) {
                        dp[i] = Math.min(dp[i], dp[factor] + j);
                    }
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSteps(3)); // Output: 3
        System.out.println(solution.minSteps(1)); // Output: 0
    }
}
