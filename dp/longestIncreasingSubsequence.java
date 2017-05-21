/*
Longest Increasing Subsequence 

 Description
 Notes
 Testcase
 Judge
Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.

Have you met this question in a real interview? Yes
Clarification
What's the definition of longest increasing subsequence?

The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.

https://en.wikipedia.org/wiki/Longest_increasing_subsequence

Example
For [5, 4, 1, 2, 3], the LIS is [1, 2, 3], return 3
For [4, 2, 4, 5, 3, 7], the LIS is [2, 4, 5, 7], return 4

Challenge 
Time complexity O(n^2) or O(nlogn)

Tags 
Binary Search Dynamic Programming LintCode Copyright
Related Problems 
Hard Russian Doll Envelopes 25 %
Medium Largest Divisible Subset 41 %
Hard Frog Jump
*/


// O(nlgn)
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        List<Integer> sequence = new ArrayList<>();
        sequence.add(nums[0]);
        int cur = 0;
        
        for (int i = 1; i < len; i++) {
            if (nums[i] > sequence.get(sequence.size() - 1)) {
                sequence.add(nums[i]);
            }
            else {
                insertCurNum(sequence, nums[i]);
            }
        }
        
        return sequence.size();
    
    }
    
    private void insertCurNum (List<Integer> list, int key) {
        int start = 0, end = list.size() - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            int curNum = list.get(mid);
            if (curNum < key) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        
        if (list.get(start) >= key) {
            list.set(start, key);
        }
        else {
            list.set(end, key);
        }
    }

// O(n^2), trivial DP

    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        int[] longest = new int[len];
        int max = 1;
        for (int i = 0; i < len; i++) {
            longest[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    longest[i] = Math.max(longest[i], longest[j] + 1);
                }
            }
            max = Math.max(max, longest[i]);
        }
        
        return max;
    }
