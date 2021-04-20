package byog.Core.WorldParts.Room;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class HallwayVer extends Room{
    public HallwayVer(int x, int y, int h) {
        super(x, y, 3, h);
    }

    public static HallwayVer getNewHalwayVer(int x, int yStart, int yEnd) {
        if (yStart < yEnd) {
            return new HallwayVer(x, yStart, yEnd - yStart + 3);
        }
        else {
            return new HallwayVer(x, yEnd, yStart - yEnd + 3);
        }
    }
}
