package at.fhv.ohe.functionplotter.exceptions;

/**
 * An Exception that shoud be thrown if a Function with the same Function Body is found
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalFunctionControllerException extends Exception {

    public IllegalFunctionControllerException() {
    }

    private IllegalFunctionControllerException(String message) {
        super(message);
    }
}
