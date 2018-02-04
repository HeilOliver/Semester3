package at.fhv.ohe.function.exceptions;

/**
 * An Exception that should be thrown if a Function Format is not correct
 * The Function is Empty
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalFunctionFormatExceptionIsEmpty extends IllegalFunctionFormatException {

    private IllegalFunctionFormatExceptionIsEmpty() {
    }

    public IllegalFunctionFormatExceptionIsEmpty(String message) {
        super(message);
    }
}
