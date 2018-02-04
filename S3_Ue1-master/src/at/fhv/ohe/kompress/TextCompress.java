package at.fhv.ohe.kompress;

public class TextCompress {

    /**
     * This method is for compress a given string
     *
     * @param s - string
     * @return - compressed string
     */
    public static String compress(String s) {
        if (s == null) return null;

        // aaabbbccc226
        // 43b3c30226
        StringBuilder sb = new StringBuilder();

        boolean isDigit = false;
        char c;
        int j;

        for (int i = 0; i < s.length(); ) {
            c = s.charAt(i);
            j = 1;

            if (!Character.isDigit(c)) {
                isDigit = false;
                while (s.length() > i + j && s.charAt(i + j) == c) {
                    j++;
                }
                if (j >= 3) {
                    sb.append(c);
                    sb.append(j);
                } else {
                    for (int k = 0; k < j; k++) {
                        sb.append(c);
                    }
                }
            } else {
                if (!isDigit) {
                    sb.append('0');
                }
                sb.append(c);
                isDigit = true;
            }
            i += j;
        }

        return sb.toString();
    }

    /**
     * This method is for decompress a given string to its original state
     *
     * @param s - compressed string
     * @return - decompressed string
     */
    public static String decompress(String s) {
        if (s == null) return null;

        StringBuilder sb = new StringBuilder();

        char c;
        char l = 0;
        int multiplikator = 0;


        boolean isDigit = false;
        for (int i = 0, j = 0; i < s.length(); i++) {
            c = s.charAt(i);

            if (c == '0') {// 01234 a3
                if (isDigit) {
                    for (int k = 0; k < multiplikator - 1; k++) {
                        sb.append(l);
                    }
                    isDigit = false;
                }
                j = i + 1;
                while (j < s.length()) {
                    c = s.charAt(j);
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    } else {
                        break;
                    }
                    j++;
                }
                i = j - 1;
            } else if (Character.isDigit(c)) {
                if (isDigit) {
                    multiplikator *= 10;
                    multiplikator += c - '0';
                } else {
                    isDigit = true;
                    multiplikator = c - '0';
                }
            } else {
                if (isDigit) {
                    for (int k = 0; k < multiplikator - 1; k++) {
                        sb.append(l);
                    }
                    isDigit = false;
                }
                l = c;
                sb.append(c);
            }
        }

        if (isDigit) {
            for (int k = 0; k < multiplikator - 1; k++) {
                sb.append(l);
            }
        }
        return sb.toString();
    }

}
