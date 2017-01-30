var inOrderSuccessor = function(root, n) {

  if(n.right !== null) {
    return minValue(n.right);
  }

  let successor = null;
  while (root !== null) {
    // imagine a flattened tree
    // successor is just like a successor
    // in a linked list 
    if (n.val < root.val) {
      successor = root;
      root = root.left;
    }
    else if (n.val > root.val) {
      root = root.right;
    }
    else
      break;
  }
  return successor;
}

function minValue(n){
  let current = n;
  while(current.left !== null) {
    current = current.left;
  } 
  return current;
}
