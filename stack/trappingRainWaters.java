/*
Trapping Rain Water 

 Description
 Notes
 Testcase
 Judge
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

Trapping Rain Water

Have you met this question in a real interview? Yes
Example
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

Challenge 
O(n) time and O(1) memory

O(n) time and O(n) memory is also acceptable.

Tags 
Two Pointers Array Forward-Backward Traversal
Related Problems 
Hard Trapping Rain Water II 25 %
Medium Container With Most Water
*/

// sol 1:
// Instead of calculating area by height * width, we can think it in an accumulative manner. In other words, summing water up of each bin (width=1).
// Search from left/right to the middle and maintain a max height for left boundary and right boundary separately. When current is lower than boundary, accumulate water, otherwise update the boundary. 
// Possible confussion: what if there is a blocker inside?
// e.g. 3 ......1 7 ......4
//      | ......|  ...... |
//    leftMax..current...rightMax  
// even if there is a 7 in the future to update the rightMax, we still only have 2 units of water here (3 - 1). It is exclusively determined by 3 (since 3 < 4) and 1 (since 1 < 3).
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length <= 2) {
            return 0;
        }
        
        int left = 0, right = heights.length - 1;
        int leftMax = heights[left], rightMax = heights[right];
        int water = 0;
        
        while (left <= right) {  // here < will also work because the reason we have left here is because left - 1 < right, and 
// left - 1 has already been considered, and right couldn't make further contribution. 
            if (heights[left] < heights[right]) {
                if (heights[left] < leftMax) {
                    water += leftMax - heights[left];
                } else {
                    leftMax = heights[left];
                }
                left++;
            } else {
                if (heights[right] < rightMax) {
                    water += rightMax - heights[right];
                } else {
                    rightMax = heights[right];
                }
                right--;
            }
        }
        
        return water;
    }

// sol 2, descending stack
// This approach treats all, no matter it's water or concrete, as some uniform elements with heights, and pushes them indiscriminately into the stack. (Actually sol 1 also did this way!!) 
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length <= 2) {
            return 0;
        }
        
        int i = 0, n = heights.length;
        int water = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        
        while (i < n) {
            if (stack.isEmpty() || heights[i] < heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int last = stack.pop();
                if (!stack.isEmpty()) {
                    water += (Math.min(heights[i], heights[stack.peek()]) - heights[last]) * (i - stack.peek() - 1); 
// ATTN: here, each popped value is responsible for NOT only its own bar, but all the length between i and stack.peek(). This is
// because that 1) by maintaining a descending stack, we already guaranteed that all values in heights[] between stack.peek and stack.pop are smaller than Math.min(stack.peek, stack.pop). and 2) More importantly, any value between stack.peek and stack.pop has been counted for its contribution.
// |  |.....___  
// |  | 1 __|  |
// |  |__|  |  |
// |  |  |  |  |
// =======================
// e.g. here the contribution below 1 had been counted when the third bar got pushed into the stack, so we the fourth bar comes into play, we only need the part under the dotted line - that area.  
// In other words, this is a progress where we continously add the contribution the smaller bars between stack.peek and stack.pop and "squeeze them out" so we don't have to worry about them in the future. Pop here serves as a stadard/basis that gives "additional" contributions to all of the smaller bars. 
                }
            }
        }
        
        return water;
    }
