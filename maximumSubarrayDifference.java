/**
Maximum Subarray Difference
Given an array with integers.

Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.

Return the largest difference.

 Notice

The subarray should contain at least one number

Example
For [1, 2, -3, 1], return 6.

Challenge 
O(n) time and O(n) space.

Tags 
Greedy Enumeration LintCode Copyright Array Subarray Forward-Backward Traversal
Related Problems 
Medium Maximum Product Subarray 30 %
Medium Best Time to Buy and Sell Stock III 27 %
Hard Maximum Subarray III 24 %
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     */
    public int maxDiffSubArrays(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] leftMax = new int[n + 1];
        int[] rightMax = new int[n + 1];
        int[] leftMin = new int[n + 1];
        int[] rightMin = new int[n + 1];
        
        int sum = 0;
        int minSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
            sum += nums[i - 1];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            leftMax[i] = max;
// note, here we cannot use leftMax[i] = Math.max(leftMax[i - 1], sum - minSum, since leftMax[0] will be 0 instead of its currect value Integer.MIN_VALUE. 
        }
        
        sum = 0;
        minSum = 0;
        max = Integer.MIN_VALUE;
        for(int j = n - 1; j >= 1; j--) {
            sum += nums[j];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            rightMax[j] = max;
        }
        
        sum = 0;
        int maxSum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++) {
            sum += nums[i - 1];
            min = Math.min(min, sum - maxSum);
            maxSum = Math.max(maxSum, sum);
            leftMin[i] = min;
        }
        
        sum = 0;
        maxSum = 0;
        min = Integer.MAX_VALUE;
        for(int j = n - 1; j >= 1; j--) {
            sum += nums[j];
            min = Math.min(min, sum - maxSum);
            maxSum = Math.max(maxSum, sum);
            rightMin[j] = min;
        }
        
        int maximum = Integer.MIN_VALUE;
        for(int i = 1; i <= n - 1; i++) {
            int left = Math.abs(leftMax[i] - rightMin[i]);
            int right = Math.abs(rightMax[i] - leftMin[i]);
            maximum = Math.max(maximum, Math.max(left, right));
        }
        
        return maximum;
    }
}
