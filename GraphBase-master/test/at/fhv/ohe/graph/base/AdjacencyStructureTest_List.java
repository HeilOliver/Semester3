package at.fhv.ohe.graph.base;

import at.fhv.ohe.graph.base.AdjacencyStructure;
import at.fhv.ohe.graph.base.AdjacencyStructures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static at.fhv.ohe.graph.base.AdjacencyStructures.LIST;
import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("Duplicates")
class AdjacencyStructureTest_List {
    private AdjacencyStructure<String, Integer> structure;
    private AdjacencyStructures testType = LIST;

    @BeforeEach
    void createStructure() {
        structure = testType.getInstance();
        structure.addVertices(Set.of("A", "B", "C"));
        structure.addEdge("A", "B", 100);
        structure.addEdge("B", "C", 10);
        structure.addEdge("A", "C", 1);
    }

    private void clearStructure() {
        structure = testType.getInstance();
    }


    @Test
    void addVertices_Set() {
        clearStructure();
        Collection<String> expected = Set.of("A", "B", "C");
        structure.addVertices(Set.of("A", "B", "C"));

        Collection<String> result = structure.getVertices();
        assertEquals(expected, result);
    }

    @Test
    void addVertices_Set_NullCheck() {
        try {
            structure.addVertices((Collection<String>) null);
        } catch (Exception ignore) {
            fail("3");
        }
    }

    @Test
    void addVertices_SecondAdd() {
        Collection<String> expected = Set.of("A", "B", "C", "D", "E");
        structure.addVertices(Set.of("D", "E"));

        Collection<String> result = structure.getVertices();
        assertEquals(expected, result);
    }

    @Test
    void addVertices_Single() {
        clearStructure();
        Collection<String> expected = Set.of("A");
        structure.addVertices("A");
        Collection<String> result = structure.getVertices();
        assertEquals(expected, result);
    }

    @Test
    void addEdges() {
        clearStructure();
        structure.addVertices(Set.of("A", "B", "C"));
        structure.addEdge("A", "B", 100);
        structure.addEdge("B", "C", 10);
        structure.addEdge("A", "C", 1);

        Collection<Integer> expected = Set.of(100, 10, 1);
        Collection<Integer> result = structure.getEdges();

        assertEquals(3, result.size());
        for (Integer integer : expected) {
            assertTrue(result.contains(integer));
        }
    }

    @Test
    void addEdges_NullCheck() {
        try {
            structure.addEdge(null, "B", 100);
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
        try {
            structure.addEdge("A", null, 100);
            fail("2");
        } catch (IllegalArgumentException ignore) {
        }
        try {
            structure.addEdge("A", "B", null);
        } catch (IllegalArgumentException ignore) {
            fail("3");
        }
    }

    @Test
    void addEdges_WrongV() {
        try {
            structure.addEdge("G", "B", 100);
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
        try {
            structure.addEdge("A", "F", 100);
            fail("2");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void removeEdge() {
        structure.removeEdge("A", "C");

        Collection<Integer> expected = Set.of(100, 10);
        Collection<Integer> result = structure.getEdges();

        assertEquals(2, result.size());
        for (Integer integer : expected) {
            assertTrue(result.contains(integer));
        }
    }

    @Test
    void removeEdge_NullCheck() {
        try {
            structure.removeEdge(null, "B");
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
        try {
            structure.removeEdge("A", null);
            fail("2");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void removeEdge_WrongV() {
        try {
            structure.removeEdge("H", "B");
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
        try {
            structure.removeEdge("A", "F");
            fail("2");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void outputGrade() {
        int result;
        result = structure.outputGrade("A");
        assertEquals(2, result, "A");

        result = structure.outputGrade("B");
        assertEquals(1, result, "B");

        result = structure.outputGrade("C");
        assertEquals(0, result, "C");
    }

    @Test
    void outputGrade_NullCheck() {
        try {
            structure.outputGrade(null);
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void outputGrade_WrongV() {
        try {
            structure.outputGrade("F");
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void entryGrade() {
        int result;
        result = structure.entryGrade("A");
        assertEquals(0, result, "A");

        result = structure.entryGrade("B");
        assertEquals(1, result, "B");

        result = structure.entryGrade("C");
        assertEquals(2, result, "C");
    }

    @Test
    void entryGrade_NullCheck() {
        try {
            structure.entryGrade(null);
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void entryGrade_WrongV() {
        try {
            structure.entryGrade("F");
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void isConnected() {
        assertTrue(structure.isConnected("A", "B"), "1");
        assertTrue(structure.isConnected("B", "C"), "2");
        assertTrue(structure.isConnected("A", "C"), "3");

        assertFalse(structure.isConnected("C", "A"), "4");
        assertFalse(structure.isConnected("C", "B"), "5");
        assertFalse(structure.isConnected("B", "A"), "6");
    }

    @Test
    void isConnected_NullCheck() {
        try {
            structure.isConnected(null, "B");
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }
        try {
            structure.isConnected("A", null);
            fail("2");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void isConnected_WrongV() {
        try {
            structure.isConnected("F", "B");
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }

        try {
            structure.isConnected("A", "H");
            fail("2");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void isConnected_NoEdge() {
        assertFalse(structure.isConnected("B", "A"));
        assertFalse(structure.isConnected("C", "B"));
        assertFalse(structure.isConnected("C", "A"));
    }

    @Test
    void getEdge() {
        assertEquals((Integer) 100, structure.getEdge("A", "B"));
        assertEquals((Integer) 10, structure.getEdge("B", "C"));
        assertEquals((Integer) 1, structure.getEdge("A", "C"));
    }

    @Test
    void getEdge_NullCheck() {
        try {
            structure.getEdge(null, "B");
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }

        try {
            structure.getEdge("A", null);
            fail("2");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void getEdge_WrongV() {
        try {
            structure.getEdge("F", "B");
            fail("1");
        } catch (IllegalArgumentException ignore) {
        }

        try {
            structure.getEdge("A", "H");
            fail("2");
        } catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void getEdge_NoEdge() {
        assertNull(structure.getEdge("B", "A"));
        assertNull(structure.getEdge("C", "B"));
        assertNull(structure.getEdge("C", "A"));
    }

    @Test
    void isUndirected() {
        boolean result = structure.isUndirected("A", "B");
        assertFalse(result);


        clearStructure();
        structure.addVertices(Set.of("A", "B", "C"));
        structure.addEdge("A", "B", 100, true);
        result = structure.isUndirected("A", "B");
        assertTrue(result);
    }
}
