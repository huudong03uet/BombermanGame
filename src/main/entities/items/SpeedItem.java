package main.entities.items;

import javafx.scene.image.Image;

import static main.graphics.Sprite.powerup_speed;

public class SpeedItem  extends Item{
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public SpeedItem(int x, int y) {
        super(x, y, null);
        img = powerup_speed.getFxImage(powerup_speed.get_realWidth(), powerup_speed.get_realHeight());
    }
    @Override
    public void update() {

    }
}
