package binarysearchtree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexicographicOrdererTest {

    @Test
    void order() {
        LexicographicOrderer orderer = new LexicographicOrderer();
        assertTrue(orderer.order("az", "bb") < 0);
        assertTrue(orderer.order("bb", "az") > 0);
        assertTrue(orderer.order("ab", "ab") == 0);
    }
}