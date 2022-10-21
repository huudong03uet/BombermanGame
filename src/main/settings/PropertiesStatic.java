package main.settings;

import static main.settings.PropertiesConstant.HEIGHT_TILE;
import static main.settings.PropertiesConstant.WIDTH_TILE;

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
    public static final int DIRECTION_DEFAULT = 4;
    // 0: right
    // 1: down
    // 2: left
    // 3: up

    public static boolean placeBomb = false;
    public final static boolean PLACE_BOMB_DEFAULT = false;
    public static int lengthFlameDefault = 1;
    public final static int LENGTH_FLAME_DEFAULT = 1;
    public static int numberPass = 0;
    public static int NUMBER_PASS_DEFAULT = 0;
    public static int numberBombDefault = 1;
    public final static int NUMBER_BOMB_DEFAULT = 1;

    public static int countBombMax = 1;
    public final static int COUNT_BOMB_MAX_DEFAULT = 0;

    public static int score = 0;
    public static int timeRemain = 0;
    public static int lifeBomber = 3;

    public static void setSettingGameDefault() {
        direction = DIRECTION_DEFAULT;
        placeBomb = PLACE_BOMB_DEFAULT;
        lengthFlameDefault = LENGTH_FLAME_DEFAULT;
        numberPass = 0;
        numberBombDefault = NUMBER_BOMB_DEFAULT;
        countBombMax = COUNT_BOMB_MAX_DEFAULT;
    }
}
