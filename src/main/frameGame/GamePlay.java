package main.frameGame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.entities.CanMoveEntity;
import main.entities.Entity;
import main.entities.bomb.Bomb;
import main.entities.bomb.Flame;
import main.entities.bomber.Bomber;
import main.entities.enemy.Pass;
import main.entities.tile.Brick;
import main.general.CheckCollision;
import main.general.TimeGame;
import main.map.MapGame;
import main.menu.InfoPlayer;
import main.settings.PropertiesStatic;
import main.soundSetting.Sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.settings.PropertiesConstant.*;
import static main.settings.PropertiesStatic.*;
import static main.settings.StatusGame.*;

public class GamePlay {
    private GraphicsContext gc;

    private MapGame mapGame;
    private List<Entity> enemies;

    private Bomber bomberman;
    private List<Bomb> bombs;
    private List<Flame> flames;

    private List<Entity> grassObject;

    private List<Entity> stillObjects;
    private List<Entity> items;
    private Sound sound = new Sound();
    private Sound enemyDieSound = new Sound();
    private Canvas canvas;

    private CheckCollision checkCollision;
    private InfoPlayer infoPlayer;
    private TimeGame timeGame;

    public void gameLoop() {
        bomberman.setCoordinate(map);
        setupBombAndFlame(bomberman);
        checkCollision();
        render();
        timeGame.setTimeRemain();
        try {
            remove();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        infoPlayer.drawInfoPlayer(gc);
        update();
    }

    public GamePlay(Canvas canvas) throws IOException {
        this.canvas = canvas;
        checkCollision = new CheckCollision();
        gc = this.canvas.getGraphicsContext2D();

        setGameDefault();
    }

    public void setGameDefault() throws IOException {
        enemies = new ArrayList<>();
        stillObjects = new ArrayList<>();
        items = new ArrayList<>();
        grassObject = new ArrayList<>();
        bombs = new ArrayList<>();
        flames = new ArrayList<>();
        mapGame = new MapGame();
        bomberman = new Bomber(1, 2);
        sound.isPlayMuzik(0);
        infoPlayer = new InfoPlayer();
        timeGame = new TimeGame();
        PropertiesStatic.setSettingGameDefault();
        loadGameFromMap();
    }

    public void loadGameFromMap() throws IOException {
        mapGame.readMapFromFile(map);
        mapGame.updateMap(stillObjects, items, mapFile);
        mapGame.updateGrass(grassObject);
        mapGame.createEnnemies(enemies);
    }


    public void remove() throws IOException {
        if (bomberman.getIsRemove()) {
            sound.stop();
            lifeBomber--;
            if(lifeBomber == 0) {
                status = GAME_OVER;
                return;
            }
            setGameDefault();
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getIsRemove()) {
                enemies.remove(i);
            }
        }

        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i).getIsRemove() == true) {
                if (numberPass < NUMBER_PASS_DEFAULT) {
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
                    enemyDieSound.playSE(4);
                    enemies.get(i).setIsExploded(true);
                }
            }
        }
    }


    public void setupBombAndFlame(Entity player) {
        if (placeBomb == true) {
            if (PropertiesStatic.countBombMax < numberBombDefault) {
                if (map[player.getYCenter()][player.getXCenter()] != CHAR_WALL && map[player.getYCenter()][player.getXCenter()] != CHAR_BRICK) {
                    bombs.add(new Bomb(player.getXCenter(), player.getYCenter()));
                }
            }
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


