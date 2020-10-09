package binarysearchtree;

/**
 * This interface is used as part of the Visitor pattern in order for a binary search tree to accept a visitor.
 */
public interface IBinarySearchTreeVisitor {

    /**
     * This method will visit a BinarySearchTree.
     * @param tree BinarySearchTree
     */
    void visitBinarySearchTree(BinarySearchTree tree);

    /**
     * This method will visit a NullBinarySearchTree.
     * @param tree NullBinarySearchTree
     */
    void visitNullBinarySearchTree(NullBinarySearchTree tree);

}
