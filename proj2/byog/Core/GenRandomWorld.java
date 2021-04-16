package byog.Core;
import byog.Core.Deque.LinkedListDeque;
import byog.Core.WorldParts.Room.HorHallway;
import byog.Core.WorldParts.Room.Room;
import byog.Core.WorldParts.Room.VerHallway;
import byog.Core.WorldParts.WorldCol;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


public class GenRandomWorld {
    private LinkedListDeque<WorldCol> WorldCols = new LinkedListDeque<>();
    private LinkedListDeque<HorHallway> HorHallways = new LinkedListDeque<>();

    private static TETile[][] genEmptyWorld(int w, int h) {
        TETile[][] world = new TETile[w][h];
        for (int x = 0; x < w; x += 1) {
            for (int y = 0; y < h; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    public GenRandomWorld(Random R, int w, int h) {
        WorldCol c = WorldCol.nextWorldCol(R, 0, w, h);
        while (c != null) {
            WorldCols.addLast(c);
            c = WorldCol.nextWorldCol(R, c.xLower + c.xUpper, w, h);
        }
        /*
        for (int i = 0; i < WorldCols.size() - 1; i = i + 1) {
            int hallwayX = WorldCols.get(i).hallway.xStart;
            int hallwayWidth = WorldCols.get(i + 1).hallway.xStart - hallwayX + 3;
            int hallwayY = 10 + RandomUtils.uniform(R,h - 20) ;
            HorHallway tempHallway = new HorHallway(hallwayX, hallwayY,hallwayWidth);
            tempHallway.connected = true;
            HorHallways.addLast(tempHallway);
        }
        */
    }

    public TETile[][] genWorld(int w, int h) {
        TETile[][] world = genEmptyWorld(w, h);
        /*
        for (int i = 0; i < HorHallways.size(); i = i + 1) {
            HorHallways.get(i).genHallway(world);
        }
        */
        for (int i = 0; i < WorldCols.size(); i = i + 1) {
            WorldCols.get(i).generateWorldCol(world);
        }

        return world;
    }

    public static void main(String[] args) throws InterruptedException {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        int WIDTH = 80;
        int HEIGHT = 30;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        Random R = new Random();
        for (int i = 0; i < 100; i = i + 1) {
            GenRandomWorld w = new GenRandomWorld(R,WIDTH,HEIGHT);
            TETile[][] world = w.genWorld(WIDTH,HEIGHT);
            ter.renderFrame(world);
            Thread.sleep(10000);
        }
    }

}
