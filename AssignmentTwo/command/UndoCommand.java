package client.command;

import client.SpreadsheetModel;

/**
 * A concrete command class that undoes any changes made to a cell and its observers.
 */
public class UndoCommand implements Command {

    private String previousText;
    private int row;
    private int column;
    private SpreadsheetModel model;

    /**
     * An UndoCommand will contain the previous equation data of a cell before it was altered.
     * @param previousText String previous equation data.
     * @param row int row index of cell that was changed.
     * @param column int column index of cell that was changed.
     * @param model SpreadSheetModel pointer back to the spreadsheet model.
     */
    public UndoCommand(String previousText, int row, int column, SpreadsheetModel model) {
        if(previousText == null) {
            previousText = "";
        }
        this.previousText = previousText;
        this.row = row;
        this.column = column;
        this.model = model;
    }

    /**
     * This implements the command interface.  If executed, it will revert the spreadsheet cell and any observers
     * it has back to its previous state.
     */
    @Override
    public void execute() {
        this.model.inputText(this.previousText, this.row, this.column, true);
    }
}