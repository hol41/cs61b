import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'B'));
        assertTrue(offByOne.equalChars('b', 'A'));
        assertTrue(offByOne.equalChars('e', 'f'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('a', 'a'));

    }
}
