public class OffByN implements CharacterComparator {
    private int OffSet;
    public OffByN(int N) {
        OffSet = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        if (x - y == OffSet || y - x == OffSet){
            return true;
        }
        return false;
    }

}
