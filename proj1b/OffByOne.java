public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        x = Character.toLowerCase(x);
        y = Character.toLowerCase(y);
        if (x < 61 || x > 122 || y < 61 || y > 122) {
            return false;
        }
        if (x - y == 1 || y - x == 1) {
            return true;
        }
        return false;
    }

}
