package byog.Core.WorldParts.Room;

import byog.Core.Deque.LinkedListDeque;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import static org.junit.Assert.*;


public class RoomTest {
    @Test
    public void calIntersectTest() {
        LinkedListDeque<int[]> intSect = new LinkedListDeque<>();
        Room room1 = new Room(0, 0, 10 ,10);
        VerHallway hallway1 = new VerHallway(0, 0 ,20);
        room1.calIntersect1(hallway1, intSect);
        int[] expect1 = new int[]{1, 9};
        assertArrayEquals(intSect.get(0), expect1);

        Room room2 = new Room(0, 1, 10 ,10);
        VerHallway hallway2 = new VerHallway(0, 0 ,20);
        room2.calIntersect1(hallway2, intSect);
        int[] expect2 = new int[]{1, 1};
        int[] expect3 = new int[]{1, 10};
        assertArrayEquals(intSect.get(1), expect2);
        assertArrayEquals(intSect.get(2), expect3);

        Room room3 = new Room(0, 10, 10 ,10);
        VerHallway hallway3 = new VerHallway(0, 0 ,20);
        room3.calIntersect1(hallway3, intSect);
        int[] expect4 = new int[]{1, 10};
        assertArrayEquals(intSect.get(3), expect4);

        Room room4 = new Room(0, 5, 10 ,10);
        VerHallway hallway4 = new VerHallway(8, 0 ,20);
        room4.calIntersect1(hallway4, intSect);
        assertEquals(intSect.size(), 4);

        Room room5 = new Room(0, 5, 10 ,10);
        VerHallway hallway5 = new VerHallway(7, 0 ,20);
        room5.calIntersect1(hallway5, intSect);
        int[] expect5 = new int[]{8, 5};
        int[] expect6 = new int[]{8, 14};
        assertArrayEquals(intSect.get(4), expect5);
        assertArrayEquals(intSect.get(5), expect6);
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
        LinkedListDeque<int[]> intSect = new LinkedListDeque<>();

        Room testRoom = new Room(7, 10, 10, 5);
        VerHallway testHallway = new VerHallway(7,7, 20);
        // fills in a block 14 tiles wide by 4 tiles tall
        testHallway.genHallway(world);
        testRoom.genRoom(world);
        testRoom.calIntersect1(testHallway, intSect);


        // draws the world to the screen
        ter.renderFrame(world);
    }
}
