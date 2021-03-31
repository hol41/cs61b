public class LinkedListDeque<ll_type> {

    private class StuffNode{
        public StuffNode pre;
        public ll_type item;
        public StuffNode next;

        public StuffNode(StuffNode p, ll_type i, StuffNode n){
            pre = p;
            item = i;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new StuffNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(ll_type x){
        sentinel = new StuffNode(null, null, null);
        sentinel.next = new StuffNode(sentinel, x, sentinel);
        sentinel.pre = sentinel.next;
        size = 1;
    }

    public void addFirst(ll_type x){
        sentinel.next = new StuffNode(sentinel, x, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size = size + 1;
    }

    public void addLast(ll_type x){
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

    public ll_type removeFirst(){
        if (isEmpty()){
            return null;
        }
        ll_type return_item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        size = size -1;
        return return_item;
    }

    public ll_type removeLast(){
        if (isEmpty()){
            return null;
        }
        ll_type return_item = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        size = size - 1;
        return return_item;
    }

    public ll_type get(int index){
        if (index > size - 1 || index < 0){
            return null;
        }
        StuffNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index = index - 1;
        }
        return p.item;
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> test1 = new LinkedListDeque<>();
        test1.addFirst(1);
        test1.addLast((2));
        test1.addLast(3);
        LinkedListDeque<String> test2 = new LinkedListDeque<>("two");
        test2.addFirst("one");
        test2.addLast("three");
        System.out.println(test2.get(0));
        System.out.print(test2.get(4));

        int x = 0;
    }
}