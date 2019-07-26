package bearmaps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private PriorityQueueNode[] pq;
    private Map<T, Integer> map;
    private int size;
    private int resizeFactor = 2;
    private int capacity;

    private class PriorityQueueNode {
        private T item;
        private double priority;

        PriorityQueueNode() {
        }

        PriorityQueueNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        public T getItem() {
            return item;
        }

        public double getPriority() {
            return priority;
        }

        public void setPriority(double priority) {
            this.priority = priority;
        }
    }


    /**
     * Return the index of the parent
     **/
    private int parent(int k) {
        return k / 2;
    }

    /**
     * Return the index of the left child
     **/
    private int leftChild(int k) {
        return 2 * k;
    }

    /**
     * Return the index of right child
     */
    private int rightChild(int k) {
        return 2 * k + 1;
    }

    /**
     * Up the item in the priority queue
     */
    private void shiftUp(T item, int k) {
        while (parent(k) != 0) {
            int parentNode = parent(k);
            if (pq[parentNode].getPriority() > pq[k].getPriority()) {
                PriorityQueueNode temp = pq[parentNode];
                map.put(item, parentNode);
                map.put(temp.getItem(), k);
                pq[parentNode] = pq[k];
                pq[k] = temp;
                k = parentNode;
            } else {
                break;
            }
        }
    }

    /**
     * Down the item in the priority queue
     */
    private void shiftDown(T item, int k) {
        while (leftChild(k) <= size) {
            int child = leftChild(k);
            if (child < size && pq[child].getPriority() > pq[child + 1].getPriority()) {
                child += 1; //Right Child
            }
            if (pq[k].getPriority() < pq[child].getPriority()) {
                break;
            }
            PriorityQueueNode temp = pq[child];
            map.put(item, child);
            map.put(temp.getItem(), k);
            pq[child] = pq[k];
            pq[k] = temp;
            k = child;
        }
    }

    /**
     * Resize the array
     */
    @SuppressWarnings("unchecked")
    private void resize(int cap) {
        PriorityQueueNode[] temp = (PriorityQueueNode[])
                new ArrayHeapMinPQ<?>.PriorityQueueNode[cap + 1];
        if (cap < capacity) {
            System.arraycopy(pq, 1, temp, 1, cap);
        } else {
            System.arraycopy(pq, 1, temp, 1, capacity);
        }

        pq = temp;
        capacity = cap;
    }

    /**
     * Initialize the MinPQ with capacity
     */
    @SuppressWarnings("unchecked")
    ArrayHeapMinPQ(int capacity) {
        pq = (PriorityQueueNode[]) new ArrayHeapMinPQ<?>.PriorityQueueNode[capacity + 1];
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    /**
     * Initialize the empty MinPQ
     */
    ArrayHeapMinPQ() {
        this(2);
    }

    /**
     * Return the size of the priority queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Add the item to the priority queue
     */
    @Override
    public void add(T item, double priority) {
        if (size >= capacity) {
            resize(resizeFactor * capacity);
        }
        if (map.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        map.put(item, size + 1);
        pq[size + 1] = new PriorityQueueNode(item, priority);
        shiftUp(item, size + 1);
        size += 1;
    }

    /**
     * Get the smallest item
     */
    @Override
    public T getSmallest() {
        return pq[1].getItem();
    }

    /**
     * Remove and return the smallest item
     */
    @Override
    public T removeSmallest() {
        T tempItem = pq[1].getItem();
        pq[1] = pq[size];
        size -= 1;
        shiftDown(pq[1].getItem(), 1);
        if (size < 0.25 * capacity) {
            resize((int) (0.5 * capacity));
        }
        map.remove(tempItem);
        return tempItem;
    }

    /**
     * Return whether the item exists in the priority queue
     */
    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    /**
     * Change the priority of the node and modify the whole queue
     */
    @Override
    public void changePriority(T item, double priority) {
        int index = map.get(item);
        double oldPriority = pq[index].getPriority();
        pq[index].setPriority(priority);
        if (oldPriority > priority){
            shiftUp(item, index);
        }else{
            shiftDown(item, index);
        }
    }

    /**
     * Return the capacity of the MinPQ (Used for test)
     */
    public int getCapacity() {
        return capacity;
    }
}
