package byog.Core;
import byog.Core.Deque.LinkedListDeque;
import byog.Core.Room.Room;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.Random;


public class GameWorld implements Serializable {
    final int WIDTH = 80;
    final int HEIGHT = 30;

    private int[][] intWorld;
    boolean ifWin;
    private int peopleX;
    private int peopleY;
    private int flowerX;
    private int flowerY;

    // 0 - NOTHING;
    // 1 - WALL;
    // 2 - FlOOR;
    // 3 - PLAYER;
    // 4 - FLOWER;
    private static int[][] toIntWorld(TETile[][] world) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        int[][] intWorld = new int[numXTiles][numYTiles];
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y].equals(Tileset.NOTHING)) {
                    intWorld[x][y] = 0;
                } else if (world[x][y].equals(Tileset.WALL)) {
                    intWorld[x][y] = 1;
                } else if (world[x][y].equals(Tileset.FLOOR)) {
                    intWorld[x][y] = 2;
                } else if (world[x][y].equals(Tileset.PLAYER)) {
                    intWorld[x][y] = 3;
                } else {
                    intWorld[x][y] = 4;
                }
            }
        }
        return intWorld;
    }

    public TETile[][] getWorld() {
        int numXTiles = intWorld.length;
        int numYTiles = intWorld[0].length;
        TETile[][] world = new TETile[numXTiles][numYTiles];
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                if (intWorld[x][y] == 0) {
                    world[x][y] = Tileset.NOTHING;
                } else if (intWorld[x][y] == 1) {
                    world[x][y] = Tileset.WALL;
                } else if (intWorld[x][y] == 2) {
                    world[x][y] = Tileset.FLOOR;
                } else if (intWorld[x][y] == 3) {
                    world[x][y] = Tileset.PLAYER;
                } else {
                    world[x][y] = Tileset.FLOWER;
                }
            }
        }
        return world;
    }

    public void moveUp() {
        if (intWorld[peopleX][peopleY + 1] == 2) {
            intWorld[peopleX][peopleY] = 2;
            intWorld[peopleX][peopleY + 1] = 3;
            peopleY = peopleY + 1;
        } else if (intWorld[peopleX][peopleY + 1] == 4) {
            intWorld[peopleX][peopleY] = 2;
            intWorld[peopleX][peopleY + 1] = 3;
            peopleY = peopleY + 1;
            ifWin = true;
        }
    }

    public void moveDown() {
        if (intWorld[peopleX][peopleY - 1] == 2) {
            intWorld[peopleX][peopleY] = 2;
            intWorld[peopleX][peopleY - 1] = 3;
            peopleY = peopleY - 1;
        } else if (intWorld[peopleX][peopleY - 1] == 4) {
            intWorld[peopleX][peopleY] = 2;
            intWorld[peopleX][peopleY - 1] = 3;
            peopleY = peopleY - 1;
            ifWin = true;
        }
    }

    public void moveLeft() {
        if (intWorld[peopleX - 1][peopleY] == 2) {
            intWorld[peopleX][peopleY] = 2;
            intWorld[peopleX - 1][peopleY] = 3;
            peopleX = peopleX - 1;
        } else if (intWorld[peopleX - 1][peopleY] == 4) {
            intWorld[peopleX][peopleY] = 2;
            intWorld[peopleX - 1][peopleY] = 3;
            peopleX = peopleX - 1;
            ifWin = true;
        }
    }

    public void moveRight() {
        if (intWorld[peopleX + 1][peopleY] == 2) {
            intWorld[peopleX][peopleY] = 2;
            intWorld[peopleX + 1][peopleY] = 3;
            peopleX = peopleX + 1;
        } else if (intWorld[peopleX + 1][peopleY] == 4) {
            intWorld[peopleX][peopleY] = 2;
            intWorld[peopleX + 1][peopleY] = 3;
            peopleX = peopleX + 1;
            ifWin = true;
        }
    }

    protected static int randomIntRange(Random R, int lower, int upper) {
        if (upper - lower == 0) {
            return lower;
        }
        return lower + RandomUtils.uniform(R, upper - lower);
    }

    private static TETile[][] genEmptyWorld(int w, int h) {
        TETile[][] world = new TETile[w][h];
        for (int x = 0; x < w; x += 1) {
            for (int y = 0; y < h; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    private static Room findMinDisRoom(Room R, LinkedListDeque<Room> rooms) {
        int minIndex = 0;
        int minDis = R.calRoomDistance(rooms.get(0));
        for (int i = 0; i < rooms.size(); i = i + 1) {
            if (R.calRoomDistance(rooms.get(i)) < minDis) {
                minDis = R.calRoomDistance(rooms.get(i));
                minIndex = i;
            }
        }
        return rooms.pop(minIndex);
    }

    private static void connectRooms(LinkedListDeque<Room> rooms, LinkedListDeque<Room> hallways) {
        Room r = rooms.removeFirst();
        while (!rooms.isEmpty()) {
            Room preR = r;
            r = findMinDisRoom(r, rooms);
            preR.connectRoom(r, hallways);
        }
    }

    private static void printRooms(TETile[][] world, LinkedListDeque<Room> rooms) {
        for (int i = 0; i < rooms.size(); i = i + 1) {
            rooms.get(i).print(world);
        }
    }

    private static LinkedListDeque<Room> genRandomRooms(Random R, int width, int height) {
        final int minwidth = 4;
        final int minheight = 4;
        final int maxwidth = 15;
        final int maxheight = 15;
        final int nrooms = randomIntRange(R, 15, 25);
        LinkedListDeque<Room> rooms = new LinkedListDeque<>();
        for (int i = 0; i <= nrooms; i = i + 1) {
            Room tempRoom = new Room(randomIntRange(R, 0,  width - minwidth),
                    randomIntRange(R, 0, height - minheight),
                    randomIntRange(R, minwidth, maxwidth),
                    randomIntRange(R, minheight, maxheight));
            while (tempRoom.isOverlapWithRooms(rooms)
                    || tempRoom.xStart() + tempRoom.width() >= width
                    || tempRoom.yStart() + tempRoom.height() >= height) {
                tempRoom = new Room(randomIntRange(R, 0, width - minwidth),
                        randomIntRange(R, 0, height - minheight),
                        randomIntRange(R, minwidth, maxwidth),
                        randomIntRange(R, minheight, maxheight));
            }
            rooms.addLast(tempRoom);
        }
        return rooms;
    }

    private void genPeopleInRoom(LinkedListDeque<Room> rooms, Random R) {
        peopleX = randomIntRange(R, rooms.get(1).xStart() + 1,
                rooms.get(1).xStart() + rooms.get(1).width() - 2);
        peopleY = randomIntRange(R,  rooms.get(1).yStart() + 1,
                rooms.get(1).yStart() + rooms.get(1).height() - 2);
    }

    private void genFlowerInRoom(LinkedListDeque<Room> rooms, Random R) {
        int n = rooms.size() - 1;
        flowerX = randomIntRange(R, rooms.get(n).xStart() + 1,
                rooms.get(n).xStart() + rooms.get(n).width() - 2);
        flowerY = randomIntRange(R, rooms.get(n).yStart() + 1,
                rooms.get(n).yStart() + rooms.get(n).height() - 2);
    }

    public GameWorld(long seed) {
        Random R = new Random(seed);
        TETile[][] world;
        ifWin = false;
        world = genEmptyWorld(this.WIDTH, this.HEIGHT);
        LinkedListDeque<Room> hallways = new LinkedListDeque<>();
        LinkedListDeque<Room> rooms = GameWorld.genRandomRooms(R, WIDTH, HEIGHT);

        genFlowerInRoom(rooms, R);
        genPeopleInRoom(rooms, R);
        printRooms(world, rooms);
        connectRooms(rooms, hallways);
        printRooms(world, hallways);
        world[peopleX][peopleY] = Tileset.PLAYER;
        world[flowerX][flowerY] = Tileset.FLOWER;

        intWorld = toIntWorld(world);
    }
}
