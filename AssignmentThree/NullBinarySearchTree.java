package binarysearchtree;

/**
 * This class represents a null binary search tree by implementing the IBinarySearchTree interface as part of the
 * Null Object pattern.  It also implements the IBinarySearchTreeElement interface as part of the Visitor pattern.
 */
public class NullBinarySearchTree implements IBinarySearchTree, IBinarySearchTreeElement {

    private IOrderer orderer;

    /**
     * A NullBinarySearchTree must take as input an IOrderer for when a value is later added to the node.
     * @param orderer IOrderer specifies how the tree will order itself.
     */
    public NullBinarySearchTree(IOrderer orderer) {
        this.orderer = orderer;
    }

    /**
     * This implements the IBinarySearchTree interface as part of the Null Object pattern.  This method will return a
     * newly created BinarySearchTree if add is called on this null node.
     * @param value String value of input for new node created.
     * @return IBinarySearchTree a newly created BinarySearchTree to replace the NullBinarySearchTree in data structure.
     */
    @Override
    public IBinarySearchTree add(String value) {
        return new BinarySearchTree(value, this.orderer);
    }

    /**
     * This implements the IBinarySearchTree interface as part of the Null Object pattern.  This method will return
     * itself since there is no left tree.
     * @return IBinarySearchTree
     */
    @Override
    public IBinarySearchTree getLeftTree() {
        return this;
    }

    /**
     * This implements the IBinarySearchTree interface as part of the Null Object pattern.  This method will return
     * itself since there is no right tree.
     * @return IBinarySearchTree
     */
    @Override
    public IBinarySearchTree getRightTree() {
        return this;
    }

    /**
     * This implements the IBinarySearchTree interface as part of the Null Object pattern.  This method will return
     * an empty string since there is no value in this node.
     * @return String an empty string
     */
    @Override
    public String getValue() {
        return "";
    }

    /**
     * This implements the IBinarySearchTreeElement interface as part of the Visitor pattern.
     * @param visitor IBinarySearchTreeVisitor the visitor of the tree
     */
    @Override
    public void accept(IBinarySearchTreeVisitor visitor) {
        visitor.visitNullBinarySearchTree(this);
    }

}
