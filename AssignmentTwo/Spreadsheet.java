package client;

import java.awt.*;
import javax.swing.*;

/**
 * This class is the main window of the spreadsheet GUI.  It creates a SpreadsheetModel and displays itself.
 */
public class Spreadsheet extends JFrame {
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 100;
    private SpreadsheetModel spreadsheetModel;

    public Spreadsheet() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.spreadsheetModel = new SpreadsheetModel(1,9);
        this.add(this.spreadsheetModel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Spreadsheet spreadsheet = new Spreadsheet();
            spreadsheet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            spreadsheet.setVisible(true);
        });
    }
}
