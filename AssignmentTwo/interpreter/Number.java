package client.interpreter;

/**
 * Implements the Expression interface and represents a concrete number which is the base in the interpreter pattern.
 */
public class Number implements Expression {

    private float number;

    /**
     * Stores the number for later interpretation.
     * @param number float.
     */
    public Number(float number) {
        this.number = number;
    }

    /**
     * This interprets the expression by simply returning the stored value data.
     * @param values Context that contains the current values.
     * @return float the value data.
     */
    @Override
    public float interpret(Context values) {
        return this.number;
    }
}
