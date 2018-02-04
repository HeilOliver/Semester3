package at.fhv.ohe.kompress;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TestClass for TextCompression Class
 * <p>
 * Created by OliverHeil on 10.10.2017.
 */
class TextCompressTest {
    @Test
    void compress() {
        String[] inputParameter0 = {"abcdefg", "aaabbccc", "", "aabbcc", "abc125", "a1bb2bbb3", "1234"};
        String[][] expectetResults = {
                {"abcdefg"},
                {"a3bbc3"},
                {""},
                {"aabbcc"},
                {"abc0125"},
                {"a01bb02b303"},
                {"01234"}
        };

        for (int i = 0; i < inputParameter0.length; i++) {
            assertTrue(TextCompress.compress(inputParameter0[i]).equals(expectetResults[i][0]), "TextCompress - " + i);
        }

        assertTrue(TextCompress.compress(null) == null, "TextCompress - " + inputParameter0.length);
    }

    @Test
    void decompress() {
        String[] inputParameter0 = {"abcdefg", "a3bbc3", "", "aabbcc", "abc0125", "a01bb02b303", "01234"};
        String[][] expectetResults = {
                {"abcdefg"},
                {"aaabbccc"},
                {""},
                {"aabbcc"},
                {"abc125"},
                {"a1bb2bbb3"},
                {"1234"}
        };

        for (int i = 0; i < inputParameter0.length; i++) {
            assertTrue(TextCompress.decompress(inputParameter0[i]).equals(expectetResults[i][0]), "TextDecompress - " + i);
        }

        assertTrue(TextCompress.decompress(null) == null, "TextDecompress - " + inputParameter0.length);
    }

}