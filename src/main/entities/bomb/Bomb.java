package main.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.AnimateEntity;

import static main.PropertiesConstant.*;
import static main.graphics.Sprite.*;

public class Bomb extends AnimateEntity {
    protected Image[] images = new Image[BOMB_SPRITE];
    protected int countBomb = 0;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void update() {
        update();
    }

    public Bomb(int x, int y) {
        super(x, y, bomb.getFxImage(bomb.get_realWidth(), bomb.get_realHeight()));
        images[0] = bomb.getFxImage(bomb.get_realWidth(), bomb.get_realHeight());
        images[1] = bomb_1.getFxImage(bomb_1.get_realWidth(), bomb_1.get_realHeight());
        images[2] = bomb_2.getFxImage(bomb_2.get_realWidth(), bomb_2.get_realHeight());
    }

    @Override
    public void render(GraphicsContext gc) {
        int indexAnimate = (countBomb % 60) / ((FRAME_PER_SECOND) / BOMB_SPRITE);
        img = images[indexAnimate];
        super.render(gc);
        countBomb++;
        if (countBomb >= timeToExplore) {
            countBomb = 0;
        }
    }
}
