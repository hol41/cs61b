package byog.Core.Room;

public class HallwayHor extends Room {
    public HallwayHor(int x, int y, int w) {
        super(x, y, w, 3);
    }

    public static HallwayHor getNewHalwayHor(int y, int xStart, int xEnd) {
        if (xStart < xEnd) {
            return new HallwayHor(xStart, y, xEnd - xStart + 3);
        } else {
            return new HallwayHor(xEnd, y, xStart - xEnd + 3);
        }
    }
}
