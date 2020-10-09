package flyweight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FontFlyweightFactoryTest {

    @Test
    void getFactoryInstance() {
        // test that there is only one instance of this class
        FontFlyweightFactory firstFontFlyweightFactory = FontFlyweightFactory.getFactoryInstance();
        FontFlyweightFactory secondFontFlyweightFactory = FontFlyweightFactory.getFactoryInstance();
        assertTrue(firstFontFlyweightFactory == secondFontFlyweightFactory);
    }

    @Test
    void getFlyweight() {
        // test that multiple flyweights are not created when key is the same
        FontFlyweightFactory fontFlyweightFactory = FontFlyweightFactory.getFactoryInstance();
        FontFlyweight firstFlyweight = fontFlyweightFactory.getFlyweight("Times New Roman", 12, "Bold");
        FontFlyweight secondFlyweight = fontFlyweightFactory.getFlyweight("Times New Roman", 12, "Bold");
        assertTrue(firstFlyweight == secondFlyweight);
        // test that multiple flyweights are different when key is different
        FontFlyweight thirdFlyweight = fontFlyweightFactory.getFlyweight("Ariel", 12, "Bold");
        FontFlyweight fourthFlyweight = fontFlyweightFactory.getFlyweight("Ariel", 12, "Italics");
        assertTrue(secondFlyweight != thirdFlyweight);
        assertTrue(thirdFlyweight != fourthFlyweight);
    }
}