package main.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public void setDirectionBomber(KeyCode keyCode) {
        if(keyCode == KeyCode.RIGHT){
            x++;
        } else if(keyCode == KeyCode.LEFT) {
            x--;
        } else if(keyCode == KeyCode.UP) {
            y--;
        } else if(keyCode == KeyCode.DOWN) {
            y++;
        }
    }
    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
