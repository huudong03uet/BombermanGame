package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import main.entities.Bomber;
import main.entities.Entity;
import main.entities.Grass;
import main.entities.Wall;
import main.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static main.PropertiesConstant.*;

public class GamePlay {

    // Tao Canvas
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public void start(Stage stage) {
        canvas = new Canvas(WIDTH, HEIGHT);

        gc = canvas.getGraphicsContext2D();

        // Tao root container

        BombermanGame.root.getChildren().add(canvas);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH_TILE; i++) {
          for (int j = 0; j < HEIGHT_TILE; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT_TILE - 1 || i == 0 || i == WIDTH_TILE - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).render(gc);
        }
        for(int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }
    }
}
