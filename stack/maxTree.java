/*
Max Tree 

 Description
 Notes
 Testcase
 Judge
Given an integer array with no duplicates. A max tree building on this array is defined as follow:

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.

Have you met this question in a real interview? Yes
Example
Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:

    6
   / \
  5   3
 /   / \
2   0   1
Challenge 
O(n) time and memory.

Tags 
LintCode Copyright Stack Cartesian Tree
Related Problems 
Hard Largest Rectangle in Histogram 27 %
Medium Min Stack
*/


// 1. Maintain an incrementing/decrementing stack
// 2. when pop, do something to THE POPPED OUT ELEMENT according to the problem
// 3. i <= len instead of i < len, and set the (virtual) element at len to be some special value that can pops up all the remaining elements in stack 
public TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        int len = A.length;
        
        for (int i = 0; i <= len; i++) {
            TreeNode curNode = null;
            if (i < len) {
                curNode = new TreeNode(A[i]);
            } else {
                curNode = new TreeNode(Integer.MAX_VALUE);
            }
            
            while (!stack.isEmpty() && stack.peek().val < curNode.val) {
                TreeNode popNode = stack.pop();
                if (stack.isEmpty() || stack.peek().val > curNode.val) {
                    curNode.left = popNode;
                } else {
                    stack.peek().right = popNode;
                }// end if-else
            }// end while
            stack.push(curNode);
        }// end for
        
        return stack.peek().left;
    }

