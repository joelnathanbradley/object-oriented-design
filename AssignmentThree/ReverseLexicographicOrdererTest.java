package binarysearchtree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseLexicographicOrdererTest {

    @Test
    void order() {
        ReverseLexicographicOrderer orderer = new ReverseLexicographicOrderer();
        assertTrue(orderer.order("az", "bb") > 0);
        assertTrue(orderer.order("bb", "az") < 0);
        assertTrue(orderer.order("ab", "ab") == 0);
    }
}