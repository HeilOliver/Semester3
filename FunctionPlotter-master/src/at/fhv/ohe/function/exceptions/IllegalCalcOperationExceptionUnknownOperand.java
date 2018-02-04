package at.fhv.ohe.function.exceptions;

/**
 * An Exception that should be thrown if a Function Calc Operation occurs
 * An Unknown character is inside the Function
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class IllegalCalcOperationExceptionUnknownOperand extends IllegalCalcOperationException {
    private IllegalCalcOperationExceptionUnknownOperand() {
    }

    public IllegalCalcOperationExceptionUnknownOperand(String message) {
        super(message);
    }
}
