package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<> (10);
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(1);
        arb.enqueue(1);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();

        arb.enqueue(10);
        arb.enqueue(20);
        arb.enqueue(30);
        arb.enqueue(40);
        arb.enqueue(50);
        arb.enqueue(60);
        arb.enqueue(70);
        arb.enqueue(80);
        arb.enqueue(90);
        arb.enqueue(100);
        assertTrue(arb.isFull());
        assertFalse(arb.isEmpty());
        int test0 = arb.peek();
        assertEquals(10, test0);

        int test1 = arb.dequeue();
        int test2 = arb.dequeue();
        int test3 = arb.dequeue();
        int test4 = arb.dequeue();
        int test5 = arb.dequeue();
        int test6 = arb.dequeue();
        int test7 = arb.dequeue();
        int test8 = arb.dequeue();
        int test9 = arb.dequeue();
        int test10 = arb.dequeue();


        assertEquals(test1,10);
        assertEquals(test2,20);
        assertEquals(test3,30);
        assertEquals(test4,40);
        assertEquals(test5,50);
        assertEquals(test6,60);
        assertEquals(test7,70);
        assertEquals(test8,80);
        assertEquals(test9,90);
        assertEquals(test10,100);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
    }

    @Test
    public void task5test() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<> (10);
        arb.enqueue(10);
        arb.enqueue(20);
        arb.enqueue(30);
        arb.enqueue(40);
        arb.enqueue(50);
        arb.enqueue(60);
        arb.enqueue(70);
        arb.enqueue(80);
        arb.enqueue(90);
        arb.enqueue(100);
        for (double c: arb) {
            System.out.println(c);
        }
    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
