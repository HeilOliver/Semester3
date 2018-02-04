package at.fhv.ohe.functionplotter.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Describes the Complete Menu Bar in the Function Plotter
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
class FunctionPlotterMenuBar extends MenuBar {

    private FunctionPlotterController _controller;

    FunctionPlotterMenuBar(FunctionPlotterController controller) {
        _controller = controller;

        Menu fileMenu = new Menu("File");
        fileMenu.add("New");
        fileMenu.add("Open");
        fileMenu.add("Save");
        fileMenu.add("Close");
        fileMenu.addActionListener(e -> {
            switch (e.getActionCommand()) {
                case "New":
                    _controller.resetAll();
                    break;

                case "Open":
                    _controller.openFunctions();
                    break;

                case "Save":
                    _controller.saveFunctions();
                    break;

                case "Close":
                    System.exit(0);
                    break;
            }
        });
        add(fileMenu);

        Menu aboutMenu = new Menu("About");
        aboutMenu.add("Help");
        aboutMenu.add("About");
        aboutMenu.addActionListener(e -> {
            System.out.println(e.getActionCommand());
            switch (e.getActionCommand()) {
                case "Help":
                    showHelp();
                    break;

                case "About":
                    showAbout();
                    break;
            }
        });
        add(aboutMenu);
    }

    /**
     * Show the Help Box
     */
    private void showHelp() {
        JOptionPane.showMessageDialog(null,
                "Function Plotter Help\n" +
                        "You can add Functions by clicking the \"Add Function\" Button",
                "About",
                JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Shows the About Box
     */
    private void showAbout() {
        JOptionPane.showMessageDialog(null,
                "Function Plotter\nBuild:10002\nBy Oliver Heil (c)2017",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
