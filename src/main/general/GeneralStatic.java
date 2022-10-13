package main.general;

import main.map.MapGame;
import main.settings.PropertiesStatic;

import static main.settings.StatusGame.*;

public class GeneralStatic {
    public static void changeLevel(int n) {
        MapGame.level = n;
        if(n == 1) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 0;
        } else if(n == 2) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 1;
        } else if(n == 3) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 3;
        } else if(n == 4) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 3;
        } else if(n == 5) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 5;
        }
        status = GAME_CHANGE_LEVEL;
    }
}
