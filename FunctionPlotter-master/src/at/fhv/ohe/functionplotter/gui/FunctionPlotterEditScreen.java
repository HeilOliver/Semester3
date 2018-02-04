package at.fhv.ohe.functionplotter.gui;

import at.fhv.ohe.function.Function;
import at.fhv.ohe.function.exceptions.IllegalCalcOperationException;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;

/**
 * This Dialog is for Editing and Creation from Functions. It Allows you to change the Function, Color and Visibility.
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
class FunctionPlotterEditScreen extends Dialog {
    private Function _function;

    /**
     * Returns a beautiful New Function Dialog
     *
     * @param owner - The Owner of this Dialog
     */
    FunctionPlotterEditScreen(Frame owner) {
        this(owner, null);
    }

    /**
     * Returns a beautiful Edit Function Dialog
     *
     * @param owner    - The Owner of this Dialog
     * @param function - The function for Editing
     */
    FunctionPlotterEditScreen(Frame owner, Function function) {
        super(owner, "", true);
        _function = function;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setResizable(false);
        Point parloc = owner.getLocation();
        setLocation(parloc.x + 100, parloc.y + 100);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                _function = null;
                setVisible(false);
                dispose();
            }
        });
        if (_function != null) {
            setTitle("FunctionPlotter - Edit Function");
        } else {
            setTitle("FunctionPlotter - Create Function");
        }

        // Declerations
        Button cancelBut = new Button("Cancel");
        Button addBut = new Button("Add");

        Choice choice = new Choice();
        TextField textArea = new TextField();


        // InputFields
        Panel inputPanel = new Panel(new GridLayout(3, 1));

        textArea.setPreferredSize(new Dimension(250, 25));
        if (_function != null) {
            textArea.setText(_function.getFunctionString());
            textArea.setBackground(Color.GREEN);
        } else {
            textArea.setBackground(Color.RED);
        }
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    _function = new Function(textArea.getText());
                    _function.getFunctionValueAt(2);
                    addBut.setEnabled(true);
                    textArea.setBackground(Color.GREEN);
                } catch (IllegalFunctionFormatException | IllegalCalcOperationException e1) {
                    addBut.setEnabled(false);
                    textArea.setBackground(Color.RED);
                }
            }
        });


        choice.add("Black");
        choice.add("Blue");
        choice.add("Cyan");
        choice.add("Gray");
        choice.add("Magenta");
        choice.add("Orange");
        choice.add("Pink");
        choice.add("Red");
        choice.add("Green");
        choice.add("Yellow");

        Checkbox checkbox = new Checkbox("Is Visible");
        checkbox.setState(true);

        //Buttons
        Panel butPanel = new Panel(new GridLayout(1, 2));
        if (_function != null) {
            addBut.setLabel("Edit");
        }
        addBut.setEnabled(false);
        addBut.addActionListener(e -> {
            try {
                Color color = Color.BLACK;
                switch (choice.getSelectedItem()) {
                    case "Black":
                        color = Color.BLACK;
                        break;
                    case "Blue":
                        color = Color.BLUE;
                        break;
                    case "Cyan":
                        color = Color.CYAN;
                        break;
                    case "Gray":
                        color = Color.GRAY;
                        break;
                    case "Magenta":
                        color = Color.MAGENTA;
                        break;
                    case "Orange":
                        color = Color.ORANGE;
                        break;
                    case "Pink":
                        color = Color.PINK;
                        break;
                    case "Red":
                        color = Color.RED;
                        break;
                    case "Green":
                        color = Color.GREEN;
                        break;
                    case "Yellow":
                        color = Color.YELLOW;
                        break;
                }
                _function = new Function(textArea.getText(), color, checkbox.getState());
            } catch (IllegalFunctionFormatException e1) {
                _function = null;
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            setVisible(false);
            dispose();
        });


        cancelBut.addActionListener(e -> {
            _function = null;
            setVisible(false);
            dispose();
        });

        butPanel.add(addBut);
        butPanel.add(cancelBut);
        add(butPanel, SOUTH);

        inputPanel.add(textArea);
        inputPanel.add(choice);
        inputPanel.add(checkbox);
        add(inputPanel, CENTER);

        pack();
    }

    /**
     * The Return Vale after the Dialog
     *
     * @return {@code null} if the Dialog was Cancel, {@code Function} if the Dialog can create a Function
     */
    Function getFunction() {
        return _function;
    }
}
