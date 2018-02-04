package at.fhv.ohe.function.exceptions;

/**
 * An Exception that should be thrown if a Function Format is not correct
 * Too much or not enough braces in the Function
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalFunctionFormatExceptionBraceCount extends IllegalFunctionFormatException {

    public IllegalFunctionFormatExceptionBraceCount() {
    }

    public IllegalFunctionFormatExceptionBraceCount(String message) {
        super(message);
    }
}
