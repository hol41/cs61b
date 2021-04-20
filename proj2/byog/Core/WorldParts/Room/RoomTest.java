package byog.Core.WorldParts.Room;

import byog.Core.Deque.LinkedListDeque;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.Random;


public class RoomTest {
    @Test
    public void HallwayVerTest() throws InterruptedException {
        int WIDTH = 50;
        int HEIGHT = 50;
        Random R = new Random();
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

            HallwayVer test = HallwayVer.getNewHalwayVer(0, 4, 0);
            test.print(world);
            // draws the world to the screen
            ter.renderFrame(world);
            Thread.sleep(200);
        }
    }

    @Test
    public  void conectRoomTest() throws InterruptedException {
        int WIDTH = 50;
        int HEIGHT = 50;
        Random R= new Random();
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        for (int i = 0; i < 50; i = i + 1) {
            // initialize tiles
            TETile[][] world = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }

            Room room1 = new Room(10,5, 5, 5);
            Room room2 = new Room(i,0, 5, 5);
            LinkedListDeque<Room> hallways = new LinkedListDeque<>();
            room2.connectRoom(room1, hallways);
            room1.print(world);
            room2.print(world);
            for (int j = 0; j < hallways.size(); j = j + 1) {
                hallways.get(j).print(world);
            }

            // draws the world to the screen
            ter.renderFrame(world);
            Thread.sleep(500);
        }

    }

    @Test
    public  void isOverlapRoomsTest() throws InterruptedException {
        int WIDTH = 50;
        int HEIGHT = 50;
        Random R= new Random();
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        for (int i = 0; i < 50; i = i + 1) {
            // initialize tiles
            TETile[][] world = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    world[x][y] = Tileset.NOTHING;
                }
            }
            Room TestRoom = new Room(i, 4, 7 ,7);
            Room mark = new Room(40, 40, 4,4);
            LinkedListDeque<Room> rooms = new LinkedListDeque<>();
            rooms.addLast(new Room(0,0, 5, 5));
            rooms.addLast(new Room(10,1, 5, 5));
            rooms.addLast(new Room(0,10, 5, 5));
            rooms.addLast(new Room(10,9, 5, 5));

            for (int j = 0; j < rooms.size(); j = j + 1) {
                rooms.get(j).print(world);
            }
            TestRoom.print(world);

            if (TestRoom.isOverlapWithRooms(rooms)) {
                mark.print(world);
            }
            // draws the world to the screen
            ter.renderFrame(world);
            Thread.sleep(500);
        }

    }

}
