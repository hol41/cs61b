import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    public boolean is_equal_array(ArrayDeque<Integer> ad, int[] a){
        for (int i = 0; i < a.length; i = i + 1){
            if (ad.get(i) - a[i] != 0){
                return false;
            }
        }
        return true;
    }


    @Test
    public void testAddLast(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        int[] expect = new int[5000001];
        for (int i = 0; i <= 5000000; i = i + 1){
            test.addLast(i);
            expect[i] = i;
        }
        System.out.print(is_equal_array(test, expect));
    }


    @Test
    public void testAddFirst(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        int[] expect = new int[50001];
        for (int i = 0; i <= 50000; i = i + 1){
            test.addFirst(50000 - i);
            expect[i] = i;
        }
        System.out.print(is_equal_array(test, expect));
    }


    @Test
    public void testAddFirstWithAddLast(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        int[] expect = new int[] {-9,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9};
        test.addFirst(-1);
        test.addFirst(-2);
        test.addFirst(-3);
        test.addFirst(-4);
        test.addFirst(-5);
        test.addFirst(-6);
        test.addFirst(-7);
        test.addFirst(-8);
        test.addFirst(-9);
        test.addLast(0);
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        test.addLast(4);
        test.addLast(5);
        test.addLast(6);
        test.addLast(7);
        test.addLast(8);
        test.addLast(9);

        System.out.print(is_equal_array(test, expect));
    }


    @Test
    public void testSize(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        System.out.println(test.size());
        test.addFirst(-1);
        System.out.println(test.size());
        test.addFirst(-2);
        System.out.println(test.size());
        test.addFirst(-3);
        System.out.println(test.size());
        test.addFirst(-4);
        System.out.println(test.size());
        test.addFirst(-5);
        System.out.println(test.size());
        test.addFirst(-6);
        System.out.println(test.size());
        test.addFirst(-7);
        System.out.println(test.size());
        test.addFirst(-8);
        System.out.println(test.size());
        test.addFirst(-9);
        System.out.println(test.size());
        test.addLast(0);
        System.out.println(test.size());
        test.addLast(1);
        System.out.println(test.size());
        test.addLast(2);
        System.out.println(test.size());
        test.addLast(3);
        System.out.println(test.size());
        test.addLast(4);
        System.out.println(test.size());
        test.addLast(5);
        System.out.println(test.size());
        test.addLast(6);
        System.out.println(test.size());
        test.addLast(7);
        System.out.println(test.size());
        test.addLast(8);
        System.out.println(test.size());
        test.addLast(9);
        System.out.println(test.size());
    }


    @Test
    public void testRemove(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i <= 500; i = i + 1){
            test.addFirst(500 - i);
        }
        for (int i = 501; i <= 1001; i = i + 1){
            test.addLast(i);
        }
        for (int i = 0; i <= 1001; i = i + 1){
            int a = test.removeFirst();
            assertEquals(i, a);
        }
        assertEquals(null, test.removeFirst());
        assertEquals(null, test.removeLast());
        assertEquals(0, test.size());
        assertTrue(test.isEmpty());

        for (int i = 0; i <= 500; i = i + 1){
            test.addFirst(500 - i);
        }
        for (int i = 501; i <= 1001; i = i + 1){
            test.addLast(i);
        }
        for (int i = 0; i <= 1001; i = i + 1){
            test.removeFirst();
        }
        assertEquals(null, test.removeFirst());
        assertEquals(null, test.removeLast());
        assertEquals(0, test.size());
        assertTrue(test.isEmpty());

        for (int i = 0; i <= 500; i = i + 1){
            test.addFirst(500 - i);
        }
        for (int i = 501; i <= 1001; i = i + 1){
            test.addLast(i);
        }
        for (int i = 0; i <= 1001; i = i + 1){
            int a = test.removeLast();
            assertEquals(1001 - i, a);
        }
        assertEquals(null, test.removeFirst());
        assertEquals(null, test.removeLast());
        assertEquals(0, test.size());
        assertTrue(test.isEmpty());
    }


    @Test
    public void testGet(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i <= 500; i = i + 1){
            test.addFirst(500 - i);
        }
        for (int i = 501; i <= 1001; i = i + 1){
            test.addLast(i);
        }
        int actual1 = test.get(10);
        assertEquals(actual1,10);
        assertEquals(test.get(-1), null);
        assertEquals(test.get(10000000), null);
    }


    public static void main(String[] args){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(5);
        test.addFirst(6);
        test.addFirst(7);
        test.addFirst(8);
        test.addFirst(9);
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        test.addLast(10);
        test.addLast(11);
        System.out.println(test.size());
        test.printDeque();
    }

}
