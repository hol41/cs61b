package byog.Core.WorldParts.Room;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.Random;


public class RoomTest {
    @Test
    public  void nextRandomRoomTest() throws InterruptedException {
        int WIDTH = 50;
        int HEIGHT = 50;
        Random R= new Random();
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        for (int i = 0; i < 100; i = i + 1) {
            // initialize tiles
            TETile[][] world = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }

            HallwayVer testHallway = new HallwayVer(17,0, 5);
            HallwayVer bound1 = new HallwayVer(20, 0,50);
            Room test1 = Room.nextRandom(R,testHallway,0, 20,50);
            Room test2 = HallwayVer.nextRandom(R, test1, 0, 20, 50);
            // fills in a block 14 tiles wide by 4 tiles tall
            test2.print(world);
            test1.print(world);
            testHallway.print(world);
            bound1.print(world);

            // draws the world to the screen
            ter.renderFrame(world);
            Thread.sleep(500);
        }

    }

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

        Room testRoom = new Room(7, 10, 10, 5);
        Room testRoom2 = new Room( 9, 13, 10, 5);
        HallwayVer testHallway = new HallwayVer(8,7, 25);
        HallwayHor testHallway2 = new HallwayHor(3,20, 15);
        // fills in a block 14 tiles wide by 4 tiles tall
        testRoom.print(world);
        testHallway.print(world);
        testHallway2.print(world);
        testRoom2.print(world);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
