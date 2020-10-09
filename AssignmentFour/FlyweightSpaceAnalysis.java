package flyweight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class analyzes how much space in memory is saved from using the Flyweight pattern compared to if the document
 * was implemented without the Flyweight pattern.
 */
public class FlyweightSpaceAnalysis {

    private char[] nonFlyweightText;
    private ArrayList<CharacterFlyweight> flyweightText;
    private RunArray runArray;
    private CharacterFlyweightFactory characterFlyweightFactory;
    private FontFlyweightFactory fontFlyweightFactory;
    private double nonFlyweightSpace;
    private double flyweightSpace;


    /**
     * This initializes all variables needed for later analysis
     */
    public FlyweightSpaceAnalysis() {
        this.flyweightText = new ArrayList<>();
        this.runArray = new RunArray();
        this.characterFlyweightFactory = CharacterFlyweightFactory.getFactoryInstance();
        this.fontFlyweightFactory = FontFlyweightFactory.getFactoryInstance();
        this.flyweightSpace = 0;
        this.nonFlyweightSpace = 0;
    }

    /**
     * This method will run the analysis, assuming that the sample text has already been loaded by the setText() method.
     * @return String report of how much space is saved using Flyweights
     */
    public String analyzeSpace() {
        // check that text has been loaded
        String report = "";
        if(this.nonFlyweightText == null) {
            return null;
        }
        // create fonts used in sample text (in a better program, this would be determined when the document is parsed).
        FontFlyweight firstFontFlyweight = this.fontFlyweightFactory.getFlyweight("Times New Roman", 12, "Bold");
        FontFlyweight secondFontFlyweight = this.fontFlyweightFactory.getFlyweight("Times New Roman", 12, "Normal");
        // adds runs to runArray
        this.runArray.addRun(0, 143, firstFontFlyweight);
        this.runArray.appendRun(212, secondFontFlyweight);
        // create array of CharacterFlyweight objects for sample text from document.
        for(char character : this.nonFlyweightText) {
            CharacterFlyweight characterFlyweight = this.characterFlyweightFactory.getFlyweight(character);
            this.flyweightText.add(characterFlyweight);
        }
        // compute size of objects used in Flyweight pattern and sum them together.
        this.flyweightSpace += this.runArray.getObjectSize();
        this.flyweightSpace += this.characterFlyweightFactory.getObjectSize();
        this.flyweightSpace += this.fontFlyweightFactory.getObjectSize();
        // include size of array that holds pointers to CharacterFlyweight objects.
        this.flyweightSpace += new SizeofUtil() {
            Object object = null;
            @Override
            protected int create() {
                object = FlyweightSpaceAnalysis.this.getFlyweightText();
                return 1;
            }
        }.averageBytes();
        // compute size used of document if flyweight pattern is not used.
        this.nonFlyweightSpace += new SizeofUtil() {
            Object object = null;
            @Override
            protected int create() {
                object = FlyweightSpaceAnalysis.this.getNonFlyweightText();
                return 1;
            }
        }.averageBytes();
        // create report of results
        report += "Flyweight Space: " + this.flyweightSpace + " bytes\n";
        report += "NonFlyweight Space: " + this.nonFlyweightSpace + " bytes\n";
        // compute size of memory space saved
        double spaceSaved = this.nonFlyweightSpace - this.flyweightSpace;
        report += "Space Saved Using Flyweight: " + spaceSaved + " bytes\n";
        return report;
    }

    /**
     * This loads in as a character array the sample text from the document.
     * @param text String
     */
    public void setText(String text) {
        this.nonFlyweightText = text.toCharArray();
    }

    /**
     * This retrieves as a character array the sample text from the document.
     * @return
     */
    public char[] getNonFlyweightText() {
        return this.nonFlyweightText;
    }

    /**
     * This retrieves the list of CharacterFlyweights used to represent the sample text of the document.
     * @return
     */
    public ArrayList<CharacterFlyweight> getFlyweightText() {
        return this.flyweightText;
    }

    /**
     * This is the main method that will parse in the sample text, represent it using both the Flyweight pattern, and
     * then not using the Flyweight pattern, compares how much space each one uses, and compares them to see how much
     * space is saved.  This needs to use eden space, which is done by using the option -XX:-UseTLAB
     */
    public static void main() {
        // run this with java option -XX:-UseTLAB
        FlyweightSpaceAnalysis flyweightSpaceAnalysis = new FlyweightSpaceAnalysis();
        // load in sample text
        try {
            File file = new File("sampleText.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String sampleText = "";
            String sampleTextLine;
            while ((sampleTextLine = reader.readLine()) != null) {
                sampleText += sampleTextLine;
            }
            flyweightSpaceAnalysis.setText(sampleText);
            // determine the amount of flyweight pattern space saved
            String report = flyweightSpaceAnalysis.analyzeSpace();
            System.out.println(report);
        } catch(Exception exception) {
            System.out.println(exception.toString());
        }
    }
}
