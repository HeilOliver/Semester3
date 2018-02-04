package at.fhv.ohe.testutil;

class TestClass{
    private int _result;

    private int withBasicParameterAndReturnType(int a, int b) {
        return a+b;
    }

    private void withBasicParameter(int a, int b) {
        _result = a+b;
    }

    private int withoutParameterButReturnType() {
        return 5;
    }

    private void withoutParameter() {
        _result = 5;
    }

    private String withObjectParameterAndObjectReturn(String s0, String s1, String s2) {
        return (s0 + s1 + s2);
    }

    int getResult() {
        return _result;
    }
}