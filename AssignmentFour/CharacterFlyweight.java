package flyweight;

/**
 * This class represents a unicode character flyweight object, and implements the IFlyweight interface along with
 * the the ObjectSize interface.
 */
public class CharacterFlyweight implements IFlyweight, ObjectSize {

    private char character;

    /**
     * CharacterFlyweight stores the character it represents.
     * @param character char
     */
    public CharacterFlyweight(char character) {
        this.character = character;
    }

    /**
     * This implements the IFlyweight interface by displaying the character with the font provided from runArray
     * @param runArray RunArray extrinsic state of character flyweight pattern.
     * @param runIndex int extrinsic state of character flyweight pattern.
     */
    @Override
    public void display(RunArray runArray, int runIndex) {
        // code here to display character with the font provided from the runArray extrinsic state

        // In a desire to show my understanding of the Flyweight pattern, but without being told any specific
        // operations to implement in the project specification, I have just added a generic display method that
        // could potentially be an operation that this flyweight object would have.
    }

    /**
     * This implements the ObjectSize interface and returns the size of this object when called.
     * @return double size of space in memory of this object.
     */
    @Override
    public double getObjectSize() {
        return new SizeofUtil() {
            CharacterFlyweight object = null;
            @Override
            protected int create() {
                object = CharacterFlyweight.this;
                return 1;
            }
        }.averageBytes();
    }
}
