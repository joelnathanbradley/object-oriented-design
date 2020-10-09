package flyweight;

import java.util.ArrayList;

/**
 * This stores the extrinsic state of a document for CharacterFlyweights and FontFlyweights as part of the Flyweight
 * pattern.
 */
public class RunArray implements ObjectSize {

    private ArrayList<FontFlyweight> fonts;

    /**
     * This class keeps track in a list the FontFlyweights that are used at each character in the document
     */
    public RunArray() {
        this.fonts = new ArrayList<>();
    }

    /**
     * This method will add to the list the FontFlyweights, given a starting index in the document, and how long the
     * font applies for (the runLength).
     * @param startIndex int index in document of where the font flyweight applies.
     * @param runLength int specifies how long the run is for starting from startIndex
     * @param font FontFlyweight
     */
    public void addRun(int startIndex, int runLength, FontFlyweight font) {
        for(int index=0; index<runLength; index++) {
            this.fonts.add((startIndex + index), font);
        }
    }

    /**
     * This will also add to the list of FontFlyweights, but only being provided with how long the run is for.  This
     * will just append onto the end of the existing list of font flyweights.
     * @param runLength int specifies how long the run is for starting from end of existing FontFlyweight list.
     * @param font FontFlyweight
     */
    public void appendRun(int runLength, FontFlyweight font) {
        this.addRun(this.fonts.size(), runLength, font);
    }

    /**
     * Retrieves the FontFlyweight given an index location of the document.
     * @param index int location in document where font is requested
     * @return FontFlyweight
     */
    public FontFlyweight getFont(int index) {
        return this.fonts.get(index);
    }

    /**
     * This implements the ObjectSize interface and returns the size of this object when called.
     * @return double size of space in memory of this object.
     */
    @Override
    public double getObjectSize() {
        return new SizeofUtil() {
            RunArray object = null;
            @Override
            protected int create() {
                object = RunArray.this;
                return 1;
            }
        }.averageBytes();
    }
}
