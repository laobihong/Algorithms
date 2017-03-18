/**
Maximum Subarray
Given an array of integers, find a contiguous subarray which has the largest sum.

 Notice

The subarray should contain at least one number.

Have you met this question in a real interview? Yes
Example
Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Challenge 
Can you do it in time complexity O(n)?

Tags 
LinkedIn Subarray Greedy Enumeration LintCode Copyright Array
Related Problems 
Medium Maximum Average Subarray 13 %
Medium Continuous Subarray Sum 24 %
Medium Best Time to Buy and Sell Stock III 27 %
Hard Maximum Subarray III 24%
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(ArrayList<Integer> nums) {
        
        if(nums == null || nums.size() == 0) {
            return 0;
        }
        
        int len = nums.size();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int lowest = 0; // lowest should be an index
        for(int i = 0; i < len; i++) {
            sum += nums.get(i);
            max = Math.max(max, sum - lowest);
            lowest = Math.min(lowest, sum);
        }
        
        return max;
    }
}

