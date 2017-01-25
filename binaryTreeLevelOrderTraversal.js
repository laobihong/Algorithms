102. Binary Tree Level Order Traversal 

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number[][]}
 */
var levelOrder = function(root) {
    let queue = [];
    let number = [];
    if (root !== null) {
        queue.push(root);
    }
    let index = 0;
    while(queue.length > 0) {
        let len = queue.length;
        number[index] = []; 
        for (var v = 0; v < len; v++) {
            let current = queue.shift();
            number[index].push(current.val);
            if (current.left !== null) {
                queue.push(current.left);
            }
            if (current.right !== null) {
                queue.push(current.right);
            }
        }
        index++;
    }
    return number;
};
