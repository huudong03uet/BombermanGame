package main.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.graphics.Sprite;

import static main.PropertiesConstant.*;

public class Oneal extends Enemy {
    private int speed = 3;
    private final int FRAME_PER_ONE = FRAME_PER_SECOND / 2;
    protected Image[][] imagesTwoWay = new Image[4][BALLOOM_SPRITE];

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void setCoordinate(char[][] mapGame) {
        setDirection(mapGame);
        setCoordinateAfterMove();
    }

    @Override
    public void setCoordinateAfterMove() {
        if (directionAnimate == 0) {
            x += speed;
        } else if(directionAnimate == 1) {
            y += speed;
        } else if(directionAnimate == 2) {
            x -= speed;
        } else if(directionAnimate == 3) {
            y -= speed;
        }
    }
    public Oneal(int x, int y) {
        super(x, y, Sprite.balloom_right1.getFxImage(Sprite.oneal_right1.get_realWidth(), Sprite.oneal_right1.get_realHeight()));
        imagesTwoWay[0][0] = Sprite.oneal_right1.getFxImage(Sprite.oneal_right1.get_realWidth(), Sprite.oneal_right1.get_realHeight());
        imagesTwoWay[1][0] = Sprite.oneal_right1.getFxImage(Sprite.oneal_left1.get_realWidth(), Sprite.oneal_left1.get_realHeight());
        imagesTwoWay[2][0] = Sprite.oneal_left1.getFxImage(Sprite.oneal_right1.get_realWidth(), Sprite.oneal_right1.get_realHeight());
        imagesTwoWay[3][0] = Sprite.oneal_left1.getFxImage(Sprite.oneal_left1.get_realWidth(), Sprite.oneal_left1.get_realHeight());

        imagesTwoWay[0][1] = Sprite.oneal_right2.getFxImage(Sprite.oneal_right2.get_realWidth(), Sprite.oneal_right2.get_realHeight());
        imagesTwoWay[1][1] = Sprite.oneal_right2.getFxImage(Sprite.oneal_left2.get_realWidth(), Sprite.oneal_left2.get_realHeight());
        imagesTwoWay[2][1] = Sprite.oneal_left2.getFxImage(Sprite.oneal_right2.get_realWidth(), Sprite.oneal_right2.get_realHeight());
        imagesTwoWay[3][1] = Sprite.oneal_left2.getFxImage(Sprite.oneal_left2.get_realWidth(), Sprite.oneal_left2.get_realHeight());

        imagesTwoWay[0][2] = Sprite.oneal_right3.getFxImage(Sprite.oneal_right3.get_realWidth(), Sprite.oneal_right3.get_realHeight());
        imagesTwoWay[1][2] = Sprite.oneal_right3.getFxImage(Sprite.oneal_left3.get_realWidth(), Sprite.oneal_left3.get_realHeight());
        imagesTwoWay[2][2] = Sprite.oneal_left3.getFxImage(Sprite.oneal_right3.get_realWidth(), Sprite.oneal_right3.get_realHeight());
        imagesTwoWay[3][2] = Sprite.oneal_left3.getFxImage(Sprite.oneal_left3.get_realWidth(), Sprite.oneal_left3.get_realHeight());
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
        int frame = indexAnimate / (FRAME_PER_ONE / BOMBER_SPRITE);
        if (directionAnimate == 0) {
            img = imagesTwoWay[0][frame];
        } else if (directionAnimate == 1) {
            img = imagesTwoWay[1][frame];
        } else if (directionAnimate == 2) {
            img = imagesTwoWay[2][frame];
        } else if (directionAnimate == 3) {
            img = imagesTwoWay[3][frame];
        }
        super.render(gc);
    }

    @Override
    public void setCoordinateAfterMoveReverse() {
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
    public void findCoordinatesRenderFromMap(char[][] mapGame) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (mapGame[i][j] == '2') {
                    y = i * TILE_SIZE * SCALE;
                    x = j * TILE_SIZE * SCALE;
                    mapGame[i][j] = ' ';
                    return;
                }
            }
        }
    }
}
