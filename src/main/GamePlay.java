package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.entities.CanMoveEntity;
import main.entities.Entity;
import main.entities.bomb.Bomb;
import main.entities.bomb.Flame;
import main.entities.bomber.Bomber;
import main.entities.enemy.Balloom;
import main.entities.enemy.Doll;
import main.entities.enemy.Oneal;
import main.general.CheckCollision;
import main.keyEvent.KeyEventGame;
import main.map.MapGame;
import main.menu.MenuSetup;

import java.util.ArrayList;
import java.util.List;

import static main.PropertiesConstant.*;
import static main.PropertiesStatic.placeBomb;

public class GamePlay {
    private GraphicsContext gc;
    private Canvas canvas;

    private MenuSetup menu;
    private MapGame mapGame;
    private List<Entity> enemies = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();

    private Bomber bomberman;
    private List<Bomb> bombs = new ArrayList<>();
    private List<Flame> flames = new ArrayList<>();

    private final List<Entity> stillObjects = new ArrayList<>();
    private char[][] map = new char[HEIGHT_TILE][WIDTH_TILE];

    private CheckCollision checkCollision;
    KeyEventGame keyEventGame;

    public GamePlay() {
        menu = new MenuSetup();
        canvas = new Canvas(WIDTH, HEIGHT);
        BombermanGame.root.getChildren().add(canvas);

        menu.setMenuBar(BombermanGame.root);
        keyEventGame = new KeyEventGame();
        checkCollision = new CheckCollision();

        mapGame = new MapGame();
        bomberman = new Bomber(1, 1);
    }


    public void start(Stage stage) throws Exception {
        gc = canvas.getGraphicsContext2D();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventGame.getKeyEventGame());
        stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEventGame.getKeyEventGame1());

        mapGame.readMapFromFile(map);
        mapGame.updateMap(stillObjects, map);

        createEntity();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                bomberman.setCoordinate(map);

                setCoordinateEntity();

                setupBombAndFlame(bomberman);

                checkCollision();

                render();
                remove();
                update();
            }

        };

        timer.start();
    }


    public void remove() {
        if (bomberman.getIsRemove()) {
            System.out.println("remove");
            System.exit(0);
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getIsRemove()) {
                enemies.remove(i);
            }
        }
    }

    public void update() {
        bomberman.update();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
    }

    public void render() {

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).render(gc);
        }

        for(int i = 0; i < bombs.size(); i++) {
            bombs.get(i).render(gc);
        }

        for(int i = 0; i < flames.size(); i++) {
            flames.get(i).render(gc);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(gc);
        }

        bomberman.render(gc);
    }

    public void checkCollision() {
        for (int i = 0; i < enemies.size(); i++) {
            if (checkCollision.checkCollision(bomberman, enemies.get(i))) {
                bomberman.setIsDead(true);
            }
        }
    }

    public void setCoordinateEntity() {
        for (int i = 0; i < enemies.size(); i++) {
            ((CanMoveEntity) enemies.get(i)).setCoordinate(map, bomberman);
        }
    }

    public void createEntity() {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (map[i][j] == '1') {
                    enemies.add(new Balloom(j, i));
                } else if (map[i][j] == '2') {
                    enemies.add(new Oneal(j, i));
                } else if (map[i][j] == '3') {
                    enemies.add(new Doll(j, i));
                }
            }
        }
    }

    public void setupBombAndFlame(Entity player) {
        player.getYCenter();
        createBomb(player);
        createFlame();
        removeFlame();
    }

    public void createBomb(Entity player) {
        if(placeBomb) {
            bombs.add(new Bomb(player.getXCenter(), player.getYCenter()));
            placeBomb = false;
        }
    }

    public void createFlame() {
        for(int i = 0; i < bombs.size(); i++) {
            if(bombs.get(i).getIsExploded()) {
                flames.add(new Flame(bombs.get(i).getXCenter(), bombs.get(i).getYCenter()));
                bombs.remove(i);
            }
        }
    }

    public void removeFlame() {
        for(int i = 0; i < flames.size(); i++) {
            if(flames.get(i).getIsRemove()) {
                flames.remove(i);
            }
        }
    }
}


