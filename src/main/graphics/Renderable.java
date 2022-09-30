package main.graphics;

import javafx.scene.canvas.GraphicsContext;

public interface Renderable {
    void update();

    void render(GraphicsContext gc);
}
