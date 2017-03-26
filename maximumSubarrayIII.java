/**
Maximum Subarray III
Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

 Notice

The subarray should contain at least one number

Example
Given [-1,4,-2,3,-2,3], k=2, return 8

Tags 
LintCode Copyright Dynamic Programming Subarray Array
Related Problems 
Medium Best Time to Buy and Sell Stock III 27 %
Medium Best Time to Buy and Sell Stock II 49 %
Medium Best Time to Buy and Sell Stock 41 %
Medium Maximum Subarray Difference 24 %
Hard Maximum Subarray III 24 %
Medium Maximum Subarray II 25 %
Easy Maximum Subarray 38 %
*/

//DP 
    public int maxSubArray(int[] nums, int k) {
        int n = nums.length;  
        int[][] d = new int[n + 1][k + 1];  
        for (int j = 1; j <= k; j++) {  
            for (int i = j; i <= n; i++) {  
                d[i][j] = Integer.MIN_VALUE;  
                int max = Integer.MIN_VALUE;  
                int localMax = 0;  
                // select j - 1 numbers from the first m element(i.e. nums[0] ~ nums[m]).
                // Since we have i numbers in total, the upper bound of m is i - 1
                for (int m = i - 1; m >= j - 1; m--) {  
                    // the next two lines are computing max subarray(m + 1, i)
                    localMax = Math.max(nums[m], nums[m]+localMax);  
                    max = Math.max(localMax, max);  
                    d[i][j] = Math.max(d[i][j], d[m][j - 1] + max);  
                }  
            }  
        }
        return d[n][k];  
    }
