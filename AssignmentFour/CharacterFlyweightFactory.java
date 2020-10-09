package flyweight;

import java.util.HashMap;

/**
 * This is a factory for CharacterFlyweights as part of the Flyweight pattern.  This also implements the Singleton
 * pattern.
 */
public class CharacterFlyweightFactory implements ObjectSize {

    private HashMap<Character, CharacterFlyweight> flyweights;
    private static CharacterFlyweightFactory instance;

    /**
     * This class will keep track in a hash map the different CharacterFlyweights.
     */
    private CharacterFlyweightFactory() {
        this.flyweights = new HashMap<>();
    }

    /**
     * This implements the Singleton pattern by returning a reference to itself, and maintaining that there will only
     * be one object of this class.
     * @return CharacterFlyweightFactory
     */
    public static CharacterFlyweightFactory getFactoryInstance(){
        // if instance is not created yet, create it, then return reference to itself
        if(instance == null) {
            instance = new CharacterFlyweightFactory();
        }
        return instance;
    }

    /**
     * This method will retrieve the CharacterFlyweight based off the provided character key, and will create and store
     * the CharacterFlyweight if it has not yet been created.
     * @param key char
     * @return CharacterFlyweight
     */
    public CharacterFlyweight getFlyweight(char key) {
        // return character flyweight if it exists
        if(this.flyweights.containsKey(key)) {
            return this.flyweights.get(key);
        } else {
            // otherwise, create, store, and then return character flyweight
            CharacterFlyweight flyweight = new CharacterFlyweight(key);
            this.flyweights.put(key, flyweight);
            return flyweight;
        }
    }

    /**
     * This implements the ObjectSize interface and returns the size of this object when called.
     * @return double size of space in memory of this object.
     */
    @Override
    public double getObjectSize() {
        return new SizeofUtil() {
            CharacterFlyweightFactory object = null;
            @Override
            protected int create() {
                object = CharacterFlyweightFactory.this;
                return 1;
            }
        }.averageBytes();
    }
}
