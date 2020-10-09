package binarysearchtree;

/**
 * This interface is used as part of the Strategy pattern to represent the algorithm that will be called to order the
 * two inputs.
 */
public interface IOrderer {

    /**
     * This method will return an int less than zero if the first value comes first in the ordering, and it will
     * return a value greater than zero if the second value comes first in the ordering.  It will return zero if both
     * values are equal.
     * @param firstValue String
     * @param secondValue String
     * @return int
     */
    int order(String firstValue, String secondValue);

}
