import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
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
