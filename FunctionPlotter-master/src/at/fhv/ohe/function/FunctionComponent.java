package at.fhv.ohe.function;

/**
 * Describes a Component of an Function
 * <p>
 * Created by OliverHeil on 04.06.2017.
 */
public class FunctionComponent {
    private FunctionComponentType _type;
    private double _value;

    /**
     * Return a Function Component. It will parse the given content directly to {@code FunctionComponentType}
     *
     * @param content - The Content of the component
     */
    FunctionComponent(String content) {
        parseToFunctionComponent(content);
    }

    /**
     * If the Function component Type == {@code Number} it returns its value
     *
     * @return - The Value of this component
     */
    double getValue() {
        if (_type != FunctionComponentType.NUMBER) {
            throw new IllegalStateException("You cant get a Number out of -" + _type);
        }
        return _value;
    }

    /**
     * Returns the {@code FunctionComponentType} of this component
     *
     * @return {@code FunctionComponentType} of this component
     */
    FunctionComponentType getType() {
        return _type;
    }

    private void parseToFunctionComponent(String toParse) {
        switch (toParse) {
            case "(":
                _type = FunctionComponentType.OPENBRACES;
                break;

            case ")":
                _type = FunctionComponentType.CLOSEBRACES;
                break;

            case "+":
                _type = FunctionComponentType.ADDITION;
                break;

            case "-":
                _type = FunctionComponentType.SUBTRACTION;
                break;

            case "*":
                _type = FunctionComponentType.MULTIPLICATION;
                break;

            case "/":
                _type = FunctionComponentType.DIVISION;
                break;

            case "x":
                _type = FunctionComponentType.VARIABLE;
                break;

            case "-x":
                _type = FunctionComponentType.NEGVARIABLE;
                break;

            case "^":
                _type = FunctionComponentType.EXPONENT;
                break;

            default:
                _type = FunctionComponentType.NUMBER;
                _value = Double.valueOf(toParse);
        }
    }

    @Override
    public String toString() {
        if (_type == FunctionComponentType.NUMBER) {
            return _type.toString() + " - " + _value;
        }
        return _type.toString();
    }

    /**
     * The {@code FunctionComponentType} that a component can have
     */
    enum FunctionComponentType {
        NUMBER,
        VARIABLE,
        OPENBRACES,
        CLOSEBRACES,
        ADDITION,
        SUBTRACTION,
        DIVISION,
        MULTIPLICATION,
        NEGVARIABLE,
        EXPONENT
    }
}
