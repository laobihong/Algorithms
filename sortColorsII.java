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
// I. left >= right rather than left > right, because next loop we have colorMid and colorMid + 1
//      rainbowSort(colors, left, r, colorFrom, colorMid);
//      rainbowSort(colors, l, right, colorMid + 1, colorTo);
// if we want to use colorMid as both the upper bound for left and lower bound for right, we have to do it this way:
/**
class Solution {

    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
    
    private void rainbowSort(int[] colors,
                            int left,
                            int right,
                            int colorFrom,
                            int colorTo) {
        if (colorFrom == colorTo) {
            return;
        }
        
        if (left >= right) {
            return;
        }
        
        int colorMid = colors[left + (right - left) / 2];
        int l = left, r = right;
        while (l <= r) {
            while (l <= r && colors[l] < colorMid) {
                l++;
            }
            while (l <= r && colors[r] > colorMid) {
                r--;
            }
            if (l <= r) {
                int temp = colors[l];
                colors[l] = colors[r];
                colors[r] = temp;
                
                l++;
                r--;
            }
        }
        
        rainbowSort(colors, left, r, colorFrom, colorMid);
        rainbowSort(colors, l, right, colorMid, colorTo);
    }
}
*/
// otherwise corner case: [1,1,3,3,3,3,3,3,3,3]. after first split, [3,3,3,3,3,3,3,3], colorMid = 2, while colorTo = 3, then in each iteration, 
// (l <= r colors[r] > colorMid) will be satisfied, and r-- got executed until r == -1, so we effectively split the array into [](left,r) and [3,3,3,3,3,3,3,3,3](l,right) again, infinite loop, stackoverflow

//II. in quicksort, mid could also exist in the split sub arrays, however here, mid will only appear in left subarray(l <= r && colors[l] <= colorMid) ATTN: colors[l] <= colorMid

//III. here, because in each iteration, we separate the array by using k/2 as the pivot, the array will be split into [1, ..., k/2] and [k/2 + 1, ..., k], so log(k) instead of log(n).
class Solution {
    
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }
    
    public void rainbowSort(int[] colors,
                            int start,
                            int end,
                            int colorFrom,
                            int colorTo) {
        if (colorFrom == colorTo) {
            return;
        }
        
        if (start >= end) {
            return;
        }
        
        int colorMid = (colorFrom + colorTo) / 2;
        int l = start, r = end;
        while (l <= r) {
            while (l <= r && colors[l] <= colorMid) {
                l++;
            }
            while (l <= r && colors[r] > colorMid) {
                r--;
            }
            if (l <= r) {
                int temp = colors[l];
                colors[l] = colors[r];
                colors[r] = temp;
                
                l++;
                r--;
            }
        }
        
        rainbowSort(colors, start, r, colorFrom, colorMid);
        rainbowSort(colors, l, end, colorMid + 1, colorTo);
    }
}


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
