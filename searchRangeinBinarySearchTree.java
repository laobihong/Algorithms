/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in ascending order.
     */
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        
        ArrayList<Integer> results = new ArrayList<Integer>();
        
        searchRangeHelper(root, results, k1, k2);
        
        return results;
    }
    
    private void searchRangeHelper(TreeNode root, ArrayList<Integer> results, int k1, int k2) {
        if(root == null) {
            return;
        }
        
        if(root.val > k1){
            searchRangeHelper(root.left, results, k1, k2);
        }
        if(k1 <= root.val && root.val <= k2) {
            results.add(root.val);
        }
        if(root.val < k2){
            searchRangeHelper(root.right, results, k1, k2);
        }
    }
}

