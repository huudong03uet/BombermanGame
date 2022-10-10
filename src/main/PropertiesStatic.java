package main;

import static main.PropertiesConstant.HEIGHT_TILE;
import static main.PropertiesConstant.WIDTH_TILE;

public class PropertiesStatic {
    /**
     * Coordinate to subtract from the position of the bomberman.
     * * The camera will follow the bomberman.
     */
    public static int xHide = 0;
    public final static int yHide = 0; // the height of the map is fixed

    /**
     * The map of the game.
     */
    public static char[][] map = new char[HEIGHT_TILE][WIDTH_TILE];
    public static char[][] mapFile = new char[HEIGHT_TILE][WIDTH_TILE];
    /**
     * Direction of bomberman.
     */
    public static int direction = 4;
    // 0: right
    // 1: down
    // 2: left
    // 3: up

    public static boolean placeBomb = false;
    public static int lengthFlameDefault = 1;
    public static int numberPass = 0;
    public static int numberBombDefault = 1;
}
