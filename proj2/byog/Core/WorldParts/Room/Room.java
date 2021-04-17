package byog.Core.WorldParts.Room;

import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Room {
    public int xStart;
    public int yStart;
    public int width;
    public int height;
    public boolean connected;

    public Room(int x, int y, int w, int h) {
        xStart = x;
        yStart = y;
        width = w;
        height = h;
        connected = false;
    }

    protected static int randomIntRange(Random R, int lower, int upper) {
        if (upper - lower == 0) {
            return lower;
        }
        return lower + RandomUtils.uniform(R, upper - lower);
    }

    public void print(TETile[][] world) {
        for (int x = xStart + 1; x < xStart + width - 1; x = x + 1) {
            for (int y = yStart + 1; y < yStart + height - 1; y = y + 1) {
                world[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = xStart; x < xStart + width; x = x + 1) {
            if (Tileset.NOTHING.equals(world[x][yStart])) {
                world[x][yStart] = Tileset.WALL;
            }
            if (Tileset.NOTHING.equals(world[x][yStart + height - 1])) {
                world[x][yStart + height - 1] = Tileset.WALL;
            }
        }
        for (int y = yStart + 1; y < yStart + height - 1; y = y + 1) {
            if (Tileset.NOTHING.equals(world[xStart][y])) {
                world[xStart][y] = Tileset.WALL;
            }
            if (Tileset.NOTHING.equals(world[xStart + width - 1][y])) {
                world[xStart + width - 1][y] = Tileset.WALL;
            }
        }
    }


    public static Room nextRandom(Random random, Room hallway, int xLower, int xUpper, int yUpper) {
        // y = hallway.yStart + height
        // y + height >= yUpper  STOP
        // x >= 0
        // x + width < xUpper
        // x <= hallway.x
        // x <= xUpper - MINWIDTH
        // width >= MINWIDTH
        // height >= MINHIGHT
        final int MINWIDTH = 4;
        final int MINHEIGHT = 4;
        final int xStartMin = Math.min(hallway.xStart,xUpper - MINWIDTH);
        final int xEndMin = hallway.xStart + hallway.width;
        int x = randomIntRange(random, xLower, xStartMin);
        int y = hallway.yStart + hallway.height - 2;

        int width = randomIntRange(random,xEndMin  - x , xUpper - x );

        int height = randomIntRange(random,MINHEIGHT , 20);
        if (y + height >= yUpper) {
            return null;
        }
        return new Room(x, y, width, height);
    }



}
