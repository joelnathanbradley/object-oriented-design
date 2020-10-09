package flyweight;

/**
 * This class represents a font as a flyweight object, and implements the IFlyweight interface along with
 * the the ObjectSize interface.
 */
public class FontFlyweight implements IFlyweight, ObjectSize {

    private String fontName;
    private int pointSize;
    private String fontStyle;

    /**
     * This font flyweight object keeps track of the font name, the font point size, and the font style.
     * @param fontName String
     * @param pointSize int
     * @param fontStyle String
     */
    public FontFlyweight(String fontName, int pointSize, String fontStyle) {
        this.fontName = fontName;
        this.pointSize = pointSize;
        this.fontStyle = fontStyle;
    }

    /**
     * This implements the IFlyweight interface
     * @param runArray RunArray
     * @param runIndex int
     */
    @Override
    public void display(RunArray runArray, int runIndex) {

    }

    /**
     * Returns the font name
     * @return String
     */
    public String getFontName() {
        return this.fontName;
    }

    /**
     * returns the font point size
     * @return int
     */
    public int getPointSize() {
        return this.pointSize;
    }

    /**
     * returns the font style
     * @return
     */
    public String getFontStyle() {
        return this.fontStyle;
    }

    /**
     * This implements the ObjectSize interface and returns the size of this object when called.
     * @return double size of space in memory of this object.
     */
    @Override
    public double getObjectSize() {
        return new SizeofUtil() {
            FontFlyweight object = null;
            @Override
            protected int create() {
                object = FontFlyweight.this;
                return 1;
            }
        }.averageBytes();
    }
}
