package at.fhv.ohe.binarytree;

import java.util.*;

public class BinaryTree<T> extends AbstractTreeSet<T> implements ITreeSet<T> {
    protected Node<T> rootNode;
    private int size;
    private int leaves;

    //region Constructors
    public BinaryTree(Set<T> initialValues) {
        this();

        for (T t : initialValues) {
            add(t);
        }
    }

    public BinaryTree(T[] initialValues) {
        this();

        for (T t : initialValues) {
            add(t);
        }
    }

    public BinaryTree(List<T> initialValues) {
        this();

        for (T t : initialValues) {
            add(t);
        }
    }

    public BinaryTree() {
        leaves = 0;
        size = 0;
    }
    //endregion

    @Override
    public void add(T element) {
        ++size;
        Node<T> newNode = new Node<T>(element);

        if (rootNode == null) {
            leaves = 1;
            rootNode = newNode;
            return;
        }

        addElement(rootNode, newNode);
    }

    @Override
    public boolean contains(T element) {
        return findElement(rootNode, element);
    }

    @Override
    public List<T> toArray(ReturnOrder order) {
        List<T> arr = new ArrayList<>(size);
        toArray(rootNode,arr,order);
        return arr;
    }

    private boolean findElement(Node node, T element) {
        if (node == null) {
            return false;
        }

        if (node.element.equals(element)) {
            return true;
        }

        if (findElement(node.leftChild, element)) {
            return true;
        }

        if (findElement(node.rightChild, element)) {
            return true;
        }

        return false;
    }

    /*
    private void addElement(Node start, Node addNode) {
        Node next = start;
        int size = this.size+1;
        int level = 1;
        int maxCount = 0;

        while (size >= 0) {
            maxCount = (int)Math.pow(2,level++) - 1;

            if ((((size-maxCount)/2) % 2) == 0) {
                if (next.rightChild == null) {
                    next.rightChild = addNode;
                    return;
                }
                next = next.rightChild;
            } else {
                if (next.leftChild == null) {
                    next.leftChild = addNode;
                    return;
                }
                next = next.leftChild;
            }
        }
    }
*/

    private boolean addElement(Node start, Node addNode) {
        if (doElement(start, addNode)) {
            return true;
        }

        if (doElement(start.leftChild, addNode)) {
            return true;
        }
        if (doElement(start.rightChild, addNode)) {
            return true;
        }

        if (addElement(start.leftChild, addNode)) {
            return true;
        }
        if (addElement(start.rightChild, addNode)) {
            return true;
        }
        return false;
    }

    private boolean doElement(Node start, Node addNode) {
        if (start.leftChild == null) {
            start.leftChild = addNode;
            return true;
        }
        if (start.rightChild == null) {
            start.rightChild = addNode;
            leaves++;
            return true;
        }
        return false;
    }


    @Override
    public int leaveCount() {
        return leaves;
    }

    @Override
    public int height() {
        int height = 0;
        int size = this.size;

        if (size <= 0)
            return 0;

        do {
            height++;
        } while ((size = (size >> 1)) > 0);

        return height;
    }

    @Override
    public int size() {
        return size;
    }

}
