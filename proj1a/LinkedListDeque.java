public class LinkedListDeque<T> {
    private StuffNode sentinel;
    private int size;

    private class StuffNode{
        public StuffNode pre;
        public T item;
        public StuffNode next;

        public StuffNode(StuffNode p, T i, StuffNode n){
            pre = p;
            item = i;
            next = n;
        }
    }



    public LinkedListDeque(){
        sentinel = new StuffNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }



    public void addFirst(T x){
        sentinel.next = new StuffNode(sentinel, x, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size = size + 1;
    }

    public void addLast(T x){
        sentinel.pre = new StuffNode(sentinel.pre, x, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size = size + 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        StuffNode p = sentinel.next;
        while (p.item != null) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T return_item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        size = size -1;
        return return_item;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T return_item = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        size = size - 1;
        return return_item;
    }

    public T get(int index){
        if (index > size - 1 || index < 0){
            return null;
        }
        StuffNode p = sentinel.next;
        while (index > 0){
            p = p.next;
            index = index - 1;
        }
        return p.item;
    }

    public T getRecursive(int index){
        return null;
    }
}