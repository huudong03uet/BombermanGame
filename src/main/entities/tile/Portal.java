package main.entities.tile;

import javafx.scene.image.Image;

import static main.settings.PropertiesStatic.score;

public class Portal extends StaticEntity {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public void increaseScore() {
        score += 100;
    }

}
