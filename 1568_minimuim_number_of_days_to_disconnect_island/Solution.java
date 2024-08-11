/*
 * Problem:
 * You are given an m x n binary grid where 1 represents land and 0 represents
 * water.
 * An island is a maximal 4-directionally (horizontal or vertical) connected
 * group of 1's.
 * 
 * The grid is said to be connected if we have exactly one island; otherwise, it
 * is said to be disconnected.
 * 
 * In one day, we are allowed to change any single land cell (1) into a water
 * cell (0).
 * 
 * Return the minimum number of days to disconnect the grid.
 * 
 * Example 1:
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 2
 * Explanation: We need at least 2 days to get a disconnected grid.
 * Change land grid[1][1] and grid[0][2] to water and get 2 disconnected
 * islands.
 * 
 * Example 2:
 * Input: grid = [[1,1]]
 * Output: 2
 * Explanation: A grid of full water is also disconnected ([[1,1]] -> [[0,0]]),
 * 0 islands.
 * 
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 30
 * - grid[i][j] is either 0 or 1.
 */

import java.util.*;

public class Solution {

    // Directions for 4-way connectivity
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int minDaysToDisconnect(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        // Check the number of islands in the grid
        int initialIslandCount = countIslands(grid);

        // If there are already more than 1 islands, return 0
        if (initialIslandCount > 1)
            return 0;

        // Try to remove each land cell and check the number of islands
        int minDays = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0; // Change land to water
                    if (countIslands(grid) > 1) {
                        minDays = Math.min(minDays, 1);
                    }
                    grid[i][j] = 1; // Revert back to land
                }
            }
        }

        if (minDays == Integer.MAX_VALUE) {
            return 2; // If not possible with one change, then at least 2 changes are needed
        }

        return minDays;
    }

    private int countIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, boolean[][] visited, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] { i, j });

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int x = cell[0];
            int y = cell[1];
            if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] == 0) {
                continue;
            }
            visited[x][y] = true;
            for (int[] dir : DIRECTIONS) {
                stack.push(new int[] { x + dir[0], y + dir[1] });
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test cases
        int[][] grid1 = {
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };
        System.out.println(sol.minDaysToDisconnect(grid1)); // Output: 2

        int[][] grid2 = {
                { 1, 1 }
        };
        System.out.println(sol.minDaysToDisconnect(grid2)); // Output: 2
    }
}