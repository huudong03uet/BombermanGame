package main.entities.items;

import javafx.scene.image.Image;
import main.entities.Entity;

public abstract class Item extends Entity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }
}
