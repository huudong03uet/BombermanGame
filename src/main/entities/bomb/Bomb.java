package main.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.AnimateEntity;

import static main.PropertiesConstant.*;
import static main.graphics.Sprite.*;

public class Bomb extends AnimateEntity {
    protected Image[] images = new Image[BOMB_SPRITE];
    protected Image[] imageExplode = new Image[BOMB_SPRITE];
    protected int countBomb = 0;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Bomb(int x, int y) {
        super(x, y, bomb.getFxImage(bomb.get_realWidth(), bomb.get_realHeight()));
        images[0] = bomb.getFxImage(bomb.get_realWidth(), bomb.get_realHeight());
        images[1] = bomb_1.getFxImage(bomb_1.get_realWidth(), bomb_1.get_realHeight());
        images[2] = bomb_2.getFxImage(bomb_2.get_realWidth(), bomb_2.get_realHeight());

        imageExplode[0] = bomb_exploded.getFxImage(bomb_exploded.get_realWidth(), bomb_exploded.get_realHeight());
        imageExplode[1] = bomb_exploded1.getFxImage(bomb_exploded1.get_realWidth(), bomb_exploded1.get_realHeight());
        imageExplode[2] = bomb_exploded2.getFxImage(bomb_exploded2.get_realWidth(), bomb_exploded2.get_realHeight());
    }
    public void update() {
    }



    @Override
    public void render(GraphicsContext gc) {

        int indexAnimate = (countBomb % 30) / ((FRAME_PER_SECOND / 2) / BOMB_SPRITE);
        img = images[indexAnimate];
        super.render(gc);
        countBomb++;
        if (countBomb >= TIME_REMAIN * 3) {
            countBomb = 0;
            isExploded = true;
        }
    }
}
