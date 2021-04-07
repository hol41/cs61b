import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('A', 'b'));
        assertTrue(offByOne.equalChars('b', 'A'));
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('B', 'A'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('e', 'a'));
        assertFalse(offByOne.equalChars('Z', '{'));
        assertFalse(offByOne.equalChars('z', '{'));
        assertFalse(offByOne.equalChars('{', '}'));
        assertFalse(offByOne.equalChars('}', '{'));

    }
}
