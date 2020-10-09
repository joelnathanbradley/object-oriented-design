package client;

/**
 * The SpreadsheetCellSubject interface used in the observer pattern.
 */
public interface SpreadsheetCellSubject {

    void attach(SpreadsheetCellObserver observer);

    void detach(SpreadsheetCellObserver observer);

    void notifyObservers();
}
