package main.entities.items;

import javafx.scene.image.Image;
import static main.graphics.Sprite.*;

public class FlamePassItem extends Item{

    public FlamePassItem(int x, int y, Image img) {
        super(x, y, img);
    }
    public FlamePassItem(int x, int y) {
        super(x, y, powerup_flamepass.getFxImage(powerup_flamepass.get_realWidth(), powerup_flamepass.get_realHeight()));
        img = powerup_flamepass.getFxImage(powerup_flamepass.get_realWidth(), powerup_flamepass.get_realHeight());
    }
    @Override
    public void update() {

    }
}
