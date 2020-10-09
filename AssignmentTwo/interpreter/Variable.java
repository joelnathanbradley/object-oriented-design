package client.interpreter;

/**
 * This implements the Expression interface and represents a concrete variable
 */
public class Variable implements Expression {

    private String variable;

    /**
     * This stores the variable for later interpretation
     * @param variable String variable that is a single letter representing the column header.  (A, B, C, etc.).
     */
    public Variable(String variable) {
        this.variable = variable;
    }

    /**
     * Implements the Expression interface.  This pulls the value data from the Context.
     * @param values Context that contains the current values.
     * @return
     */
    @Override
    public float interpret(Context values) {
        return values.getValue(this.variable);
    }
}
