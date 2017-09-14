package test;

/**
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * http://www.geeksforgeeks.org/avl-tree-set-2-deletion/
 * @author x
 *
 */
public class AVLTree {
	
	Node root;
	
	public AVLTree() {
		root = null;
	}
	
	private void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.val + " ");
		preOrder(node.left);
		preOrder(node.right);
	}
	
	private Node rotateRight(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		x.right = y;
		y.left = T2;
		
		// important! update heights! 
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		return x;
	}
	
	private Node rotateLeft(Node x) {
		Node y = x.right;
		Node T2 = y.left;
		y.left = x;
		x.right = T2;
		
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		return y;
	}
	
	private int height(Node node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}
	
	public Node insert(Node node, int value) {
		// regular BST insert
		if (node == null) {
			return new Node(value);
		}
		
		if (node.val > value) {
			node.left = insert(node.left, value);
		} else if (node.val < value) {
			node.right = insert(node.right, value);
		} else {
			return node;
		}
		
		// must update first so the properties of the tree will not be violated! 
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		
		// 1: calculate balance
		int balance = height(node.left) - height(node.right);
		
		if (balance > 1 && node.left.val > value) {
			node = rotateRight(node); // self! not node.left
		}
		
		if (balance > 1 && node.left.val < value) {
			node.left = rotateLeft(node.left);
			node = rotateRight(node);
		}
		
		if (balance < -1 && node.right.val < value) {
			node = rotateLeft(node); // self! not node.right
		}
		
		if (balance < -1 && node.right.val > value) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
		}
		
		return node;
	}
	
	private Node findMin(Node node) {
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	private int getBalance(Node node) {
		if (node == null) {
			return 0;
		}
		
		return height(node.left) - height(node.right);
	}
	
	public Node deleteNode (Node node, int key) {
		// perform normal deletion for BST
		if (node == null) {
			return node;
		}
		
		if (node.val > key) {
			node.left = deleteNode(node.left, key);
		} else if (node.val < key) {
			node.right = deleteNode(node.right, key);
		} else {
			if (node.left == null) {
				node = node.right;
			} else if (node.right == null) {
				node = node.left;
			} else {
				Node min = findMin(node.right);
				node.val = min.val;
				node.right = deleteNode(node.right, min.val);
			}
		}
		
		// important! check if node is null
		if (node == null) {
			return node;
		}
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		
		int balance = getBalance(node);
		
		// attn: just because balance (self balance) is > 1, we perform rotateRight on self
		// this well explains why rorateRight(node.left) is wrong
		// or we could say that like D & C, we only focus on self
		if (balance > 1 && getBalance(node.left) >= 0) {
			node = rotateRight(node);
		}
		
		if (balance > 1 && getBalance(node.left) < 0) {
			node.left = rotateLeft(node.left);
			node = rotateRight(node);
		}
		
		if (balance < -1 && getBalance(node.right) <= 0) {
			node = rotateLeft(node);
		}
		
		if (balance < -1 && getBalance(node.right) > 0) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
		}
		
		return node;
	}
	
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
 
//        /* Constructing tree given in the above figure */
//        tree.root = tree.insert(tree.root, 10);
//        tree.root = tree.insert(tree.root, 20);
//        tree.root = tree.insert(tree.root, 30);
//        tree.root = tree.insert(tree.root, 40);
//        tree.root = tree.insert(tree.root, 50);
//        tree.root = tree.insert(tree.root, 25);
// 
//        /* The constructed AVL Tree would be
//             30
//            /  \
//          20   40
//         /  \     \
//        10  25    50
//        */
//        System.out.println("Preorder traversal" +
//                        " of constructed tree is : ");
//        tree.preOrder(tree.root);
        
        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, -1);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);
 
        /* The constructed AVL Tree would be
           9
          /  \
         1    10
        /  \    \
        0    5    11
        /    /  \
        -1   2    6
         */
        System.out.println("Preorder traversal of "+
                            "constructed tree is : ");
        tree.preOrder(tree.root);
 
        tree.root = tree.deleteNode(tree.root, 10);
 
        /* The AVL Tree after deletion of 10
           1
          /  \
         0    9
        /     / \
        -1    5   11
        /  \
        2    6
         */
        System.out.println("");
        System.out.println("Preorder traversal after "+
                           "deletion of 10 :");
        tree.preOrder(tree.root);
    }
}

class Node {
	int val;
	int height;
	Node left, right;
	public Node(int val) {
		this.val = val;
		height = 1; // height = 0;
		left = null;
		right = null;
	}
}