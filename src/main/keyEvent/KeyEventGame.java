package main.keyEvent;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import main.general.GeneralStatic;

import static main.settings.PropertiesConstant.*;
import static main.settings.PropertiesStatic.direction;
import static main.settings.PropertiesStatic.placeBomb;
import static main.settings.StatusGame.*;

public class KeyEventGame {

    private EventHandler keyEventGame;
    private EventHandler keyEventGame1;
    KeyCode keyCode = null;

    KeyCombination keyCombinationNewGame = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    KeyCombination keyCombinationPause = new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN);
    KeyCombination keyCombinationExit = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
    KeyCombination keyCombinationExit1 = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN );

    KeyCombination keyCombinationChangeLevel1 = new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.SHIFT_DOWN);
    KeyCombination keyCombinationChangeLevel2 = new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.SHIFT_DOWN);
    KeyCombination keyCombinationChangeLevel3 = new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.SHIFT_DOWN);
    KeyCombination keyCombinationChangeLevel4 = new KeyCodeCombination(KeyCode.DIGIT4, KeyCombination.SHIFT_DOWN);
    KeyCombination keyCombinationChangeLevel5 = new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.SHIFT_DOWN);

    public KeyEventGame() {


        keyEventGame = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    keyCode = KeyCode.RIGHT;
                    direction = RIGHT;
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    keyCode = KeyCode.DOWN;
                    direction = DOWN;
                } else if (keyEvent.getCode() == KeyCode.LEFT) {
                    keyCode = KeyCode.LEFT;
                    direction = LEFT;
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    keyCode = KeyCode.UP;
                    direction = UP;
                }
                if(keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
                    placeBomb = true;
                }


                if (keyCombinationNewGame.match(keyEvent)) {
                    status = GAME_RESTART_LEVEL;
                }

                if (keyCombinationPause.match(keyEvent)) {
                    if (status == GAME_PLAY) {
                        status = GAME_PAUSE;
                    } else if (status == GAME_PAUSE) {
                        status = GAME_PLAY;
                    }
                }
                if(keyCombinationExit.match(keyEvent) || keyCombinationExit1.match(keyEvent)) {
                    status = GAME_EXIT;
                }

                if(keyCombinationChangeLevel1.match(keyEvent)) {
                    GeneralStatic.changeLevel(1);
                }
                if(keyCombinationChangeLevel2.match(keyEvent)) {
                    GeneralStatic.changeLevel(2);
                }
                if(keyCombinationChangeLevel3.match(keyEvent)) {
                    GeneralStatic.changeLevel(3);
                }
                if(keyCombinationChangeLevel4.match(keyEvent)) {
                    GeneralStatic.changeLevel(4);
                }
                if(keyCombinationChangeLevel5.match(keyEvent)) {
                    GeneralStatic.changeLevel(5);
                }
            }
        };


        keyEventGame1 = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.RIGHT && direction == RIGHT) {
                    direction = STOP;
                } else if (keyEvent.getCode() == KeyCode.DOWN && direction == DOWN) {
                    direction = STOP;
                } else if (keyEvent.getCode() == KeyCode.LEFT && direction == LEFT) {
                    direction = STOP;
                } else if (keyEvent.getCode() == KeyCode.UP && direction == UP) {
                    direction = STOP;
                }
            }
        };
    }


    public EventHandler getKeyEventGame() {
        return keyEventGame;
    }

    public EventHandler getKeyEventGame1() {
        return keyEventGame1;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }


}
