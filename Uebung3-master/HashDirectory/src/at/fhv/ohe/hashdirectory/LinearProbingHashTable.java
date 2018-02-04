package at.fhv.ohe.hashdirectory;

public class LinearProbingHashTable<K,V> extends HashDirectory<K,V> {

    public LinearProbingHashTable() {
    }

    public LinearProbingHashTable(int size) {
        super(size);
    }

    public LinearProbingHashTable(int initSize, float loadFactor) {
        super(initSize, loadFactor);
    }

    @Override
    protected int getPosition(int hash, int i, int size) {
        return ((hash + i) % size);
    }
}
