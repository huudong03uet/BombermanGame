package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.keyBoard.KeyEventGame;

import static main.PropertiesConstant.*;

public class BombermanGame extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static Group root = new Group();
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();



        GamePlay gamePlay = new GamePlay();
        gamePlay.start(stage);
    }
}
