/**
Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.

Example
Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].

Challenge 
O(nlogn) time

Tags 
Subarray Sort

Related Problems 
Medium Submatrix Sum 24 %
Medium Minimum Size Subarray Sum 27 %
Easy Subarray Sum 29 %
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    
    class Pair{
        int sum;
        int index;
        
        public Pair(int sum, int index) {
            this.sum = sum;
            this.index= index;
        }
    }
    
    public int[] subarraySumClosest(int[] nums) {
        
        final int LEN_ARRAY = 2;
        int[] points = new int[LEN_ARRAY];
        
        if (nums == null || nums.length == 0) {
            return points;
        }
        
        int len = nums.length;
        if (len == 1) {
            points[0] = points[1] = 0;
            return points;
        }
        
        Pair[] pairs = new Pair[len + 1];
        int sum = 0;
        pairs[0] = new Pair(0, 0);
        
        for(int i = 1; i <= len; i++) {
            sum += nums[i - 1];
            pairs[i] = new Pair(sum, i);
        }
        
        Arrays.sort(pairs, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            }
        });
        
        int distance = Integer.MAX_VALUE;
        for(int i = 1; i <= len; i++) {
            if (pairs[i].sum - pairs[i - 1].sum < distance) {
                distance = pairs[i].sum - pairs[i - 1].sum;
                points[0] = Math.min(pairs[i].index - 1, pairs[i - 1].index - 1) + 1;
                points[1] = Math.max(pairs[i].index - 1, pairs[i - 1].index - 1);
            }
        }
        
        return points;
    }
}
