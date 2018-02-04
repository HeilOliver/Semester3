package at.fhv.ohe.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static at.fhv.ohe.binarytree.ITreeSet.ReturnOrder.*;
import static at.fhv.ohe.binarytree.ITreeSet.ReturnOrder.LevelByLevel;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest extends BinarySearchTree {
    private BinarySearchTree<Integer> tree;

    public BinarySearchTreeTest() { // Only for testing
        super(null);
    }

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>(((o1, o2) -> o1 > o2 ? 1 : o1 < o2 ? -1 : 0));
    }

    @Test
    void TestAdd() {
        tree.add(10);
        tree.add(11);
        tree.add(12);
        tree.add(16);

        assertEquals(4,tree.size());

        assertNotNull(tree.rootNode,"1");
        assertNotNull(tree.rootNode.leftChild,"2");
        assertNotNull(tree.rootNode.leftChild.leftChild,"3");
        assertNotNull(tree.rootNode.leftChild.leftChild.leftChild,"4");
        assertNull(tree.rootNode.rightChild);

        tree.add(5);
        tree.add(7);
        tree.add(3);
        assertNotNull(tree.rootNode.rightChild);
        assertNotNull(tree.rootNode.rightChild.rightChild);
        assertNotNull(tree.rootNode.rightChild.rightChild);
        assertNotNull(tree.rootNode.rightChild.leftChild);
    }

    @Test
    void TreeLeaveCount() {
        assertEquals(0, tree.leaveCount());
        tree.add(10);
        tree.add(11);
        tree.add(12);
        tree.add(16);

        assertEquals(1, tree.leaveCount());
        tree.add(5);

        assertEquals(2, tree.leaveCount());
        tree.add(7);
        assertEquals(2, tree.leaveCount());
        tree.add(3);
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
        assertEquals(3, tree.height());
        tree.add(16);
        assertEquals(4, tree.height());
    }

    @Test
    void TestContains() {
        tree.add(22);
        tree.add(13);
        tree.add(14);
        tree.add(15);

        assertTrue(tree.contains(22),"0");
        assertTrue(tree.contains(14),"1");

        assertFalse(tree.contains(20),"2");
        assertFalse(tree.contains(25),"3");
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
        tree.add(5);
        tree.add(7);

        Map<ITreeSet.ReturnOrder, List<Integer>> map = new EnumMap<>(ITreeSet.ReturnOrder.class);

        map.put(PreOrder, List.of(10,11,12,5,7));
        map.put(PostOrder, List.of(12,11,7,5,10));
        map.put(InOrder, List.of(12,11,10,7,5));
        map.put(LevelByLevel, List.of(10,11,5,12,7));

        assertEquals(map.get(PreOrder), tree.toArray(PreOrder));
        assertEquals(map.get(PostOrder), tree.toArray(PostOrder));
        assertEquals(map.get(InOrder), tree.toArray(InOrder));
        assertEquals(map.get(LevelByLevel), tree.toArray(LevelByLevel));
    }

    @Test
    void TestConstruct_List() {
        List<Integer> list = List.of(20, 21, 22, 23, 24);
        tree = new BinarySearchTree<>(list,(o1, o2) -> o1 > o2 ? 1 : o1 < o2 ? -1 : 0);

        assertTrue(tree.contains(20));
        assertTrue(tree.contains(21));
        assertTrue(tree.contains(22));
        assertTrue(tree.contains(23));
        assertTrue(tree.contains(24));
    }

    @Test
    void TestConstruct_Set() {
        Set<Integer> set = Set.of(20, 21, 22, 23, 24);
        tree = new BinarySearchTree<>(set,(o1, o2) -> o1 > o2 ? 1 : o1 < o2 ? -1 : 0);

        assertTrue(tree.contains(20));
        assertTrue(tree.contains(21));
        assertTrue(tree.contains(22));
        assertTrue(tree.contains(23));
        assertTrue(tree.contains(24));
    }

    @Test
    void TestConstruct_Array() {
        Integer[] arr = new Integer[]{20, 21, 22, 23, 24};
        tree = new BinarySearchTree<>(arr,(o1, o2) -> o1 > o2 ? 1 : o1 < o2 ? -1 : 0);

        assertTrue(tree.contains(20));
        assertTrue(tree.contains(21));
        assertTrue(tree.contains(22));
        assertTrue(tree.contains(23));
        assertTrue(tree.contains(24));
    }
}