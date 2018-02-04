package at.fhv.ohe.functionplotter.gui;

import at.fhv.ohe.function.Function;
import at.fhv.ohe.function.exceptions.IllegalCalcOperationException;
import at.fhv.ohe.function.exceptions.IllegalFunctionFormatException;

import java.awt.*;

/**
 * A drawing canvas for drawing functions
 * <p>
 * Created by Oliver Heil on 03.06.2017.
 */
public class DrawingCanvas extends Canvas implements IFunctionChange {
    private Function[] _functions;

    DrawingCanvas() {
        _functions = new Function[0];
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int xscale = getWidth() / 2;
        int yscale = getHeight() / 2;
        double scala = 50;


        g.setColor(Color.BLACK);
        g.drawLine(0, yscale, getWidth(), yscale);
        g.drawLine(xscale, 0, xscale, getHeight());

        for (int i = 0; i < xscale; i += scala) {
            g.drawLine(xscale - i, yscale, xscale - i, yscale + 10);
            g.drawLine(i + xscale, yscale, i + xscale, yscale + 10);

            g.drawLine(xscale, yscale - i, xscale - 10, yscale - i);
            g.drawLine(xscale, yscale + i, xscale - 10, yscale + i);

        }

        for (Function function : _functions) {
            if (!function.isVisible()) {
                break;
            }
            g.setColor(function.getColor());

            int[] yList = new int[getWidth()];


            for (int i = 0; i < getWidth(); i += 1) {
                try {
                    yList[i] = (int) (((function.getFunctionValueAt((i - xscale) / scala) * scala) * -1) + yscale);
                } catch (IllegalCalcOperationException | IllegalFunctionFormatException e) {
                    e.printStackTrace(); // TODO was kann man hier tun?
                }
            }

            for (int i = 1; i < getWidth(); i += 1) {
                g.drawLine(i - 1, yList[i - 1], i, yList[i]);
            }
        }
    }


    @Override
    public void functionChange(Function[] functions) {
        _functions = functions;
        repaint();
    }
}
