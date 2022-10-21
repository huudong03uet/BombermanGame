package main.entities.tile;

import javafx.scene.image.Image;
import main.entities.AnimateEntity;

import static main.settings.PropertiesStatic.score;

public abstract class StaticEntity extends AnimateEntity {
    public StaticEntity(int x, int y, Image img) {
        super(x, y, img);
    }
    @Override
    public void increaseScore() {
        score += 0;
    }
}
