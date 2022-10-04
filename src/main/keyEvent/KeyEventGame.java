package main.keyEvent;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static main.PropertiesStatic.*;

public class KeyEventGame {

    private EventHandler keyEventGame;
    private EventHandler keyEventGame1;
    KeyCode keyCode = null;

    public KeyEventGame() {
        keyEventGame = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    System.out.println("Right");
                    keyCode = KeyCode.RIGHT;
                    direction = 0;
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    System.out.println("Down");
                    keyCode = KeyCode.DOWN;
                    direction = 1;
                } else if (keyEvent.getCode() == KeyCode.LEFT) {
                    System.out.println("Left");
                    keyCode = KeyCode.LEFT;
                    direction = 2;
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    System.out.println("Up");
                    keyCode = KeyCode.UP;
                    direction = 3;
                }
            }
        };
        keyEventGame1 = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.RIGHT && direction == 0) {
                    System.out.println("Right release");
                    direction = 4;
                } else if (keyEvent.getCode() == KeyCode.DOWN && direction == 1) {
                    System.out.println("Down release");
                    direction = 4;
                } else if (keyEvent.getCode() == KeyCode.LEFT && direction == 2) {
                    System.out.println("Left release");
                    direction = 4;
                } else if (keyEvent.getCode() == KeyCode.UP && direction == 3) {
                    System.out.println("Up release");
                    direction = 4;
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
