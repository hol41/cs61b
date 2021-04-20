package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.Random;

public class Game {
    public static final int WIDTH = 80 + 1;
    public static final int HEIGHT = 30;
    public static final String SAVEPATH = "byog/Core/data/save.dat";
    private int gamePhase;
    private String seedStr;
    private long seed;
    private char mode;
    private GameWorld gameWorld;

    public Game() {
        StdDraw.setCanvasSize(this.WIDTH * 16, HEIGHT * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        }

    private void gamePhaseOne() {
        while (gamePhase == 0) {
            drawModeSelect();
            if (StdDraw.hasNextKeyTyped()) {
                char tempKey = StdDraw.nextKeyTyped();
                if (tempKey == 'q' || tempKey == 'l' || tempKey == 'n') {
                    mode = tempKey;
                    StdDraw.pause(500);
                    gamePhase = gamePhase + 1;
                }
            }
        }

    }

    private void gamePhaseTwo() {
        seedStr = "";
        if (mode == 'n') {
            while (gamePhase == 1) {
                drawSeedEnter();
                if (StdDraw.hasNextKeyTyped()) {
                    char tempKey = StdDraw.nextKeyTyped();
                    if (tempKey == 's') {
                        seed = Integer.parseInt(seedStr);
                        gamePhase = gamePhase + 1;
                    } else {
                        seedStr = seedStr + tempKey;
                    }
                }
            }
            gameWorld = new GameWorld(seed);
        } else if (mode == 'l') {
            seed = 1111111;
            gameWorld = new GameWorld(seed);
            gamePhase = gamePhase + 1;
        } else {
            gamePhase = gamePhase + 1;
            StdDraw.clear();
        }
    }

    private void gamePhaseThree() throws IOException, ClassNotFoundException {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        boolean trigger = false;
        while (gamePhase == 2) {
            StdDraw.clear();
            Double mouseX = StdDraw.mouseX();
            Double mouseY = StdDraw.mouseY();
            if (StdDraw.hasNextKeyTyped()) {
                char tempKey = StdDraw.nextKeyTyped();
                if (trigger && tempKey == 'q') {
                    gamePhase = gamePhase + 1;
                    saveWorld(gameWorld);
                }
                trigger = false;
                if (tempKey == 'w') {gameWorld.moveUp();}
                else if (tempKey == 's') {gameWorld.moveDown();}
                else if (tempKey == 'a') {gameWorld.moveLeft();}
                else if (tempKey == 'd') {gameWorld.moveRight();}
                else if (tempKey == ':') {trigger = true;}
            }
            drawWorld(gameWorld.getWorld(),mouseX, mouseY);
        }
    }

    private static void drawModeSelect() {
        int midWidth = WIDTH / 2;
        int midHeight = HEIGHT / 2;
        StdDraw.clear();
        StdDraw.clear(Color.black);

        StdDraw.setPenColor(Color.white);
        Font titleFont = new Font("Monaco", Font.PLAIN, 30);
        StdDraw.setFont(titleFont);
        StdDraw.text(midWidth, HEIGHT - 10, "CS61B: THE GAME");

        Font optionFont = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(optionFont);
        StdDraw.text(midWidth, midHeight, "New Game (N)");
        StdDraw.text(midWidth, midHeight - 2, "Load Game (L)");
        StdDraw.text(midWidth, midHeight - 4, " Quit (Q)");
        StdDraw.show();
    }

    private void drawSeedEnter() {
        int midWidth = WIDTH / 2;
        int midHeight = HEIGHT / 2;
        StdDraw.clear();
        StdDraw.clear(Color.black);

        StdDraw.setPenColor(Color.white);
        Font titleFont = new Font("Monaco", Font.PLAIN, 30);
        StdDraw.setFont(titleFont);
        StdDraw.text(midWidth, HEIGHT - 10, "Please Enter Seed:");

        Font optionFont = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(optionFont);
        StdDraw.text(midWidth, midHeight, seedStr);

        StdDraw.show();
    }

    private void drawWorld(TETile[][] world, double mouseX, double mouseY) {
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        StdDraw.clear(new Color(0, 0, 0));
        for (int x = 0; x < numXTiles; x += 1) {
            for (int y = 0; y < numYTiles; y += 1) {
                world[x][y].draw(x, y);
            }
        }

        StdDraw.setPenColor(Color.white);
        TETile block = world[Math.min((int)Math.floor(mouseX),WIDTH - 1)][Math.min((int)Math.floor(mouseY),HEIGHT -1)];
        if (block.equals(Tileset.NOTHING)) {
            StdDraw.textLeft(0, HEIGHT - 0.5, "Outdoor space");
        } else if (block.equals(Tileset.PLAYER)) {
            StdDraw.textLeft(0, HEIGHT - 0.5, "Player");
        } else if (block.equals(Tileset.FLOWER)) {
            StdDraw.textLeft(0, HEIGHT - 0.5, "Flower");
        } else if (block.equals(Tileset.WALL)) {
            StdDraw.textLeft(0, HEIGHT - 0.5, "Wall");
        } else {
            StdDraw.textLeft(0, HEIGHT - 0.5, "Floor");
        }
        if (gameWorld.ifWin) {
            StdDraw.textLeft(10, HEIGHT - 0.5, "You Win!");
        } else {
            StdDraw.textLeft(10, HEIGHT - 0.5, "Keep Going");
        }

        StdDraw.show();
    }

    private void saveWorld(GameWorld g) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVEPATH));
        out.writeObject(g);
        out.close();
    }


    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
            }
        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        TETile[][] finalWorldFrame;

        if (input.charAt(0) == 'N') {
            String seedStr = "";
            for (int i = 1; input.charAt(i) != 'S'; i = i + 1) {
                seedStr = seedStr + input.charAt(i);
            }
            int seed = Integer.parseInt(seedStr);
            GameWorld world = new GameWorld(seed);
            finalWorldFrame = world.getWorld();

        }
        else {
            finalWorldFrame = null;
        }
        return finalWorldFrame;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Game a = new Game();
        a.gamePhaseOne();
        a.gamePhaseTwo();
        a.gamePhaseThree();
    }
}
