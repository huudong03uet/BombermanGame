package main.map;

import main.entities.*;
import main.entities.tile.Brick;
import main.entities.tile.Grass;
import main.entities.tile.Portal;
import main.entities.tile.Wall;
import main.graphics.Sprite;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static main.PropertiesConstant.*;

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
                map[i][j] = line.charAt(j);
            }
        }
    }

    public void updateMap(List<Entity> stillObjects, char[][] map) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                Entity object;
                if (map[i][j] == CHAR_WALL) {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (map[i][j] == CHAR_PORTAL) {
                    object = new Portal(j, i, Sprite.brick.getFxImage());
                } else if (map[i][j] == CHAR_BRICK) {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
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
