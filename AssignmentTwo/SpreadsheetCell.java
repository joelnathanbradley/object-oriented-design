package client;

import javax.swing.*;
import java.util.*;

/**
 * An individual spreadsheet cell that is both an observer, and subject in the observer pattern.
 * (This doesn't quite follow the observer pattern exactly since the subject doesn't return its state.  Each observer
 * knows who its observees (subjects) are so that it can easily detach itself when needed, instead of calling every
 * single spreadsheet cell).
 */
public class SpreadsheetCell extends JTextField implements SpreadsheetCellObserver, SpreadsheetCellSubject {

    private int row;
    private int column;
    private boolean updated;
    private SpreadsheetModel model;
    private ArrayList<SpreadsheetCellObserver> observers;
    private ArrayList<SpreadsheetCellSubject> observees;

    /**
     * A spreadsheet cell will know its row and column index within the spreadsheet, and will have a pointer back
     * to the spreadsheet model.
     * @param row int row index of the cell.
     * @param column int column index of the cell.
     * @param model SpreadsheetModel that the cell belongs to.
     */
    public SpreadsheetCell(int row, int column, SpreadsheetModel model) {
        this.row = row;
        this.column = column;
        this.model = model;
        this.updated = false;
        this.observers = new ArrayList<>();
        this.observees = new ArrayList<>();
    }

    /**
     * This will get called by the action listener attached to each individual spreadsheet cell when new data is
     * input.  It will detach itself from its observees, attach itself to any new cells that it needs to observe, and
     * then call the spreadsheet model so that its equation and value data can be updated.  Finally, it will notify
     * any observers it has.
     * @param text String new text data input into the cell
     */
    public void inputText(String text) {
        this.model.detachObserver(this.observees, this.row, this.column);
        this.observees = this.model.attachObserver(text, this.row, this.column);
        this.model.inputText(text, this.row, this.column, false);
        this.notifyObservers();
    }

    /**
     * This implements the SpreadsheetCellObserver interface.  It updates its value data and then notifies any
     * observers it has.  The private boolean field updated will be used to detect any circular dependencies.
     */
    @Override
    public void update() {
        // if no circular dependencies, update value data, otherwise, set text to "Error"
        if(!this.updated) {
            this.model.updateText(this.row, this.column);
            this.updated = true;
            this.notifyObservers();
        } else {
            super.setText("Error");
        }
        this.updated = false;
    }

    /**
     * This implements the SpreadsheetCellSubject interface.  It will keep a list of its observers.
     * @param observer SpreadsheetCellObserver that needs to be notified if the cell changes.
     */
    @Override
    public void attach(SpreadsheetCellObserver observer) {
        this.observers.add(observer);
    }

    /**
     * This implements the SpreadsheetCellSubject interface.  It will remove an observer from its list of observers.
     * @param observer SpreadsheetCellObserver that needs to be detached since it is no longer observing.
     */
    @Override
    public void detach(SpreadsheetCellObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * This implements the SpreadsheetCellSubject interface.  It will notify any observers it has if its data is
     * changed.
     */
    @Override
    public void notifyObservers() {
        for(SpreadsheetCellObserver observer : this.observers) {
            observer.update();
        }
    }
}
