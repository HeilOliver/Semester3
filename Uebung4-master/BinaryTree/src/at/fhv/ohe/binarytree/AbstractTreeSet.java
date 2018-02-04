package at.fhv.ohe.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractTreeSet<T> {

    public void toArray(Node<T> rootNode, List<T> arr,ITreeSet.ReturnOrder order) {
        switch (order) {
            case PreOrder:
                toArrayPreOrder(rootNode, arr);
                break;
            case InOrder:
                toArrayInOrder(rootNode, arr);
                break;
            case PostOrder:
                toArrayPostOrder(rootNode, arr);
                break;
            case LevelByLevel:
                toArrayLevelOrder(rootNode, arr);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void toArrayPreOrder(Node<T> t, List<T> arr) {
        if (t != null) {
            arr.add(t.element);
            toArrayPreOrder(t.leftChild, arr);
            toArrayPreOrder(t.rightChild, arr);
        }
    }

    private void toArrayInOrder(Node<T> t, List<T> arr) {
        if (t != null) {
            toArrayInOrder(t.leftChild, arr);
            arr.add(t.element);
            toArrayInOrder(t.rightChild, arr);
        }
    }

    private void toArrayPostOrder(Node<T> t, List<T> arr) {
        if (t != null) {
            toArrayPostOrder(t.leftChild, arr);
            toArrayPostOrder(t.rightChild, arr);
            arr.add(t.element);
        }
    }

    private void toArrayLevelOrder(Node<T> rootNode, List<T> arr) {
        Queue<Node<T>> q = new LinkedList<>();
        if (rootNode == null) {
            return;
        }
        q.add(rootNode);

        while (true) {

            int nodeCount = q.size();
            if (nodeCount == 0)
                break;

            while (nodeCount > 0) {
                Node<T> node = q.peek();
                arr.add(node.element);
                q.remove();
                if (node.leftChild != null)
                    q.add(node.leftChild);
                if (node.rightChild != null)
                    q.add(node.rightChild);
                nodeCount--;
            }
        }
    }

    class Node<T> {
        T element;

        Node<T> leftChild;
        Node<T> rightChild;

        Node(T element) {
            this.element = element;
        }
    }
}
