/*
 * Problem:
 * You are given `m` arrays, where each array is sorted in ascending order.
 * You can pick two integers from two different arrays (each array picks one) and calculate the distance.
 * We define the distance between two integers `a` and `b` to be their absolute difference |a - b|.
 * 
 * Return the maximum distance.
 * 
 * Example 1:
 * Input: arrays = [[1, 2, 3], [4, 5], [1, 2, 3]]
 * Output: 4
 * Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array 
 *              and pick 5 in the second array.
 * 
 * Example 2:
 * Input: arrays = [[1], [1]]
 * Output: 0
 * 
 * Constraints:
 * - m == arrays.length
 * - 2 <= m <= 10^5
 * - 1 <= arrays[i].length <= 500
 * - -10^4 <= arrays[i][j] <= 10^4
 * - arrays[i] is sorted in ascending order.
 * - There will be at most 10^5 integers in all the arrays.
 */

import java.util.List;

public class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        // Initialize the minimum and maximum values with the first array's elements
        int minVal = arrays.get(0).get(0);
        int maxVal = arrays.get(0).get(arrays.get(0).size() - 1);

        int maxDistance = 0;

        // Iterate through the remaining arrays
        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> array = arrays.get(i);
            int currentMin = array.get(0);
            int currentMax = array.get(array.size() - 1);

            // Calculate the potential maximum distance for this array
            maxDistance = Math.max(maxDistance, Math.abs(currentMax - minVal));
            maxDistance = Math.max(maxDistance, Math.abs(maxVal - currentMin));

            // Update the global minimum and maximum values
            minVal = Math.min(minVal, currentMin);
            maxVal = Math.max(maxVal, currentMax);
        }

        return maxDistance;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();
        List<List<Integer>> arrays = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(1, 2, 3));
        System.out.println(solver.maxDistance(arrays)); // Output: 4
    }
}
