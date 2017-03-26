/**
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Notice

You are not suppose to use the library's sort function for this problem. 
You should do it in-place (sort numbers in the original array).

Have you met this question in a real interview? Yes
Example
Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].

Challenge 
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

Tags 
Two Pointers Sort Array Facebook

*/

// two takeaways: 
// 1. start ALWAYS points to the next 1 when cur > start, e.g. 0,0,0,1,1,1,0,2
                                                                     |     | 
//                                                                 start  cur
// 2. this is an asymmetric structure, start != end, i.e. the number on the left of start all been examined and placed, however this is not the case for the number on the right of end! Hence, if and else do not share identical structure.
class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }
        
        int start = 0, end = nums.length - 1, cur = 0;
        
        while(cur <= end) {
            if (nums[cur] == 0) {
                swap(nums, cur, start);
                start++;
                cur++;
            }
            else if(nums[cur] == 1) {
                cur++;
            }
            else {
                swap(nums, cur, end);
                end--;
            }
        }
        
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


