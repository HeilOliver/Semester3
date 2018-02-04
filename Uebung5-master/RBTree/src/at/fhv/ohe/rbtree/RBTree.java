package at.fhv.ohe.rbtree;

import java.util.Comparator;

/**
 * A class for the Red Black Tree structure.
 * The RedBlack Tree structure is an balanced binary tree structure.
 *
 */
public class RBTree<T> {

    private Comparator<T> comparator;
    private RBNode<T> root;

    /**
     * Creates an new Tree with the given comparator
     *
     * @param comparator - The comparator what is used to weighting the insert Values.
     *                   The {@code Comparator<T>} need to be form
     *                   Type {@code <T>} from the Tree structure.
     */
    public RBTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /***
     * Compares two nodes value.
     *
     * @param n1 - {@code Node<T>} the first Node
     * @param n2 - {@code Node<T>} the second Node
     * @return - return {@code true} if the second node is bigger than the first node.
     * Return {@code false} if the second node is smaller or equal to the first node
     */
    private boolean compareNode(RBNode<T> n1,RBNode<T> n2) {
        return comparator.compare(n1.value, n2.value) > 0;
    }

    /**
     * search and insert a node into the right position in the tree
     *
     * @param nextNode - The next node that shoud compare with the {@code insertNode}
     * @param insertNode - The Node that shoud be insert into the structure
     */
    private void insertNode(RBNode<T> nextNode, RBNode<T> insertNode ) {
        if (compareNode(nextNode, insertNode)) {
            if (nextNode.left == null) {
                nextNode.left = insertNode;
                insertNode.parent = nextNode;
                return;
            }
            insertNode(nextNode.left, insertNode);
        } else {
            if (nextNode.right == null) {
                nextNode.right = insertNode;
                insertNode.parent = nextNode;
                return;
            }
            insertNode(nextNode.right, insertNode);
        }
    }

    /**
     * Balances the Tree
     * @param node - The new Node that be added.
     */
    private void balanceTree(RBNode<T> node) {
        if (node.parent == null || node.parent.parent == null) {
            return;
        }

        if (node.parent.color != RBColor.RED) {
            return;
        }

        RBNode<T> uncle = getUncleFrom(node);

        balanceCaseOne(node, uncle);
        root.color = RBColor.BLACK;
        balanceCaseThree(node, uncle);
        balanceCaseTwo(node, uncle);
    }

    /**
     * Check the given {@code RBNode<T>} for the RbTree second case.
     * @param node - The {@code RBNode<T>} that should be checked.
     * @param uncle - The {@code RBNode<T>} uncle. The uncle can be null.
     */
    private void balanceCaseTwo(RBNode<T> node, RBNode<T> uncle) {
        if (uncle != null && uncle.color != RBColor.BLACK) {
            return;
        }

        node.parent.right = node.parent.parent;

        if (node.parent.parent.parent == null) {
            root = node.parent;
            node.parent.parent = null;
        } else {
            node.parent.parent = node.parent.parent.parent;
            if (node.parent.parent.parent.left.equals(node.parent.parent)) {
                node.parent.parent.parent.left = node.parent;
            } else {
                node.parent.parent.parent.right = node.parent;
            }
        }

        node.parent.right.parent = node.parent;
    }

    /**
     * Check the given {@code RBNode<T>} for the RbTree third case.
     * @param node - The {@code RBNode<T>} that should be checked.
     * @param uncle - The {@code RBNode<T>} uncle. The uncle can be null.
     */
    private void balanceCaseThree(RBNode<T> node, RBNode<T> uncle) {
        if (node.parent.left.equals(node)) {
            return;
        }
        if (uncle != null && uncle.color != RBColor.BLACK) {
            return;
        }

        if (node.parent.parent.left.equals(node.parent)) {
            node.parent.parent.left = node;
        } else {
            node.parent.parent.right = node;
        }

        node.left = node.parent;
        node.parent = node.parent.parent;

        node.left.parent = node;
        node.left.right = null;
    }

    /**
     * Return the uncle of the given {@code RBNode<T>}
     * @param node - The node where you want the uncle from
     * @return An {@code RBNode<T>} as uncle or {@code null} if no uncle is present
     */
    private RBNode<T> getUncleFrom(RBNode<T> node) {
        if (node.parent.parent.left.equals(node.parent)) {
            return node.parent.parent.right;
        } else {
            return node.parent.parent.left;
        }
    }

    /**
     * Check the given {@code RBNode<T>} for the RbTree first case.
     * @param node - The {@code RBNode<T>} that should be checked.
     * @param uncleOfNode - The {@code RBNode<T>} uncle. The uncle can be null.
     */
    private void balanceCaseOne(RBNode<T> node, RBNode<T> uncleOfNode) {
        if (uncleOfNode == null) {
            return;
        }

        if (uncleOfNode.color != RBColor.RED) {
            return;
        }

        uncleOfNode.color = RBColor.BLACK;
        node.parent.color = RBColor.BLACK;
        node.parent.parent.color = RBColor.RED;
        balanceTree(node.parent.parent);
    }


    /**
     * A Method that insert a given Value to the Tree
     *
     * @param value - The value that should be added into the structure
     * @return - The given value
     */
    public T insert(T value){
        RBNode<T> newNode = new RBNode<>(value, RBColor.RED);

        if (root == null) { // check if root exist
            root = newNode;
            newNode.color = RBColor.BLACK;
            return value;
        }

        insertNode(root, newNode);
        balanceTree(newNode);

        return value;
    }

    private int calcHeigth(RBNode<T> node) {
        if (node == null) {
            return 0;
        }
        int h1 = calcHeigth(node.left);
        int h2 = calcHeigth(node.right);

        return (h1 > h2 ? h1 : h2) +1;
    }

    /**
     * Return the normal Height of the RB Tree.
     *
     * @return the height
     */
    public int getHeight() {
        return calcHeigth(root);
    }

    /**
     * Returns the root of this tree
     * @return - {@code RBNode<T>} the root node of the tree
     */
    public RBNode<T> getRoot() {
        return root;
    }

    //region innerClasses
    /**
     * The specified color that a tree can have
     */
    private enum RBColor {
        RED,
        BLACK
    }

    /**
     * A Node in an RedBlackTree structure.
     *
     * @param <T> The Generic Parameter for the Node.
     */
    public class RBNode<T> {
        private RBNode<T> parent;
        private RBNode<T> left;
        private RBNode<T> right;

        private T value;
        private RBColor color;

        RBNode(T value, RBColor color) {
            this.value = value;
            this.color = color;
        }

        public RBNode<T> getParent() {
            return parent;
        }

        public RBNode<T> getLeft() {
            return left;
        }

        public RBNode<T> getRight() {
            return right;
        }

        public T getValue() {
            return value;
        }

        public RBColor getColor() {
            return color;
        }
    }
    //endregion




}
