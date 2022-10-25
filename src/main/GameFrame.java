package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.frameGame.GameOver;
import main.frameGame.GamePlay;
import main.frameGame.GameStart;
import main.frameGame.GameTraining;
import main.general.GeneralStatic;
import main.keyEvent.KeyEventGame;

import java.io.IOException;

import static main.map.MapGame.level;
import static main.settings.PropertiesConstant.HEIGHT;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.StatusGame.*;


public class GameFrame {
    private Canvas canvas;

    GameStart gameStart;
    GamePlay gamePlay;
    GameTraining gameTraining;
    GameOver gameOver;


    KeyEventGame keyEventGame = new KeyEventGame();

    public GameFrame() {
        canvas = new Canvas(WIDTH, HEIGHT);
        BombermanGame.root.getChildren().add(canvas);
    }

    public void start(Stage stage) throws Exception {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventGame.getKeyEventGame());
        stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEventGame.getKeyEventGame1());

        gameStart = new GameStart(canvas);
        gamePlay = new GamePlay(canvas);
        gameTraining = new GameTraining(canvas);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if (status == GAME_MENU) {
                    gameStart.startLoop();
                }
                if (status == GAME_PLAY) {
                    gamePlay.gameLoop();
                    if (status == GAME_OVER) {
                        try {
                            gameOver = new GameOver(canvas);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (status == GAME_TRAINING) {
                    gameTraining.gameLoop();
                }

                if (status == GAME_PAUSE) {

                }
                if (status == GAME_OVER) {
                    gameOver.startLoop();
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
                        if (isTraining) {
                            gameTraining.setGameDefault();
                            status = GAME_TRAINING;
                        } else {
                            gamePlay.setGameDefault();
                            status = GAME_PLAY;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // render level with black background

                }
                if (status == GAME_CHANGE_LEVEL) {
                    GeneralStatic.changeLevel(level);
                    GeneralStatic.renderLevelBackground(canvas);
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
