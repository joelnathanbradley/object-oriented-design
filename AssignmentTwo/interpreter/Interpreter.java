package client.interpreter;

import java.util.*;

/**
 * This class reads the equation data entered into a spreadsheet cell, builds an expression from it, and then
 * interprets the data in order to return the value data.
 */
public class Interpreter {

    private Stack<Expression> expressions;

    /**
     * Initializes stack for later equation data parsing.
     */
    public Interpreter() {
        this.expressions = new Stack<>();
    }

    /**
     * This takes the equation data and parses it in order to calculate its equivalent value data.  This enforces
     * postfix notation, where the operands come first, followed by the operator.
     * @param text String the equation data.
     * @param values Context the current value data.
     * @return float the value data calculated from the equation data.
     */
    public float interpret(String text, Context values) {
        // split equation data up into its separate components
        String[] textComponents = text.split(" ");
        // for each text component, create an expression based on whether it is an operator with a single operand,
        // or an operator with two operands, or a variable, or a number.  After creating the expression, push it
        // onto the expression stack.
        for (String textComponent : textComponents) {
            // if the equation data is empty, treat it has a number with value of zero
            if(textComponent.equals("")) {
                textComponent = "0";
            }
            Expression expression;
            // check if the current text component is lg or sin, which only have one operand
            if(this.hasSingleOperand(textComponent)) {
                // pop operand off expression stack, create a new expression, interpret it (perform operation) and
                // push back onto stack
                Expression operand = this.expressions.pop();
                expression = this.createExpressionWithSingleOperand(textComponent, operand);
                float number = expression.interpret(values);
                this.expressions.push(new Number(number));
                // check if the current text component is an operator wth two operands (+, -, *, or /).
            } else if(this.hasDoubleOperands(textComponent)) {
                // pop both operands off the expression stack, create a new expression, interpret it, and push back
                // onto stack
                Expression secondOperand = this.expressions.pop();
                Expression firstOperand = this.expressions.pop();
                expression = this.createExpressionWithDoubleOperands(textComponent, firstOperand, secondOperand);
                float number = expression.interpret(values);
                this.expressions.push(new Number(number));
                // check if the current text component is a variable.
            } else if(this.isVariable(textComponent)) {
                // create variable expression and push onto stack
                expression = new Variable(textComponent.substring(1));
                this.expressions.push(expression);
                // text component must be a number if not an operator or a variable
            } else {
                // create number expression and push onto stack
                expression = new Number(Float.parseFloat(textComponent));
                this.expressions.push(expression);
            }
        }
        // pop final number expression, interpret, and return
        return this.expressions.pop().interpret(values);
    }

    /**
     * Checks if the text is a variable.  A variable is two characters long, starts with '$', and is a capital letter.
     * @param text String the variable.
     * @return boolean.
     */
    public boolean isVariable(String text) {
        if(text.length() != 2) {
            return false;
        }
        if(text.charAt(0) != '$') {
            return false;
        }
        char variable = text.charAt(1);
        if(!(variable >= 'A' && variable <= 'Z')) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the text is an operator that only has a single operand.
     * @param operator String.
     * @return boolean.
     */
    private boolean hasSingleOperand(String operator) {
        switch (operator) {
            case "lg":
                return true;
            case "sin":
                return true;
        }
        return false;
    }

    /**
     * Checks if the text is an operator that requires two operands.
     * @param operator String.
     * @return boolean.
     */
    private boolean hasDoubleOperands(String operator) {
        switch (operator) {
            case "+":
                return true;
            case "-":
                return true;
            case "*":
                return true;
            case "/":
                return true;
        }
        return false;
    }

    /**
     * Creates an Expression for operators that only have a single operand.
     * @param operator String.
     * @param operand Expression.
     * @return Expression.
     */
    private Expression createExpressionWithSingleOperand(String operator, Expression operand) {
        switch (operator) {
            case "lg":
                return new Lg(operand);
            case "sin":
                return new Sin(operand);
        }
        return null;
    }

    /**
     * Creates an Expression for operators that have two operands.
     * @param operator String.
     * @param firstOperand Expression.
     * @param secondOperand Expression.
     * @return Expression.
     */
    private Expression createExpressionWithDoubleOperands(String operator, Expression firstOperand, Expression secondOperand) {
        switch (operator) {
            case "+":
                return new Add(firstOperand, secondOperand);
            case "-":
                return new Subtract(firstOperand, secondOperand);
            case "*":
                return new Multiply(firstOperand, secondOperand);
            case "/":
                return new Divide(firstOperand, secondOperand);
        }
        return null;
    }
}
