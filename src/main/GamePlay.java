package main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
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

public class GamePlay {
    private GraphicsContext gc;
    private Canvas canvas;

    private MenuSetup menu;
    private MapGame mapGame;
    private Entity balloom;
    private Entity oneal;
    private Entity doll;

    private Entity bomb;
    private Entity flameHorizontal1;
    private Entity flameHorizontal2;
    private Entity flameHoLeft;
    private Entity flameHoRight;
    private Entity flameVertical1;
    private Entity flameVertical2;
    private Entity flameVerUp;
    private Entity flameVerDown;
    private List<Entity> entities = new ArrayList<>();
    private Bomber bomberman;

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
        oneal = new Oneal(1, 1);
        doll = new Doll(1, 1);
        bomb = new Bomb(3, 3);
        flameHorizontal1 = new Flame(bomb.getX() + 1, bomb.getY(), 0);
        flameHorizontal2 = new Flame(bomb.getX() - 1, bomb.getY(), 0);
        flameHoRight = new Flame(flameHorizontal1.getX() + 1, flameHorizontal1.getY(), 1);
        flameHoLeft = new Flame(flameHorizontal2.getX() - 1, flameHorizontal2.getY(), 2);
        flameVertical1 = new Flame(bomb.getX(), bomb.getY() + 1, 3);
        flameVertical2 = new Flame(bomb.getX(), bomb.getY() - 1, 3);
        flameVerDown = new Flame(flameVertical1.getX(), flameVertical1.getY() + 1, 4);
        flameVerUp = new Flame(flameVertical2.getX(), flameVertical2.getY() - 1, 5);
    }


    public void start(Stage stage) throws Exception {
        gc = canvas.getGraphicsContext2D();
        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventGame.getKeyEventGame());
        stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEventGame.getKeyEventGame1());


        mapGame.readMapFromFile(map);
        mapGame.updateMap(stillObjects, map);

        entities.add(balloom);
        entities.add(oneal);
        entities.add(doll);

        stillObjects.add(bomb);
        stillObjects.add(flameHorizontal1);
        stillObjects.add(flameHorizontal2);
        stillObjects.add(flameHoRight);
        stillObjects.add(flameHoLeft);
        stillObjects.add(flameVertical1);
        stillObjects.add(flameVertical2);
        stillObjects.add(flameVerDown);
        stillObjects.add(flameVerUp);

        ((Balloom) balloom).findCoordinatesRenderFromMap(map);
        ((Oneal) oneal).findCoordinatesRenderFromMap(map);
        ((Doll) doll).findCoordinatesRenderFromMap(map);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                bomberman.setCoordinate(map);

                ((Balloom) balloom).setCoordinate(map);
                ((Oneal) oneal).setCoordinate(map);
                ((Doll) doll).setCoordinate(map);

                if (checkCollision.checkCollision(bomberman, balloom)) {
                    bomberman.setIsDead(true);
                }
                if(checkCollision.checkCollision(bomberman, oneal)){
                    bomberman.setIsDead(true);
                }
                if(checkCollision.checkCollision(bomberman, doll)){
                    bomberman.setIsDead(true);
                }

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
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getIsRemove()) {
                entities.remove(i);
            }
        }
    }

    public void update() {
        bomberman.update();
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
        bomberman.render(gc);
    }

}
