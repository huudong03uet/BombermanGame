package main.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.graphics.Sprite;

import java.util.ArrayList;

import static main.PropertiesConstant.*;

public class Balloom extends Enemy {
    private final int FRAME_PER_ONE = FRAME_PER_SECOND / 2;
    protected Image[][] imagesTwoWay = new Image[4][BALLOOM_SPRITE];

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void setCoordinate(char[][] mapGame) {
        setDirection(mapGame);
        setCoordinateAfterMove();
    }

    public Balloom(int x, int y) {
        super(x, y, Sprite.balloom_right1.getFxImage(Sprite.balloom_right1.get_realWidth(), Sprite.balloom_right1.get_realHeight()));
        imagesTwoWay[0][0] = Sprite.balloom_right1.getFxImage(Sprite.balloom_right1.get_realWidth(), Sprite.balloom_right2.get_realHeight());
        imagesTwoWay[1][0] = Sprite.balloom_right1.getFxImage(Sprite.balloom_left1.get_realWidth(), Sprite.balloom_left2.get_realHeight());
        imagesTwoWay[2][0] = Sprite.balloom_left1.getFxImage(Sprite.balloom_right1.get_realWidth(), Sprite.balloom_right2.get_realHeight());
        imagesTwoWay[3][0] = Sprite.balloom_left1.getFxImage(Sprite.balloom_left1.get_realWidth(), Sprite.balloom_left2.get_realHeight());

        imagesTwoWay[0][1] = Sprite.balloom_right2.getFxImage(Sprite.balloom_right1.get_realWidth(), Sprite.balloom_right2.get_realHeight());
        imagesTwoWay[1][1] = Sprite.balloom_right2.getFxImage(Sprite.balloom_left1.get_realWidth(), Sprite.balloom_left2.get_realHeight());
        imagesTwoWay[2][1] = Sprite.balloom_left2.getFxImage(Sprite.balloom_right1.get_realWidth(), Sprite.balloom_right2.get_realHeight());
        imagesTwoWay[3][1] = Sprite.balloom_left2.getFxImage(Sprite.balloom_left1.get_realWidth(), Sprite.balloom_left2.get_realHeight());

        imagesTwoWay[0][2] = Sprite.balloom_right3.getFxImage(Sprite.balloom_right1.get_realWidth(), Sprite.balloom_right2.get_realHeight());
        imagesTwoWay[1][2] = Sprite.balloom_right3.getFxImage(Sprite.balloom_left1.get_realWidth(), Sprite.balloom_left2.get_realHeight());
        imagesTwoWay[2][2] = Sprite.balloom_left3.getFxImage(Sprite.balloom_right1.get_realWidth(), Sprite.balloom_right2.get_realHeight());
        imagesTwoWay[3][2] = Sprite.balloom_left3.getFxImage(Sprite.balloom_left1.get_realWidth(), Sprite.balloom_left2.get_realHeight());
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
    public void setCoordinateAfterMove() {
        // 0: right, 1: down, 2: left, 3: up
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

    public void setDirection(char[][] mapGame) {
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

            int numberRandom = (int) (Math.random() * arrayList.size());
            directionAnimate = arrayList.get(numberRandom);
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
    public void findCoordinatesRenderFromMap(char[][] mapGame) {
        for (int i = 0; i < HEIGHT_TILE; i++) {
            for (int j = 0; j < WIDTH_TILE; j++) {
                if (mapGame[i][j] == '1') {
                    y = i * TILE_SIZE * SCALE;
                    x = j * TILE_SIZE * SCALE;
                    mapGame[i][j] = ' ';
                    return;
                }
            }
        }
    }
}
