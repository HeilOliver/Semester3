package at.fhv.ohe.arraytotree;

import at.fhv.ohe.binarytree.BinaryTree;

import java.io.*;

public class TreeFactory {


    public static BinaryTree<Integer> getTree(String fileLoacation) {
        char[] chars = new char[50];
        int count = 0;

        try(InputStreamReader reader = new InputStreamReader(TreeFactory.class.getResourceAsStream(fileLoacation))) {
            count = reader.read(chars);
        } catch (IOException ignore) {
            return null;
            // Logger or something
        }

        BinaryTree<Integer> tree = new BinaryTree<>();

        int nextValue = 0;
        for (int i = 0; i < count; i++) {
            if (chars[i] == ',') {
                tree.add(nextValue);
                nextValue = 0;
            } else {
                nextValue *= 10;
                nextValue += chars[i]-'0';
            }
        }
        if (count > 0) {
            tree.add(nextValue);
        }

        return tree;
    }
}
