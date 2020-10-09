package flyweight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterFlyweightFactoryTest {

    @Test
    void getFactoryInstance() {
        // test that there is only one instance of this class
        CharacterFlyweightFactory firstCharacterFlyweightFactory = CharacterFlyweightFactory.getFactoryInstance();
        CharacterFlyweightFactory secondCharacterFlyweightFactory = CharacterFlyweightFactory.getFactoryInstance();
        assertTrue(firstCharacterFlyweightFactory == secondCharacterFlyweightFactory);
    }

    @Test
    void getFlyweight() {
        // test that multiple flyweights are not created when key is the same
        CharacterFlyweightFactory characterFlyweightFactory = CharacterFlyweightFactory.getFactoryInstance();
        CharacterFlyweight firstFlyweight = characterFlyweightFactory.getFlyweight('a');
        CharacterFlyweight secondFlyweight = characterFlyweightFactory.getFlyweight('a');
        assertTrue(firstFlyweight == secondFlyweight);
        // test that multiple flyweights are different when key is different
        CharacterFlyweight thirdFlyweight = characterFlyweightFactory.getFlyweight('b');
        assertTrue(secondFlyweight != thirdFlyweight);
    }
}