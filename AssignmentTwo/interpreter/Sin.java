package client.interpreter;

import java.lang.Math;

/**
 * This implements the Expression interface and represents a concrete Sin() operation.
 */
public class Sin implements Expression {

    private Expression operand;

    /**
     * Stores the operand for later interpretation.
     * @param operand Expression.
     */
    public Sin(Expression operand) {
        this.operand = operand;
    }

    /**
     * Implements the Expression interface by taking the Sin of the operand.
     * @param values Context that contains the current values.
     * @return float the value data.
     */
    @Override
    public float interpret(Context values) {
        double number = (double)this.operand.interpret(values);
        return (float)Math.sin(number);
    }
}
