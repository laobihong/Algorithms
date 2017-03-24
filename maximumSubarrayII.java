/**
Given an array of integers, find two non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

 Notice

The subarray should contain at least one number

Example
For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.

Challenge 
Can you do it in time complexity O(n) ?

Tags 
Greedy Enumeration LintCode Copyright Array Subarray Forward-Backward Traversal
Related Problems 
Medium Maximum Product Subarray 30 %
Medium Best Time to Buy and Sell Stock III 27 %
Hard Maximum Subarray III

*/

/**
len + 1 version: should prefer this, each element in left/right means when the "plate" was placed at each position(0 to len), the maximum of left and right.
e.g. |0|1|2|3| we have four positions to place the plate.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
                
        if(nums == null || nums.size() == 0) {
            return 0;
        }
        
        int len = nums.size();
        int[] left = new int[len + 1];
        int[] right = new int[len + 1];
        
        int sum = 0;
        int minSum = 0;
        int max = Integer.MIN_VALUE;
        
        // left -> right
        
        for(int i = 1; i <= len - 1; i++) {
            sum += nums.get(i - 1);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            left[i] = max;
        }
        
        // right -> left
        sum = 0;
        minSum = 0;
        max = Integer.MIN_VALUE;
        for(int j = len - 1; j >= 1; j--) {
            sum += nums.get(j);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            right[j] = max;
        }
        
        int maximum = Integer.MIN_VALUE;
        for(int k = 1; k <= len - 1; k++) {
            maximum = Math.max((left[k]+right[k]), maximum);
        }
        
        return maximum;
    }
}


/**
len version
*/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        
        if(nums == null || nums.size() == 0) {
            return 0;
        }
        
        int len = nums.size();
        int[] left = new int[len];
        int[] right = new int[len];
        
        int sum = 0;
        int minSum = 0;
        int max = Integer.MIN_VALUE;
        
        // left -> right
        
        for(int i = 0; i < len; i++) {
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            left[i] = max;
        }
        
        // right -> left
        sum = 0;
        minSum = 0;
        max = Integer.MIN_VALUE;
        for(int j = len - 1; j >= 0; j-- ) {
            sum += nums.get(j);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(sum, minSum);
            right[j] = max;
        }
        
        int maximum = Integer.MIN_VALUE;
        for(int k = 0; k < len-1; k++) {
            maximum = Math.max((left[k]+right[k+1]), maximum);
        }
        
        return maximum;
    }
}

