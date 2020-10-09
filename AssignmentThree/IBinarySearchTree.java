package binarysearchtree;

/**
 * This is the interface used as part of the Null Object pattern to represent a binary search tree.
 */
public interface IBinarySearchTree {

    /**
     * This method will add a value to the binary search tree.
     * @param value String
     * @return IBinarySearchTree a reference to itself.
     */
    IBinarySearchTree add(String value);

    /**
     * This method will return the left subtree.
     * @return IBinarySearchTree
     */
    IBinarySearchTree getLeftTree();

    /**
     * This method will return the right subtree.
     * @return IBinarySearchTree
     */
    IBinarySearchTree getRightTree();

    /**
     * This method will return the value of the tree.
     * @return String
     */
    String getValue();

}
