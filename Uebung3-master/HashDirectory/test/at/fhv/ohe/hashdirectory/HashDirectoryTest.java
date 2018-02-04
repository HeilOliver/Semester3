package at.fhv.ohe.hashdirectory;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class HashDirectoryTest {
    private HashDirectory<TestClass, String> _testTable;

    class TestClass {
        int a;
        int b;
        boolean hu;
        int _eqlC;
        int _hcC;

        private TestClass(int a, int b, boolean hashUse) {
            this.a = a;
            this.b = b;
            hu = hashUse;
        }

        private TestClass() {
        this(0,0,false);
        }

        @Override
        public int hashCode() {
            _hcC++;
            if (hu) {
                return a * b;
            }
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TestClass))
                return false;

            TestClass obj1 = (TestClass) obj;
            obj1._eqlC++;
            _eqlC++;

            if (hu) {
                return obj1.a == a && obj1.b == b;
            }
            return super.equals(obj);
        }
    }

    @BeforeEach
    void setUp() {
        _testTable = new DoubleHashingHashTable<>(7);
        //_testTable = new LinearProbingHashTable<>(7);
        //_testTable = new QuadraticProbingHashTable<>(7);
    }

    @Test
    void testTable_Put_ReturnVal() {
        TestClass key0 = new TestClass(0, 0, false);
        String val0 = "abc";
        String val1 = "abc";
        String result;

        result = _testTable.put(key0, val0);
        assertEquals(val0, result);
        result = _testTable.put(key0, val1);
        assertEquals(val0, result);
        result = _testTable.put(key0, val0);
        assertEquals(val1, result);
    }

    @Test
    void testTable_Put_ReturnValTwoElements() {
        TestClass key0 = new TestClass(0, 2, true);
        TestClass key1 = new TestClass(2, 0, true);
        // generating hash collisions
        String val0 = "abc";
        String val1 = "abc";
        String result;

        result = _testTable.put(key0, val0);
        assertEquals(val0, result);
        result = _testTable.put(key1, val0);
        assertEquals(val0, result);
        result = _testTable.put(key0, val1);
        assertEquals(val0, result);
        result = _testTable.put(key1, val1);
        assertEquals(val0, result);
        result = _testTable.put(key0, val0);
        assertEquals(val1, result);
        result = _testTable.put(key1, val0);
        assertEquals(val1, result);
    }

    @Test
    void testTable_ContainsKey() {
        TestClass key0 = new TestClass(0, 2, false);
        TestClass key1 = new TestClass(2, 0, false);
        TestClass key2 = new TestClass(2, 2, false);
        // generating hash collisions
        String val0 = "abc";

        _testTable.put(key0, val0);
        _testTable.put(key1, val0);
        _testTable.put(key2, val0);

        assertEquals(3, _testTable.size(), "Table Size");

        assertTrue(_testTable.containsKey(key0), "0");
        assertTrue(_testTable.containsKey(key1), "1");
        assertTrue(_testTable.containsKey(key2), "2");

        assertFalse(_testTable.containsKey(new TestClass()), "3");
    }

    @Test
    void testTable_containsValue() {
        TestClass key0 = new TestClass(0, 2, false);
        TestClass key1 = new TestClass(2, 0, false);
        TestClass key2 = new TestClass(2, 2, false);

        String val0 = "a";
        String val1 = "b";
        String val2 = "c";

        _testTable.put(key0,val0);
        _testTable.put(key1,val1);
        _testTable.put(key2,val2);

        assertTrue(_testTable.containsValue(val0), " val 0");
        assertTrue(_testTable.containsValue(val1), " val 1");
        assertTrue(_testTable.containsValue(val2), " val 2");
    }

    @Test
    void testTable_get() {
        TestClass key0 = new TestClass(0, 2, false);
        TestClass key1 = new TestClass(2, 0, false);
        TestClass key2 = new TestClass(2, 2, false);

        String val0 = "a";
        String val1 = "b";
        String val2 = "c";

        _testTable.put(key0,val0);
        _testTable.put(key1,val1);
        _testTable.put(key2,val2);

        assertEquals(val0,_testTable.get(key0), "val 0");
        assertEquals(val1,_testTable.get(key1), "val 1");
        assertEquals(val2,_testTable.get(key2), "val 2");
    }

    @Test
    void testTable_remove() {
        TestClass key0 = new TestClass(0, 2, false);
        TestClass key1 = new TestClass(2, 0, false);
        TestClass key2 = new TestClass(2, 2, false);

        String val0 = "a";
        String val1 = "b";
        String val2 = "c";

        _testTable.put(key0,val0);
        _testTable.put(key1,val1);
        _testTable.put(key2,val2);

        assertEquals(val0,_testTable.remove(key0), "rm val 0");
        assertEquals(val1,_testTable.remove(key1), "rm val 1");
        assertEquals(val2,_testTable.remove(key2), "rm val 2");

        assertFalse(_testTable.containsValue(val0), "val 0");
        assertFalse(_testTable.containsValue(val1), "val 1");
        assertFalse(_testTable.containsValue(val2), "val 2");
        assertFalse(_testTable.containsKey(key0), "key 0");
        assertFalse(_testTable.containsKey(key1), "key 1");
        assertFalse(_testTable.containsKey(key2), "key 2");

        assertEquals(0,_testTable.size(), "tab size");
    }

    @Test
    void testTable_clear() {
        TestClass key0 = new TestClass(0, 2, false);
        TestClass key1 = new TestClass(2, 0, false);
        TestClass key2 = new TestClass(2, 2, false);

        String val0 = "a";
        String val1 = "b";
        String val2 = "c";

        _testTable.put(key0,val0);
        _testTable.put(key1,val1);
        _testTable.put(key2,val2);

        _testTable.clear();

        assertFalse(_testTable.containsValue(val0), "val 0");
        assertFalse(_testTable.containsValue(val1), "val 1");
        assertFalse(_testTable.containsValue(val2), "val 2");
        assertFalse(_testTable.containsKey(key0), "key 0");
        assertFalse(_testTable.containsKey(key1), "key 1");
        assertFalse(_testTable.containsKey(key2), "key 2");

        assertEquals(0,_testTable.size(), "tab size");
    }

    @Test
    void testTable_SetEntry() {
        TestClass key0 = new TestClass(0, 2, false);
        TestClass key1 = new TestClass(2, 0, false);
        TestClass key2 = new TestClass(2, 2, false);

        String val0 = "a";
        String val1 = "b";
        String val2 = "c";

        _testTable.put(key0,val0);
        _testTable.put(key1,val1);
        _testTable.put(key2,val2);

        Set<Map.Entry<TestClass, String>> entries = _testTable.entrySet();

        assertEquals(3,entries.size(), "set size");
        _testTable.clear();
        assertEquals(0,entries.size(), "set size");
    }

    @Test
    void testTable_InsertTestWithoutHC() {
        TestClass key0 = new TestClass(1, 2, true);
        TestClass key1 = new TestClass(1, 3, true);
        TestClass key2 = new TestClass(1, 4, true);

        String val0 = "a";
        String val1 = "b";
        String val2 = "c";

        _testTable.put(key0,val0);
        _testTable.put(key1,val1);
        _testTable.put(key2,val2);

        assertEquals(0,key0._eqlC, "key0 - equal Count");
        assertEquals(1,key0._hcC, "key0 - HashCode Count");

        assertEquals(0,key1._eqlC, "key1 - equal Count");
        assertEquals(1,key1._hcC, "key1 - HashCode Count");

        assertEquals(0,key2._eqlC, "key2 - equal Count");
        assertEquals(1,key2._hcC, "key2 - HashCode Count");
    }

    @Test
    void testTable_InsertTestWithHC() {
        TestClass key0 = new TestClass(0, 1, true);
        TestClass key1 = new TestClass(0, 2, true);
        TestClass key2 = new TestClass(0, 3, true);

        String val0 = "a";
        String val1 = "b";
        String val2 = "c";

        _testTable.put(key0,val0);
        assertEquals(0,key0._eqlC, "key0 - equal Count");
        assertEquals(1,key0._hcC, "key0 - HashCode Count");

        _testTable.put(key1,val1);
        assertEquals(1,key1._eqlC, "key1 - equal Count");
        assertEquals(1,key1._hcC, "key1 - HashCode Count");

        _testTable.put(key2,val2);
        assertEquals(2,key2._eqlC, "key2 - equal Count");
        assertEquals(1,key2._hcC, "key2 - HashCode Count");
    }

    @Test
    void testTable_growTable() {
        TestClass[] keyArr = new TestClass[15];
        for (int i = 0; i < keyArr.length; i++) {
            keyArr[i] = new TestClass();
        }

        String val0 = "a";

        // Putting first one in and out
        _testTable.put(keyArr[0], val0);
        _testTable.remove(keyArr[0]);

        // Add all other keys and forces the Table to grow
        for (int i = 1; i < keyArr.length; i++) {
            _testTable.put(keyArr[i], val0);
        }

        assertEquals(keyArr.length - 1, _testTable.size(), "Size after Grow");

        // testing first entry. it shout be not there anymore
        assertFalse(_testTable.containsKey(keyArr[0]), "key 0");

        // testing all other entries
        for (int i = 1; i < keyArr.length; i++) {
            assertTrue(_testTable.containsKey(keyArr[i]));
        }
    }
}