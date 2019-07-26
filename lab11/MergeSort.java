import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     * <p>
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     * <p>
     * This method should take linear time.
     *
     * @param items A Queue of items.
     * @return A Queue of queues, each containing an item from items.
     */
    private static <Item extends Comparable> Queue<Queue<Item>>
    makeSingleItemQueues(Queue<Item> items) {
        Iterator<Item> temp = items.iterator();
        Queue<Queue<Item>> result = new Queue<>();
        while (temp.hasNext()) {
            Queue<Item> temp2 = new Queue<>();
            temp2.enqueue(temp.next());
            result.enqueue(temp2);
        }
        return result;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        int totalSize = q1.size() + q2.size();
        Queue<Item> result = new Queue<>();
        for (int i = 0; i < totalSize; i++) {
            result.enqueue(getMin(q1, q2));
        }
        return result;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     * <p>
     * This method should take roughly nlogn time where n is the size of "items"
     * this method should be non-destructive and not empty "items".
     *
     * @param items A Queue to be sorted.
     * @return A Queue containing every item in "items".
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() == 2) {
            Queue<Queue<Item>> temp = makeSingleItemQueues(items);
            return mergeSortedQueues(temp.dequeue(), temp.dequeue());
        }
        if (items.size() == 1) {
            return items;
        }
        Queue<Item> temp2 = new Queue<>();
        Queue<Item> temp3 = new Queue<>();
        int half = items.size() / 2;
        int size = items.size();
        for (int i = 0; i < half; i++) {
            temp2.enqueue(items.dequeue());
        }
        for (int i = half; i < size; i++) {
            temp3.enqueue(items.dequeue());
        }
        temp2 = mergeSort(temp2);
        temp3 = mergeSort(temp3);
        temp3 = mergeSortedQueues(temp2, temp3);
        while (!temp3.isEmpty()) {
            items.enqueue(temp3.dequeue());
        }
        return items;
    }
}
