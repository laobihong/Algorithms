/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) != -1;
    }
    private int isBalancedHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = isBalancedHelper(root.left);
        int right = isBalancedHelper(root.right);
        // Here left == -1 || right == -1 is necessary
        // e.g. [1,2,2,3,null,null,3,4,null,null,4]
        // 1's left and right have identical max depth
        // however, both are inbalanced
        // here return -1 just propagates the result upwards, since finally we check the return value of the root!
        if ( left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }
}
