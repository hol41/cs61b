package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static void addHexagon(int s, int x, int y, TETile[][] world) {
        int[][] hex = genHexMatrix(s);
        for (int i = 0; i < hex.length; i = i + 1) {
            for (int j = 0; j < hex[0].length; j = j + 1) {
                replaceItem(hex[i][j], x + i, y + j, world, Tileset.WALL);
            }
        }
    }

    public static int[][] genHexMatrix(int s) {
        int[][] hex = new int[3 * s - 2][s * 2];
        int j = 0;
        for (int i = 2; i <= 2 * s; i = i + 2) {
            hex[j] =  midItemGen(s * 2, i);
            j = j + 1;
        }
        for (int i = 0; i < s - 2; i = i + 1) {
            hex[j] = midItemGen(s * 2, s * 2);
            j = j + 1;
        }
        for (int i = 2 * s; i >= 2; i = i - 2) {
            hex[j] =  midItemGen(s * 2, i);
            j = j + 1;
        }
        return hex;
    }


    public static int[] midItemGen(int length, int n) {
        int nothing = (length - n) / 2;
        int[] res = new int[length];
        for (int i = 0; i < length; i = i + 1) {
            if (i < nothing || i >= length - nothing ) {
                res[i] = 0;
            }
            else {
                res[i] = 1;
            }
        }
        return res;
    }

    public static void replaceItem(int i, int x, int y,TETile[][] world, TETile T) {
        if (i == 0) {
            return;
        }
        world[x][y] = T;
    }

    @Test
    public void midItemGenTest() {
        int[] expect1 = {0,0,1,1,0,0};
        int[] actual1 = midItemGen(6, 2);
        assertArrayEquals(expect1, actual1);
        int[] expect2 = {0,0,0,0};
        int[] actual2 = midItemGen(4, 0);
        assertArrayEquals(expect2, actual2);
        int[] expect3 = {0,0,1,1,1,1,1,0,0};
        int[] actual3 = midItemGen(9, 5);
        assertArrayEquals(expect3, actual3);
    }

    @Test
    public void genHexMatrixTest() {
        int[][] expect1 = {{0,1,1,0},{1,1,1,1},{1,1,1,1},{0,1,1,0}};
        int[][] actual1 = genHexMatrix(2);
        assertArrayEquals(expect1, actual1);
        int[][] expect2 = {{0,0,1,1,0,0},{0,1,1,1,1,0},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{0,1,1,1,1,0},{0,0,1,1,0,0}};
        int[][] actual2 = genHexMatrix(3);
        assertArrayEquals(expect2, actual2);
        int[][] expect3 = {{1,1},{1,1}};
        int[][] actual3 = genHexMatrix(1);
        assertArrayEquals(expect1, actual1);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(60, 60);

        // initialize tiles
        TETile[][] world = new TETile[60][60];
        for (int x = 0; x < 60; x += 1) {
            for (int y = 0; y < 60; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        addHexagon(8, 30, 30, world);
        ter.renderFrame(world);

    }
}
