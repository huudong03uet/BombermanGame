package main.keyEvent;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static main.PropertiesStatic.*;

public class KeyEventGame {

    private EventHandler keyEventGame;
    KeyCode keyCode = null;
    KeyCode keyCodeCandidate = KeyCode.RIGHT;

    public KeyEventGame() {
        keyEventGame = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                keyCodeCandidate = keyEvent.getCode();
                if (direction == 4) {
                    if (keyCodeCandidate == KeyCode.RIGHT) {
                        System.out.println("Right");
                        keyCode = KeyCode.RIGHT;
                        direction = 0;
                    } else if (keyCodeCandidate == KeyCode.DOWN) {
                        System.out.println("Down");
                        keyCode = KeyCode.DOWN;
                        direction = 1;
                    } else if (keyCodeCandidate == KeyCode.LEFT) {
                        System.out.println("Left");
                        keyCode = KeyCode.LEFT;
                        direction = 2;
                    } else if (keyCodeCandidate == KeyCode.UP) {
                        System.out.println("Up");
                        keyCode = KeyCode.UP;
                        direction = 3;
                    }
                }
            }
        };
    }


    public EventHandler getKeyEventGame() {
        return keyEventGame;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public void setKeyCodeAfterMove(KeyCode keyCode) {
        this.keyCode = null;
    }

}
