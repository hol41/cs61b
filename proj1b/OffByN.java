public class OffByN implements CharacterComparator {
    private int offSet;
    public OffByN(int N) {
        offSet = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        x = Character.toLowerCase(x);
        y = Character.toLowerCase(y);
        if (x < 141 || x > 172 || y < 141 || y > 172) {
            return false;
        }
        if (x - y == offSet || y - x == offSet) {
            return true;
        }
        return false;
    }

}
