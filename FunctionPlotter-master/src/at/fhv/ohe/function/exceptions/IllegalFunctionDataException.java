package at.fhv.ohe.function.exceptions;

/**
 * An Exception that should be thrown if a Function Data Operation occurs (Save/Load)
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalFunctionDataException extends Exception {

    public IllegalFunctionDataException() {
    }

    public IllegalFunctionDataException(String message) {
        super(message);
    }
}
