/*
Subarray Sum Closest 

 Description
 Notes
 Testcase
 Judge
Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.

Have you met this question in a real interview? Yes
Example
Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].

Challenge 
Tags 
Subarray Sort
Related Problems 
Medium Submatrix Sum 25 %
Hard Subarray Sum II 29 %
Medium Minimum Size Subarray Sum 27 %
Easy Subarray Sum 29 %
*/

// PITFALL: see the wrong solution below
    public int[] subarraySumClosest(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        if (nums.length == 1) {
            return new int[]{0,0};
        }
        
        int n = nums.length;
        Pair[] pairs = new Pair[n + 1];
        pairs[0] = new Pair(0, -1);
        for (int i = 1; i <= n; i++) {
            pairs[i] = new Pair(nums[i - 1] + pairs[i - 1].sum, i - 1);
        }
        
        Arrays.sort(pairs, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            }
        });
        
        int[] res = new int[2];
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (pairs[i].sum - pairs[i - 1].sum < min) {
                min = pairs[i].sum - pairs[i - 1].sum;
                res[1] = Math.max(pairs[i].index, pairs[i - 1].index);
                res[0] = Math.min(pairs[i].index, pairs[i - 1].index) + 1;
            }
        }
        return res;
    }
    
    class Pair {
        int sum;
        int index;
        public Pair (int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }

// ATTN: Wrong if defining an array of len n! [-3,1,2], then sum [-3,-2,0], after sort [0,-2,-3], will get[1,1] but actually the correct answer is [0,2]. the sum array is virtually [[0],-3,-2,0], after sort [[0],0,-2,3], will return [0(==-1 + 1),2]
    public int[] subarraySumClosest(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        if (nums.length == 1) {
            return new int[]{0,0};
        }
        
        int n = nums.length;
        Pair[] pairs = new Pair[n];
        pairs[0] = new Pair(nums[0], 0);
        for (int i = 1; i < n; i++) {
            pairs[i] = new Pair(nums[i] + pairs[i - 1].sum, i);
        }
        
        Arrays.sort(pairs, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            }
        });
        
        int[] res = new int[2];
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (pairs[i].sum - pairs[i - 1].sum < min) {
                min = pairs[i].sum - pairs[i - 1].sum;
                res[1] = Math.max(pairs[i].index, pairs[i - 1].index);
                res[0] = Math.min(pairs[i].index, pairs[i - 1].index) + 1;
            }
        }
        return res;
    }
    
    class Pair {
        int sum;
        int index;
        public Pair (int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }
