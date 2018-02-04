package at.fhv.ohe.textcoding;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class CompressData {
    private Node rootNode;

    public CompressData(PriorityQueue<Character> queue) {
        Iterator<Character> iterator = queue.iterator();
        rootNode = new Node();
        boolean switchChild = false;


        Node nextItem = rootNode;
        while (iterator.hasNext()) {
            Node addNew = new Node(iterator.next());

            if (switchChild) {
                nextItem.rightChild = new Node();
                nextItem = nextItem.rightChild;
                nextItem.rightChild = addNew;
            } else {
                nextItem.leftChild = new Node();
                nextItem = nextItem.leftChild;
                nextItem.leftChild = addNew;
            }
            switchChild = !switchChild;
        }


    }

    public void getRoot() {

    }

    private class Node{
        boolean empty;
        Node leftChild;
        Node rightChild;
        char character;

        private Node() {
            empty = true;
        }

        public Node(Character next) {
            character = next;
        }
    }
}
