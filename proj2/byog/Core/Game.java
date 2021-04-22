package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Locale;


public class Game {
    public static final int WIDTH = 80 + 1;
    public static final int HEIGHT = 30;
    public static final String SAVEPATH = "save.txt";
    private int gamePhase;
    private String seedStr;
    private long seed;
    private char mode;
    private GameWorld gameWorld;

    public Game() {

    }

    private void gamePhaseOne() {
        StdDraw.setCanvasSize(this.WIDTH * 16, HEIGHT * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
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
            gameWorld = loadWorld();
            gamePhase = gamePhase + 1;
        } else {
            gamePhase = gamePhase + 1;
            StdDraw.clear();
        }
    }

    private void gamePhaseThree() {
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
                if (tempKey == 'w') {
                    gameWorld.moveUp();
                } else if (tempKey == 's') {
                    gameWorld.moveDown();
                } else if (tempKey == 'a') {
                    gameWorld.moveLeft();
                } else if (tempKey == 'd') {
                    gameWorld.moveRight();
                } else if (tempKey == ':') {
                    trigger = true;
                }
            }
            drawWorld(gameWorld.getWorld(), mouseX, mouseY);
        }
    }

    private void gameWithString(String playStr) {
        boolean trigger = false;
        for (int i = 0; i < playStr.length(); i = i + 1) {
            char tempKey = playStr.charAt(i);
            if (trigger && tempKey == 'Q') {
                saveWorld(gameWorld);
                break;
            }
            trigger = false;
            if (tempKey == 'W') {
                gameWorld.moveUp();
            } else if (tempKey == 'S') {
                gameWorld.moveDown();
            } else if (tempKey == 'A') {
                gameWorld.moveLeft();
            } else if (tempKey == 'D') {
                gameWorld.moveRight();
            } else if (tempKey == ':') {
                trigger = true;
            }
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
        TETile block = world[Math.min((int) Math.floor(mouseX), WIDTH - 1)]
                [Math.min((int) Math.floor(mouseY), HEIGHT - 1)];
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

    private void saveWorld(GameWorld g) {
        try {
            ObjectOutputStream out;
            FileOutputStream fileOut = new FileOutputStream(SAVEPATH);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(g);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GameWorld loadWorld() {
        ObjectInputStream in = null;
        try {
            FileInputStream fileIn = new FileInputStream(SAVEPATH);
            in = new ObjectInputStream(fileIn);
            GameWorld world = (GameWorld) in.readObject();
            in.close();
            fileIn.close();
            return world;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        Game a = new Game();
        a.gamePhaseOne();
        a.gamePhaseTwo();
        a.gamePhaseThree();
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
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        input = input.toUpperCase(Locale.ROOT);

        int index = 1;
        if (input.charAt(0) == 'N') {
            seedStr = "";
            while (input.charAt(index) != 'S') {
                seedStr = seedStr + input.charAt(index);
                index = index + 1;
            }
            index = index + 1;
            seed = Long.parseLong(seedStr);
            gameWorld = new GameWorld(Long.parseLong(seedStr));
        } else {
            gameWorld = loadWorld();
        }

        String playString = "";
        for (int i = index; i < input.length(); i = i + 1) {
            playString = playString + input.charAt(i);
        }
        gameWithString(playString);
        return gameWorld.getWorld();
    }
}
