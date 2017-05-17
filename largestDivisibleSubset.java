/*
Largest Divisible Subset 

 Description
 Notes
 Testcase
 Judge
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

 Notice

If there are multiple solutions, return any subset is fine.

Have you met this question in a real interview? Yes
Example
Given nums = [1,2,3], return [1,2] or [1,3]

Given nums = [1,2,4,8], return [1,2,4,8]

Tags 
Dynamic Programming
Related Problems 
Medium Longest Increasing Subsequence
*/

/*
pre[i] is like the father pointer in Union-Find. Every element has its pointer to some
previous element(or self) that is a factor of itself, since the array has been sorted!
Then all the element pointed by the previous element is also the factors of the current 
element. The meaning of count: size of the subset that contains the current element, so 
at least it's 1 (only self). 
*/
public class Solution {
    /**
     * @param nums a set of distinct positive integers
     * @return the largest subset 
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> subset = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return subset;
        }
        
        Arrays.sort(nums);
        
        int n = nums.length;
        int[] pre = new int[n];
        int[] count = new int[n];
        
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            pre[i] = i;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && count[j] + 1 > count[i]) {
                    count[i] = count[j] + 1;
                    pre[i] = j;
                }
            }
            if (count[i] > max) {
                max = count[i];
                maxIndex = i;
            }
        }
        
// NOTE: here cannot use do while, since the first round doesn't need to 
// assign maxIndex = pre[maxIndex]! Say no to Danaima!
        subset.add(nums[maxIndex]);
        while (maxIndex != pre[maxIndex]) {
            maxIndex = pre[maxIndex];
            subset.add(nums[maxIndex]);
        }
        
        Collections.reverse(subset);
        return subset;
    }
}
