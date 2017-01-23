/**
 * Definition for binary tree
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * @constructor
 * @param {TreeNode} root - root of the binary search tree
 */
 var stack = [];
 var current = null;
var BSTIterator = function(root) {
    current = root;
};


/**
 * @this BSTIterator
 * @returns {boolean} - whether we have a next smallest number
 */
BSTIterator.prototype.hasNext = function() {
    return (stack.length > 0 && stack[stack.length - 1] !== null) || current !== null;
};
// if no current !== null, then corner case [1] will return [] instead of
// the right answer [1]

/**
 * @this BSTIterator
 * @returns {number} - the next smallest number
 */
BSTIterator.prototype.next = function() {
    while (current !== null) {
        stack.push(current);
        current = current.left;
    }
    current = stack.pop();
    let returnVal = current.val;
    current = current.right;
    return returnVal;
};

/**
 * Your BSTIterator will be called like this:
 * var i = new BSTIterator(root), a = [];
 * while (i.hasNext()) a.push(i.next());
*/
