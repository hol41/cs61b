package byog.Core.WorldParts.Room;

import byog.Core.Deque.LinkedListDeque;
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

    public void genRoom(TETile[][] world) {
        if (!connected) {
            return;
        }

        for (int x = xStart + 1; x < xStart + width - 1; x = x + 1) {
            for (int y = yStart; y < yStart + height - 1; y = y + 1) {
                world[x][y] = Tileset.FLOOR;
            }
        }
        for (int x = xStart; x < xStart + width; x = x + 1) {
            world[x][yStart] = Tileset.WALL;
            world[x][yStart + height - 1] = Tileset.WALL;
        }
        for (int y = yStart + 1; y < yStart + height - 1; y = y + 1) {
            world[xStart][y] = Tileset.WALL;
            world[xStart + width - 1][y] = Tileset.WALL;
        }
    }


    public static Room nextRandomRoom(Random random, int xLower, int xUpper, int yLower, int yUpper) {
        final int MINWIDTH = 4;
        final int MINHEIGHT = 4;
        final int YINREMENT = 5;
        System.out.println();
        int x = RandomUtils.uniform(random, xUpper - MINWIDTH);
        int y = yLower + RandomUtils.uniform(random, YINREMENT);

        int width = MINWIDTH + RandomUtils.uniform(random, xUpper - x - MINHEIGHT);
        int height = MINHEIGHT + RandomUtils.uniform(random,xUpper - x - MINHEIGHT );
        if (y + height >= yUpper) {
            return null;
        }
        return new Room(x + xLower , y, width, height);
    }


    public boolean calIntersect1(VerHallway h, LinkedListDeque<int[]> intersections) {
        int[] res1 = new int[2];
        int[] res2 = new int[2];
        if (xStart <= h.xStart && h.xStart <= xStart + width - 3) {
            if (h.yStart < yStart) {
                res1[0] = h.xStart + 1;
                res1[1] = yStart;
                intersections.addLast(res1);
            }
            if (h.yStart + h.height > yStart + height) {
                res2[0] = h.xStart + 1;
                res2[1] = yStart + height - 1;
                intersections.addLast(res2);
            }
            return true;
        }
        return false;
    }


}
