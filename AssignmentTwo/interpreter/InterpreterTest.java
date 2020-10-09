package client.interpreter;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.Math;

class InterpreterTest {

    @org.junit.jupiter.api.Test
    void interpret() {
        Interpreter interpreter = new Interpreter();
        Context values = new Context();
        // test number interpretation
        String equation = "5";
        assertTrue(interpreter.interpret(equation, values) == 5);
        // test addition interpretation
        equation = "5 10 +";
        assertTrue(interpreter.interpret(equation, values) == 15);
        // test subtraction interpretation
        equation = "5 10 -";
        assertTrue(interpreter.interpret(equation, values) == -5);
        // test multiplication interpretation
        equation = "5 10 *";
        assertTrue(interpreter.interpret(equation, values) == 50);
        // test division interpretation
        equation = "5 10 /";
        assertTrue(interpreter.interpret(equation, values) == 0.5);
        // test sin interpretation
        equation = "0 sin";
        assertTrue(interpreter.interpret(equation, values) == 0);
        // test lg interpretation
        equation = "8 lg";
        assertTrue(interpreter.interpret(equation, values) == 3);
        // test series of operations
        equation = "5 10 + 15 - sin 8 + lg 4 * 6 /";
        assertTrue(interpreter.interpret(equation, values) == 2);
        // test variables combined with operations
        values.setValue("A", Float.parseFloat("10"));
        values.setValue("B", Float.parseFloat("5"));
        equation = "$A $B *";
        assertTrue(interpreter.interpret(equation, values) == 50);
        // test variable interpretation
        equation = "$B";
        assertTrue(interpreter.interpret(equation, values) == 5);
    }

}