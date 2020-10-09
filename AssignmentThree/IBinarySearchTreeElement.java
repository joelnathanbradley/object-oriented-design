package binarysearchtree;

/**
 * This is the interface used for a binary search tree element as a part of the Visitor pattern.
 */
public interface IBinarySearchTreeElement {

    /**
     * This method accepts a visitor to itself.
     * @param visitor IBinarySearchTreeVisitor
     */
    void accept(IBinarySearchTreeVisitor visitor);

}
