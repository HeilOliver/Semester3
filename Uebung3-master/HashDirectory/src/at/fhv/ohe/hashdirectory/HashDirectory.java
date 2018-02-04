package at.fhv.ohe.hashdirectory;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public abstract class HashDirectory<K,V> implements Map<K,V>{
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;  // Create new Table when Table > 75% load
    private static final int DEFAULT_START_SIZE = 17;    // Prime number

    private int _size;
    private int _modCount;
    private float _loadFactor;
    private int _growSize;
    private int _bucketSize;
    private Entry<K,V>[] _buckets;

    private transient volatile Set<K> _keySet;
    private transient volatile Set<Map.Entry<K,V>> _entrySet;
    private transient volatile Collection<V> _values;

    public HashDirectory() {
        this(DEFAULT_START_SIZE,DEFAULT_LOAD_FACTOR);
    }

    public HashDirectory(int size) {
        this(size, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashDirectory(int initSize, float loadFactor) {
        _loadFactor = loadFactor;
        _buckets = (Entry<K, V>[]) Array.newInstance(Entry.class, initSize);
        _bucketSize = _buckets.length;
        calcNextGrow();
    }

    private void calcNextGrow() {
        _growSize = (int) (_buckets.length * _loadFactor);
    }

    private static class Entry<K,V> implements Map.Entry<K,V> {
        private final int _hash;
        private final K _key;
        private V _value;
        private boolean _alive;

        private Entry(K key, V value) {
            _hash = key.hashCode();
            _key =  key;
            _value = value;
            _alive = true;
        }

        public K getKey() {
            return _key;
        }

        public V getValue() {
            return _value;
        }

        public V setValue(V value) {
            if (value == null)
                throw new NullPointerException();

            V oldValue = _value;
            _value = value;
            return oldValue;
        }

        private void isDead() {
            _alive = false;
        }

        private boolean isAlive() {
            return _alive;
        }

        @Override
        public boolean equals(Object obj) {
            return _alive && _key.equals(obj);
        }
    }

    protected abstract int getPosition(int hash, int i, int size);

    @SuppressWarnings("unchecked")
    private void growTable() {
        BigInteger bValue = BigInteger.valueOf(_buckets.length*2);
        bValue = bValue.nextProbablePrime();
        Entry<K,V>[] arr = _buckets;
        _buckets = (Entry<K, V>[]) Array.newInstance(Entry.class, Integer.parseInt(bValue.toString()));
        _bucketSize = _buckets.length;
        clear();
        calcNextGrow();

        for (Entry<K, V> anArr : arr) {
            if (anArr == null) {
                continue;
            }
            if (!anArr.isAlive()) {
                continue;
            }
            put(anArr._key, anArr._value);
        }
    }

    @Override
    public V put(K key, V value) {
        _modCount++;
        Entry<K, V> entry = new Entry<>(key, value);
        int pos = getPosition(entry._hash,0,_bucketSize);

        for (int i = 0;;) {
            if (_buckets[pos] != null) {
                if(_buckets[pos].equals(entry._key)) {
                    return _buckets[pos].setValue(value);
                }
                pos = getPosition(entry._hash,++i,_bucketSize);
            } else {
                _buckets[pos] = entry;
                break;
            }
        }

        if (++_size >= _growSize)
            growTable();
        return value;
    }

    @Override
    public boolean containsKey(Object key) {
        int pos = getPosition(key.hashCode(),0,_bucketSize);

        for (int i = 0;;) {
            if (_buckets[pos] != null) {
                if(_buckets[pos].equals(key)) {
                    return true;
                }
                pos = getPosition(key.hashCode(),i++,_bucketSize);
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean containsValue(Object value) {
        ValueIterator iterator = new ValueIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int pos = getPosition(key.hashCode(),0,_bucketSize);

        for (int i = 0;;) {
            if (_buckets[pos] != null) {
                if(_buckets[pos].equals(key)) {
                    return _buckets[pos].getValue();
                }
                pos = getPosition(key.hashCode(),i++,_bucketSize);
            } else {
                return null;
            }
        }
    }

    @Override
    public V remove(Object key) {
        _modCount++;
        int pos = getPosition(key.hashCode(),0,_bucketSize);

        for (int i = 0;;) {
            if (_buckets[pos] != null) {
                if(_buckets[pos].equals(key)) {
                    _buckets[pos].isDead();
                    --_size;
                    return _buckets[pos].getValue();
                }
                pos = getPosition(key.hashCode(),i++,_bucketSize);
            } else {
                return null;
            }
        }
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        _modCount++;
        Entry<?,?> tab[] = _buckets;
        for (int index = tab.length; --index >= 0; )
            tab[index] = null;
        _size = 0;
    }

    @Override
    public Collection<V> values() {
        if (_values == null)
            _values = Collections.unmodifiableCollection(new ValueCollection());
        return _values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (_entrySet == null)
            _entrySet = Collections.unmodifiableSet(new EntrySet());
        return _entrySet;
    }

    @Override
    public Set<K> keySet() {
        if (_keySet == null)
            _keySet = Collections.unmodifiableSet(new KeySet());
        return _keySet;
    }

    private abstract class TableSet<T> extends AbstractSet<T> {
        public int size() {
            return _size;
        }
        public boolean contains(Object o) {
            return containsKey(o);
        }
        public boolean remove(Object o) {
            return HashDirectory.this.remove(o) != null;
        }
        public void clear() {
            HashDirectory.this.clear();
        }
    }

    private abstract class TableIterator<T> implements Iterator<T> {
        private final int _expectedModCount;
        private T _nextEntry;

        private TableIterator() {
            _expectedModCount = _modCount;
            _nextEntry = searchNextEntry();
        }

        protected abstract T searchNextEntry();

        @Override
        public boolean hasNext() {
            if (_expectedModCount != _modCount) {
                throw new ConcurrentModificationException();
            }
            return _nextEntry != null;
        }

        @Override
        public T next() {
            if (_expectedModCount != _modCount) {
                throw new ConcurrentModificationException();
            }
            if (_nextEntry == null) {
                throw new NoSuchElementException();
            }
            T returnEntry = _nextEntry;
            _nextEntry = searchNextEntry();
            return returnEntry;
        }
    }

    private class KeySet extends TableSet<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    private class EntrySet extends TableSet<Map.Entry<K,V>> {
        @Override
        public Iterator<Map.Entry<K,V>> iterator() {
            return new EntryIterator();
        }
    }

    private class KeyIterator extends TableIterator<K> {
        int _pos;

        private KeyIterator() {
            _pos = -1;
        }

        @Override
        protected K searchNextEntry() {
            for (_pos++; _pos < _buckets.length; _pos++) {
                if (_buckets[_pos] != null) {
                    if (_buckets[_pos].isAlive()) {
                        return _buckets[_pos].getKey();
                    }
                }
            }
            return null;
        }
    }

    private class EntryIterator extends TableIterator<Map.Entry<K,V>> {
        int _pos;

        private EntryIterator() {
            _pos = -1;
        }

        @Override
        protected Map.Entry<K,V> searchNextEntry() {
            for (_pos++; _pos < _buckets.length; _pos++) {
                if (_buckets[_pos] != null) {
                    if (_buckets[_pos].isAlive()) {
                        return _buckets[_pos];
                    }
                }
            }
            return null;
        }
    }

    private class ValueIterator extends TableIterator<V> {
        int _pos;

        private ValueIterator() {
            _pos = -1;
        }

        @Override
        protected V searchNextEntry() {
            for (_pos++; _pos < _buckets.length; _pos++) {
                if (_buckets[_pos] != null) {
                    if (_buckets[_pos].isAlive()) {
                        return _buckets[_pos].getValue();
                    }
                }
            }
            return null;
        }
    }

    private class ValueCollection extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
        public int size() {
            return _size;
        }
        public boolean contains(Object o) {
            return containsValue(o);
        }
        public void clear() {
            HashDirectory.this.clear();
        }
    }
}
