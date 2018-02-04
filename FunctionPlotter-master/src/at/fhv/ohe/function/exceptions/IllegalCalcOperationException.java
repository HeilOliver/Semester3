package at.fhv.ohe.function.exceptions;

/**
 * An Exception that shoud be thrown if a Function Calc Operation occurs
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalCalcOperationException extends Exception {

    public IllegalCalcOperationException() {
    }

    public IllegalCalcOperationException(String message) {
        super(message);
    }
}
