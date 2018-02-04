package at.fhv.ohe.testutil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("WeakerAccess")
public class PrivateMethodTestUtil {

    private static boolean checkParameterEquality (Class<?>[] a,Class<?>[] b ) {
        if (a.length != b.length)
            return false;

        for (int i = 0; i < a.length; i++) {
            String s = a[i].getName();
            switch (s) {
                case "int":
                    s = "java.lang.Integer";
                    break;

                case "long":
                    s = "java.lang.Long";
                    break;

                case "boolean":
                    s = "java.lang.Boolean";
                    break;

                case "char":
                    s = "java.lang.Character";
                    break;

                case "byte":
                    s = "java.lang.Byte";
                    break;

                case "short":
                    s = "java.lang.Short";
                    break;

                case "float":
                    s = "java.lang.Float";
                    break;

                case "double":
                    s = "java.lang.Double";
                    break;
            }
            if (!b[i].getName().equals(s)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings({"UnusedReturnValue", "unchecked"})
    public static <T> T privateMethodCall(Object targetClass, String method, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method searchedMethod = null;

        Class<?>[] argTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        Method[] methods = targetClass.getClass().getDeclaredMethods();
        for (Method classMethod : methods) {
            if (classMethod.getName().equals(method) && checkParameterEquality(classMethod.getParameterTypes(),argTypes)) {
                searchedMethod = classMethod;
                break;
            }
        }

        if (searchedMethod == null) {
            throw new NoSuchMethodException();
        }

        searchedMethod.setAccessible(true);
        return (T) searchedMethod.invoke(targetClass, args);
    }
}
