package binarysearchtree;

/**
 * This class represents a binary search tree visitor by implementing the IBinarySearchTreeVisitor as part of the
 * Visitor pattern.
 */
public class BinarySearchTreeVisitor implements IBinarySearchTreeVisitor {

    private String treeRepresentation;

    /**
     * This visitor will store the structure of a binary search tree using following the syntax:
     * (Value (Left Subtree)(RightSubtree))
     */
    public BinarySearchTreeVisitor() {
        this.treeRepresentation = "";
    }

    /**
     * This method implements the IBinarySearchTreeVisitor as part of the Visitor pattern.  Specifically, it visits a
     * BinarySearchTree object.
     * @param tree BinarySearchTree the tree being examined by the visitor
     */
    @Override
    public void visitBinarySearchTree(BinarySearchTree tree) {
        // store the opening parenthesis, the tree value, left subtree structure, right subtree structure, and finally
        // the closing parenthesis, all as part of the syntax specified for this visitor.
        this.treeRepresentation = this.treeRepresentation + "(" + tree.getValue() + " ";
        ((IBinarySearchTreeElement)tree.getLeftTree()).accept(this);
        ((IBinarySearchTreeElement)tree.getRightTree()).accept(this);
        this.treeRepresentation = this.treeRepresentation + ")";
    }

    /**
     * This method implements the IBinarySearchTreeVisitor as part of the Visitor pattern.  Specifically, it visits a
     * NullBinarySearchTree object.
     * @param tree NullBinarySearchTree the null tree being examined by the visitor.
     */
    @Override
    public void visitNullBinarySearchTree(NullBinarySearchTree tree) {
        // since the tree is null, store the opening and closing parenthesis needed to represent a null node in the
        // syntax specified for this visitor.
        this.treeRepresentation = this.treeRepresentation + "(" + tree.getValue() + ")";
    }

    /**
     * This will return as a string the tree representation.
     * @return
     */
    @Override
    public String toString() {
        return this.treeRepresentation;
    }

    /**
     * This will be called if the visitor has already visited a binary search tree, and needs to visit another binary
     * search tree.  The current tree representation will be cleared out in preparation for another visit.
     */
    public void clearTreeRepresentation() {
        this.treeRepresentation = "";
    }

}
