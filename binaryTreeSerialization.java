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
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
              StringBuilder sb = new StringBuilder("");
        
        if(root == null) {
            return "{}";
        }
        
        ArrayList<TreeNode> nodesList = new ArrayList<TreeNode>();
        int index = 0;
        nodesList.add(root);
        
        for(int i = 0; i < nodesList.size(); i++) {
            TreeNode current = nodesList.get(i);
            if(current == null) {
                continue;
            }
            nodesList.add(current.left);
            nodesList.add(current.right);
        }
        
        while(nodesList.size() > 0 && nodesList.get(nodesList.size() - 1) == null) {
            nodesList.remove(nodesList.size() - 1);
        }
        
        sb.append("{");
        sb.append(nodesList.get(0).val);
        for(int i = 1; i < nodesList.size(); i++) {
            TreeNode current = nodesList.get(i);
            if(current == null) {
                sb.append(",#");
            }
            else {
                sb.append(",");
                sb.append(current.val);
            }
        }
        sb.append("}");
        
        return sb.toString();
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
         if(data.equals("{}")) {
            return null;
        }
        
        String[] vals = data.substring(1, data.length() - 1).split(",");
        ArrayList<TreeNode> nodesList = new ArrayList<TreeNode>();

        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        nodesList.add(root);
        
        boolean nextChildIsLeft = true;
        for(int i = 1; i < vals.length; i++) {
            
            if(!vals[i].equals("#")) {
                TreeNode current = new TreeNode(Integer.parseInt(vals[i]));
                if(nextChildIsLeft) {
                    nodesList.get(index).left = current;
                }
                else {
                    nodesList.get(index).right = current;
                }
                nodesList.add(current);
            }
            
            if(!nextChildIsLeft) {
                index++;
            }
            
            nextChildIsLeft = !nextChildIsLeft;
        }
        
        return root;

    }
}
