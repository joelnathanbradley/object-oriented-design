package client.interpreter;

/**
 * This implements the Expression interface by representing a concrete addition operation.
 */
public class Add implements Expression {

    private Expression firstOperand;
    private Expression secondOperand;

    /**
     * Stores the operands for later interpretation.
     * @param firstOperand Expression.
     * @param secondOperand Expression.
     */
    public Add(Expression firstOperand, Expression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    /**
     * This interprets by adding the two operands.
     * @param values Context that contains the current values.
     * @return float the value data.
     */
    @Override
    public float interpret(Context values) {
        return this.firstOperand.interpret(values) + this.secondOperand.interpret(values);
    }
}
