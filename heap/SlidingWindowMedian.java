/**
Sliding Window Median 

 Description
 Notes
 Testcase
 Judge
Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the median of the element inside the window at each moving. (If there are even numbers in the array, return the N/2-th number after sorting the element in the window. )

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], moving window size k = 3. return [2,7,7]

At first the window is at the start of the array like this

[ | 1,2,7 | ,8,5] , return the median 2;

then the window move one step forward.

[1, | 2,7,8 | ,5], return the median 7;

then the window move one step forward again.

[1,2, | 7,8,5 | ], return the median 7;

Challenge 
O(nlog(n)) time

Tags 
LintCode Copyright Heap
Related Problems 
Hard Paint House II 25 %
Super Building Outline 14 %
Super Sliding Window Maximum 27 %
Hard Data Stream Median
*/


// sol 1: self mock + polishing： two heaps
public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (nums == null || k <= 0 || k > nums.length) {
            return ans;
        }
        
        PriorityQueue<Integer> upper = new PriorityQueue<>();
        // NOTE: k/2 could be 1/2 == 0, then IllegalArgumentException will be thrown
        PriorityQueue<Integer> lower = new PriorityQueue<>(k, Collections.reverseOrder());
        // initialization
        for (int i = 0; i < k; i++) {
            lower.offer(nums[i]);
        }
        
        for (int i = 0; i < k/2; i++) {
            upper.offer(lower.poll());
        }
        
        ans.add(lower.peek());
        int len = nums.length;
        for (int i = 0; i <= len - k - 1; i++) {
            // NOTE: upper could be null, so first check lower
            if (nums[i] <= lower.peek()) {
                lower.remove(nums[i]);
            } else {
                upper.remove(nums[i]);
            }
            
            // NOTE: cannot use lower.isEmpty() || nums[i + k] <= lower.peek()
            // because e.g. lower is empty, upper only contains 1, the new num
            // to be added is 2, then wrong
            if (!lower.isEmpty() && nums[i + k] <= lower.peek()) {
                lower.offer(nums[i + k]);
            } else {
                upper.offer(nums[i + k]);
            }
            
            while (lower.size() - 1 > upper.size()) {
                upper.offer(lower.poll());
            }
            
            while (upper.size() > lower.size()) {
                lower.offer(upper.poll());
            }
            
            ans.add(lower.peek());
        }
        
        return ans;
    }
