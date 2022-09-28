package main.map;

import main.entities.*;
import main.graphics.Sprite;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static main.PropertiesConstant.HEIGHT_TILE;
import static main.PropertiesConstant.WIDTH_TILE;

public class MapGame {
    private int level;

    public void readMapFromFile(char[][] map) throws IOException {
        File file = new File("res\\levels\\level1.txt");
        Scanner scanner = new Scanner(file);
        String firstLine = scanner.nextLine();
        //scanner.nextLine();
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
                if (map[i][j] == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (map[i][j] == 'x') {
                    object = new Portal(j, i, Sprite.brick.getFxImage());
                } else if (map[i][j] == '*') {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }
}
