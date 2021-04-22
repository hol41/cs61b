package byog.Core.Room;

import byog.Core.Deque.LinkedListDeque;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


public class Room {
    private int xStart;
    private int yStart;
    private int width;
    private int height;

    public Room(int x, int y, int w, int h) {
        xStart = x;
        yStart = y;
        width = w;
        height = h;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public int xStart() {
        return xStart;
    }

    public int yStart() {
        return yStart;
    }
    protected static int overlap(int s1, int e1, int s2, int e2) {
        s1 = Math.max(s1, 0);
        s2 = Math.max(s2, 0);
        for (int i = Math.min(s1, s2); i <= Math.max(e1, e2); i = i + 1) {
            if (i >= s1 && i <= e1 && i >= s2 && i <= e2) {
                return i;
            }
        }
        return -1;
    }

    private boolean contains(int x, int y) {
        if (x >= xStart && x <= xStart + width - 1 && y >= yStart && y <= yStart + height - 1) {
            return true;
        }
        return false;
    }

    public boolean isOverlapWithRoom(Room room) {
        if (room.contains(xStart, yStart)) {
            return true;
        }
        if (room.contains(xStart + width - 1, yStart)) {
            return true;
        }
        if (room.contains(xStart, yStart + height - 1)) {
            return true;
        }
        if (room.contains(xStart + width - 1, yStart + height - 1)) {
            return true;
        }
        return false;
    }

    public boolean isOverlapWithRooms(LinkedListDeque<Room> rooms) {
        for (int i = 0; i < rooms.size(); i = i + 1) {
            if (isOverlapWithRoom(rooms.get(i))) {
                return true;
            }
            if (rooms.get(i).isOverlapWithRoom(this)) {
                return true;
            }
        }
        return false;
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

    public int calRoomDistance(Room room) {
        return Math.abs(room.xStart - xStart) + Math.abs(room.yStart - yStart);
    }

    public void connectRoom(Room room, LinkedListDeque<Room> hallways) {
        int overlapX = overlap(room.xStart - 1, room.xStart + room.width - 2,
                xStart - 1, xStart + width - 2);
        if (overlapX >= 0) {
            hallways.addLast(HallwayVer.getNewHalwayVer(overlapX, yStart, room.yStart));
            return;
        }
        int overlapY = overlap(room.yStart - 1, room.yStart + room.height - 2,
                yStart - 1, yStart + height - 2);
        if (overlapY >= 0) {
            hallways.addLast(HallwayHor.getNewHalwayHor(overlapY, xStart, room.xStart));
            return;
        }

        hallways.addLast(HallwayVer.getNewHalwayVer(xStart, yStart, room.yStart));
        hallways.addLast(HallwayHor.getNewHalwayHor(room.yStart, xStart, room.xStart));

    }



}
