package binarysearchtree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeVisitorTest {

    @Test
    void visitBinarySearchTree() {
        BinarySearchTreeVisitor visitor = new BinarySearchTreeVisitor();
        IBinarySearchTree tree = new BinarySearchTree("5", new LexicographicOrderer());

        // test that the visitor correctly represents a tree with a single node.
        visitor.visitBinarySearchTree((BinarySearchTree)tree);
        assertTrue(visitor.toString().equals("(5 ()())"));
        visitor.clearTreeRepresentation();

        // test that a value already in the binary search tree is not added again.
        tree = tree.add("5");
        visitor.visitBinarySearchTree((BinarySearchTree)tree);
        assertTrue(visitor.toString().equals("(5 ()())"));
        visitor.clearTreeRepresentation();

        // test tree representation with a node with both children set, a node with just a the left child set, a node
        // with just the right child set, and nodes with no children set.
        tree = tree.add("9");
        tree = tree.add("3");
        tree = tree.add("4");
        tree = tree.add("8");
        visitor.visitBinarySearchTree((BinarySearchTree)tree);
        assertTrue(visitor.toString().equals("(5 (3 ()(4 ()()))(9 (8 ()())()))"));
        visitor.clearTreeRepresentation();

        // repeat the same tests, but using a different orderer
        tree = new NullBinarySearchTree(new ReverseLexicographicOrderer());
        tree = tree.add("56");

        // test that the visitor correctly represents a tree with a single node.
        visitor.visitBinarySearchTree((BinarySearchTree)tree);
        assertTrue(visitor.toString().equals("(56 ()())"));
        visitor.clearTreeRepresentation();

        // test that a value already in the binary search tree is not added again.
        tree = tree.add("56");
        visitor.visitBinarySearchTree((BinarySearchTree)tree);
        assertTrue(visitor.toString().equals("(56 ()())"));
        visitor.clearTreeRepresentation();

        // test tree representation with a node with both children set, a node with just a the left child set, a node
        // with just the right child set, and nodes with no children set.
        tree = tree.add("93");
        tree = tree.add("89");
        tree = tree.add("48");
        tree = tree.add("84");
        visitor.visitBinarySearchTree((BinarySearchTree)tree);
        assertTrue(visitor.toString().equals("(56 (93 ()(84 ()()))(89 (48 ()())()))"));
        visitor.clearTreeRepresentation();
    }

    @Test
    void visitNullBinarySearchTree() {
        // test a null tree representation
        BinarySearchTreeVisitor visitor = new BinarySearchTreeVisitor();
        IBinarySearchTree tree = new NullBinarySearchTree(new ReverseLexicographicOrderer());
        visitor.visitNullBinarySearchTree((NullBinarySearchTree)tree);
        assertTrue(visitor.toString().equals("()"));
    }
}