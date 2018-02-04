package at.fhv.ohe.functionplotter.gui;

import at.fhv.ohe.function.Function;

/**
 * An Interface for the FunctionChange Event that occurs when the Controller find changes in Functions
 * <p>
 * Created by OliverHeil on 07.06.2017.
 */
public interface IFunctionChange {

    /**
     * A Method for the FunctionChange Event that occurs when the Controller find changes in Functions
     *
     * @param functions - The updated Function List
     */
    void functionChange(Function[] functions);

}
