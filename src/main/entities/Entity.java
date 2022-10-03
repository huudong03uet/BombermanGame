package main.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.graphics.Renderable;
import main.graphics.Sprite;

import static main.PropertiesStatic.*;

public abstract class Entity implements Renderable {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc){
        gc.drawImage(img, x - xHide, y - yHide);
    }

    public int getY() {
        return y;
    }

    public Image getSprite() {
        return img;
    }
    public abstract void update();

    public int getX() {
        return x;
    }
}
