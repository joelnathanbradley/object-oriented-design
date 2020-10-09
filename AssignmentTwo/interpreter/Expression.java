package client.interpreter;

/**
 * The expression interface used in the interpreter pattern.
 */
public interface Expression {

    /**
     * This will interpret the data
     * @param values Context that contains the current values
     * @return float the value to be returned.
     */
    float interpret(Context values);

}
