package main.keyBoard;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventGame {

    private EventHandler keyEventGame;
    KeyCode keyCode;

    public KeyEventGame() {
        keyEventGame = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    System.out.println("Right");
                    keyCode = KeyCode.RIGHT;
                } else if (keyEvent.getCode() == KeyCode.LEFT) {
                    System.out.println("Left");
                    keyCode = KeyCode.LEFT;
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    System.out.println("Up");
                    keyCode = KeyCode.UP;
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    System.out.println("Down");
                    keyCode = KeyCode.DOWN;
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
}
