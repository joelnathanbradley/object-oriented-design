package client;

/**
 * The SpreadsheetState interface used in the state pattern.
 */
public interface SpreadsheetState {

    /**
     * This will set the value of a spreadsheet cell
     * @param variable String column variable needed to get either the equation or value data.
     * @return String either the equation or value data to be displayed in the spreadsheet cell.
     */
    String setValueAt(String variable);

}
