package main.entities.bomber;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.entities.CanMoveEntity;
import main.graphics.Sprite;

import static main.PropertiesConstant.*;
import static main.PropertiesStatic.*;

public class Bomber extends CanMoveEntity {
    protected Image[][] imagesTwoWay = new Image[4][BOMBER_SPRITE];
    private KeyCode keyCode = null;
    private int directionPrevious = 0;
    private Image imageRender = img;
    private int indexAnimate = 0;

    public Bomber(int x, int y) {
        super(x, y, Sprite.player_right.getFxImage(Sprite.player_right.get_realWidth(), Sprite.player_right.get_realHeight()));
        speed = SPEED_BOMBER;

        imagesTwoWay[0][0] = Sprite.player_right.getFxImage(Sprite.player_right.get_realWidth(), Sprite.player_right.get_realHeight());
        imagesTwoWay[1][0] = Sprite.player_down.getFxImage(Sprite.player_down.get_realWidth(), Sprite.player_down.get_realHeight());
        imagesTwoWay[2][0] = Sprite.player_left.getFxImage(Sprite.player_left.get_realWidth(), Sprite.player_left.get_realHeight());
        imagesTwoWay[3][0] = Sprite.player_up.getFxImage(Sprite.player_up.get_realWidth(), Sprite.player_up.get_realHeight());

        imagesTwoWay[0][1] = Sprite.player_right_1.getFxImage(Sprite.player_right_1.get_realWidth(), Sprite.player_right_1.get_realHeight());
        imagesTwoWay[1][1] = Sprite.player_down_1.getFxImage(Sprite.player_down_1.get_realWidth(), Sprite.player_down_1.get_realHeight());
        imagesTwoWay[2][1] = Sprite.player_left_1.getFxImage(Sprite.player_left_1.get_realWidth(), Sprite.player_left_1.get_realHeight());
        imagesTwoWay[3][1] = Sprite.player_up_1.getFxImage(Sprite.player_up_1.get_realWidth(), Sprite.player_up_1.get_realHeight());

        imagesTwoWay[0][2] = Sprite.player_right_2.getFxImage(Sprite.player_right_2.get_realWidth(), Sprite.player_right_2.get_realHeight());
        imagesTwoWay[1][2] = Sprite.player_down_2.getFxImage(Sprite.player_down_2.get_realWidth(), Sprite.player_down_2.get_realHeight());
        imagesTwoWay[2][2] = Sprite.player_left_2.getFxImage(Sprite.player_left_2.get_realWidth(), Sprite.player_left_2.get_realHeight());
        imagesTwoWay[3][2] = Sprite.player_up_2.getFxImage(Sprite.player_up_2.get_realWidth(), Sprite.player_up_2.get_realHeight());
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void setCoordinate(char[][] mapGame) {
        setKeyCodeFromDirection();
        setCoordinateAfterMove();
        if (checkWallCollision(mapGame)) {
            setCoordinateAfterMoveReverse();
        }
        setCoordinatesRenderMap();
    }

    public void updateSprite() {
        if (direction == 4) {
            numberSprite = 0;
        } else {

            numberSprite = indexAnimate / (FRAME_PER_ONE_BOMBER / (BOMBER_SPRITE - 1)) + 1;
            indexAnimate += 1;
            if (indexAnimate >= FRAME_PER_ONE_BOMBER) {
                indexAnimate = 0;
                direction = 4;
            }

        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        updateSprite();
        if (direction == 4) {
            imageRender = imagesTwoWay[directionPrevious][0];
        } else {
            imageRender = imagesTwoWay[direction][numberSprite];
            directionPrevious = direction;
        }
        gc.drawImage(imageRender, x - xHide, y - yHide);
    }

    public void setKeyCodeFromDirection() {
        if (direction == 0) {
            keyCode = KeyCode.RIGHT;
        } else if (direction == 1) {
            keyCode = KeyCode.DOWN;
        } else if (direction == 2) {
            keyCode = KeyCode.LEFT;
        } else if (direction == 3) {
            keyCode = KeyCode.UP;
        } else if (direction == 4) {
            keyCode = null;
        }
    }

    @Override
    public void setCoordinateAfterMoveReverse() {
        if (keyCode == KeyCode.RIGHT) {
            this.x -= speed;
        } else if (keyCode == KeyCode.LEFT) {
            this.x += speed;
        } else if (keyCode == KeyCode.UP) {
            this.y += speed;
        } else if (keyCode == KeyCode.DOWN) {
            this.y -= speed;
        }
    }

    @Override
    public void setCoordinateAfterMove() {
        if (keyCode == KeyCode.RIGHT) {
            this.x += speed;
        } else if (keyCode == KeyCode.LEFT) {
            this.x -= speed;
        } else if (keyCode == KeyCode.UP) {
            this.y -= speed;
        } else if (keyCode == KeyCode.DOWN) {
            this.y += speed;
        }
    }


}
