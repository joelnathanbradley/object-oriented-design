package flyweight;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunArrayTest {

    @Test
    void addRun() {
        RunArray runArray = new RunArray();
        runArray.addRun(0, 12, new FontFlyweight("Ariel", 12, "Bold"));
        for(int index=0; index<12; index++) {
            assertTrue(runArray.getFont(index).getFontName().equals("Ariel"));
            assertTrue(runArray.getFont(index).getPointSize() == 12);
            assertTrue(runArray.getFont(index).getFontStyle().equals("Bold"));
        }
        runArray.addRun(12, 42, new FontFlyweight("Times New Roman", 12, "Bold"));
        for(int index=0; index<42; index++) {
            assertTrue(runArray.getFont(index+12).getFontName().equals("Times New Roman"));
            assertTrue(runArray.getFont(index+12).getPointSize() == 12);
            assertTrue(runArray.getFont(index+12).getFontStyle().equals("Bold"));
        }
    }

    @Test
    void appendRun() {
        RunArray runArray = new RunArray();
        runArray.addRun(0, 12, new FontFlyweight("Ariel", 12, "Bold"));
        runArray.appendRun(42, new FontFlyweight("Times New Roman", 12, "Italics"));
        for(int index=0; index<42; index++) {
            assertTrue(runArray.getFont(index+12).getFontName().equals("Times New Roman"));
            assertTrue(runArray.getFont(index+12).getPointSize() == 12);
            assertTrue(runArray.getFont(index+12).getFontStyle().equals("Italics"));
        }
    }
}