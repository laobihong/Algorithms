// recursion
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
  if (root == null) {
    return null;
  }

  if (root.val <= p.val) {
    return inorderSuccessor(root.right, p);
  }
  else {
    TreeNode left = inorderSuccessor(root.left, p);
    return left? left: root;
  }
}

// iteration
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
  TreeNode successor = null;
  while (node != null) {
    if (node.val > p.val) {
       successor = node;
       node = node.left;
    }
    else {
       node = node.right;
    }
  }
  return successor;
}
