package main.entities.tile;

import javafx.scene.image.Image;
import main.entities.Entity;

public abstract class StaticEntity extends Entity {
    public StaticEntity(int x, int y, Image img) {
        super(x, y, img);
    }
}
