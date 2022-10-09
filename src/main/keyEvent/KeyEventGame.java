package main.keyEvent;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static main.PropertiesConstant.*;
import static main.PropertiesStatic.direction;
import static main.PropertiesStatic.placeBomb;

public class KeyEventGame {

    private EventHandler keyEventGame;
    private EventHandler keyEventGame1;
    KeyCode keyCode = null;

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
