package main.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.Entity;

import java.util.ArrayList;

import static java.lang.Math.min;
import static main.settings.PropertiesConstant.*;
import static main.settings.PropertiesStatic.*;
import static main.graphics.Sprite.*;

public class Kondoria extends Enemy{

    private final int FRAME_PER_ONE = FRAME_PER_SECOND / 2;
    protected Image[][] imagesTwoWay = new Image[4][KONDORIA_SPRITE];
    protected int speed = SPEED_ENEMY;

    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
    }

    public Kondoria(int x, int y) {
        super(x, y, kondoria_right1.getFxImage(kondoria_right1.get_realWidth(), kondoria_right1.get_realHeight()));
        imagesTwoWay[RIGHT][0] = kondoria_right1.getFxImage(kondoria_right1.get_realWidth(), kondoria_right2.get_realHeight());
        imagesTwoWay[DOWN][0] = kondoria_right1.getFxImage(kondoria_right1.get_realWidth(), kondoria_right1.get_realHeight());
        imagesTwoWay[LEFT][0] = kondoria_left1.getFxImage(kondoria_left1.get_realWidth(), kondoria_left1.get_realHeight());
        imagesTwoWay[UP][0] = kondoria_left1.getFxImage(kondoria_left1.get_realWidth(), kondoria_left2.get_realHeight());

        imagesTwoWay[RIGHT][1] = kondoria_right2.getFxImage(kondoria_right1.get_realWidth(), kondoria_right2.get_realHeight());
        imagesTwoWay[DOWN][1] = kondoria_right2.getFxImage(kondoria_right1.get_realWidth(), kondoria_right1.get_realHeight());
        imagesTwoWay[LEFT][1] = kondoria_left2.getFxImage(kondoria_left1.get_realWidth(), kondoria_left1.get_realHeight());
        imagesTwoWay[UP][1] = kondoria_left2.getFxImage(kondoria_left1.get_realWidth(), kondoria_left2.get_realHeight());

        imagesTwoWay[RIGHT][2] = kondoria_right3.getFxImage(kondoria_right1.get_realWidth(), kondoria_right2.get_realHeight());
        imagesTwoWay[DOWN][2] = kondoria_right3.getFxImage(kondoria_right1.get_realWidth(), kondoria_right1.get_realHeight());
        imagesTwoWay[LEFT][2] = kondoria_left3.getFxImage(kondoria_left1.get_realWidth(), kondoria_left1.get_realHeight());
        imagesTwoWay[UP][2] = kondoria_left3.getFxImage(kondoria_left1.get_realWidth(), kondoria_left2.get_realHeight());

        imagesExploded[0] = kondoria_dead.getFxImage(kondoria_dead.get_realWidth(), kondoria_dead.get_realHeight());
    }

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

    public void setIsExploded(boolean isExploded) {
        if(map[getYCenter()][getXCenter()] == '#' || map[getYCenter()][getXCenter()] == '*') {
            return;
        }
        super.setIsExploded(isExploded);
    }
    public void setDirection(char[][] mapGame, Entity player) {
        if(x % (TILE_SIZE * 3) != 0 || y % (TILE_SIZE * 3) != 0) return;

        double distanceMin = 100000;
        ArrayList<Double> arrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            directionAnimate = i;

            setCoordinateAfterMove();
            arrayList.add(distanceObject(this, player));
            distanceMin = min(distanceMin, distanceObject(this, player));

            setCoordinateAfterMoveReverse();
        }

        directionAnimate = arrayList.indexOf(distanceMin);
        if(distanceMin < DISTANCE_SPEED_KONDORIA) {
            speed = SPEED_ENEMY * 2;
        } else {
            speed = SPEED_ENEMY;
        }
    }

    @Override
    public void setCoordinate(char[][] mapGame, Entity player) {
        if (directionAnimate == STOP) {
            return;
        }
        setDirection(mapGame, player);
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

    @Override
    public void setCoordinateAfterMoveReverse() {
        // 0: right, 1: down, 2: left, 3: up
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

    @Override
    public void update() {
        updateSprite();
    }

    @Override
    public void updateSprite() {
        indexAnimate += 1;
        if (indexAnimate >= FRAME_PER_ONE) {
            indexAnimate = 0;
        }
    }

    @Override
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
