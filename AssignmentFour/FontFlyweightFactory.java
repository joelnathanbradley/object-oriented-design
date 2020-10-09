package flyweight;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a factory for FontFlyweights as part of the Flyweight pattern.  This also implements the Singleton
 * pattern.
 */
public class FontFlyweightFactory implements ObjectSize {

    private HashMap<String, ArrayList<FontFlyweight>> fontFlyweights;
    private static FontFlyweightFactory instance;

    /**
     * This class will keep track in a hash map the different FontFlyweights.  The key will be the font name, and the
     * value will be a list of all FontFlyweights with that font name.  Note that a single list can have varying point
     * sizes and font styles.
     */
    private FontFlyweightFactory() {
        this.fontFlyweights = new HashMap<>();
    }

    /**
     * This implements the Singleton pattern by returning a reference to itself, and maintaining that there will only
     * be one object of this class.
     * @return FontFlyweightFactory
     */
    public static FontFlyweightFactory getFactoryInstance(){
        // if instance is not created yet, create it, then return reference to itself
        if(instance == null) {
            instance = new FontFlyweightFactory();
        }
        return instance;
    }

    /**
     * This method will retrieve the FontFlyweight based off the provided font name, font point size, and font style.
     * It will create and store the FontFlyweight if it has not yet been created.
     * @param fontName String
     * @param pointSize int
     * @param fontStyle String
     * @return FontFlyweight
     */
    public FontFlyweight getFlyweight(String fontName, int pointSize, String fontStyle) {
        // retrieve FontFlyweight if it exists, otherwise, create and store it.
        if(this.fontFlyweights.containsKey(fontName)) {
            // retrieve list of all FontFlyweights of the same font name
            ArrayList<FontFlyweight> fontFlyweights = this.fontFlyweights.get(fontName);
            // find FontFlyweight that also has same point size and font style
            for(FontFlyweight fontFlyweight : fontFlyweights) {
                if(fontFlyweight.getPointSize() == pointSize && fontFlyweight.getFontStyle().equals(fontStyle)) {
                    return fontFlyweight;
                }
            }
            // create the FontFlyweight since it doesn't exist, and add it to list of FontFlyweights that share the
            // same font name.  Then return the newly created FontFlyweight
            FontFlyweight newFontFlyweight = new FontFlyweight(fontName, pointSize, fontStyle);
            fontFlyweights.add(newFontFlyweight);
            return newFontFlyweight;
        } else {
            // this part is reached if there are no FontFlyweights with the provided font name.  Create the flyweight,
            // store it in a newly created list, which is then referenced to by the hash map fontFlyweights.
            FontFlyweight newFontFlyweight = new FontFlyweight(fontName, pointSize, fontStyle);
            ArrayList<FontFlyweight> fontFlyweights = new ArrayList<>();
            fontFlyweights.add(newFontFlyweight);
            this.fontFlyweights.put(fontName, fontFlyweights);
            return newFontFlyweight;
        }
    }

    /**
     * This implements the ObjectSize interface and returns the size of this object when called.
     * @return double size of space in memory of this object.
     */
    @Override
    public double getObjectSize() {
        return new SizeofUtil() {
            FontFlyweightFactory object = null;
            @Override
            protected int create() {
                object = FontFlyweightFactory.this;
                return 1;
            }
        }.averageBytes();
    }
}
