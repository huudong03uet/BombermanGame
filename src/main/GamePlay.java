package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.entities.Entity;
import main.entities.bomber.Bomber;
import main.entities.enemy.Balloom;
import main.general.CheckCollision;
import main.keyEvent.KeyEventGame;
import main.map.MapGame;
import main.menu.MenuSetup;

import java.util.ArrayList;
import java.util.List;

import static main.PropertiesConstant.*;

public class GamePlay {
    private GraphicsContext gc;
    private Canvas canvas;

    private MenuSetup menu;
    private MapGame mapGame;
    private Entity bomberman;
    private Entity balloom;

    private List<Entity> entities = new ArrayList<>();

    private final List<Entity> stillObjects = new ArrayList<>();
    private char[][] map = new char[HEIGHT_TILE][WIDTH_TILE];

    private CheckCollision checkCollision;
    KeyEventGame keyEventGame;

    public GamePlay() {
        menu = new MenuSetup();
        canvas = new Canvas(WIDTH, HEIGHT);
        BombermanGame.root.getChildren().add(canvas);
        menu.setMenuBar(BombermanGame.root);
        mapGame = new MapGame();
        keyEventGame = new KeyEventGame();

        checkCollision = new CheckCollision();
        bomberman = new Bomber(1, 1);
        balloom = new Balloom(1, 1);
        //bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(Sprite.player_right.get_realWidth(), Sprite.player_right.get_realHeight()));
    }


    public void start(Stage stage) throws Exception {
        gc = canvas.getGraphicsContext2D();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventGame.getKeyEventGame());

        mapGame.readMapFromFile(map);
        mapGame.updateMap(stillObjects, map);

        entities.add(bomberman);
        entities.add(balloom);

        ((Balloom) balloom).findCoordinatesRenderFromMap(map);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                ((Bomber) bomberman).setCoordinate(map);
                ((Balloom) balloom).setCoordinate(map);
                if(checkCollision.checkCollision(bomberman, balloom)) {
                    System.out.println("Collision");
                }
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
