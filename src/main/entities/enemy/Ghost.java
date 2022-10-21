package main.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.Entity;

import java.util.ArrayList;

import static main.settings.PropertiesConstant.*;
import static main.graphics.Sprite.*;
import static main.settings.PropertiesStatic.score;

public class Ghost extends Enemy {
    protected double speed = SPEED_ENEMY;
    private final int FRAME_PER_ONE = FRAME_PER_SECOND / 2;
    protected Image[][] imagesTwoWay = new Image[4][BALLOOM_SPRITE];
    protected int timeHidden = 0;
    protected boolean isHidden = false;

    public Ghost(int x, int y, Image img) {
        super(x, y, img);
    }

    public Ghost(int x, int y) {
        super(x, y, ghost_right1.getFxImage(ghost_right1.get_realWidth(), ghost_right1.get_realHeight()));
        imagesTwoWay[RIGHT][0] = ghost_right1.getFxImage(ghost_right1.get_realWidth(), ghost_right1.get_realHeight());
        imagesTwoWay[DOWN][0] = ghost_right1.getFxImage(ghost_right1.get_realWidth(), ghost_right1.get_realHeight());
        imagesTwoWay[LEFT][0] = ghost_left1.getFxImage(ghost_left1.get_realWidth(), ghost_left1.get_realHeight());
        imagesTwoWay[UP][0] = ghost_left1.getFxImage(pass_left1.get_realWidth(), ghost_left1.get_realHeight());

        imagesTwoWay[RIGHT][1] = ghost_right2.getFxImage(ghost_right2.get_realWidth(), ghost_right2.get_realHeight());
        imagesTwoWay[DOWN][1] = ghost_right2.getFxImage(ghost_right2.get_realWidth(), ghost_right2.get_realHeight());
        imagesTwoWay[LEFT][1] = ghost_left2.getFxImage(ghost_left2.get_realWidth(), ghost_left2.get_realHeight());
        imagesTwoWay[UP][1] = ghost_left2.getFxImage(ghost_left2.get_realWidth(), ghost_left2.get_realHeight());

        imagesTwoWay[RIGHT][2] = ghost_right3.getFxImage(ghost_right3.get_realWidth(), ghost_right3.get_realHeight());
        imagesTwoWay[DOWN][2] = ghost_right3.getFxImage(ghost_right3.get_realWidth(), ghost_right3.get_realHeight());
        imagesTwoWay[LEFT][2] = ghost_left3.getFxImage(ghost_left3.get_realWidth(), ghost_left3.get_realHeight());
        imagesTwoWay[UP][2] = ghost_left3.getFxImage(ghost_left3.get_realWidth(), ghost_left3.get_realHeight());

        imagesExploded[0] = ghost_dead.getFxImage(ghost_dead.get_realWidth(), ghost_dead.get_realHeight());

    }

    @Override
    public void setCoordinate(char[][] mapGame, Entity player) {
        if (directionAnimate == STOP) {
            return;
        }
        if (distanceObject(this, player) < DISTANCE_HIDDEN_GHOST) {
            isHidden = false;
        }
        setDirection(mapGame, player);
        setCoordinateAfterMove();
    }

    public void setDirection(char[][] mapGame, Entity player) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int directionAnimateNow = directionAnimate;
        for (int i = 0; i < 4; i++) {
            directionAnimate = i;
            setCoordinateAfterMove();
            if (checkWallCollision(mapGame) == false) {
                arrayList.add(i);
            }
            setCoordinateAfterMoveReverse();

        }
        if (arrayList.size() == 0) {
            directionAnimate = STOP;
            return;
        }
        if (arrayList.size() == 2) {
            if (directionAnimateNow == 0 && arrayList.contains(2) || directionAnimateNow == 2 && arrayList.contains(0)
                    || directionAnimateNow == 1 && arrayList.contains(3) || directionAnimateNow == 3 && arrayList.contains(1)) {

                directionAnimate = directionAnimateNow;
            } else {
                if (directionAnimateNow == arrayList.get(0)) {
                    directionAnimate = arrayList.get(1);
                } else {
                    directionAnimate = arrayList.get(0);
                }
            }
        } else if (arrayList.size() == 1) {
            directionAnimate = arrayList.get(0);
        } else {
            if (directionAnimateNow == 0) {
                arrayList.remove((Integer) 2);
            } else if (directionAnimateNow == 1) {
                arrayList.remove((Integer) 3);
            } else if (directionAnimateNow == 2) {
                arrayList.remove((Integer) 0);
            } else if (directionAnimateNow == 3) {
                arrayList.remove((Integer) 1);
            }

            double min = 10000;
            ArrayList<Double> arrayList1 = new ArrayList<>();

            for (int i = 0; i < arrayList.size(); i++) {
                directionAnimate = arrayList.get(i);
                setCoordinateAfterMove();

                double distance = distanceObject(this, player);
                min = Math.min(min, distance);
                arrayList1.add(distance);

                setCoordinateAfterMoveReverse();
            }

            for (int i = 0; i < arrayList1.size(); i++) {
                if (arrayList1.get(i) == min) {
                    directionAnimate = arrayList.get(i);
                    break;
                }
            }
        }
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
        isProtected += 1;
        if (isProtected >= TIME_HIDDEN_GHOST) {
            if (isHidden == false && isExploded == false) {
                isHidden = true;
            } else {
                isHidden = false;
            }
            isProtected = 0;
        }
        if (isHidden == true) {
            return;
        }
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

    @Override
    public void increaseScore() {
        score += 35;
    }
}
