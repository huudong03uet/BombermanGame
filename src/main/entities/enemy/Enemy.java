package main.entities.enemy;

import javafx.scene.image.Image;
import main.entities.CanMoveEntity;

import java.util.ArrayList;

import static main.PropertiesConstant.*;
import static main.graphics.Sprite.*;
import static main.graphics.Sprite.mob_dead3;

public abstract class Enemy extends CanMoveEntity {
    protected int speed = SPEED_ENEMY;
    protected Image[] imagesExploded = new Image[BALLOOM_SPRITE + 1];
    protected int timeRemain = 0;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        imagesExploded[1] = mob_dead1.getFxImage(mob_dead1.get_realWidth(), mob_dead1.get_realHeight());
        imagesExploded[2] = mob_dead2.getFxImage(mob_dead2.get_realWidth(), mob_dead2.get_realHeight());
        imagesExploded[3] = mob_dead3.getFxImage(mob_dead3.get_realWidth(), mob_dead3.get_realHeight());
    }

    public abstract void updateSprite();

    public abstract void findCoordinatesRenderFromMap(char[][] mapGame);

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
        if(arrayList.size() == 0) {
            directionAnimate = STOP;
            return;
        }
        if (arrayList.size() == 2) {
            if (!(directionAnimateNow == RIGHT && arrayList.contains(LEFT) || directionAnimateNow == LEFT && arrayList.contains(RIGHT)
                    || directionAnimateNow == DOWN && arrayList.contains(UP) || directionAnimateNow == UP && arrayList.contains(DOWN))) {
                if (directionAnimateNow == arrayList.get(0)) {
                    directionAnimate = arrayList.get(1);
                } else {
                    directionAnimate = arrayList.get(0);
                }
            } else {
                directionAnimate = directionAnimateNow;
            }
        } else if (arrayList.size() == 1) {
            directionAnimate = arrayList.get(0);
        } else {
            if (directionAnimateNow == RIGHT) {
                arrayList.remove((Integer) LEFT);
            } else if (directionAnimateNow == DOWN) {
                arrayList.remove((Integer) UP);
            } else if (directionAnimateNow == LEFT) {
                arrayList.remove((Integer) RIGHT);
            } else if (directionAnimateNow == UP) {
                arrayList.remove((Integer) DOWN);
            }
            int numberRandom = (int) (Math.random() * arrayList.size());
            directionAnimate = arrayList.get(numberRandom);
        }
    }
}
