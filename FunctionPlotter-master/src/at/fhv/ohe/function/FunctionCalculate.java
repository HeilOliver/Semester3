package at.fhv.ohe.function;

import at.fhv.ohe.function.exceptions.IllegalCalcOperationException;
import at.fhv.ohe.function.exceptions.IllegalCalcOperationExceptionDividingZero;
import at.fhv.ohe.function.exceptions.IllegalCalcOperationExceptionUnknownOperand;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatException;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Describes the Calculator for one function.
 * <p>
 * Created by OliverHeil on 04.06.2017.
 */
class FunctionCalculate {

    FunctionComponent[] _functionComponents;

    /**
     * Returns a Function calculator. It need components for work
     *
     * @param functionComponents - The function components
     */
    FunctionCalculate(FunctionComponent[] functionComponents) {
        _functionComponents = functionComponents;
    }

    /**
     * Returns the Function Value at an given Point
     *
     * @param variable - The x Value
     * @return - The y Value
     * @throws IllegalCalcOperationException
     * @throws IllegalFunctionFormatException
     */
    double getFunctionValue(double variable) throws IllegalCalcOperationException, IllegalFunctionFormatException {
        return calculateFunction(variable,
                _functionComponents,
                new Stack<>(),
                new NumberINT());
    }

    private double calculateFunction(double variable,
                                     FunctionComponent[] list,
                                     Stack<Double> stack,
                                     NumberINT pos)
            throws IllegalCalcOperationException, IllegalFunctionFormatException {

        FunctionComponent _operator = null;

        while (pos._num < list.length) {
            switch (list[pos._num].getType()) {

                case NUMBER:
                    stack.push(list[pos._num].getValue());
                    operation(_operator, stack);
                    _operator = null;
                    break;

                case NEGVARIABLE:
                    stack.push(variable * (-1));
                    operation(_operator, stack);
                    _operator = null;
                    break;

                case VARIABLE:
                    stack.push(variable);
                    operation(_operator, stack);
                    _operator = null;
                    break;

                case OPENBRACES:
                    ++pos._num;
                    stack.push(calculateFunction(variable, list, stack, pos));
                    operation(_operator, stack);
                    _operator = null;
                    break;

                case CLOSEBRACES:
                    return stack.pop();

                default:
                    if (_operator != null) {
                        throw new IllegalFunctionFormatException("The Function Format is incorrect @Multiple Use of Operands");
                    }
                    _operator = list[pos._num];

            }
            pos._num++;
        }
        try {
            return stack.pop();
        } catch (EmptyStackException e) {
            throw new IllegalFunctionFormatException("The Function Format is incorrect @Function Format not correct");
        }
    }

    private void operation(FunctionComponent component, Stack<Double> stack) throws IllegalCalcOperationException, IllegalFunctionFormatException {
        if (component == null) {
            return;
        }
        double secondNum;
        double firstNum;
        try {
            secondNum = stack.pop();
            firstNum = stack.pop();
        } catch (EmptyStackException e) {
            throw new IllegalFunctionFormatException("The Function Format is incorrect @Function Format not correct");
        }
        double result;

        switch (component.getType()) {
            case ADDITION:
                result = firstNum + secondNum;
                break;

            case SUBTRACTION:
                result = firstNum - secondNum;
                break;

            case MULTIPLICATION:
                result = firstNum * secondNum;
                break;

            case DIVISION:
                if (secondNum == 0) {
                    throw new IllegalCalcOperationExceptionDividingZero("Dividing throw 0");
                }
                result = firstNum / secondNum;
                break;

            case EXPONENT:
                result = Math.pow(firstNum, secondNum);
                break;

            default:
                throw new IllegalCalcOperationExceptionUnknownOperand("Unknown Operand");
        }
        stack.push(result);
    }

    private class NumberINT {
        private int _num = 0;
    }
}
