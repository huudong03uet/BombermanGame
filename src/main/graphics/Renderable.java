package main.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Screen;

public interface Renderable {
    void update();
    void render(GraphicsContext gc);
}
