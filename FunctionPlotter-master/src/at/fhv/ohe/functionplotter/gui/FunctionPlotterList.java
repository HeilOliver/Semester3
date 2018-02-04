package at.fhv.ohe.functionplotter.gui;

import at.fhv.ohe.function.Function;

import java.awt.*;


/**
 * Describes the Complete List on the left hand side
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public class FunctionPlotterList extends List implements IFunctionChange {

    @Override
    public void functionChange(Function[] functions) {
        int index = getSelectedIndex();
        removeAll();

        for (Function function : functions) {
            add(function.getFunctionString());
        }
        select(index);
    }
}
