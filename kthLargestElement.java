/*
Find K-th largest element in an array.

 Notice

You can swap elements in the array

Example
In array [9,3,2,4,8], the 3rd largest element is 4.

In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

Challenge 
O(n) time, O(1) extra memory.

Tags 
Quick Sort Sort
Related Problems 
Medium Wiggle Sort II 25 %
Medium Kth Smallest Number in Sorted Matrix 22 %
Easy Median 23 %


// solution 1: always use the first number as the pivot
class Solution {

    public int kthLargestElement(int k, int[] numbers) {
        return helper(0, numbers.length - 1, k, numbers);
    }
    
    private int helper(int start, int end, int k, int[] numbers) {
        
        if( k < 1 || k > numbers.length) {
            throw new IllegalArgumentException();
        }
        if(start > end) {
            return 0; // ??
        }
        
        int pos = partition(start, end, numbers);
        if(pos == k - 1) {
            return numbers[pos];
        }
        else if(pos > k - 1) {
            return helper(start, pos - 1, k, numbers);
        }
        else {
            return helper(pos + 1, end, k, numbers);
        }
    }
    
    private int partition(int start, int end, int[] numbers) {
        int partition = numbers[start];
        int i = end, j = end; 
        while(j > start) {
            if(numbers[j] <= partition) {
                swap(numbers, i, j);
                i--;
            }
            j--;
        }
        
        swap(numbers, i, start);
        return i;
    }
    
    private void swap(int[] numbers, int i, int j ) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
    
}
*/

// wrong solution 1
    private int partition(int[] nums, int start, int end, int k) {
        int left = start, right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        
        if (right - start + 1 < k) {
            return partition(nums, right + 1, end, k - right + start - 1);
        }
        else if (left - start + 1 > k) {
            return partition(nums, start, left - 1, k);
        }
        else {
            return nums[right + 1];
        }
     }

//=========================
// Template from Lewis Liu

    public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return -1;
        }
        
        return partition(nums, 0, nums.length - 1, k);
    }
    
    private int partition(int[] nums, int start, int end, int k) {
        int left = start, right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        
        if (right - start + 1 >= k) {
            return partition(nums, start, right, k);
        }
        else if (left - start + 1 <= k) {
            return partition(nums, left, end, k - (left - start));
        }
        else {
            return nums[right + 1];
        }
        /* ATTN: here [start,...,right,(1 or 0 elements),left,...,end]
we must use (start, right) and (left, end) as the next pairs. It is
WRONG to use (start, left - 1) and (right + 1, end), as written above in wrong solution 1, since there might be
cases like right[ (some element), left,...,end], i.e. right is already == -1.
then if we pass (3,{9,8,4,3,2}), then right + 1 == end == 3, infinite loop.
why this solution works? because it TAKES THE RESPONSE in the next loop ITSELF instead of pushing to other 
section of the array. e.g. if right - start + 1 (the length of the left section) >= k, then we are guaranteed that
if we use (nums, start, right, k) we MUST have the solution somewhere. To the contrary, if we use solution 2, we don't know where the solution EXACTLY would be. e.g. 3,{9,8,4,3,2} we should return nums[right + 1] instead! Namely, we failed to rule out the possibility the first if contains some cases that overlap with those in the last else.
*/
     }

