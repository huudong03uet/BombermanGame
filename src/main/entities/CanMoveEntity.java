package main.entities;

import javafx.scene.image.Image;

import static main.settings.PropertiesConstant.*;

public abstract class CanMoveEntity extends AnimateEntity {
    protected int directionAnimate;

    protected int indexAnimate = 0;

    public CanMoveEntity(int x, int y, Image img) {
        super(x, y, img);
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

    @Override
    public void setIsExploded(boolean isExploded) {
        directionAnimate = STOP;
        super.setIsExploded(isExploded);
    }

    public abstract void setCoordinate(char[][] mapGame, Entity player);
    public abstract void setCoordinateAfterMove();

    public abstract void setCoordinateAfterMoveReverse();
}
