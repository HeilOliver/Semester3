package at.fhv.ohe.functionplotter.gui;

import at.fhv.ohe.function.Function;
import at.fhv.ohe.functionplotter.exceptions.IllegalFunctionControllerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Describes the Complete Gui for the Function Plotter
 * <p>
 * Created by Oliver Heil on 03.06.2017.
 */
public class MainPage extends Frame {
    private static final int STARTWITH = 1000;
    private static final int STARTHEIGTH = 600;
    private FunctionPlotterController _controller;

    public MainPage() {
        setSize(STARTWITH, STARTHEIGTH);
        setTitle("FunctionPlotter");
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        // Controller
        _controller = new FunctionPlotterController(this);

        // Menü
        setMenuBar(new FunctionPlotterMenuBar(_controller));

        // Canvas
        DrawingCanvas canvas = new DrawingCanvas();
        _controller.addFunctionChangeEvent(canvas);
        add(canvas, BorderLayout.CENTER);

        // List
        FunctionPlotterList list_functions = new FunctionPlotterList();
        _controller.addFunctionChangeEvent(list_functions);
        Panel leftSidePanel = new Panel(new BorderLayout());
        leftSidePanel.add(list_functions, BorderLayout.CENTER);

        // Button
        Button but_add = new Button("Add Function");
        but_add.setPreferredSize(new Dimension(150, 50));
        but_add.addActionListener(e -> {
            FunctionPlotterEditScreen screen = new FunctionPlotterEditScreen(this);
            screen.setVisible(true);
            if (screen.getFunction() != null) {
                try {
                    _controller.addFunction(screen.getFunction());
                } catch (IllegalFunctionControllerException e1) {
                    JOptionPane.showMessageDialog(this, "There is already a Function called like that :(", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(but_add, BorderLayout.NORTH);

        Panel panel = new Panel(new GridLayout(1, 2));

        Button but_edit = new Button("Edit");
        but_edit.setPreferredSize(new Dimension(75, 50));
        but_edit.addActionListener(e -> {
            if (list_functions.getSelectedItem() != null) {
                Function function = _controller.getFunctionBy(list_functions.getSelectedItem());
                _controller.deleteFunction(function);
                FunctionPlotterEditScreen screen = new FunctionPlotterEditScreen(this, function);
                screen.setVisible(true);
                try {
                    if (screen.getFunction() != null) {
                        _controller.addFunction(screen.getFunction());
                    } else {
                        _controller.addFunction(function);
                    }
                } catch (IllegalFunctionControllerException e1) {
                    JOptionPane.showMessageDialog(this, "There is already a Function called like that :(", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(but_edit);

        Button but_del = new Button("Delete");
        but_del.setPreferredSize(new Dimension(75, 50));
        but_del.addActionListener(e -> {
            if (list_functions.getSelectedItem() != null) {
                Function function = _controller.getFunctionBy(list_functions.getSelectedItem());
                _controller.deleteFunction(function);
            }
        });
        panel.add(but_del);
        leftSidePanel.add(panel, BorderLayout.SOUTH);
        add(leftSidePanel, BorderLayout.WEST);


        setVisible(true);
    }
}
