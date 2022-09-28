package main.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Portal extends Entity {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    @Override
    public void update() {

    }

}
