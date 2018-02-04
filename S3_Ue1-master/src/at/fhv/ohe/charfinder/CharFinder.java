package at.fhv.ohe.charfinder;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * A class for finding chars in string
 */
public class CharFinder {

    /**
     * Find the given character in the given String starting on the left side.
     * Are more than one search character in the string than the function will
     * return the leftmost search character.
     * O-Notation
     * BestCase     O(1)
     * AvrCase      O(n/2)
     * WorstCase    O(n)
     *
     * @param str - The string where the char should be
     * @param ch - The character to be searched for
     * @return -1 if the char is not found or the position of the leftmost search character.
     */
    public static int findCharLeft(String str, char ch) {
        if (str == null) {
            return -1;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find the given character in the given String starting on the right side.
     * Are more than one search character in the string than the function will
     * return the rightmost search character.
     * O-Notation
     * BestCase     O(1)
     * AvrCase      O(n/2)
     * WorstCase    O(n)
     *
     * @param str - The string where the char should be
     * @param ch - The character to be searched for
     * @return -1 if the char is not found or the position of the rightmost search character.
     */
    public static int findCharRight(String str, char ch) {
        if (str == null) {
            return -1;
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find the given character in the given String by Using an random generator.
     * Are more than one search character in the string than the function will randomly give you one position.
     * O-Notation
     * BestCase     O(1)
     * AvrCase      O(n/2)      -> normally distributed!
     * WorstCase    O(inf.)
     *
     * @param str - The string where the char should be
     * @param ch - The character to be searched for
     * @return -1 if the char is not found or the position of the char.
     */
    public static int findCharPos(String str, char ch) {
        if (str == null) return -1;

        Random rng = new Random();
        Set<Integer> generated = new LinkedHashSet<>();
        int pos;

        while (generated.size() < str.length()) {
            pos = rng.nextInt(str.length());
            if (generated.add(pos)) {
                if (str.charAt(pos) == ch) {
                    return pos;
                }
            }
        }
        return -1;
    }
}
