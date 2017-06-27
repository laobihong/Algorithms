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
        // k or 1 should both be ok to initialize
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
        for (int i = 0; i <= len - k - 1; i++) { // O(n - k)
            // NOTE: upper could be null, so first check lower
            if (nums[i] <= lower.peek()) { // O(k)
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
// 

// partial sol 1: time limit exceed when k is large (e.g. k == 9009)
// O (n - k) * k
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (nums == null || k <= 0 || k > nums.length) {
            return ans;
        }
        
        int len = nums.length;
        
        for (int i = 0; i <= len - k; i++) { // O(n - k)
            PriorityQueue<Integer> heap = new PriorityQueue<>(k, Collections.reverseOrder());
            for (int j = i; j < i + k; j++) { // O(k)
                heap.add(nums[j]);
            }
            for (int l = 0; l < k/2; l++) { // NOTE: each poll() is log(k), so O(klogk)
                heap.poll();
            }
            ans.add(heap.peek());
        }
        
        return ans;
    }

// partial sol 2 sliding window + quick select: time limit exceed for large k

    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (nums == null || k <= 0 || k > nums.length) {
            return ans;
        }
        
        int len = nums.length;
        
        for (int i = 0; i <= len - k; i++) {
            int[] copy = Arrays.copyOfRange(nums, i, i + k);
            if ( k % 2 == 1) {
                ans.add(quickSelect(copy, 0, k - 1, k/2));
            } else {
                ans.add(quickSelect(copy, 0, k - 1, k/2 - 1));
            }
        }
        
        return ans;
    }
    
// Lomuto partition scheme
    private int quickSelect_1(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        
        int pivot = nums[start];
        
        int i = end, j = end;
        while (start < j) {
            if (nums[j] > pivot) {
                swap(nums, j, i);
                i--;
            }
            j--;
        }
        
        swap(nums, start, i);
        if (i == k) {
            return nums[i];
        } else if (i > k) {
            return quickSelect(nums, start, i - 1, k);
        } else {
            return quickSelect(nums, i + 1, end, k);
        }
    }
    
// Hoare partition scheme
    private int quickSelect(int[] A, int start, int end , int k) {

        if (start == end)
            return A[start];
        
        int left = start, right = end;
        int pivot = A[(start + end) / 2];

        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }

            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                
                left++;
                right--;
            }
        }

        if (right >= k && start <= right)
            return quickSelect(A, start, right, k);
        else if (left <= k && left <= end)
            return quickSelect(A, left, end, k);
        else
            return A[k];
    }

    
    private void swap (int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
