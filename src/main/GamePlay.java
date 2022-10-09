package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.entities.CanMoveEntity;
import main.entities.Entity;
import main.entities.bomb.Bomb;
import main.entities.bomb.Flame;
import main.entities.bomber.Bomber;
import main.entities.enemy.*;
import main.entities.tile.Brick;
import main.general.CheckCollision;
import main.keyEvent.KeyEventGame;
import main.map.MapGame;
import main.menu.MenuSetup;

import java.util.ArrayList;
import java.util.List;

import static main.PropertiesConstant.*;
import static main.PropertiesStatic.*;

public class GamePlay {
    private GraphicsContext gc;
    private Canvas canvas;

    private MenuSetup menu;
    private MapGame mapGame;
    private List<Entity> enemies = new ArrayList<>();

    private Bomber bomberman;
    private List<Bomb> bombs = new ArrayList<>();
    private List<Flame> flames = new ArrayList<>();

    private final List<Entity> grassObject = new ArrayList<>();

    private final List<Entity> stillObjects = new ArrayList<>();
    private final List<Entity> items = new ArrayList<>();


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
        mapGame.updateMap(stillObjects, items, mapFile);
        mapGame.updateGrass(grassObject);
        mapGame.createEnnemies(enemies);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                bomberman.setCoordinate(map);
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

        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i).getIsRemove() == true) {
                if(numberPass < NUMBER_PASS_MAX) {
                    enemies.add(new Pass(flames.get(i).getXCenter(), flames.get(i).getYCenter()));
                }
                flames.remove(i);
            }
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            if (stillObjects.get(i).getIsRemove()) {
                if (map[stillObjects.get(i).getYCenter()][stillObjects.get(i).getXCenter()]
                        == mapFile[stillObjects.get(i).getYCenter()][stillObjects.get(i).getXCenter()]) {
                    map[stillObjects.get(i).getYCenter()][stillObjects.get(i).getXCenter()] = CHAR_GRASS;
                } else {
                    map[stillObjects.get(i).getYCenter()][stillObjects.get(i).getXCenter()] = mapFile[stillObjects.get(i).getYCenter()][stillObjects.get(i).getXCenter()];
                }
                stillObjects.remove(i);
            }
        }
    }

    public void update() {
        for (int i = 0; i < enemies.size(); i++) {
            ((CanMoveEntity) enemies.get(i)).setCoordinate(map, bomberman);
        }
        bomberman.update(items);

        for (int i = 0; i < bombs.size(); i++) {
            boolean canChangeMap = true;
            for (int j = 0; j < enemies.size(); j++) {
                if (checkCollision.checkCollision(bombs.get(i), enemies.get(j))) {
                    canChangeMap = false;
                    break;
                }
            }
            if (checkCollision.checkCollision(bombs.get(i), bomberman)) {
                canChangeMap = false;
            }
            if (canChangeMap == true) {
                map[bombs.get(i).getYCenter()][bombs.get(i).getXCenter()] = CHAR_WALL;
            }
        }
    }

    public void render() {

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < grassObject.size(); i++) {
            grassObject.get(i).render(gc);
        }
        for (int i = 0; i < items.size(); i++) {
            items.get(i).render(gc);
        }

        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).render(gc);
        }
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).render(gc);
        }

        for (int i = 0; i < flames.size(); i++) {
            flames.get(i).render(gc);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(gc);
        }
        bomberman.render(gc);
    }

    public void checkCollision() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getIsExploded() == false && checkCollision.checkCollision(bomberman, enemies.get(i))) {
                bomberman.setIsDead(true);
            }
        }
        for (int i = 0; i < flames.size(); i++) {
            if (checkCollision.checkCollisionWithFlame(bomberman, flames.get(i))) {
                bomberman.setIsDead(true);
            }
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            if (stillObjects.get(i) instanceof Brick) {
                for (int j = 0; j < flames.size(); j++) {
                    if (checkCollision.checkCollisionWithFlame(stillObjects.get(i), flames.get(j))) {
                        stillObjects.get(i).setIsExploded(true);
                    }
                }
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < flames.size(); j++) {
                if (checkCollision.checkCollisionWithFlame(enemies.get(i), flames.get(j))) {
                    enemies.get(i).setIsExploded(true);
                }
            }
        }
    }



    public void setupBombAndFlame(Entity player) {
        if (placeBomb == true) {
            bombs.add(new Bomb(player.getXCenter(), player.getYCenter()));
            placeBomb = false;
        }
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).getIsExploded() == true) {
                flames.add(new Flame(bombs.get(i).getXCenter(), bombs.get(i).getYCenter()));
                map[bombs.get(i).getYCenter()][bombs.get(i).getXCenter()] = CHAR_GRASS;
                bombs.remove(i);
            }
        }
    }

}


