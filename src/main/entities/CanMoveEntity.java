package main.entities;

import javafx.scene.image.Image;
import main.graphics.Renderable;

import static main.PropertiesConstant.SCALE;
import static main.PropertiesConstant.TILE_SIZE;

public abstract class CanMoveEntity extends AnimateEntity
implements Renderable
{
    protected int directionAnimate;
    protected int speed = 2;
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

    public abstract void setCoordinate(char[][] mapGame);
    public abstract void setCoordinateAfterMove();
    public abstract void setCoordinateAfterMoveReverse();
}