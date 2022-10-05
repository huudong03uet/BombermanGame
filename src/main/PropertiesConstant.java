package main;

public final class PropertiesConstant {


    /**
     * Title of the game.
     */
    public static String TITLE = "BombermanGame";

    /**
     * The size of the cell.
     */
    public static int TILE_SIZE = 16;

    /**
     * The scale of the game.
     */
    public static int SCALE = 3;

    /**
     * The size of the map according to the number of cells.
     */
    public static int WIDTH_TILE = 30;
    public static int HEIGHT_TILE = 13;

    /**
     * The size of the screen according to the number of pixels.
     */
    public static int WIDTH = 16 * TILE_SIZE * SCALE;
    public static int HEIGHT = 13 * TILE_SIZE * SCALE;


    /**
     * Frame per second
     */
    public static int FRAME_PER_SECOND = 60;


    /**
     * Direction of bomberman
     */
    public static int RIGHT = 0;
    public static int DOWN = 1;
    public static int LEFT = 2;
    public static int UP = 3;
    public static int STOP = 4;

    /**
     * The character of the map
     */
    public static char CHAR_BLANK = ' ';
    public static char CHAR_WALL = '#';
    public static char CHAR_BRICK = '*';
    public static char CHAR_PORTAL = 'x';
    public static char CHAR_BALLOOM = '1';
    public static char CHAR_ONEAL = '2';
    public static char CHAR_DOLL = '3';


    /**
     * The feature of the bomberman.
     */
    public static int SPEED_BOMBER = 2;
    public static int FRAME_PER_ONE_BOMBER = 12;
    public static int BOMBER_SPRITE = 3;
    public static int BOMBER_DEAD_SPRITE = 3;

    /**
     * The feature of the bomb.
     */
    public static int BOMB_SPRITE = 3;
    public static int FLAME_HORIZONTAL_SPRITE = 3;
    public static int FLAME_HORIZONTAL_LEFT = 3;
    public static int FLAME_HORIZONTAL_RIGHT = 3;
    public static int FLAME_VERTICAL_SPRITE = 3;
    public static int FLAME_VERTICAL_UP = 3;
    public static int FLAME_VERTICAL_DOWN = 3;
    public static final int FLAME_CENTER_SPRITE = 3;
    public static int TIME_REMAIN = 60;


    /**
     * The general feature of the enemy.
     */
    public static int SPEED_ENEMY = 1;


    /**
     * The feature of the balloom.
     */
    public static int BALLOOM_SPRITE = 3;

    /**
     * The feature of the oneal.
     */
    public static int ONEAL_SPRITE = 3;

    /**
     * The feature of the doll.
     */
    public static int DOLL_SPRITE = 3;
}
