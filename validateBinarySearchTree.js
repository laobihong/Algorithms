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
var lastVal = Number.MIN_VALUE;
var firstNode = true;
var isValidBST = function(root) {
    if(root === null) {
        return true;
    }
    if(!isValidBST(root.left)) {
        return false;
    }
    if (!firstNode && lastVal >= root.val) {
        return false;
    }
    firstNode = false;
    lastVal = root.val;
    if (!isValidBST(root.right)) {
        return false;
    }
    return true; 
};
