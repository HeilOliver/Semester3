package at.fhv.ohe.arraytotree;

import at.fhv.ohe.binarytree.BinaryTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeFactoryTest {

    @Test
    void Sample0Test() {
        BinaryTree<Integer> tree = TreeFactory.getTree("/samples/sample0.txt");

        assertNotNull(tree);
        assertTrue(tree.contains(0));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(4));
    }

}