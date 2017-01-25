public class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return max;
    }
    private int maxPathSumHelper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int left = maxPathSumHelper(root.left);
        int right = maxPathSumHelper(root.right);
        
        int singlePath = root.val + Math.max(left, right);
        singlePath = Math.max(singlePath, 0);
        max = Math.max(max, left + root.val + right);
        return singlePath;
    }
}

