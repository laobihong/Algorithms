/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var balanced = true; 
var isBalanced = function(root) {
    
    isBalancedHelper(root);
    return balanced;
};

var isBalancedHelper = function(root) {
    if (root === null) {
        return 0;
    }
    
    let leftDepth = isBalancedHelper(root.left);
    let rightDepth = isBalancedHelper(root.right);
    if( Math.abs(leftDepth - rightDepth) > 1) {
        balanced = false;
    }
    return 1 + Math.max(leftDepth, rightDepth);
}
