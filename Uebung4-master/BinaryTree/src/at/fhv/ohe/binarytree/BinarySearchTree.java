package at.fhv.ohe.binarytree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class BinarySearchTree<T> extends AbstractTreeSet<T> implements ITreeSet<T> {

    private Comparator<T> comparator;
    protected Node<T> rootNode;
    private int size;
    private int leaves;
    private int height;
    private int addHeight;

    //region Constructors
    public BinarySearchTree(Set<T> initialValues, Comparator<T> comparator) {
        this(comparator);

        for (T t : initialValues) {
            add(t);
        }
    }

    public BinarySearchTree(T[] initialValues, Comparator<T> comparator) {
        this(comparator);

        for (T t : initialValues) {
            add(t);
        }
    }

    public BinarySearchTree(List<T> initialValues, Comparator<T> comparator) {
        this(comparator);

        for (T t : initialValues) {
            add(t);
        }
    }

    //endregion

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(T element) {
        size++;
        Node<T> newNode = new Node<T>(element);

        if (rootNode == null) {
            rootNode = newNode;
            height++;
            leaves++;
            return;
        }

        addNode(newNode);
    }

    @Override
    public boolean contains(T element) {
        return findElement(element, rootNode);
    }

    private boolean findElement(T element, Node<T> nextNode) {
        if (nextNode == null)
            return false;

        if (nextNode.element.equals(element))
            return true;

        if (comparator.compare(element, nextNode.element) >= 0) {
            return findElement(element,nextNode.leftChild);
        } else {
            return findElement(element,nextNode.rightChild);
        }
    }

    private void addNode(Node<T> newNode) {
        addHeight = 1;
        addNode(newNode, rootNode);
        height = addHeight > height ? addHeight : height;
    }

    private void addNode(Node<T> newNode, Node<T> startNode) {
        if (comparator.compare(newNode.element, startNode.element) >= 0) {
            addHeight++;
            if (startNode.leftChild == null) {
                if (startNode.rightChild != null) {
                    leaves++;
                }
                startNode.leftChild = newNode;
                return;
            }
            addNode(newNode, startNode.leftChild);
        } else {
            addHeight++;
            if (startNode.rightChild == null) {
                if (startNode.leftChild != null) {
                    leaves++;
                }
                startNode.rightChild = newNode;
                return;
            }
            addNode(newNode, startNode.rightChild);
        }
    }

    public List<T> toArray(ITreeSet.ReturnOrder order) {
        List<T> arr = new ArrayList<>(size);
        toArray(rootNode,arr,order);
        return arr;
    }

    @Override
    public int leaveCount() {
        return leaves;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int size() {
        return size;
    }


}
