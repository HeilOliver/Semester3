package at.fhv.ohe.charfinder;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CharFinderTest {
    @org.junit.jupiter.api.Test
    void findCharLeft() {
        String[] s  = {"abc" , "bli", "", "ansjansfjkbajkbsajfbakj",null, "abcd"};
        char[] c    = {'a'  , 'i', 'f' , 'j','c', 'e'};
        int[] ints  = {0,2,-1,3,-1,-1};

        for (int i = 0; i < ints.length; i++) {
            assertTrue(CharFinder.findCharLeft(s[i],c[i])== ints[i],"CharFinderLeft - " + i);
        }
    }

    @org.junit.jupiter.api.Test
    void findCharRight() {
        String[] s  = {"abc" , "bli", "", "ansjansfjkbajkbsajfbakj",null, "abcd"};
        char[] c    = {'a'  , 'i', 'f' , 'j','c', 'e'};
        int[] ints  = {0,2,-1,3,-1,-1};

        for (int i = 0; i < ints.length; i++) {
            assertTrue(CharFinder.findCharRight(s[i],c[i])== ints[i],"CharFinderRight - " + i);
        }
    }

    @org.junit.jupiter.api.Test
    void findChar() {
        String[] s  = {"abc" , "bli", "", "abasasakajkjlhjklghjfghfhg",null, "abcd"};
        char[] c    = {'a'  , 'i', 'f' , 'a','c', 'e'};
        int[][] ints  = {
                {0},
                {2},
                {-1},
                {0,2,4,6,8},
                {-1},
                {-1}
        };

        boolean testCondition = false;
        for (int i = 0; i < s.length; i++) {
            int charPos = CharFinder.findCharPos(s[i],c[i]);

            for (int j = 0; j < ints[i].length && !testCondition; j++) {
                if (ints[i][j] == charPos) {
                    testCondition = true;
                }
            }
            assertTrue(testCondition,"CharFinderRand - " + i);
        }
    }

}