public class Solution {
    /**
     * @param A an integer array
     * @return void
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        partition(A, 0, A.length - 1);
    }
    
    private void partition(int[] A, int start, int end) {
        if(start >= end) {
            return;
        }
        int pivot = A[start];
        int i = end;
        int j = end;
        while(start < j) {
            if (A[j] > pivot) {
                swap(A, i, j);
                i--;
            }
            j--;
        }
        swap(A, start, i);
        partition(A, start, i - 1);
        partition(A, i + 1, end);
    }

/**
    private void partition(int[] A, int start, int end) {
        if(start >= end) {
            return;
        }
        int left = start, right = end, pivot = A[start + (end - start) / 2];
        while(left <= right) {
            while(left <= right && A[left] < pivot) {
                left++;
            }
            while(left <= right && A[right] > pivot) {
                right--;
            }
            if(left <= right) {
                swap(A, left, right);
                left++;
                right--;
            }
        }
        partition(A, start, right);
        partition(A, left, end);
    }
*/
    
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
