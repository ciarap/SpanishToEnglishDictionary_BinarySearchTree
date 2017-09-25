public interface BinaryTreeInterface extends TreeInterface

{ /** Sets an existing binary tree to a new one-node binary tree.
* @param rootData an object that is the data in the new tree�s root */
public void setTree(Object rootData);
/** Sets an existing binary tree to a new binary tree.
* @param rootData an object that is the data in the new tree�s root
* @param leftTree the left subtree of the new tree
* @param rightTree the right subtree of the new tree */
public void setTree(Object rootData,
BinaryTreeInterface leftTree,
BinaryTreeInterface rightTree);
} // end BinaryTreeInterface