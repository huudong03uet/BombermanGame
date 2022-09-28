package main.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends  Entity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
