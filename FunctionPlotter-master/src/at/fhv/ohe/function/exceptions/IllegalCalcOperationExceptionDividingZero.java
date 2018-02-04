package at.fhv.ohe.function.exceptions;

/**
 * An Exception that shoud be thrown if a Function Calc Operation occurs
 * Dividing Zero
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalCalcOperationExceptionDividingZero extends IllegalCalcOperationException {
    private IllegalCalcOperationExceptionDividingZero() {
    }

    public IllegalCalcOperationExceptionDividingZero(String message) {
        super(message);
    }
}
