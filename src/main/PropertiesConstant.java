package main;

public final class PropertiesConstant {


    /**
     * Title of the game.
     */
    public final static String TITLE = "BombermanGame";

    /**
     * The size of the cell.
     */
    public final static int TILE_SIZE = 16;

    /**
     * The scale of the game.
     */
    public final static int SCALE = 3;

    /**
     * The size of the map according to the number of cells.
     */
    public final static int WIDTH_TILE = 30;
    public final static int HEIGHT_TILE = 13;

    /**
     * The size of the screen according to the number of pixels.
     */
    public final static int WIDTH = 16 * TILE_SIZE * SCALE;
    public final static int HEIGHT = 13 * TILE_SIZE * SCALE;


    /**
     * Frame per second
     */
    public final static int FRAME_PER_SECOND = 60;


    /**
     * Direction of bomberman
     */
    public final static int RIGHT = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int UP = 3;
    public final static int STOP = 4;

    /**
     * The character of the map
     */
    public final static char CHAR_BLANK = ' ';
    public final static char CHAR_GRASS = ' ';

    public final static char CHAR_WALL = '#';
    public final static char CHAR_BRICK = '*';
    public final static int BRICK_EXPLOSION = 3;
    public final static char CHAR_PORTAL = 'x';
    public final static char CHAR_BALLOOM = '1';
    public final static char CHAR_ONEAL = '2';
    public final static char CHAR_DOLL = '3';
    public final static char CHAR_MINVO = '4';
    public final static char CHAR_PASS = '5';
    public final static char CHAR_GHOST = '5';
    public final static char CHAR_KONDORIA = '6';
    /**
     * The character of the item.
     */
    public final static char SPEED_ITEM = 's';

    public final static char FLAME_ITEM = 'f';

    public final static char FLAME_PASS_ITEM = 't';
    public final static String USING = " #*xbodmkg   sft";
    public static boolean isHavingFlame = false;
    /**
     * The feature of the bomberman.
     */
    public final static int SPEED_BOMBER = 2;
    public final static int FRAME_PER_ONE_BOMBER = 12;
    public final static int BOMBER_SPRITE = 3;
    public final static int BOMBER_DEAD_SPRITE = 3;

    /**
     * The feature of the bomb.
     */
    public final static int BOMB_SPRITE = 3;
    public final static int FLAME_HORIZONTAL_SPRITE = 3;
    public final static int FLAME_HORIZONTAL_LEFT = 3;
    public final static int FLAME_HORIZONTAL_RIGHT = 3;
    public final static int FLAME_VERTICAL_SPRITE = 3;
    public final static int FLAME_VERTICAL_UP = 3;
    public final static int FLAME_VERTICAL_DOWN = 3;
    public final static int FLAME_CENTER_SPRITE = 3;
    public final static int TIME_REMAIN = 30;


    /**
     * The general feature of the enemy.
     */
    public final static int SPEED_ENEMY = 1;
    public final static int TIME_EXPLOYED = 60;


    /**
     * The feature of the balloom.
     */
    public final static int BALLOOM_SPRITE = 3;


    /**
     * The feature of the oneal.
     */
    public final static int ONEAL_SPRITE = 3;

    /**
     * The feature of the doll.
     */
    public final static int DOLL_SPRITE = 3;

/**
 * The feature of the ghost
 */
    public final static int GHOST_SPRITE = 3;
    public final static int TIME_HIDDEN_GHOST = 60 * 5;
    public final static int DISTANCE_HIDDEN_GHOST = TILE_SIZE * SCALE * 2;
    /**
     * The feature of the portal.
     */

    /**
     * The feature of the pass.
     */
    public final static int NUMBER_PASS_MAX = 3;

    /**
     * The feature of the Kondoria.
     */
    public final static int KONDORIA_SPRITE = 3;
    public final static int DISTANCE_SPEED_KONDORIA = TILE_SIZE * SCALE * 4;
}
