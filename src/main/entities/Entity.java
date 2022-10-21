package main.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.graphics.Renderable;

import static main.settings.PropertiesConstant.SCALE;
import static main.settings.PropertiesConstant.TILE_SIZE;
import static main.settings.PropertiesStatic.xHide;
import static main.settings.PropertiesStatic.yHide;
import static main.graphics.Sprite.SCALED_SIZE;

public abstract class Entity implements Renderable {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected int xUnit;
    protected int yUnit;
    protected Image img;

    protected boolean isRemove = false;

    /**
     * The entity can be rendered on the map.
     */
    protected boolean isExploded = false;
    protected boolean isHidden = true;
    protected int isProtected = 0;
    public boolean getIsExploded() {
        return isExploded;
    }

    public void setIsExploded(boolean isExploded) {
        this.isExploded = isExploded;
    }

    public boolean getIsRemove() {
        if(isRemove) {
            increaseScore();
        }
        return isRemove;
    }

    public abstract void increaseScore();

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        this.x = xUnit * SCALED_SIZE;
        this.y = yUnit * SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x - xHide, y - yHide);
    }

    public int getxUnit() {
        return xUnit;
    }

    public int getXCenter() {
        return (int) ((x + img.getWidth() / 2) * 1.0 / (SCALE * TILE_SIZE));
    }

    public int getYCenter() {
        return (int) ((y + img.getHeight() / 2) * 1.0 / (SCALE * TILE_SIZE));
    }

    public int getyUnit() {
        return yUnit;
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
