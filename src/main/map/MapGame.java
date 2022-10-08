package main.map;

import main.entities.Entity;
import main.entities.items.SpeedItem;
import main.entities.tile.Brick;
import main.entities.tile.Grass;
import main.entities.tile.Portal;
import main.entities.tile.Wall;
import main.graphics.Sprite;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static main.PropertiesConstant.*;
import static main.PropertiesStatic.mapFile;

public class MapGame {
    private int level;

    public MapGame() {
        level = 1;
    }

    public String mapPath() {
        return "res\\levels\\level" + level + ".txt";
    }

    public void readMapFromFile(char[][] map) throws IOException {
        File file = new File(mapPath());
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        for (int i = 0; i < HEIGHT_TILE; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < WIDTH_TILE; j++) {
                mapFile[i][j] = line.charAt(j);
                if (mapFile[i][j] == CHAR_PORTAL || mapFile[i][j] == SPEED_ITEM) {
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

                if(map[i][j] == CHAR_PORTAL){
                    list.add(new Portal(j, i, Sprite.portal.getFxImage()));
                    stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                }
                if(map[i][j] == SPEED_ITEM) {
                    list.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
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
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
