package main.settings;

public class StatusGame {
    public static final int GAME_SETTING_MENU = -1;
    public static final int GAME_MENU = 0;
    public static final int GAME_PLAY = 1;
    public static final int GAME_PAUSE = 2; // Ctrl + P
    public static final int GAME_OVER = 3;
    public static final int GAME_WIN = 4;
    public static final int GAME_EXIT = 5; // Alt + F4 or Ctrl + W
    public static final int GAME_RESTART_GAME = 6;
    public static final int GAME_TRAINING = 10;
    public static final int GAME_LEVEL = 12;
    public static boolean isSurvival = false;

    /**
     * Change level:
     * Shift + 1, 2, 3, 4, 5
     */
    public static final int GAME_RESTART_LEVEL = 7;

    public static final int GAME_CHANGE_LEVEL = 8;
    public static final int GAME_CHANGE_DIFFICULTY = 9;

    public static int status = GAME_SETTING_MENU;
}

