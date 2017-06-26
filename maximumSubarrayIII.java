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

// sol1: Kadane's Algorithm
/** Lu's: 
1. Use max_ending_here instead of local. "Local" is vague and somehow misleading, what is local? You cannot easily tell just by its name. Max_ending_here is pretty clear and staightforward: we are looking for a subarray with maximum sum ending at the current element. 
2. The logic: 
2.1 The maximum subarray ending at the current element, i.e. given it contains the current element, either has some of the maximum subarray ending at THE previous element as a prefix (max_ending_here), or it doesn't (x).
2.2 The global max (max_so_far) at element i must be one of those max_ending_here at 0 to i, note the result at i - 1 has included all the possibilities(it can end at any place between 0 and i - 1) for us so we don't have to think about anything one step further before (this is the idea of DP!). Since We already have had max_ending_here, what we only need to do next is to compare it's value with the previous global max.   
In other words, the global max must end at somewhere between 0 and i - 1, so we compute the max_end_here at each place and keep a global max. If we change global max to max this could be clearer :). Global max is just keeping track of the max value, and the real DP part is done through max_ending_here!
def max_subarray(A):
    max_ending_here = max_so_far = A[0]
    for x in A[1:]:
        max_ending_here = max(x, max_ending_here + x)
        max_so_far = max(max_so_far, max_ending_here)
    return max_so_far
*/ 
    public int maxSubArray(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            return Integer.MIN_VALUE;
        }
        
        int len = nums.length;
        int[][] local = new int[k + 1][len + 1];
        int[][] global = new int[k + 1][len + 1];
        
        for (int i = 1; i <= k; i++) {
            for (int j = i; j < len + 1; j++) {
                if (j == i) {
                    local[i][j] = global[i - 1][j - 1] + nums[j - 1];
                    global[i][j] = local[i][j];
                } else {
                    local[i][j] = Math.max(global[i - 1][j - 1], local[i][j - 1]) + nums[j - 1];
                    global[i][j] = Math.max(global[i][j - 1], local[i][j]);
                }
            }
        }
        return global[k][len];
    }

//sol2: DP 
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

// sol2' DP
    public int maxSubArray(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            return Integer.MIN_VALUE;
        }
        
        int len = nums.length;
        int[][] dp = new int[len + 1][k + 1];
        int[][] local = new int[len][len];
        int[][] global = new int[len][len];
        
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (j == i) {
                    local[i][j] = nums[i];
                    global[i][j] = nums[i];
                } else {
                    local[i][j] = Math.max(local[i][j - 1], 0) + nums[j];
                    global[i][j] = Math.max(global[i][j - 1], local[i][j]);
                }
            }
        }
        
        for (int i = 1; i < k + 1; i++) {
            for (int j = i; j < len + 1; j++) {
                int tmp = Integer.MIN_VALUE;
                for (int curStr = i - 1; curStr <= j - 1; curStr++) {
                    // dp[][] is ith, global is index
                    tmp = Math.max(tmp, dp[curStr][i - 1] + global[curStr][j - 1]);
                }
                dp[j][i] = tmp;
            }
        }
        return dp[len][k];
    }
// sol2'' DP (deprecated)
    public int maxSubArray(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        int[] leftMax = new int[n + 1];
        int[] rightMax = new int[n + 1];
        
        int sum = 0;
        int minSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
            sum += nums[i - 1];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            leftMax[i] = max;
        }
        
        if (k == 1) {
            return sum + nums[n - 1];
        }
        
        sum = 0;
        minSum = 0;
        max = Integer.MIN_VALUE;
        for(int j = n - 1; j >= 1; j--) {
            sum += nums[j];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            rightMax[j] = max;
        }
        
        int[][] res = new int[n + 1][k + 1];
        
        int ans = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
            res[i][2] = leftMax[i] + rightMax[i];
            ans = Math.max(ans, res[i][2]);
        }
        
        if(k == 2) {
            return ans;
        }
        
        for(int i = 3; i < k; i++) {
            for(int j = 1; j <= n - i + 1; j++) {
                res[j][i] = leftMax[j] + res[j][i - 1];
            }
            for(int j = n -1; j>= i - 1; j--) {
                res[j][i] = Math.max(res[j][i], rightMax[j] + res[j][i - 1]);
            }
        }
        
        ans = Integer.MIN_VALUE;
        for(int j = 1; j <= n - k + 1; j++) {
            ans = Math.max(ans, leftMax[j] + res[j][k - 1]);
        }
        
        return ans;
    }

// sol3

