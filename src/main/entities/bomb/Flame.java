package main.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.AnimateEntity;

import static main.settings.PropertiesConstant.*;
import static main.settings.PropertiesStatic.*;
import static main.graphics.Sprite.*;

public class Flame extends AnimateEntity {
    protected int countFlame = 0;
    protected Image[] imagesCenter = new Image[FLAME_CENTER_SPRITE];
    protected Image[] imagesHorizontal = new Image[FLAME_HORIZONTAL_SPRITE];
    protected Image[] imageHorizontalLeft = new Image[FLAME_HORIZONTAL_LEFT];
    protected Image[] imageHorizontalRight = new Image[FLAME_HORIZONTAL_RIGHT];
    protected Image[] imagesVertical = new Image[FLAME_VERTICAL_SPRITE];
    protected Image[] imageVerticalUp = new Image[FLAME_VERTICAL_UP];
    protected Image[] imageVerticalDown = new Image[FLAME_VERTICAL_DOWN];

    protected int lengthFlame = lengthFlameDefault;

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Flame(int x, int y) {
        super(x, y, explosion_vertical.getFxImage(explosion_vertical.get_realWidth(), explosion_vertical.get_realHeight()));
        imagesCenter[2] = bomb_exploded.getFxImage(bomb_exploded.get_realWidth(), bomb_exploded1.get_realHeight());
        imagesCenter[1] = bomb_exploded1.getFxImage(bomb_exploded1.get_realWidth(), bomb_exploded1.get_realHeight());
        imagesCenter[0] = bomb_exploded2.getFxImage(bomb_exploded2.get_realWidth(), bomb_exploded2.get_realHeight());

        //Horizontal
        imagesHorizontal[2] = explosion_horizontal.getFxImage(
                explosion_horizontal.get_realWidth(), explosion_horizontal.get_realHeight()
        );
        imagesHorizontal[1] = explosion_horizontal1.getFxImage(
                explosion_horizontal1.get_realWidth(), explosion_horizontal1.get_realHeight()
        );
        imagesHorizontal[0] = explosion_horizontal2.getFxImage(
                explosion_horizontal2.get_realWidth(), explosion_horizontal2.get_realHeight()
        );

        //Left

        imageHorizontalLeft[2] = explosion_horizontal_left_last.getFxImage(
                explosion_horizontal_left_last.get_realWidth(), explosion_horizontal_left_last.get_realHeight()
        );
        imageHorizontalLeft[1] = explosion_horizontal_left_last1.getFxImage(
                explosion_horizontal_left_last1.get_realWidth(), explosion_horizontal_left_last1.get_realHeight()
        );
        imageHorizontalLeft[0] = explosion_horizontal_left_last2.getFxImage(
                explosion_horizontal_left_last2.get_realWidth(), explosion_horizontal_left_last2.get_realHeight()
        );

        // Right

        imageHorizontalRight[2] = explosion_horizontal_right_last.getFxImage(
                explosion_horizontal_right_last.get_realWidth(), explosion_horizontal_right_last.get_realHeight()
        );
        imageHorizontalRight[1] = explosion_horizontal_right_last1.getFxImage(
                explosion_horizontal_right_last1.get_realWidth(), explosion_horizontal_right_last2.get_realHeight()
        );
        imageHorizontalRight[0] = explosion_horizontal_right_last2.getFxImage(
                explosion_horizontal_right_last2.get_realWidth(), explosion_horizontal_right_last2.get_realHeight()
        );

        // Vertical

        imagesVertical[2] = explosion_vertical.getFxImage(
                explosion_horizontal.get_realWidth(), explosion_horizontal.get_realHeight()
        );
        imagesVertical[1] = explosion_vertical1.getFxImage(
                explosion_vertical1.get_realWidth(), explosion_vertical1.get_realHeight()
        );
        imagesVertical[0] = explosion_vertical2.getFxImage(
                explosion_vertical2.get_realWidth(), explosion_vertical2.get_realHeight()
        );

        //Up
        imageVerticalUp[2] = explosion_vertical_top_last.getFxImage(
                explosion_vertical_top_last.get_realWidth(), explosion_vertical_top_last.get_realHeight()
        );
        imageVerticalUp[1] = explosion_vertical_top_last1.getFxImage(
                explosion_vertical_top_last1.get_realWidth(), explosion_vertical_top_last1.get_realHeight()
        );
        imageVerticalUp[0] = explosion_vertical_top_last2.getFxImage(
                explosion_vertical_top_last2.get_realWidth(), explosion_vertical_top_last2.get_realHeight()
        );

        //Down

        imageVerticalDown[2] = explosion_vertical_down_last.getFxImage(
                explosion_vertical_down_last.get_realWidth(), explosion_vertical_down_last.get_realHeight()
        );
        imageVerticalDown[1] = explosion_vertical_down_last1.getFxImage(
                explosion_vertical_down_last1.get_realWidth(), explosion_vertical_down_last1.get_realHeight()
        );
        imageVerticalDown[0] = explosion_vertical_down_last2.getFxImage(
                explosion_vertical_down_last2.get_realWidth(), explosion_vertical_down_last2.get_realHeight()
        );
    }

    @Override
    public void update() {

    }

    public void renderFlame(GraphicsContext gc, int x, int y) {
        gc.drawImage(img, x - xHide, y - yHide);
    }

    public boolean checkHideFlameByEntity(int x, int y) {
        if (y < 0 || y > HEIGHT_TILE - 1 || x < 0 || x > WIDTH_TILE - 1) return false;
        if (map[y][x] == '*') {
            return true;
        }
        if (map[y][x] == '#') {
            return true;
        }
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {

        int indexAnimate = countFlame % FRAME_PER_SECOND / (FRAME_PER_SECOND / FLAME_HORIZONTAL_SPRITE);

        if (!checkHideFlameByEntity(getXCenter(), getYCenter())) {
            img = imagesCenter[indexAnimate];
            renderFlame(gc, x, y);
        }

        if (lengthFlame == 2) {
            if (!checkHideFlameByEntity(getXCenter() + 1, getYCenter())) {
                img = imagesHorizontal[indexAnimate];
                renderFlame(gc, x + TILE_SIZE * SCALE, y);
                if (!checkHideFlameByEntity(getXCenter() + lengthFlame, getYCenter())) {
                    img = imageHorizontalRight[indexAnimate];
                    renderFlame(gc, x + TILE_SIZE * SCALE * lengthFlame, y);
                }
            }

            if (!checkHideFlameByEntity(getXCenter() - 1, getYCenter())) {
                img = imagesHorizontal[indexAnimate];
                renderFlame(gc, x - TILE_SIZE * SCALE, y);
                if (!checkHideFlameByEntity(getXCenter() - lengthFlame, getYCenter())) {
                    img = imageHorizontalLeft[indexAnimate];
                    renderFlame(gc, x - TILE_SIZE * SCALE * lengthFlame, y);
                }
            }

            if (!checkHideFlameByEntity(getXCenter(), getYCenter() - 1)) {
                img = imagesVertical[indexAnimate];
                renderFlame(gc, x, y - TILE_SIZE * SCALE);
                if (!checkHideFlameByEntity(getXCenter(), getYCenter() - lengthFlame)) {
                    img = imageVerticalUp[indexAnimate];
                    renderFlame(gc, x, y - TILE_SIZE * SCALE * lengthFlame);
                }
            }

            if (!checkHideFlameByEntity(getXCenter(), getYCenter() + 1)) {
                img = imagesVertical[indexAnimate];
                renderFlame(gc, x, y + TILE_SIZE * SCALE);
                if (!checkHideFlameByEntity(getXCenter(), getYCenter() + lengthFlame)) {
                    img = imageVerticalDown[indexAnimate];
                    renderFlame(gc, x, y + TILE_SIZE * SCALE * lengthFlame);
                }
            }
        }

        if (lengthFlame == 1) {
            if (!checkHideFlameByEntity(getXCenter() + lengthFlame, getYCenter())) {
                img = imageHorizontalRight[indexAnimate];
                renderFlame(gc, x + TILE_SIZE * SCALE * lengthFlame, y);
            }

            if (!checkHideFlameByEntity(getXCenter() - lengthFlame, getYCenter())) {
                img = imageHorizontalLeft[indexAnimate];
                renderFlame(gc, x - TILE_SIZE * SCALE * lengthFlame, y);
            }

            if (!checkHideFlameByEntity(getXCenter(), getYCenter() + lengthFlame)) {
                img = imageVerticalDown[indexAnimate];
                renderFlame(gc, x, y + TILE_SIZE * SCALE * lengthFlame);
            }

            if (!checkHideFlameByEntity(getXCenter(), getYCenter() - lengthFlame)) {
                img = imageVerticalUp[indexAnimate];
                renderFlame(gc, x, y - TILE_SIZE * SCALE * lengthFlame);
            }
        }
        countFlame += 2;
        if(isHavingFlame) {
            countFlame -= 1;
            if (countFlame >= TIME_REMAIN * 2) {
                isRemove = true;
                countFlame = 0;
                isHavingFlame = false;
            }
        }
        else {
            if (countFlame >= TIME_REMAIN * 2) {
                isRemove = true;
                countFlame = 0;
            }
        }
    }

    @Override
    public void increaseScore() {
        score -= 0;
    }
}
