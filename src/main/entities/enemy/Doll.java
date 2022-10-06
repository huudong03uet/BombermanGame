package main.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.Entity;

import java.util.ArrayList;

import static main.PropertiesConstant.*;
import static main.graphics.Sprite.*;

public class Doll extends Enemy {
    private final int FRAME_PER_ONE = FRAME_PER_SECOND / 2;
    protected Image[][] imagesTwoWay = new Image[4][DOLL_SPRITE];

    public Doll(int x, int y, Image img) {
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

    public void setCoordinate(char[][] mapGame, Entity player) {
        setDirection(mapGame, player);
        setCoordinateAfterMove();
    }

    /**
     * Override set direction for suitable with Doll follow player.
     *
     * @param mapGame - map game.
     */
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
        if (arrayList.size() == 2) {
            if (!(directionAnimateNow == 0 && arrayList.contains(2) || directionAnimateNow == 2 && arrayList.contains(0)
                    || directionAnimateNow == 1 && arrayList.contains(3) || directionAnimateNow == 3 && arrayList.contains(1))) {
                if (directionAnimateNow == arrayList.get(0)) {
                    directionAnimate = arrayList.get(1);
                } else {
                    directionAnimate = arrayList.get(0);
                }
            } else {
                directionAnimate = directionAnimateNow;
            }
        } else if (arrayList.size() == 1) {
            if (directionAnimateNow == 0) {
                directionAnimate = 2;
            } else if (directionAnimateNow == 1) {
                directionAnimate = 3;
            } else if (directionAnimateNow == 2) {
                directionAnimate = 0;
            } else if (directionAnimateNow == 3) {
                directionAnimate = 1;
            }
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

    public double distanceObject(Entity entity1, Entity entity2) {
        return Math.sqrt(Math.pow(entity1.getX() - entity2.getX(), 2) + Math.pow(entity1.getY() - entity2.getY(), 2));
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

    public Doll(int x, int y) {
        super(x, y, doll_right1.getFxImage(doll_right1.get_realWidth(), doll_right1.get_realHeight()));
        imagesTwoWay[RIGHT][0] = doll_right1.getFxImage(doll_right1.get_realWidth(), doll_right1.get_realHeight());
        imagesTwoWay[DOWN][0] = doll_left1.getFxImage(doll_left1.get_realWidth(), doll_left1.get_realHeight());
        imagesTwoWay[LEFT][0] = doll_right1.getFxImage(doll_right1.get_realWidth(), doll_right1.get_realHeight());
        imagesTwoWay[UP][0] = doll_left1.getFxImage(doll_left1.get_realWidth(), doll_left1.get_realHeight());

        imagesTwoWay[RIGHT][1] = doll_right2.getFxImage(doll_right2.get_realWidth(), doll_right2.get_realHeight());
        imagesTwoWay[DOWN][1] = doll_left2.getFxImage(doll_left2.get_realWidth(), doll_left2.get_realHeight());
        imagesTwoWay[LEFT][1] = doll_right2.getFxImage(doll_right2.get_realWidth(), doll_right2.get_realHeight());
        imagesTwoWay[UP][1] = doll_left2.getFxImage(doll_left2.get_realWidth(), doll_left2.get_realHeight());

        imagesTwoWay[RIGHT][2] = doll_right3.getFxImage(doll_right3.get_realWidth(), doll_right3.get_realHeight());
        imagesTwoWay[DOWN][2] = doll_left3.getFxImage(doll_left3.get_realWidth(), doll_left3.get_realHeight());
        imagesTwoWay[LEFT][2] = doll_right3.getFxImage(doll_right3.get_realWidth(), doll_right3.get_realHeight());
        imagesTwoWay[UP][2] = doll_left3.getFxImage(doll_left3.get_realWidth(), doll_left3.get_realHeight());
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

    public void findCoordinatesRenderFromMap(char[][] mapGame) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (mapGame[i][j] == CHAR_DOLL) {
                    y = i * TILE_SIZE * SCALE;
                    x = j * TILE_SIZE * SCALE;
                    mapGame[i][j] = CHAR_BLANK;
                    return;
                }
            }
        }
    }
}
