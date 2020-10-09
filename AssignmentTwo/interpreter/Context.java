package client.interpreter;

import java.util.*;

/**
 * This class keeps track of the value data and is used in the interpreter pattern.
 */
public class Context {
    private HashMap<String,Float> values;

    /**
     * The value data is stored in a HashMap of variables to their value data.
     */
    public Context() {
        this.values = new HashMap<>();
    }

    /**
     * Checks to see if variable currently has value data.
     * @param variable String variable that is a single letter representing the column header.  (A, B, C, etc.).
     * @return boolean
     */
    public boolean hasValue(String variable) {
        return this.values.containsKey(variable);
    }

    /**
     * This retrieves the value data
     * @param variable String variable that is a single letter representing the column header.  (A, B, C, etc.).
     * @return Float the value data.
     */
    public Float getValue(String variable) {
        if(!this.hasValue(variable)) {
            float zero = 0;
            return zero;
        }
        return this.values.get(variable);
    }

    /**
     * This sets the value data
     * @param variable String variable that is a single letter representing the column header.  (A, B, C, etc.).
     * @param value The value data to set.
     */
    public void setValue(String variable, Float value) {
        this.values.put(variable, value);
    }
}
