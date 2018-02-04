package at.fhv.uebung3;

import at.fhv.ohe.hashdirectory.DoubleHashingHashTable;

import java.util.Map;

public class RunHashTable {

    public static void main(String[] args) {
        Map<String,String> table = new DoubleHashingHashTable<>();

        table.put("test0","a");
        table.put("test1","b");
        table.put("test2","c");
        table.put("test3","d");

        System.out.println(table.size());
        System.out.println(table.containsKey("test2"));
        System.out.println(table.containsKey("test4"));
    }
}
