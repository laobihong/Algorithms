/**
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

×/

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
