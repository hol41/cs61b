package byog.Core.WorldParts.Room;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class VerHallwayTest {
    public static void main(String[] args) {
        int WIDTH = 50;
        int HEIGHT = 50;
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        VerHallway testHallway = new VerHallway(7, 7, 20);
        // fills in a block 14 tiles wide by 4 tiles tall
        testHallway.genHallway(world);
        // draws the world to the screen
        ter.renderFrame(world);
    }
}
