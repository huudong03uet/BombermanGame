package main.map;

import main.entities.Entity;

import main.entities.enemy.*;

import main.entities.items.*;
import main.entities.tile.Brick;
import main.entities.tile.Grass;
import main.entities.tile.Portal;
import main.entities.tile.Wall;
import main.graphics.Sprite;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static main.settings.PropertiesConstant.*;
import static main.settings.PropertiesStatic.map;
import static main.settings.PropertiesStatic.mapFile;

public class MapGame {
    public static int level = 1;

    public MapGame() {

    }

    public String mapPath() {
        return "res\\levels\\level" + level + ".txt";
    }
    public String mapPathTraining() {
        return "res\\levelTraining\\level" +level+ ".txt";
    }

    public void readMapFromFile(char[][] map) throws IOException {
        File file = new File(mapPath());
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        for (int i = 0; i < HEIGHT_TILE; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < WIDTH_TILE; j++) {
                mapFile[i][j] = line.charAt(j);

                if (mapFile[i][j] == CHAR_PORTAL || mapFile[i][j] == SPEED_ITEM
                        || mapFile[i][j] == FLAME_PASS_ITEM || mapFile[i][j] == FLAME_ITEM
                        || mapFile[i][j] == BOMB_ITEM || mapFile[i][j] == WALL_PASS_ITEM
                        || mapFile[i][j] == DETONATOR_ITEM) {
                    map[i][j] = CHAR_BRICK;
                } else {
                    map[i][j] = mapFile[i][j];
                }
            }
        }
    }

    public void readMapFromFile(char[][] map, boolean isTraining) throws IOException {
        File file = null;
        if (isTraining) {
            file = new File(mapPathTraining());
        }
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        for (int i = 0; i < HEIGHT_TILE; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < WIDTH_TILE; j++) {
                mapFile[i][j] = line.charAt(j);

                if (mapFile[i][j] == CHAR_PORTAL || mapFile[i][j] == SPEED_ITEM
                        || mapFile[i][j] == FLAME_PASS_ITEM || mapFile[i][j] == FLAME_ITEM
                        || mapFile[i][j] == BOMB_ITEM || mapFile[i][j] == WALL_PASS_ITEM
                        || mapFile[i][j] == DETONATOR_ITEM) {
                    map[i][j] = CHAR_BRICK;
                } else {
                    map[i][j] = mapFile[i][j];
                }
            }
        }
    }
    public void updateMap(List<Entity> stillObjects, List<Entity> list, char[][] map) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (map[i][j] == CHAR_WALL) {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else if (map[i][j] == CHAR_PORTAL) {
                    stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                } else if (map[i][j] == CHAR_BRICK) {
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                }

                if (map[i][j] == CHAR_PORTAL) {
                    list.add(new Portal(j, i, Sprite.portal.getFxImage()));
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                }

                if (map[i][j] == SPEED_ITEM) {
                    list.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                } else if (map[i][j] == FLAME_ITEM) {
                    list.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                } else if (map[i][j] == FLAME_PASS_ITEM) {
                    list.add(new FlamePassItem(j, i, Sprite.powerup_flamepass.getFxImage()));
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                } else if (map[i][j] == BOMB_ITEM) {
                    list.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                } else if(map[i][j] == WALL_PASS_ITEM) {
                    list.add(new WallPassItem(j, i, Sprite.powerup_wallpass.getFxImage()));
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                } else if(map[i][j] == DETONATOR_ITEM) {
                    list.add(new DetonatorItem(j, i, Sprite.powerup_detonator.getFxImage()));
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                }
            }
        }
    }

    public void updateGrass(List<Entity> grassObject) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                grassObject.add(new Grass(j, i, Sprite.grass.getFxImage()));
            }
        }
    }

    public void createEnnemies(List<Entity> enemies) {

        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (map[i][j] == CHAR_BALLOOM) {
                    enemies.add(new Balloom(j, i));
                } else if (map[i][j] == CHAR_ONEAL) {
                    enemies.add(new Oneal(j, i));
                } else if (map[i][j] == CHAR_DOLL) {
                    enemies.add(new Doll(j, i));
                } else if (map[i][j] == CHAR_MINVO) {
                    enemies.add(new Minvo(j, i));
                } else if (map[i][j] == CHAR_GHOST) {
                    enemies.add(new Ghost(j, i));
                } else if (map[i][j] == CHAR_KONDORIA) {
                    enemies.add(new Kondoria(j, i));
                }
            }
        }

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
