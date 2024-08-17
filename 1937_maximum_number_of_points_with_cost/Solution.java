/*
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, 
 * you want to maximize the number of points you can get from the matrix.
 * 
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates 
 * (r, c) will add points[r][c] to your score.
 * 
 * However, you will lose points if you pick a cell too far from the cell that you 
 * picked in the previous row. For every two adjacent rows r and r + 1 
 * (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) 
 * will subtract abs(c1 - c2) from your score.
 * 
 * Return the maximum number of points you can achieve.
 * 
 * abs(x) is defined as:
 * - x for x >= 0.
 * - -x for x < 0.
 * 
 * Example 1:
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 * 
 * Example 2:
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 * 
 * Constraints:
 * - m == points.length
 * - n == points[r].length
 * - 1 <= m, n <= 10^5
 * - 1 <= m * n <= 10^5
 * - 0 <= points[r][c] <= 10^5
 */

public class Solution {
    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[] dp = new long[n];

        // Initialize dp array with the first row
        for (int j = 0; j < n; j++) {
            dp[j] = points[0][j];
        }

        // Process each subsequent row
        for (int i = 1; i < m; i++) {
            long[] left = new long[n];
            long[] right = new long[n];
            long[] newDp = new long[n];

            // Calculate the best score for each column from left to right
            left[0] = dp[0];
            for (int j = 1; j < n; j++) {
                left[j] = Math.max(left[j - 1] - 1, dp[j]);
            }

            // Calculate the best score for each column from right to left
            right[n - 1] = dp[n - 1];
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] - 1, dp[j]);
            }

            // Calculate the maximum score for the current row
            for (int j = 0; j < n; j++) {
                newDp[j] = points[i][j] + Math.max(left[j], right[j]);
            }

            // Update dp array for the next row
            dp = newDp;
        }

        // Return the maximum score in the last row
        long result = 0;
        for (long val : dp) {
            result = Math.max(result, val);
        }
        return result;
    }
}
