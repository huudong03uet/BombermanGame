package main.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.Entity;

import static main.PropertiesConstant.*;
import static main.graphics.Sprite.*;

public class Balloom extends Enemy {
    private final int FRAME_PER_ONE = FRAME_PER_SECOND / 2;
    protected Image[][] imagesTwoWay = new Image[4][BALLOOM_SPRITE];
    protected int timeRemain = 0;


    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    public Balloom(int x, int y) {
        super(x, y, balloom_right1.getFxImage(balloom_right1.get_realWidth(), balloom_right1.get_realHeight()));
        imagesTwoWay[RIGHT][0] = balloom_right1.getFxImage(balloom_right1.get_realWidth(), balloom_right2.get_realHeight());
        imagesTwoWay[DOWN][0] = balloom_right1.getFxImage(balloom_left1.get_realWidth(), balloom_left2.get_realHeight());
        imagesTwoWay[LEFT][0] = balloom_left1.getFxImage(balloom_right1.get_realWidth(), balloom_right2.get_realHeight());
        imagesTwoWay[UP][0] = balloom_left1.getFxImage(balloom_left1.get_realWidth(), balloom_left2.get_realHeight());

        imagesTwoWay[RIGHT][1] = balloom_right2.getFxImage(balloom_right1.get_realWidth(), balloom_right2.get_realHeight());
        imagesTwoWay[DOWN][1] = balloom_right2.getFxImage(balloom_left1.get_realWidth(), balloom_left2.get_realHeight());
        imagesTwoWay[LEFT][1] = balloom_left2.getFxImage(balloom_right1.get_realWidth(), balloom_right2.get_realHeight());
        imagesTwoWay[UP][1] = balloom_left2.getFxImage(balloom_left1.get_realWidth(), balloom_left2.get_realHeight());

        imagesTwoWay[RIGHT][2] = balloom_right3.getFxImage(balloom_right1.get_realWidth(), balloom_right2.get_realHeight());
        imagesTwoWay[DOWN][2] = balloom_right3.getFxImage(balloom_left1.get_realWidth(), balloom_left2.get_realHeight());
        imagesTwoWay[LEFT][2] = balloom_left3.getFxImage(balloom_right1.get_realWidth(), balloom_right2.get_realHeight());
        imagesTwoWay[UP][2] = balloom_left3.getFxImage(balloom_left1.get_realWidth(), balloom_left2.get_realHeight());

        imagesExploded[0] = balloom_dead.getFxImage(balloom_dead.get_realWidth(), balloom_dead.get_realHeight());
    }

    @Override
    public void setCoordinate(char[][] mapGame, Entity player) {
        if (directionAnimate == STOP) {
            return;
        }
        setDirection(mapGame);
        setCoordinateAfterMove();
    }

    @Override
    public void setCoordinateAfterMove() {
        if (directionAnimate == 0) {
            x += speed;
        } else if (directionAnimate == 1) {
            y += speed;
        } else if (directionAnimate == 2) {
            x -= speed;
        } else if (directionAnimate == 3) {
            y -= speed;
        }
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
    public void setCoordinateAfterMoveReverse() {
        // 0: right, 1: down, 2: left, 3: up
        if (directionAnimate == 0) {
            x -= speed;
        } else if (directionAnimate == 1) {
            y -= speed;
        } else if (directionAnimate == 2) {
            x += speed;
        } else if (directionAnimate == 3) {
            y += speed;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        if (isExploded == true) {
            int frame = timeRemain % TIME_EXPLOYED / (TIME_EXPLOYED / BOMBER_SPRITE);
            timeRemain++;
            if (timeRemain >= TIME_EXPLOYED) {
                timeRemain = 0;
                isRemove = true;
            } else {
                img = imagesExploded[frame];
            }

        } else {
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

        }
        super.render(gc);

    }

    public void findCoordinatesRenderFromMap(char[][] mapGame) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (mapGame[i][j] == CHAR_BALLOOM) {
                    y = i * TILE_SIZE * SCALE;
                    x = j * TILE_SIZE * SCALE;
                    mapGame[i][j] = CHAR_BLANK;
                    return;
                }
            }
        }
    }

}
