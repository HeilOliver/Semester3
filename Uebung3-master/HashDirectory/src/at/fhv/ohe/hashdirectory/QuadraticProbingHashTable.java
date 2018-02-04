package at.fhv.ohe.hashdirectory;

public class QuadraticProbingHashTable<K,V> extends HashDirectory<K,V> {

    public QuadraticProbingHashTable() {
    }

    public QuadraticProbingHashTable(int size) {
        super(size);
    }

    public QuadraticProbingHashTable(int initSize, float loadFactor) {
        super(initSize, loadFactor);
    }

    @Override
    protected int getPosition(int hash, int i, int size) {
        return ((hash + (i*i)) % size);
    }
}
