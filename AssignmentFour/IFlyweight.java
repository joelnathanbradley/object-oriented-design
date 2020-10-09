package flyweight;

/**
 * Interface used as part of the Flyweight pattern for document
 */
public interface IFlyweight {

    /**
     * Extrinsic states provided to flyweight objects through runArray, and an index
     * @param runArray RunArray
     * @param runIndex int
     */
    void display(RunArray runArray, int runIndex);
}
