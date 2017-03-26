/**
Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

 Notice

You are not suppose to use the library's sort function for this problem.

k <= n

Have you met this question in a real interview? Yes
Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

Challenge 
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it without using extra memory?

Tags 
Two Pointers Sort
*/

// solution 1, O(nlog(k)), quickSort-like, but NOTE: here, log(k) instead of log(n), 
// and the algorithms are different in details! quicksort: nums[left] < mid, however sortColors2 colors[l] <= mid
// 1. left >= right rather than left > right



// solution 2, O(nk), so time limit exceeded, dominated , just because this is inherited from sort color I so put it here.
class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        int hasBeenSorted = 0;
        int start = 0;
        int end = colors.length - 1;
        
        while(hasBeenSorted < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int i = start; i <= end; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            
            int left = start;
            int right = end;
            int curr = start;
            while(curr <= right){
                if(colors[curr] == min) {
                    swap(curr, left, colors);
                    curr++;
                    left++;
                }
                else if(colors[curr] == max) {
                    swap(curr, right, colors);
                    right--;
                }
                else {
                    curr++;
                }
            }
            hasBeenSorted += 2;
            // NOTE:here left and right both pointing to the next candidate, not
            // the min the the max of this loop!
            start = left;
            end = right;
        }
        
    }
    
    private void swap( int i1, int i2, int[] array) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
