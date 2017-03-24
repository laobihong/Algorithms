/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
 
max = Number.MIN_VALUE;
 
var maxPathSum = function(root) {
    maxPathSumHelper(root);
    return max;
};

var maxPathSumHelper = function(root) {
    if(root === null) {
        return 0;
    }
    var left = maxPathSumHelper(root.left);
    var right = maxPathSumHelper(root.right);
    var singlePath = root.val + Math.max(left, right);
    singlePath = Math.max(0, singlePath);
    max = Math.max(max, root.val + left + right);
    return singlePath;
}

Input:
[0]
Output:
6
Expected:
0
