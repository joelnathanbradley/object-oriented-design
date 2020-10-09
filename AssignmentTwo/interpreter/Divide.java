package client.interpreter;

/**
 * This implements the Expression interface by representing a concrete division operation.
 */
public class Divide implements Expression {

    private Expression numerator;
    private Expression denominator;

    /**
     * Stores the operands for later interpretation.
     * @param numerator Expression.
     * @param denominator Expression.
     */
    public Divide(Expression numerator, Expression denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Implements the expression interface by dividing the two operands.
     * @param values Context that contains the current values.
     * @return float the value data.
     */
    @Override
    public float interpret(Context values) {
        return this.numerator.interpret(values) / this.denominator.interpret(values);
    }
}
