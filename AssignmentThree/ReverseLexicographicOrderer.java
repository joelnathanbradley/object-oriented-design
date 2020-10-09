package binarysearchtree;

/**
 * This class represents a reverse lexicographic orderer by implementing the IOrderer interface as part of the Strategy
 * pattern.
 */
public class ReverseLexicographicOrderer implements IOrderer {

    public ReverseLexicographicOrderer(){}

    /**
     * This implements the IOrderer interface as part of the Strategy pattern.  It will order the two input strings by
     * first reversing them, and then comparing them to each other in lexicographic order.
     * @param firstValue String first value to be compared
     * @param secondValue String second value to be compared
     * @return int less than zero if firstValue comes first lexicographically compared to secondValue, and more than
     *             zero if the firstValue comes second lexicographically compared to the secondValue, but both only
     *             after each value has been reversed.  zero if both values are equal.
     */
    @Override
    public int order(String firstValue, String secondValue) {
        // first, reverse both strings, then compare lexicographically
        StringBuilder firstStringBuilder = new StringBuilder(firstValue);
        StringBuilder secondStringBuilder = new StringBuilder(secondValue);
        firstValue = firstStringBuilder.reverse().toString();
        secondValue = secondStringBuilder.reverse().toString();
        return firstValue.compareTo(secondValue);
    }
}
