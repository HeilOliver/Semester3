package at.fhv.ohe.function;

import at.fhv.ohe.function.exceptions.IllegalCalcOperationException;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatException;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatExceptionBraceCount;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatExceptionIsEmpty;

import java.awt.*;
import java.util.LinkedList;


/**
 * Describes a Function
 * <p>
 * Created by Oliver Heil on 02.06.2017.
 */
public class Function {
    private static final char[] OPERATORS = {'+', '-', '*', '/', '(', ')', '^'};

    FunctionCalculate _calculator;
    private String _functionString;
    private Color _color;
    private boolean _visible;

    /**
     * Return a Function with {@code visible = true} and  {@code Color.Black}
     *
     * @param functionString - The Function String
     * @throws IllegalFunctionFormatException - Thrown if the Function Format is not correct
     */
    public Function(String functionString) throws IllegalFunctionFormatException {
        this(functionString, Color.BLACK, true);
    }

    /**
     * Return a Function
     *
     * @param functionString - The Function String
     * @param color          - The Color of this Function
     * @param visible        - Is the Function visible
     * @throws IllegalFunctionFormatException - Thrown if the Function Format is not correct
     */
    public Function(String functionString, Color color, boolean visible) throws IllegalFunctionFormatException {
        _functionString = (functionString.toLowerCase()).trim();
        _color = color;
        _visible = visible;

        try {
            if (_functionString.isEmpty()) {
                throw new IllegalFunctionFormatExceptionIsEmpty("The Function Format is incorrect @Function String Empty");
            }
            _calculator = new FunctionCalculate(
                    toFunctionComponents(_functionString));

            int countBarces = 0;
            for (FunctionComponent component : _calculator._functionComponents) {
                switch (component.getType()) {
                    case OPENBRACES:
                        countBarces++;
                        break;

                    case CLOSEBRACES:
                        countBarces--;
                        break;
                }
            }
            if (countBarces != 0) {
                throw new IllegalFunctionFormatExceptionBraceCount("The Function Format is incorrect @Braces are Wrong");
            }
        } catch (NumberFormatException e) {
            throw new IllegalFunctionFormatException("The Function Format is incorrect @" + e.getMessage());
        }
    }

    /**
     * Return the Color
     *
     * @return {@code Color}
     */
    public Color getColor() {
        return _color;
    }

    /**
     * Return the Function String
     *
     * @return {@code String} the String
     */
    public String getFunctionString() {
        return _functionString;
    }

    /**
     * Is the Function Visible
     *
     * @return {@code boolean} - Is Visible
     */
    public boolean isVisible() {
        return _visible;
    }

    /**
     * Returns the Function Value at an given Point
     *
     * @param x - The x Value
     * @return - The y Value
     * @throws IllegalCalcOperationException
     * @throws IllegalFunctionFormatException
     */
    public double getFunctionValueAt(double x) throws IllegalCalcOperationException, IllegalFunctionFormatException {
        return _calculator.getFunctionValue(x);
    }

    private FunctionComponent[] toFunctionComponents(String string) {
        int lastIndex = 0;
        LinkedList<FunctionComponent> componentList = new LinkedList<>();
        boolean isNegative = true;

        for (int i = 0; i < string.length(); i++) {
            boolean isExpression = false;

            for (char expression : OPERATORS) {
                if (string.charAt(i) == expression) {
                    isExpression = true;
                    break;
                }
            }

            if (isExpression) {
                if (lastIndex != i) {
                    componentList.add(new FunctionComponent(string.substring(lastIndex, i)));
                }
                if (!isNegative) {
                    FunctionComponent component = new FunctionComponent(Character.toString(string.charAt(i)));
                    componentList.add(component);
                    if (component.getType() == FunctionComponent.FunctionComponentType.SUBTRACTION) {
                        isNegative = true;
                    }
                    lastIndex = i + 1;
                } else {
                    isNegative = false;
                }
            } else {
                isNegative = false;
            }
        }
        if (lastIndex != string.length()) {
            componentList.add(new FunctionComponent(string.substring(lastIndex, string.length())));
        }

        return componentList.toArray(new FunctionComponent[componentList.size()]);
    }

}
