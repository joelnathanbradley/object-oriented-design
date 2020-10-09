package client.interpreter;

import java.lang.Math;

/**
 * This implements the Expression interface by representing a concrete lg (base 2) operation.
 */
public class Lg implements Expression {

    private Expression operand;

    /**
     * Stores the operand for later interpretation.
     * @param operand Expression.
     */
    public Lg(Expression operand) {
        this.operand = operand;
    }

    /**
     * This implements the expression interface by taking the lg of the operand.
     * @param values Context that contains the current values.
     * @return float the value data.
     */
    @Override
    public float interpret(Context values) {
        // divide by log of base 2 since log is base 10.
        return (float)(Math.log(this.operand.interpret(values)) / Math.log(2));
    }
}
