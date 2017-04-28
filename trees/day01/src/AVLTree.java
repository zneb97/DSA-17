public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    //runtime insert: O(log n) always, delete O(log n)

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n,key);
        if(n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n = balance(n);
        }
        return n;
    }
    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T>  n, T key) {
        n = super.insert(n,key);
        if(n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n = balance(n);
        }
        return n;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n){
        n = super.deleteMin(n);
        if(n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null){
            return -1;
        }
        else {
            return 1 + Math.max(height(n.leftChild), height(n.rightChild));
        }
    }

    public int height() {
        return Math.max(height(root),0);
    }

    // Restores the AVL tree property of the subtree.
    TreeNode<T> balance(TreeNode<T> n) {
        //Heavy left
        if (balanceFactor(n) <= -2){
            //Special case
            if (balanceFactor(n.leftChild) >= 1) {
                n.leftChild = rotateLeft(n.leftChild);
            }
            n = rotateRight(n);
        }

        //Heavy right
        if (balanceFactor(n) >= 2){
            //Special case
            if (balanceFactor(n.rightChild) <= -1){
                n.rightChild = rotateRight(n.rightChild);
            }
            n = rotateLeft(n);
        }
        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> head = n.leftChild;
        n.leftChild = head.rightChild;
        head.rightChild = n;
        //Updates height
        n.height = 1 + Math.max(height(n.rightChild),height(n.leftChild));
        head.height = 1 + Math.max(height(head.rightChild), height(head.leftChild));
        return head;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> head = n.rightChild;
        n.rightChild = head.leftChild;
        head.leftChild = n;
        //Updates height
        n.height = 1 + Math.max(height(n.rightChild),height(n.leftChild));
        head.height = 1 + Math.max(height(head.rightChild), height(head.leftChild));
        return head;
    }
}