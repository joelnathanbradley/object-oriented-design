package client;

import client.command.*;
import client.interpreter.Interpreter;
import client.interpreter.Context;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

/**
 * This class builds and contains the model for the spreadsheet.
 */
public class SpreadsheetModel extends JPanel {

    private int rows;
    private int columns;
    private JPanel spreadsheet;
    private JButton toggleButton;
    private JButton undoButton;
    private SpreadsheetCell[][] cells;
    private Context values;
    private HashMap<String, String> equations;
    private Interpreter interpreter;
    private SpreadsheetState state;
    private SpreadsheetEquationState equationState;
    private SpreadsheetValueState valueState;
    private Stack<Command> commands;

    /**
     * A SpreadsheetModel takes as input the number of rows and columns that the spreadsheet will contain.  It builds
     * the column headers, the individual cells of the spreadsheet, and adds a toggle button to switch between views.
     * It also adds an undo button.
     * @param rows int the number of rows in the spreadsheet.
     * @param columns int the number of columns in the spreadsheet.
     */
    public SpreadsheetModel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        // use a border layout as the underlying design, and add to its north edge a grid layout of all the cells
        this.setLayout(new BorderLayout());
        this.spreadsheet = new JPanel();
        this.spreadsheet.setLayout(new GridLayout(this.rows + 1, this.columns));
        // create the spreadsheet cells
        this.cells = new SpreadsheetCell[rows][columns];
        for(int row=0; row<=rows; row++) {
            for(int column=0; column<columns; column++) {
                // in first iteration, create the column headers, otherwise, create the actual spreadsheet cells
                if(row == 0) {
                    String columnHeader = "$" + this.indexToVariable(column);
                    JTextField columnHeaderCell = new JTextField(columnHeader);
                    columnHeaderCell.setEditable(false);
                    this.spreadsheet.add(columnHeaderCell);
                } else {
                    SpreadsheetCell spreadsheetCell = new SpreadsheetCell(row-1, column, this);
                    spreadsheetCell.setEditable(true);
                    // add action listener to each cell
                    spreadsheetCell.addActionListener(event -> spreadsheetCell.inputText(spreadsheetCell.getText()));
                    this.cells[row-1][column] = spreadsheetCell;
                    this.spreadsheet.add(spreadsheetCell);
                }
            }
        }
        this.add(this.spreadsheet, BorderLayout.NORTH);
        // now create the toggle view and the undo buttons, and add them to the south edge of the border layout
        JPanel southLayout = new JPanel();
        southLayout.setLayout(new GridLayout(1,2));
        toggleButton = new JButton("Toggle View");
        // add action listener to toggle button
        toggleButton.addActionListener(event -> toggleState());
        southLayout.add(toggleButton);
        undoButton = new JButton("Undo");
        // add action listener to undo button
        undoButton.addActionListener(event -> undo());
        southLayout.add(undoButton);
        this.add(southLayout, BorderLayout.SOUTH);
        // initialize fields that will contain the spreadsheet model data
        this.commands = new Stack<>();
        this.equationState = new SpreadsheetEquationState(this);
        this.valueState = new SpreadsheetValueState(this);
        this.state = this.equationState;
        this.equations = new HashMap<>();
        this.values = new Context();
        this.interpreter = new Interpreter();
    }

    /**
     * This will get called by an individual spreadsheet cell when new information is entered or it will be called
     * by an undo command.  It will update both the equation and the value data.
     * @param text String the input text put either into the spreadsheet GUI, or the previous text entered from an undo.
     * @param row int the row index of the spreadsheet cell.
     * @param column int the column index of the spreadsheet cell.
     * @param undo boolean set to false if called by the cell and set to true if called by an undo command.
     */
    public void inputText(String text, int row, int column, boolean undo) {
        String variable = this.indexToVariable(column);
        // create an undo command and add to undo stack if called by a spreadsheet cell
        if(!undo) {
            UndoCommand command = new UndoCommand(this.equations.get(variable), row, column, this);
            this.commands.add(command);
        }
        // update both the equation data, and the value data
        this.equations.put(variable, text);
        this.interpret(text, variable, row, column);
        // if called by an undo command, notify the observers of the cell being undone
        if(undo) {
            this.cells[row][column].notifyObservers();
        }
    }

    /**
     * This will get called when a spreadsheet cell is notified that its observee has changed.
     * @param row int the row index of the spreadsheet cell.
     * @param column int the column index of the spreadsheet cell.
     */
    public void updateText(int row, int column) {
        // update just the value data since the equation data has not changed
        String variable = this.indexToVariable(column);
        String text = this.equations.get(variable);
        this.interpret(text, variable, row, column);
    }

    /**
     * When new data is input into a spreadsheet cell, if it contains any variables, the spreadsheet cell will attach
     * itself as an observer to other spreadsheet cells that it depends on.
     * @param text String the new input data entered in the spreadsheet cell.
     * @param row int row index of the spreadsheet cell that is observing.
     * @param observerColumn int column index of the spreadsheet cell that is observing.
     * @return ArrayList of SpreadsheetCellSubjects (observees) that the observer has attached itself to.
     */
    public ArrayList<SpreadsheetCellSubject> attachObserver(String text, int row, int observerColumn) {
        ArrayList<SpreadsheetCellSubject> observees = new ArrayList<>();
        // split input string up, and identify any variables so that the observer cell can find who to attach itself to
        String[] textComponents = text.split(" ");
        for(String textComponent : textComponents) {
            if(this.interpreter.isVariable(textComponent)) {
                int observeeColumn = this.variableToIndex(textComponent);
                SpreadsheetCell observee = this.cells[row][observeeColumn];
                observee.attach(cells[row][observerColumn]);
                observees.add(observee);
            }
        }
        return observees;
    }

    /**
     * When new data is input into a spreadsheet cell, this will get called by a spreadsheet cell to detach itself
     * from any subjects (observees) it is observing.
     * @param observees ArrayList of SpreadsheetCellSubjects that the observer needs to detach itself from
     * @param row int row index of the spreadsheet cell that needs to detach
     * @param column int column index of the spreadsheet cell that needs to detach
     */
    public void detachObserver(ArrayList<SpreadsheetCellSubject> observees, int row, int column) {
        SpreadsheetCellObserver observer = this.cells[row][column];
        for(SpreadsheetCellSubject observee : observees) {
            observee.detach(observer);
        }
    }

    /**
     * This will return the Context, which contains the values, to the spreadsheet state
     * @return Context
     */
    public Context getValues() {
        return this.values;
    }

    /**
     * This will return the equations to the spreadsheet state
     * @return HashMap of String to String
     */
    public HashMap<String,String> getEquations() {
        return this.equations;
    }

    /**
     * This will get called by either inputText or updateText to calculate the new value data of a spreadsheet cell
     * and it will update the text in the spreadsheet GUI with the new calculated data.
     * @param text String equation data needed to calculate value data.
     * @param variable String variable needed to update text in spreadsheet cell.
     * @param row int row index of spreadsheet cell.
     * @param column int column index of spreadsheet cell.
     */
    private void interpret(String text, String variable, int row, int column) {
        float number = this.interpreter.interpret(text, this.values);
        this.values.setValue(variable, number);
        this.cells[row][column].setText(this.state.setValueAt(variable));
    }

    /**
     * This will get called by the action listener attached to the toggle button.  It will change the state of the
     * spreadsheet to either the equation state or value state, and it will then update the text in all the cells
     * of the GUI.
     */
    private void toggleState() {
        // if current state is equation state, change to value state, or vice versa
        if(this.state.toString().equals("EquationState")) {
            this.state = this.valueState;
        } else {
            this.state = this.equationState;
        }
        // update the text of all cells in the spreadsheet based on the new spreadsheet state
        for(int row=0; row<this.rows; row++) {
            for(int column=0; column<this.columns; column++) {
                String variable = indexToVariable(column);
                SpreadsheetCell cell = this.cells[row][column];
                // Keep text as "Error" if any circular dependencies found
                if(cell.getText().equals("Error")) {
                    cell.setText("Error");
                } else {
                    cell.setText(state.setValueAt(variable));
                }
            }
        }
    }

    /**
     * This will get called by the action listener attached to the undo button.  It pops an undo command off the stack
     * and executes it.
     */
    private void undo() {
        if(!commands.isEmpty()) {
            commands.pop().execute();
        }
    }

    /**
     * This will take a column index and convert it to its ASCII letter equivalent (0->A, 1->B, 2->C, etc.).
     * @param index
     * @return
     */
    private String indexToVariable(int index) {
        index += 65;
        return Character.toString((char)index);
    }

    /**
     * This will take an ASCII letter and convert it to its column index (A->0, B->1, C->2, etc.).
     * @param variable
     * @return
     */
    private int variableToIndex(String variable) {
        return (int)variable.charAt(1) - 65;
    }
}
