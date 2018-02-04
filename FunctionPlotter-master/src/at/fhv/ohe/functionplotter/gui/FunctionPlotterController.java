package at.fhv.ohe.functionplotter.gui;

import at.fhv.ohe.function.Function;
import at.fhv.ohe.function.JSONController;
import at.fhv.ohe.function.exceptions.IllegalFunctionDataExceptionVersion;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatException;
import at.fhv.ohe.functionplotter.exceptions.IllegalFunctionControllerException;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Describes a Controller. The Controller handel's all Function Storage Operation
 * <p>
 * Created by Oliver Heil on 02.06.2017.
 */
public class FunctionPlotterController {
    private List<Function> _functions;
    private Component _frame;
    private LinkedList<IFunctionChange> _functionChange;

    /**
     * Returns a Controller for the FunctionPlotter AWT Gui
     *
     * @param frame - The Frame for this controller
     */
    FunctionPlotterController(Component frame) {
        _frame = frame;
        _functionChange = new LinkedList<>();
        _functions = new LinkedList<>();

        throwFunctionChangeEvent();
    }

    /**
     * Add a new Function to the Controller. Duplicated Functions are not allowed,
     *
     * @param addFunction - The Function that should be added
     * @throws IllegalFunctionControllerException - Thrown if a Function with the same Function Body is found
     */
    void addFunction(Function addFunction) throws IllegalFunctionControllerException {
        boolean isFound = false;

        for (Function function : _functions) {
            if (function.getFunctionString().equals(addFunction.getFunctionString())) {
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            _functions.add(addFunction);
            throwFunctionChangeEvent();
        } else {
            throw new IllegalFunctionControllerException();
        }
    }

    /**
     * Return the searched Function
     *
     * @param s - The Function by {@code String}
     * @return The Function
     */
    Function getFunctionBy(String s) {
        for (Function function : _functions) {
            if (function.getFunctionString().equals(s)) {
                return function;
            }
        }
        return null;
    }

    /**
     * Delete the given Function
     *
     * @param function - The Function
     */
    void deleteFunction(Function function) {
        _functions.remove(function);
        throwFunctionChangeEvent();
    }

    /**
     * Return a List with all Functions
     *
     * @return - List with Functions
     */
    public List<Function> getFunctions() {
        return Collections.unmodifiableList(_functions);
    }

    /**
     * Clear all Functions.
     */
    void resetAll() {
        _functions.clear();
        throwFunctionChangeEvent();
    }

    /**
     * Load all Functions out of an File
     */
    void openFunctions() {
        FileDialog fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
        fd.setDirectory(String.valueOf(new File(System.getProperty("user.dir"))));
        fd.setFilenameFilter((dir, name) -> name.endsWith(".fdat"));
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename != null) {
            try {
                Collections.addAll(_functions, JSONController.loadFunctions(new File(filename)));
            } catch (ParseException | IllegalFunctionFormatException | IllegalFunctionDataExceptionVersion | IOException e) {
                JOptionPane.showMessageDialog(_frame, "Can´t read the File", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        throwFunctionChangeEvent();
    }

    /**
     * Save all Functions in a File
     */
    void saveFunctions() {
        FileDialog fd = new FileDialog(new JFrame(), "Save file to", FileDialog.SAVE);
        fd.setDirectory(String.valueOf(new File(System.getProperty("user.dir"))));
        fd.setFilenameFilter((dir, name) -> name.endsWith(".fdat"));
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename != null) {
            if (!filename.endsWith(".fdat")) {
                filename = filename.concat(".fdat");
            }
            try {
                JSONController.saveFunctions(_functions.toArray(new Function[_functions.size()]), new File(filename));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(_frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        throwFunctionChangeEvent();
    }

    /**
     * Add a client that shout be notify
     *
     * @param functionChange - The Object that should be notify
     */
    void addFunctionChangeEvent(IFunctionChange functionChange) {
        _functionChange.add(functionChange);
        throwFunctionChangeEvent();
    }

    private void throwFunctionChangeEvent() {
        for (IFunctionChange functionChange : _functionChange) {
            functionChange.functionChange(_functions.toArray(new Function[_functions.size()]));
        }
        _frame.repaint();
    }
}
