package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.menu.MenuSetup;

import static main.settings.PropertiesConstant.*;

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

        MenuSetup menu = new MenuSetup();
        menu.setMenuBar(root);

        GameFrame gameFrame = new GameFrame();
        gameFrame.start(stage);
    }
}
