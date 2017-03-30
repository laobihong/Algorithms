/**
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.

Have you met this question in a real interview? Yes
Example
Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.

Challenge 
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

Tags 
Two Pointers Array Facebook
*/

    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int shortest = nums.length + 1;
        
        int left = 0, right = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            if (sum >= s) {
                shortest = Math.min(shortest, right - left + 1);
            }
            while(left < right && sum > s) {
                sum -= nums[left];
                left++;
                if (sum >= s) {
                    shortest = Math.min(shortest, right - left + 1);
                }
            }
            right++;
        }
        
        if (shortest == nums.length + 1) {
            return -1;
        }
        return shortest;
    }
