
/**
 * Problem:
 * Design a class to find the kth largest element in a stream. Note that it is
 * the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * Implement the KthLargest class:
 * 
 * KthLargest(int k, int[] nums)
 * - Initializes the object with the integer k and the stream of integers nums.
 * 
 * int add(int val)
 * - Appends the integer val to the stream and returns the element representing
 * the kth largest element in the stream.
 * 
 * Example:
 * Input:
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output:
 * [null, 4, 5, 5, 8, 8]
 * 
 * Explanation:
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3); // return 4
 * kthLargest.add(5); // return 5
 * kthLargest.add(10); // return 5
 * kthLargest.add(9); // return 8
 * kthLargest.add(4); // return 8
 * 
 * Constraints:
 * - 1 <= k <= 10^4
 * - 0 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - -10^4 <= val <= 10^4
 * - At most 10^4 calls will be made to add.
 * - It is guaranteed that there will be at least k elements in the array when
 * you search for the kth element.
 */

import java.util.PriorityQueue;

public class Solution {
    private final PriorityQueue<Integer> minHeap;
    private final int k;

    public Solution(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(k);

        // Add the initial numbers to the heap
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }

        // The root of the min-heap is the kth largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        Solution kthLargest = new Solution(3, new int[] { 4, 5, 8, 2 });
        System.out.println(kthLargest.add(3)); // return 4
        System.out.println(kthLargest.add(5)); // return 5
        System.out.println(kthLargest.add(10)); // return 5
        System.out.println(kthLargest.add(9)); // return 8
        System.out.println(kthLargest.add(4)); // return 8
    }
}
