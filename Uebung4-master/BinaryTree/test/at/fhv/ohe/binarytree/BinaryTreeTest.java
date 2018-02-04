package at.fhv.ohe.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;

import static at.fhv.ohe.binarytree.ITreeSet.ReturnOrder.*;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest extends BinaryTree {
    BinaryTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree<>();
    }

    @Test
    void TestAdd() {
        tree.add(10);
        tree.add(11);
        tree.add(12);
        tree.add(13);

        int expectedSize = 4;

        assertEquals(expectedSize, tree.size());

        assertNotNull(tree.rootNode,"0");

        assertNotNull(tree.rootNode.leftChild,"1");
        assertNotNull(tree.rootNode.leftChild.leftChild,"2");
        assertNull(tree.rootNode.leftChild.rightChild,"3");

        assertNotNull(tree.rootNode.rightChild,"4");
        assertNull(tree.rootNode.rightChild.leftChild,"5");
        assertNull(tree.rootNode.rightChild.rightChild,"6");

        tree.add(14);
        tree.add(15);

        assertNotNull(tree.rootNode.leftChild.rightChild,"7");

        assertNotNull(tree.rootNode.rightChild,"8");
        assertNotNull(tree.rootNode.rightChild.leftChild,"9");
        assertNull(tree.rootNode.rightChild.rightChild,"10");
/*
        tree.add(16);
        tree.add(17);
        tree.add(18);
        tree.add(19);
        tree.add(20);
        tree.add(21);

        assertNull(tree.rootNode.rightChild.rightChild.rightChild,"11");
        assertNull(tree.rootNode.rightChild.leftChild.rightChild,"12");
        assertNotNull(tree.rootNode.rightChild.leftChild.leftChild,"13");
        */
    }

    @Test
    void TreeLeaveCount() {
        assertEquals(0, tree.leaveCount());
        tree.add(10);
        tree.add(11);
        tree.add(12);
        tree.add(13);


        assertEquals(2, tree.leaveCount());
        tree.add(14);
        assertEquals(3, tree.leaveCount());
        tree.add(15);
        assertEquals(3, tree.leaveCount());
    }

    @Test
    void TestHeight() {
        assertEquals(0, tree.height());
        tree.add(10);
        assertEquals(1, tree.height());
        tree.add(11);
        assertEquals(2, tree.height());
        tree.add(12);
        assertEquals(2, tree.height());
        tree.add(13);
        assertEquals(3, tree.height());
    }

    @Test
    void contains() {
        tree.add(10);
        tree.add(11);
        tree.add(12);
        tree.add(13);

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(11));

        assertFalse(tree.contains(20));
        assertFalse(tree.contains(25));
    }

    @Test
    void toArray() {
        assertEquals(Collections.<Integer>emptyList(), tree.toArray(PreOrder));
        assertEquals(Collections.<Integer>emptyList(), tree.toArray(PostOrder));
        assertEquals(Collections.<Integer>emptyList(), tree.toArray(InOrder));
        assertEquals(Collections.<Integer>emptyList(), tree.toArray(LevelByLevel));

        tree.add(10);
        tree.add(11);
        tree.add(12);
        tree.add(13);

        Map<ReturnOrder, List<Integer>> map = new EnumMap<>(ReturnOrder.class);

        map.put(PreOrder, List.of(10, 11, 13, 12));
        map.put(PostOrder, List.of(13, 11, 12, 10));
        map.put(InOrder, List.of(13, 11, 10, 12));
        map.put(LevelByLevel, List.of(10, 11, 12, 13));

        assertEquals(map.get(PreOrder), tree.toArray(PreOrder));
        assertEquals(map.get(PostOrder), tree.toArray(PostOrder));
        assertEquals(map.get(InOrder), tree.toArray(InOrder));
        assertEquals(map.get(LevelByLevel), tree.toArray(LevelByLevel));
    }

    @Test
    void TestConstruct_List() {
        List<Integer> list = List.of(20, 21, 22, 23, 24);
        tree = new BinaryTree<>(list);

        assertTrue(tree.contains(20));
        assertTrue(tree.contains(21));
        assertTrue(tree.contains(22));
        assertTrue(tree.contains(23));
        assertTrue(tree.contains(24));
    }

    @Test
    void TestConstruct_Set() {
        Set<Integer> set = Set.of(20, 21, 22, 23, 24);
        tree = new BinaryTree<>(set);

        assertTrue(tree.contains(20));
        assertTrue(tree.contains(21));
        assertTrue(tree.contains(22));
        assertTrue(tree.contains(23));
        assertTrue(tree.contains(24));
    }

    @Test
    void TestConstruct_Array() {
        Integer[] arr = new Integer[]{20, 21, 22, 23, 24};
        tree = new BinaryTree<>(arr);

        assertTrue(tree.contains(20));
        assertTrue(tree.contains(21));
        assertTrue(tree.contains(22));
        assertTrue(tree.contains(23));
        assertTrue(tree.contains(24));
    }


}