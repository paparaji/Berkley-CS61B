import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    /**
     * Binary search tree
     */
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    /**
     * Return the size of the tree
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * The helper function of size
     */
    private int size(Node T) {
        if (T == null) {
            return 0;
        } else {
            return T.size;
        }
    }

    /**
     * Clear all the element in the tree
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Return whether the tree has target key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    /**
     * The helper function of containsKey
     */
    private boolean containsKey(Node T, K key) {
        if (T == null) {
            return false;
        }
        if (T.key.equals(key)) {
            return true;
        }
        int cmp = T.key.compareTo(key);
        if (cmp > 0) {
            return containsKey(T.right, key);
        } else {
            return containsKey(T.left, key);
        }
    }

    /**
     * Return the value of target key
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    /**
     * The helper function of get
     */
    private V get(Node T, K key) {
        if (T == null) {
            return null;
        }
        if (T.key.equals(key)) {
            return T.value;
        }
        int cmp = T.key.compareTo(key);
        if (cmp > 0) {
            return get(T.right, key);
        } else {
            return get(T.left, key);
        }
    }

    /**
     * Add the keys and values to the BST
     */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /**
     * The helper function of put
     */
    private Node put(Node T, K key, V value) {
        if (T == null) {
            return new Node(key, value, 1);
        }
        int cmp = T.key.compareTo(key);
        if (cmp > 0) {
            T.right = put(T.right, key, value);
        } else if (cmp < 0) {
            T.left = put(T.left, key, value);
        } else {
            T.value = value;
        }
        T.size = size(T.left) + size(T.right) + 1;
        return T;
    }

    @Override
    public V remove(K key) {
        V temp = get(root, key);
        if (temp == null) {
            return null;
        } else {
            root = remove(root, key);
        }
        return temp;
    }

    @Override
    public V remove(K key, V value) {
        V temp = get(root, key);
        if (!temp.equals(value)) {
            return null;
        } else {
            root = remove(root, key);
        }
        return temp;
    }

    /**
     * The helper function of remove
     */
    private Node remove(Node T, K key) {
        if (T == null) {
            return null;
        }
        int cmp = T.key.compareTo(key);
        if (cmp > 0) {
            T.right = remove(T.right, key);
        } else if (cmp < 0) {
            T.left = remove(T.left, key);
        } else {
            if (T.left == null) {
                return T.right;
            }
            if (T.right == null) {
                return T.left;
            }
            Node temp = T;
            T = min(temp.right);
            T.right = removeMin(temp.right);
            T.left = temp.left;
        }
        T.size = size(T.left) + size(T.right) + 1;
        return T;
    }

    /**
     * The helper function of private remove
     */
    private Node removeMin(Node T) {
        if (T.left == null) {
            return T.right;
        }
        T.left = removeMin(T.left);
        T.size = size(T.left) + size(T.right) + 1;
        return T;
    }

    /**
     * The helper function of remove
     */
    private Node min(Node T) {
        if (T.left == null) {
            return T;
        }
        return min(T.left);
    }

    /**
     * Return the set of all the keys
     */
    @Override
    public Set<K> keySet() {
        Set<K> temp = new HashSet<>();
        keySet(root, temp);
        return temp;
    }

    /**
     * The helper function of keySet
     */
    public void keySet(Node T, Set<K> temp) {
        if (T == null) {
            return;
        }
        temp.add(T.key);
        keySet(T.right, temp);
        keySet(T.left, temp);
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
