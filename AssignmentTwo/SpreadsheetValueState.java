package client;

import client.interpreter.Context;
/**
 * A concrete state used in the state pattern that will display the value data in the spreadsheet cells
 */
public class SpreadsheetValueState implements SpreadsheetState {

    private SpreadsheetModel model;

    /**
     * The equation state will contain a pointer back to the spreadsheet model
     * @param model SpreadsheetModel
     */
    public SpreadsheetValueState(SpreadsheetModel model) {
        this.model = model;
    }

    /**
     * This implements the SpreadsheetState interface.  It return the value data of the cell to be displayed.
     * @param variable String variable of column needed to get value data.
     * @return String the value data.
     */
    @Override
    public String setValueAt(String variable) {
        Context values = this.model.getValues();
        if(!values.hasValue(variable)) {
            return null;
        }
        return values.getValue(variable).toString();
    }

    /**
     * This will be called by the spreadsheet model to determine the current state of the spreadsheet
     * @return String of the concrete state.
     */
    @Override
    public String toString() {
        return "ValueState";
    }
}
