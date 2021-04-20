package byog.Core.Deque;

public class LinkedListDeque<T> implements Deque<T> {
    private StuffNode sentinel;
    private int size;

    private class StuffNode {
        private StuffNode pre;
        private T item;
        private StuffNode next;

        StuffNode(StuffNode p, T i, StuffNode n) {
            pre = p;
            item = i;
            next = n;
        }
    }


    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    @Override
    public void addFirst(T x) {
        sentinel.next = new StuffNode(sentinel, x, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size = size + 1;
    }


    @Override
    public void addLast(T x) {
        sentinel.pre = new StuffNode(sentinel.pre, x, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size = size + 1;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p.item != null) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
    }


    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T returnItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        size = size - 1;
        return returnItem;
    }


    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T returnItem = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        size = size - 1;
        return returnItem;
    }


    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        StuffNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index = index - 1;
        }
        return p.item;
    }


    private T getRecursive(StuffNode p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursive(p.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        } else {
            return getRecursive(sentinel.next, index);
        }
    }

    public T pop(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        StuffNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index = index - 1;
        }
        T res = p.item;
        p.pre.next = p.next;
        p.next.pre = p.pre;
        size = size - 1;
        return res;
    }
}

