package at.fhv.ohe.function.exceptions;

/**
 * An Exception that should be thrown if a Function Format is not correct
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalFunctionFormatException extends Exception {
    public IllegalFunctionFormatException() {
    }

    public IllegalFunctionFormatException(String message) {
        super(message);
    }
}
