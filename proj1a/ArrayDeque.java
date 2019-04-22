public class ArrayDeque<T> {
    private T[] item;
    private int size;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        item = (T[]) new Object[other.item.length];
        System.arraycopy(item, 0, other.item, 0, other.size());
    }

    /**
     * Resize the item
     */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(item, 0, temp, 0, size);
        item = temp;
    }

    /**
     * Add the item to the first of the AList
     */
    public void addFirst(T add) {
        if (item.length < size + 1) {
            resize(size * 2);
        }
        T[] temp = (T[]) new Object[item.length];
        System.arraycopy(item, 0, temp, 1, size);
        temp[0] = add;
        size += 1;
    }

    /**
     * Add the item to the last of the AList
     */
    public void addLast(T add) {
        if (item.length < size + 1) {
            resize(size * 2);
        }
        item[size] = add;
        size += 1;
    }

    /**
     * Determine whether the AList is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size of the AList
     */
    public int size() {
        return size;
    }

    /**
     * Print the AList
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(item[i] + " ");
        }
        System.out.println();
    }

    /**
     * Remove the first item of the AList
     */
    public T removeFirst() {
        if (0.25 * item.length > size && item.length >= 16) {
            resize((int) 0.25 * item.length);
        }

        T[] temp = (T[]) new Object[item.length];
        System.arraycopy(item, 1, temp, 0, size - 1);
        T remove = item[0];
        item = temp;
        size -= 1;
        return remove;
    }

    /**
     * Remove the last item of the AList
     */
    public T removeLast() {
        if (0.25 * item.length > size && item.length >= 16) {
            resize((int) 0.25 * item.length);
        }
        T[] temp = (T[]) new Object[item.length];
        System.arraycopy(item, 0, temp, 0, size - 1);
        T remove = item[size - 1];
        item = temp;
        size -= 1;
        return remove;
    }

    /**
     * Return the ith item of the AList
     */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return item[index];
    }
}
