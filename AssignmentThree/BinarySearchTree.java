package binarysearchtree;

/**
 * This class represents a binary search tree by implementing an IBinarySearchTree interface as part of the Null
 * Object pattern.  It also implements the IBinarySearchTreeElement interface as part of the Visitor pattern.
 */
public class BinarySearchTree implements IBinarySearchTree, IBinarySearchTreeElement {

    private String value;
    private IBinarySearchTree leftTree;
    private IBinarySearchTree rightTree;
    private IOrderer orderer;

    /**
     * A BinarySearchTree must take as input a value to store in the node, and an IOrderer so that the tree knows
     * how to order itself.
     * @param value String value of input for node.
     * @param orderer IOrderer specifies how the tree will order itself.
     */
    public BinarySearchTree(String value, IOrderer orderer) {
        this.value = value;
        this.leftTree = new NullBinarySearchTree(orderer);
        this.rightTree = new NullBinarySearchTree(orderer);
        this.orderer = orderer;
    }

    /**
     * This implements the IBinarySearchTree interface as part of the Null Object pattern.  This method will find the
     * correct node to store the value in based off of the orderer.
     * @param value String value of input for node.
     * @return IBinarySearchTree This method will return a reference to itself.
     */
    @Override
    public IBinarySearchTree add(String value) {
        // compare the input value against the node value using the orderer
        int orderValue = orderer.order(value, this.value);
        if(orderValue < 0) {
            this.leftTree = this.leftTree.add(value);
        } else if(orderValue > 0) {
            this.rightTree = this.rightTree.add(value);
        }
        // if the input value matches the node value, then don't add it to the tree
        return this;
    }

    /**
     * This implements the IBinarySearchTree interface as part of the Null Object pattern.
     * @return IBinarySearchTree the left subtree.
     */
    @Override
    public IBinarySearchTree getLeftTree() {
        return this.leftTree;
    }

    /**
     * This implements the IBinarySearchTree interface as part of the Null Object pattern.
     * @return IBinarySearchTree the right subtree.
     */
    @Override
    public IBinarySearchTree getRightTree() {
        return this.rightTree;
    }

    /**
     * This implements the IBinarySearchTree interface as part of the Null Object pattern.
     * @return String the node value.
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * This implements the IBinarySearchTreeElement interface as part of the Visitor pattern.
     * @param visitor IBinarySearchTreeVisitor the visitor of the tree
     */
    @Override
    public void accept(IBinarySearchTreeVisitor visitor) {
        visitor.visitBinarySearchTree(this);
    }

}
