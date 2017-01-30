/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var preorderTraversal = function(root) {
    var stack = [];
    var result = [];
    if(root !== null) {
        stack.push(root);
    }
    while(stack.length > 0) {
        let current = stack.pop();
        result.push(current.val);
        if(current.right !== null) {
            stack.push(current.right);
        }
        if(current.left !== null) {
            stack.push(current.left);
        }
    }
    return result;
};
