import java.lang.reflect.Array;

/** A min-heap of at most 1000 elements of type E. */
public class Heap<E> {
    /**
     * b[0..size-1] is a min-heap, i.e.:
     * 1. Each Item in b[0..size-1] contains a value and its priority.
     * 2. The children of each b[k] are b[2k+1] and b[2k+2].
     * 3. The parent of each b[k] is b[(k-1)/2].
     * 4. For each {@code k > 0}, {@code (priority of b[k]'s parent) <= (priority of b[k])}.
     */
    Item[] b;
    int size;

    /** Create an empty heap of size at most 1000. */
    @SuppressWarnings("unchecked")
    public Heap() {
        b = (Item[]) Array.newInstance(Item.class, 1000);
        size = 0;
    }

    /** Return the size of the heap. */
    public int size() {
        return size;
    }

    /** Swap b[h] and b[k]. */
    private void swap(int h, int k) {
        Item t = b[h];
        b[h] = b[k];
        b[k] = t;
    }

    /**
     * Return a string that gives this heap in the format:
     * [item[0], item[1], ..., item[size-1]].
     * That is, the list is delimited by '[' and ']' and a space separates adjacent items.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        for (int k = 0; k < size; k = k + 1) {
            if (k > 0) res.append(" ");
            res.append(b[k]);
        }

        return res + "]";
    }

    /**
     * Return a string that gives the priorities in the heap in the format:
     * [item[0], item[1], ..., item[size-1]].
     * That is, the list is delimited by '[' and ']' and ", " separates adjacent items.
     */
    public String toStringPriorities() {
        StringBuilder res = new StringBuilder("[");
        for (int k = 0; k < size; k = k + 1) {
            if (k > 0) res.append(", ");
            res.append(b[k].priority);
        }

        return res + "]";
    }

    /**
     * Add e with priority p to the heap.
     * Takes time O(log size-of-heap).
     * Requires size of heap is less than 1000.
     */
    public void push(E e, double p) {
        b[size] = new Item(e, p);
        size = size + 1;
        bubbleUp(size - 1);
    }

    /**
     * Bubble b[k] up in heap to its right place.
     * Precondition: Every b[i] ≥ its parent except perhaps for b[k].
    */
    private void bubbleUp(int k) {
        // Invariant: The class invariant holds except that the priority
        // . . . . . . of k may be < the priority of k's parent.
        while (k > 0) {
            int parent = 0;
            // TODO 1. Set parent to the parent of k. Uncomment one of the
            // . . . . following three lines and check whether testing procedure
            // . . . . testAddAndBubbleUp indicates an error.
            parent = (k - 1) / 2;
            // parent = k / 2;
            // parent = (k + 1) / 2;

            if (b[k].priority >= b[parent].priority) { return; }
            swap(k, parent);
            k = parent;
        }

    }

    /**
     * Bubble b[k] down in heap to its place.
     * If the two children have the same priority, bubble down the left one.
     * Precondition: {@code 0 <= k < size of heap} and
     *   Each b[i] ≤ its children except perhaps for b[k].
     */
    void bubbleDown(int k) {
        // Invariant: The class invariant holds except that the
        // . . . . . . priority of k may be > the priority of a child.
        // . . . . . . Also, lc is k's left child if it exists.
        int lc = 2*k + 1;
        while (lc < size) {  // while the left child exists
            // Set lc to the right child if it exists and its priority is smaller
            if (lc + 1 < size && b[lc + 1].priority < b[lc].priority) {
                lc = lc + 1;
            }

            // TODO 2: Return if b[k] should not be bubbled down further
            // if (b[k].priority < b[lc].priority) return;
            if (b[k].priority <= b[lc].priority) return;
            // if (b[lc].priority <= b[k].priority) return;
            swap(k, lc);
            k = lc;
            lc = 2*k + 1;
        }
    }

    /**
     * Return the smallest value in the heap (based on priority).
     * This operation takes constant time.
     * Requires the heap is not empty.
     */
    public E peek() {
        return b[0].val;
    }

    /**
     * Remove and return smallest value in heap.
     * Worst-case time: O(log size-of-heap).
     * Requires the heap is not empty.
     */
    public E pop() {
        assert size > 0;
        if (size == 1) {
            size = 0;
            return b[0].val;
        }
        // At least two elements in the heap
        E val = b[0].val;
        swap(0, size - 1);
        // TODO 3. Bubble down and decrement size, in the proper order.
        // { bubbleDown(0); size-- ; }
        { size-- ; bubbleDown(0); }
        return val;
    }

    /** An instance of Item contains a value and a priority. */
    class Item {
        E val;
        double priority;

        /** Constructor: An Item with value v and priority p. */
        Item(E v, double p) {
            val = v;
            priority = p;
        }

        /**
         * Return a String repr of this Item: its value and priority,
         * enclosed in parentheses and separated by ", ".
         */
        @Override
        public String toString() {
            return "(" + val + ", " + priority + ")";
        }
    }
}
