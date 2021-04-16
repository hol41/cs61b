package byog.Core.WorldParts;

import byog.Core.Deque.LinkedListDeque;
import byog.Core.RandomUtils;
import byog.Core.WorldParts.Room.Room;
import byog.Core.WorldParts.Room.VerHallway;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldCol {
    public int xLower;
    public int xUpper;
    public LinkedListDeque<Room> Rooms = new LinkedListDeque<>();
    public VerHallway hallway;
    public LinkedListDeque<int[]> intsect = new LinkedListDeque<>();


    public void generateWorldCol(TETile[][] world) {
        hallway.genHallway(world);
        for (int i = 0; i < Rooms.size(); i = i + 1) {
            Rooms.get(i).genRoom(world);
        }
        removeIntersect(intsect, world);
    }


    private static void removeIntersect(LinkedListDeque<int[]> intSect, TETile[][] world) {
        for (int i = 0; i < intSect.size(); i = i + 1) {
            int x = intSect.get(i)[0];
            int y = intSect.get(i)[1];
            world[x][y] = Tileset.FLOOR;
        }
    }

    public static WorldCol nextWorldCol (Random R, int xStart, int xUpper, int height) {

        int width = 5 + RandomUtils.uniform(R, 10);
        if (xStart + width >= xUpper) {
            return null;
        }
        xStart = xStart;
        return new WorldCol(R, width, height, xStart);
    }

    public WorldCol(Random R, int width, int height, int xStart) {
        xLower = xStart;
        xUpper = width;

        // generate random rooms
        Room r1 = Room.nextRandomRoom(R, xStart,width, 0, height);
        while (r1 != null) {
            Rooms.addLast(r1);
            r1 = Room.nextRandomRoom(R, xStart, width, r1.yStart + r1. height, height);
        }

        // generate random hallway
        int hallwayX = xStart + RandomUtils.uniform(R, width - 3);
        int hallwayY = RandomUtils.uniform(R, 5);
        int hallwayH = height - hallwayY - RandomUtils.uniform(R, 5) - 1;

        hallway = new VerHallway(hallwayX, hallwayY, hallwayH);
        hallway.connected = true;

        // intersection calculation
        for (int i = 0; i < Rooms.size(); i = i + 1) {
            Room r = Rooms.get(i);
            if (r.calIntersect1(hallway, intsect)) {
                r.connected = true;
            }
        }
    }
}
