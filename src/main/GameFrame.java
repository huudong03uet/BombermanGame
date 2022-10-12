package main;

import javafx.stage.Stage;

public class GameFrame {
    GamePlay gamePlay;
    public GameFrame() {
        gamePlay = new GamePlay();
    }

    public void start(Stage stage) throws Exception {
        gamePlay.start(stage);
    }
}
