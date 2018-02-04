package at.fhv.ohe.hashdirectory;

public class DoubleHashingHashTable<K,V> extends HashDirectory<K,V> {

    public DoubleHashingHashTable() {
    }

    public DoubleHashingHashTable(int size) {
        super(size);
    }

    public DoubleHashingHashTable(int initSize, float loadFactor) {
        super(initSize, loadFactor);
    }

    @Override
    protected int getPosition(int hash, int i, int size) {
        return ((hash % size) + i * (1 + hash % (size-1))) % size;
    }
}
