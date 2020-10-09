package binarysearchtree;

/**
 * This class represents a lexicographic orderer by implementing the IOrderer interface as part of the Strategy
 * pattern.
 */
public class LexicographicOrderer implements IOrderer {

    public LexicographicOrderer(){}

    /**
     * This implements the IOrderer interface as part of the Strategy pattern.  It will order the two input strings by
     * comparing them to each other lexicographically.
     * @param firstValue String first value to be compared
     * @param secondValue String second value to be compared
     * @return int less than zero if firstValue comes first lexicographically compared to secondValue, and more than
     *             zero if the firstValue comes second lexicographically compared to the secondValue. zero if both
     *             values are equal.
     */
    @Override
    public int order(String firstValue, String secondValue) {
        return firstValue.compareTo(secondValue);
    }

}
