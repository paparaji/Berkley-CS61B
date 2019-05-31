import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private int size = 0;
    private HashSet<K> keySet;
    private ArrayList<Entry>[] maps;
    private int initialSize;
    private double loadFactor;

    MyHashMap() {
        this.initialSize = 16;
        this.loadFactor = 0.75;
        initializeMap(initialSize);
        this.keySet = new HashSet<>();
    }

    MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        initializeMap(initialSize);
        this.keySet = new HashSet<>();
    }

    MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        initializeMap(initialSize);
        this.keySet = new HashSet<>();
    }

    /**
     * Create the generic array
     */
    private void initializeMap(int capacity) {
        maps = new ArrayList[capacity];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new ArrayList<Entry>();
        }
    }

    /**
     * contain key and values
     */
    private class Entry {

        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * clear the items in the hashmap
     */
    @Override
    public void clear() {
        this.size = 0;
        this.keySet = new HashSet<>();
        initializeMap(initialSize);
    }

    /**
     * Return whether the key exists
     */
    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    /**
     * Return the size of the map
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Return the value of the specific key
     */
    @Override
    public V get(K key) {
        int hash = (key.hashCode() & 0x7fffffff) % maps.length;
        for (Entry map : maps[hash]) {
            if (map.key.equals(key)) {
                return map.value;
            }
        }
        return null;
    }

    /**
     * Return the keySet of the map
     */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Add the key and value to the map
     */
    @Override
    public void put(K key, V value) {
        int hash = (key.hashCode() & 0x7fffffff) % maps.length;
        if (containsKey(key)) {
            for (Entry map : maps[hash]) {
                if (map.key.equals(key)) {
                    map.value = value;
                    return;
                }
            }
        }
        keySet.add(key);
        maps[hash].add(new Entry(key, value));
        this.size += 1;
        if ((double) size / maps.length - loadFactor > 0) {
            resize(maps.length * 2);
        }
    }

    /**
     * Resize the maps
     */
    private void resize(int capacity) {
        ArrayList<Entry> temp = new ArrayList<>();
        for (K key : keySet) {
            temp.add(new Entry(key, get(key)));
        }
        initializeMap(capacity);
        size = 0;
        for (Entry temp2 : temp) {
            put(temp2.key, temp2.value);
        }
    }

    /**
     * Remove the items in the map
     */
    @Override
    public V remove(K key, V value) {
        if (containsKey(key)) {
            int hash = (key.hashCode() & 0x7fffffff) % maps.length;
            ArrayList<Entry> temp = maps[hash];
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).key.equals(key)) {
                    if (temp.get(i).value.equals(value)) {
                        maps[hash].remove(i);
                        size -= 1;
                        keySet.remove(key);
                        return value;
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            int hash = (key.hashCode() & 0x7fffffff) % maps.length;
            ArrayList<Entry> temp = maps[hash];
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).key.equals(key)) {
                    V temp2 = temp.get(i).value;
                    maps[hash].remove(i);
                    size -= 1;
                    keySet.remove(key);
                    return temp2;
                }
            }
        }
        return null;
    }

    /**
     * Return the iterator of the map
     */
    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
