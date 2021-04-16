package byog.Core.WorldParts.Room;

import byog.TileEngine.TETile;

public class HorHallway extends Room{
    public HorHallway(int x, int y, int w) {
        super(x, y, w, 3);
    }

    public void genHallway(TETile[][] world) {
        super.genRoom(world);
    }
}
