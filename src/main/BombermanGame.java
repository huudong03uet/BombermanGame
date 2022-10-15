package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.menu.MenuSetup;


import static main.settings.PropertiesConstant.*;

public class BombermanGame extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
        MenuSetup menu = new MenuSetup();
        GameFrame gameFrame = new GameFrame();

        menu.setMenuBar(root);
        gameFrame.start(stage);
    }
}
