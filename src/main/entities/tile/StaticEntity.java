package main.entities.tile;

import javafx.scene.image.Image;
import main.entities.AnimateEntity;

public abstract class StaticEntity extends AnimateEntity {
    public StaticEntity(int x, int y, Image img) {
        super(x, y, img);
    }
}
