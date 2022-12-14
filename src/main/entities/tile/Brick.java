package main.entities.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.soundSetting.Sound;

import static main.settings.PropertiesConstant.*;
import static main.graphics.Sprite.*;
import static main.settings.PropertiesStatic.score;

public class Brick extends StaticEntity {
    protected Image[] images = new Image[BRICK_EXPLOSION];
    protected int countIsRemove = 0;
    private Sound brickCrash = new Sound();

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        images[0] = brick_exploded.getFxImage(brick_exploded.get_realWidth(), brick_exploded.get_realHeight());
        images[1] = brick_exploded1.getFxImage(brick_exploded1.get_realWidth(), brick_exploded1.get_realHeight());
        images[2] = brick_exploded2.getFxImage(brick_exploded2.get_realWidth(), brick_exploded2.get_realHeight());
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        if (isExploded == false) {
            super.render(gc);
        } else {
            int indexAnimate = (countIsRemove % TIME_REMAIN) / (TIME_REMAIN / BRICK_EXPLOSION);
            img = images[indexAnimate];
            super.render(gc);
            countIsRemove++;
            if(countIsRemove == 1) {
                brickCrash.playSE(3);
            }
            if (countIsRemove >= TIME_REMAIN) {
                countIsRemove = 0;
                isRemove = true;
            }
        }
    }

    @Override
    public void increaseScore() {
        score += 5;
    }
}
