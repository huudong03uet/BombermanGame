package main.entities;

import javafx.scene.image.Image;
import main.graphics.Renderable;

import static main.PropertiesConstant.*;
import static main.PropertiesConstant.WIDTH;
import static main.PropertiesStatic.xHide;

public abstract class AnimateEntity extends Entity
        implements Renderable {

    protected int numberSprite;

    public AnimateEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setCoordinatesRenderMap() {
        if (x < WIDTH / 2) {
            xHide = 0;
        } else if (x > WIDTH_TILE * TILE_SIZE * SCALE - WIDTH / 2) {
            xHide = WIDTH_TILE * TILE_SIZE * SCALE - WIDTH;
        } else {
            xHide = x - WIDTH / 2;
        }
    }


    public boolean checkWallCollision(char[][] mapGame) {
        int xTopLeft = x / (TILE_SIZE * SCALE);
        int yTopLeft = y / (TILE_SIZE * SCALE);

        int xTopRight = (x + (int) img.getWidth() - 1) / (TILE_SIZE * SCALE);
        int yTopRight = y / (TILE_SIZE * SCALE);

        int xBottomLeft = x / (TILE_SIZE * SCALE);
        int yBottomLeft = (y + (int) img.getHeight() - 1) / (TILE_SIZE * SCALE);

        int xBottomRight = (x + (int) img.getWidth() - 1) / (TILE_SIZE * SCALE);
        int yBottomRight = (y + (int) img.getHeight() - 1) / (TILE_SIZE * SCALE);


        if (mapGame[yTopLeft][xTopLeft] == '#' || mapGame[yTopRight][xTopRight] == '#' ||
                mapGame[yBottomLeft][xBottomLeft] == '#' || mapGame[yBottomRight][xBottomRight] == '#') {

            return true;
        }
        if (mapGame[yTopLeft][xTopLeft] == '*' || mapGame[yTopRight][xTopRight] == '*' ||
                mapGame[yBottomLeft][xBottomLeft] == '*' || mapGame[yBottomRight][xBottomRight] == '*') {

            return true;
        }
        return false;
    }


}
