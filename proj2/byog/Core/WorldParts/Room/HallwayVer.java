package byog.Core.WorldParts.Room;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class HallwayVer extends Room{
    public HallwayVer(int x, int y, int h) {
        super(x, y, 3, h);
    }


    public static Room nextRandom(Random random, Room room, int xLower, int xUpper, int yUpper) {
        // y = room.yStart + height - 2
        // y < upperBound - 4
        // x >= room.xStart
        // x < room.xStart - 3
        //
        // x <= xUpper - MINWIDTH
        // width >= MINWIDTH
        // height >= MINHIGHT
        final int MINWIDTH = 4;
        final int MINHEIGHT = 4;
        int x = randomIntRange(random, room.xStart, room.xStart + room.width - 3);
        int y = room.yStart + room.height - 2;

        int MAXHEIGHT = Math.min(20, yUpper - y - 1);
        int height = randomIntRange(random,MINHEIGHT, MAXHEIGHT );
        if (y + height >= yUpper) {
            return null;
        }
        return new HallwayVer(x, y, height);
    }
}
