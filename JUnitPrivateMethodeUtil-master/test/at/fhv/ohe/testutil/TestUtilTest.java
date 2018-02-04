package at.fhv.ohe.testutil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class TestUtilTest {

    @Test
    void methodCall_withBasicParameterAndReturnType() throws Exception {
        TestClass testClass = new TestClass();
        int a = 4;
        int b = 5;
        int expResult = 9;
        int result;
        result = PrivateMethodTestUtil.privateMethodCall(testClass, "withBasicParameterAndReturnType", a,b);
        assertTrue(result == expResult);
    }

    @Test
    void methodCall_withBasicParameter() throws Exception {
        TestClass testClass = new TestClass();
        int a = 4;
        int b = 5;
        int expResult = 9;

        PrivateMethodTestUtil.privateMethodCall(testClass, "withBasicParameter", a,b);
        assertTrue(testClass.getResult() == expResult);
    }

    @Test
    void methodCall_withoutParameter() throws Exception {
        TestClass testClass = new TestClass();
        int expResult = 5;

        PrivateMethodTestUtil.privateMethodCall(testClass, "withoutParameter");
        assertTrue(testClass.getResult() == expResult);
    }

    @Test
    void methodCall_withoutParameterButReturnType() throws Exception {
        TestClass testClass = new TestClass();
        int expResult = 5;
        int result;

        result = PrivateMethodTestUtil.privateMethodCall(testClass, "withoutParameterButReturnType");
        assertTrue(result == expResult);
    }

    @Test
    void methodCall_withObjectParameterAndObjectReturn() throws Exception {
        TestClass testClass = new TestClass();
        String s0 = "Test0";
        String s1 = "Test1";
        String s2 = "Test2";
        String result;

        result = PrivateMethodTestUtil.privateMethodCall(testClass, "withObjectParameterAndObjectReturn",s0,s1,s2);
        assertTrue((s0 + s1 + s2).equals(result));
    }

    @Test
    void methodCall_noSuchMethodFound() throws Exception {
        TestClass testClass = new TestClass();
        try {
            PrivateMethodTestUtil.privateMethodCall(testClass, "noSuchMethodFound");
            assertTrue(false);
        } catch (NoSuchMethodException e) {
            assertTrue(true);
        }
    }
}