package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.entities.bomber.Bomber;
import main.entities.Entity;
import main.graphics.Sprite;
import main.keyBoard.KeyEventGame;
import main.map.MapGame;
import main.menu.MenuSetup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.PropertiesConstant.*;

public class GamePlay {
    private GraphicsContext gc;
    private Canvas canvas;

    private MenuSetup menu;
    private MapGame mapGame;
    private Entity bomberman;

    private List<Entity> entities = new ArrayList<>();
    private final List<Entity> stillObjects = new ArrayList<>();
    private char[][] map = new char[HEIGHT_TILE][WIDTH_TILE];

    KeyEventGame keyEventGame;

    public GamePlay() {
        canvas = new Canvas(WIDTH, HEIGHT);

        BombermanGame.root.getChildren().add(canvas);

        menu = new MenuSetup();
        menu.setMenuBar(BombermanGame.root);

        mapGame = new MapGame();
        keyEventGame = new KeyEventGame();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    }


    public void start(Stage stage) throws IOException {
        gc = canvas.getGraphicsContext2D();

        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventGame.getKeyEventGame());

        mapGame.readMapFromFile(map);
        mapGame.updateMap(stillObjects, map);

        entities.add(bomberman);
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                ((Bomber) bomberman).setDirectionBomber(keyEventGame.getKeyCode(), map);
                render();
                update();
            }
        };

        timer.start();
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).render(gc);
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }
    }

}
