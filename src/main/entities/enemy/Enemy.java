package main.entities.enemy;

import javafx.scene.image.Image;
import main.entities.CanMoveEntity;

import java.util.ArrayList;

public abstract class Enemy extends CanMoveEntity {
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
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

}
