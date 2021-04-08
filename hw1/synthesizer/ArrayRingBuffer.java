package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    private int ringIncrement(int x) {
        if (x == capacity - 1) {
            return 0;
        }
        return x + 1;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("the queue is full");
        }
        fillCount = fillCount + 1;
        rb[last] = x;
        last = ringIncrement(last);


    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("the queue is empty");
        }
        fillCount = fillCount - 1;
        T res = rb[first];
        rb[first] = null;
        first = ringIncrement(first);
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("the queue is empty");
        }
        return rb[first];
    }

    private class ArraySetIterator implements Iterator<T> {
        private int index;
        private int itemLeft;
        ArraySetIterator() {
            index = first;
            itemLeft = fillCount;
        }

        @Override
        public boolean hasNext() {
            return itemLeft > 0;
        }

        @Override
        public T next() {
            T res = rb[index];
            index = ringIncrement(index);
            itemLeft = itemLeft - 1;
            return res;
        }
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }
}
