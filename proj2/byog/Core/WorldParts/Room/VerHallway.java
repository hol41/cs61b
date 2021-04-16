package byog.Core.WorldParts.Room;

import byog.TileEngine.TETile;

public class VerHallway extends Room{
    public VerHallway(int x, int y, int h) {
        super(x, y, 3, h);
    }

    public void genHallway(TETile[][] world) {
        super.genRoom(world);
    }
}
