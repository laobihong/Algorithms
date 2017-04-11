/**
Find the contiguous subarray within an array (containing at least one number) which has the largest product.
Example
For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.

Tags 
LinkedIn Dynamic Programming Subarray
Related Problems 
Medium Best Time to Buy and Sell Stock 41 %
Medium Maximum Subarray Difference 24 %
Easy Minimum Subarray 38 %
Medium Maximum Subarray II 25 %
*/

/**
Meaning of max and min: [any prev num, current i included] the maximum/minimum product of a subarray that start from ANY prev element and ENDS WITH CURRENT ITEM. Maximum and minimum will carry ALL the information about the positivity/negativity of ALL the prev elements, which product knows nothing about.
*/
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] max = new int[2];
        int[] min = new int[2];
        
        max[0] = nums[0];
        min[0] = nums[0];
        int ans = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            max[i % 2] = min[i % 2] = nums[i];
            if(nums[i] > 0) {
                max[i % 2] = Math.max(max[i % 2], max[1 - (i%2)] * nums[i]);
                min[i % 2] = Math.min(min[i % 2], min[1 - (i%2)] * nums[i]);
            }
            else if(nums[i] < 0) {
                max[i % 2] = Math.max(max[i % 2], min[1 - (i%2)] * nums[i]);
                min[i % 2] = Math.min(min[i % 2], max[1 - (i%2)] * nums[i]);
            }
            ans = Math.max(ans, max[i % 2]);
        }
        
        return ans;
    }
}
