    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // same strategy: reduce to "single node"
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        
        ArrayList<Integer> results = new ArrayList<Integer>();
        
        while(!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                results.add(root.val);
                root = root.right;
            }
        }
        
        return results;
    }
