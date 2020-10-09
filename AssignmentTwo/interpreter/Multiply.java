package client.interpreter;

/**
 * This implements the Expression interface and represents a concrete multiplication operation.
 */
public class Multiply implements Expression {

    private Expression firstOperand;
    private Expression secondOperand;

    /**
     * Stores the two operands for later interpretation.
     * @param firstOperand Expression.
     * @param secondOperand Expression.
     */
    public Multiply(Expression firstOperand, Expression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    /**
     * Interprets the expression by multiplying the operands.
     * @param values Context that contains the current values
     * @return float the value data.
     */
    @Override
    public float interpret(Context values) {
        return this.firstOperand.interpret(values) * this.secondOperand.interpret(values);
    }
}
