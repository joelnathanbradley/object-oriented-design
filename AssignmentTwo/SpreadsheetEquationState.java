package client;

import java.util.*;

/**
 * A concrete state used in the state pattern that will display the equation data in the spreadsheet cells
 */
public class SpreadsheetEquationState implements SpreadsheetState {

    private SpreadsheetModel model;

    /**
     * The equation state will contain a pointer back to the spreadsheet model
     * @param model SpreadsheetModel
     */
    public SpreadsheetEquationState(SpreadsheetModel model) {
        this.model = model;
    }

    /**
     * This implements the SpreadsheetState interface.  It return the equation data of the cell to be displayed.
     * @param variable String variable of column needed to get equation data.
     * @return String the equation data.
     */
    @Override
    public String setValueAt(String variable) {
        HashMap<String,String> equations = this.model.getEquations();
        if(!equations.containsKey(variable)) {
            return null;
        }
        return equations.get(variable);
    }

    /**
     * This will be called by the spreadsheet model to determine the current state of the spreadsheet
     * @return String of the concrete state.
     */
    @Override
    public String toString() {
        return "EquationState";
    }
}
