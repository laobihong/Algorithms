/**
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers.

 Notice

You may assume that each input would have exactly one solution.

Have you met this question in a real interview? Yes
Example
For example, given array S = [-1 2 1 -4], and target = 1. The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Challenge 
O(n^2) time, O(1) extra space

Tags 
Two Pointers Sort Array
*/

public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers ,int target) {
        
        int sum = 0;
        int closest = Integer.MAX_VALUE;
        Arrays.sort(numbers);
        
        for(int i = 0; i < numbers.length - 2; i++) {
            int j = i + 1;
            int k = numbers.length - 1;
            while(j < k) {
                if(Math.abs(numbers[i] + numbers[j] + numbers[k] - target) < Math.abs(closest))
                {
                    sum = numbers[i] + numbers[j] + numbers[k];
                    closest = Math.abs(numbers[i] + numbers[j] + numbers[k] - target);

                }
                if(numbers[i] + numbers[j] + numbers[k] - target > 0) {
                    k--;
                }
                else {
                    j++;
                }
            }
            
        }
        
        return sum;
    }
}
