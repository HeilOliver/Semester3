package at.fhv.ohe.function;

import at.fhv.ohe.function.exceptions.IllegalFunctionFormatException;

import java.awt.*;
import java.util.List;

/**
 * Describes a Task for Creating Functions
 * <p>
 * Created by OliverHeil on 06.06.2017.
 */
public class FuncionCreateTask implements Runnable {
    private List<Function> _functions;
    private String _functionString;
    private Color _color;
    private boolean _visible;

    /**
     * The Constructor sets all Values for the Function.
     * The Result will be added to the {@code List<Function>}
     *
     * @param functions      - {@code List<Function>} The List where the Function should be added
     * @param functionString - The Function String
     * @param visible        - Is the function afterwards visible
     */
    FuncionCreateTask(List<Function> functions, String functionString, Boolean visible) {
        _functions = functions;
        _functionString = functionString;
        _color = Color.BLUE;
        _visible = visible;
    }

    @Override
    public void run() {
        try {
            _functions.add(new Function(_functionString, _color, _visible));
        } catch (IllegalFunctionFormatException ignore) {
            // TODO Logfile oder PopUp?
        }
    }
}
