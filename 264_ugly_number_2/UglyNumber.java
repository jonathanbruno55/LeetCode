/*
 * Problem: Ugly Number II
 * 
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * 
 * Given an integer n, return the nth ugly number.
 * 
 * Example 1:
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * 
 * Example 2:
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 * 
 * Constraints:
 * 1 <= n <= 1690
 */

class Solution {
    public int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1; // The first ugly number is 1

        int index2 = 0, index3 = 0, index5 = 0; // Pointers for multiples of 2, 3, and 5
        int nextMultipleOf2 = 2;
        int nextMultipleOf3 = 3;
        int nextMultipleOf5 = 5;

        for (int i = 1; i < n; i++) {
            // The next ugly number is the minimum of the next multiples
            int nextUglyNumber = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
            uglyNumbers[i] = nextUglyNumber;

            // Update the pointers for the next multiples
            if (nextUglyNumber == nextMultipleOf2) {
                index2++;
                nextMultipleOf2 = uglyNumbers[index2] * 2;
            }
            if (nextUglyNumber == nextMultipleOf3) {
                index3++;
                nextMultipleOf3 = uglyNumbers[index3] * 3;
            }
            if (nextUglyNumber == nextMultipleOf5) {
                index5++;
                nextMultipleOf5 = uglyNumbers[index5] * 5;
            }
        }

        return uglyNumbers[n - 1]; // Return the nth ugly number
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 10;
        System.out.println("The " + n + "th ugly number is: " + solution.nthUglyNumber(n));
    }
}
