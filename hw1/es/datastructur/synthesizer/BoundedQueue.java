package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    /* Return the size of the buffer */
    int capacity();

    /* Return number of items currently in the buffer */
    int fillCount();

    /* add itex x to the end */
    void enqueue(T x);

    /* Delete and return item from the front */
    T dequeue();

    /* Return (but do not delete) item from the front */
    T peek();

    /* is the buffer empty */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /* is the buffer full */
    default boolean isFull() {
        return fillCount() == capacity();
    }

    /* Return the iterator of the queue */
    @Override
    Iterator<T> iterator();
}
