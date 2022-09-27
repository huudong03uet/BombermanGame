package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        stage.show();

        GamePlay gamePlay = new GamePlay();
        gamePlay.start(stage);
    }
}
