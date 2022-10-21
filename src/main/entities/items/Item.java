package main.entities.items;

import javafx.scene.image.Image;
import main.entities.Entity;

import static main.settings.PropertiesStatic.score;

public abstract class Item extends Entity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void increaseScore() {
        // TODO Auto-generated method stub
        score += 10;
    }
}
