package at.fhv.ohe.textcoding;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TextCompress {

    public static void main(String[] args) {
        TextCompress compress = new TextCompress();
        compress.compress("Hallo");
    }

    public void compress(String s) {
        int table[] = new int[256]; // create table
        char[] chars = s.toCharArray();

        for (char c : chars) {
            table[c]++;
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(Comparator.comparingInt(o -> table[o]));

        for (char i = 0; i < table.length; i++) {
            if (table[i] > 0) {
                queue.add(i);
            }
        }

        CompressData data = new CompressData(queue);
    }

}
