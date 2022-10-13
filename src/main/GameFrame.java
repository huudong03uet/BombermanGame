package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.frameGame.GamePlay;
import main.keyEvent.KeyEventGame;


import java.io.IOException;

import static main.settings.PropertiesConstant.HEIGHT;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.StatusGame.*;

public class GameFrame {
    private Canvas canvas;
    GamePlay gamePlay;
    KeyEventGame keyEventGame = new KeyEventGame();

    public GameFrame() {
        canvas = new Canvas(WIDTH, HEIGHT);
        BombermanGame.root.getChildren().add(canvas);
    }

    public void start(Stage stage) throws Exception {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventGame.getKeyEventGame());
        stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEventGame.getKeyEventGame1());

        gamePlay = new GamePlay(canvas);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if (status == GAME_PLAY) gamePlay.gameLoop();
                if (status == GAME_PAUSE) {

                }
                if (status == GAME_OVER) {
                    status = GAME_EXIT;
                }
                if (status == GAME_WIN) {
                    status = GAME_EXIT;
                }
                if (status == GAME_EXIT) {
                    System.exit(0);
                }
                if (status == GAME_RESTART_GAME) {
                    System.exit(0);
                }
                if (status == GAME_RESTART_LEVEL) {
                    try {
                        gamePlay.setGameDefault();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    status = GAME_PLAY;
                }
                if (status == GAME_CHANGE_LEVEL) {
                    status = GAME_RESTART_LEVEL;
                }
                if (status == GAME_CHANGE_DIFFICULTY) {
                    status = GAME_CHANGE_LEVEL;
                }


            }
        };
        timer.start();
    }
}