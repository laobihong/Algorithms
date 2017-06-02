/**
Two Sum - Input array is sorted 

 Description
 Notes
 Testcase
 Judge
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

 Notice

You may assume that each input would have exactly one solution.

Have you met this question in a real interview? Yes
Example
Given nums = [2, 7, 11, 15], target = 9
return [1, 2]

Tags 
Two Pointers Hash Table Array Amazon
Related Problems 
Medium Two Sum - Less than or equal to target 38 %
Easy Two Sum
*/

public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }

// follow up: what if not sorted

