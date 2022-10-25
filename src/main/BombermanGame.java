package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.menu.MenuSetup;

import static main.settings.PropertiesConstant.*;

public class BombermanGame extends Application {
   // private boolean isServer = true;
   // private NetworkConnection networkConnection= isServer ? createServer() : createClient();

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

//    private Server createServer() {
//        return new Server(55555, data -> {
//            root.getChildren().add(new javafx.scene.control.Label(data.toString()));
//        });
//    }
//    private Client createClient() {
//        return new Client("127.0.0.1", 55555, data -> {
//            root.getChildren().add(new javafx.scene.control.Label(data.toString()));
//        });
//    }
//
//    @Override
//    public void init() throws Exception {
//        networkConnection.startConnection();
//    }
//
//    @Override
//    public void stop() throws Exception {
//        networkConnection.closeConnection();
//    }
}
