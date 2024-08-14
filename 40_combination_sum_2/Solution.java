/*
 * Problem:
 * Given a collection of candidate numbers (candidates) and a target number
 * (target),
 * find all unique combinations in candidates where the candidate numbers sum to
 * target.
 * 
 * Each number in candidates may only be used once in the combination.
 * The solution set must not contain duplicate combinations.
 * 
 * Example 1:
 * Input: candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
 * Output:
 * [
 * [1, 1, 6],
 * [1, 2, 5],
 * [1, 7],
 * [2, 6]
 * ]
 * 
 * Example 2:
 * Input: candidates = [2, 5, 2, 1, 2], target = 5
 * Output:
 * [
 * [1, 2, 2],
 * [5]
 * ]
 * 
 * Constraints:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remaining,
            int start) {
        if (remaining < 0) {
            return; // If the sum exceeds the target, stop
        } else if (remaining == 0) {
            result.add(new ArrayList<>(tempList)); // Found a combination
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1])
                    continue; // Skip duplicates
                tempList.add(candidates[i]);
                backtrack(result, tempList, candidates, remaining - candidates[i], i + 1);
                tempList.remove(tempList.size() - 1); // Backtrack
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] candidates1 = { 10, 1, 2, 7, 6, 1, 5 };
        int target1 = 8;
        System.out.println(sol.combinationSum2(candidates1, target1));

        int[] candidates2 = { 2, 5, 2, 1, 2 };
        int target2 = 5;
        System.out.println(sol.combinationSum2(candidates2, target2));
    }
}
