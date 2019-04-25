public interface Deque<T> {

    /**
     * add the first item to the list
     */
    void addFirst(T item);

    /**
     * add the last item to the list
     */
    void addLast(T item);

    /**
     * Deternmine whether the list is empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the size of the list
     */
    int size();

    /**
     * Print out the list
     */
    void printDeque();

    /**
     * Remove and Return the item at the back of the deque
     */
    T removeLast();

    /**
     * Remove the Return the item of the first item of the deque
     */
    T removeFirst();

    /**
     * Return the ith item of the list
     */
    T get(int index);
}
