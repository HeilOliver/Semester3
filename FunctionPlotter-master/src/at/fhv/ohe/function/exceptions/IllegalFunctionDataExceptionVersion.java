package at.fhv.ohe.function.exceptions;

/**
 * An Exception that should be thrown if a Function Data Operation occurs (Save/Load)
 * The Version of the JSON Controller is not the same as in the file
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalFunctionDataExceptionVersion extends IllegalFunctionDataException {

    public IllegalFunctionDataExceptionVersion() {
    }

    public IllegalFunctionDataExceptionVersion(String message) {
        super(message);
    }

}
