package main.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.Entity;

import static main.PropertiesConstant.*;
import static main.graphics.Sprite.*;

public class Oneal extends Enemy {

    protected int speed = SPEED_ENEMY * 2;
    private final int FRAME_PER_ONE = FRAME_PER_SECOND / 2;
    protected Image[][] imagesTwoWay = new Image[4][ONEAL_SPRITE];

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void setCoordinate(char[][] mapGame) {
        setDirection(mapGame);
        if(directionAnimate == STOP) {
            return;
        }
        setCoordinateAfterMove();
    }

    @Override
    public void setCoordinate(char[][] mapGame, Entity player) {
        setDirection(mapGame);
        setCoordinateAfterMove();
    }

    @Override
    public void setCoordinateAfterMove() {
        if (directionAnimate == RIGHT) {
            x += speed;
        } else if (directionAnimate == DOWN) {
            y += speed;
        } else if (directionAnimate == LEFT) {
            x -= speed;
        } else if (directionAnimate == UP) {
            y -= speed;
        }
    }

    public Oneal(int x, int y) {
        super(x, y, balloom_right1.getFxImage(oneal_right1.get_realWidth(), oneal_right1.get_realHeight()));
        imagesTwoWay[RIGHT][0] = oneal_right1.getFxImage(oneal_right1.get_realWidth(), oneal_right1.get_realHeight());
        imagesTwoWay[DOWN][0] = oneal_right1.getFxImage(oneal_left1.get_realWidth(), oneal_left1.get_realHeight());
        imagesTwoWay[LEFT][0] = oneal_left1.getFxImage(oneal_right1.get_realWidth(), oneal_right1.get_realHeight());
        imagesTwoWay[UP][0] = oneal_left1.getFxImage(oneal_left1.get_realWidth(), oneal_left1.get_realHeight());

        imagesTwoWay[RIGHT][1] = oneal_right2.getFxImage(oneal_right2.get_realWidth(), oneal_right2.get_realHeight());
        imagesTwoWay[DOWN][1] = oneal_right2.getFxImage(oneal_left2.get_realWidth(), oneal_left2.get_realHeight());
        imagesTwoWay[LEFT][1] = oneal_left2.getFxImage(oneal_right2.get_realWidth(), oneal_right2.get_realHeight());
        imagesTwoWay[UP][1] = oneal_left2.getFxImage(oneal_left2.get_realWidth(), oneal_left2.get_realHeight());

        imagesTwoWay[RIGHT][2] = oneal_right3.getFxImage(oneal_right3.get_realWidth(), oneal_right3.get_realHeight());
        imagesTwoWay[DOWN][2] = oneal_right3.getFxImage(oneal_left3.get_realWidth(), oneal_left3.get_realHeight());
        imagesTwoWay[LEFT][2] = oneal_left3.getFxImage(oneal_right3.get_realWidth(), oneal_right3.get_realHeight());
        imagesTwoWay[UP][2] = oneal_left3.getFxImage(oneal_left3.get_realWidth(), oneal_left3.get_realHeight());
    }


    @Override
    public void update() {
        updateSprite();
    }

    public void updateSprite() {
        indexAnimate += 1;
        if (indexAnimate >= FRAME_PER_ONE) {
            indexAnimate = 0;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        int frame = indexAnimate % FRAME_PER_ONE / (FRAME_PER_ONE / BOMBER_SPRITE);
        indexAnimate++;
        if (directionAnimate == RIGHT) {
            img = imagesTwoWay[RIGHT][frame];
        } else if (directionAnimate == DOWN) {
            img = imagesTwoWay[DOWN][frame];
        } else if (directionAnimate == LEFT) {
            img = imagesTwoWay[LEFT][frame];
        } else if (directionAnimate == UP) {
            img = imagesTwoWay[UP][frame];
        }
        super.render(gc);
    }

    @Override
    public void setCoordinateAfterMoveReverse() {
        if (directionAnimate == RIGHT) {
            x -= speed;
        } else if (directionAnimate == DOWN) {
            y -= speed;
        } else if (directionAnimate == LEFT) {
            x += speed;
        } else if (directionAnimate == UP) {
            y += speed;
        }
    }

    public void findCoordinatesRenderFromMap(char[][] mapGame) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (mapGame[i][j] == CHAR_ONEAL) {
                    y = i * TILE_SIZE * SCALE;
                    x = j * TILE_SIZE * SCALE;
                    mapGame[i][j] = CHAR_BLANK;
                    return;
                }
            }
        }
    }


}
