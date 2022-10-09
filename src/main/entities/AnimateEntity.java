package main.entities;

import javafx.scene.image.Image;
import main.graphics.Renderable;

import static main.PropertiesConstant.*;
import static main.PropertiesConstant.WIDTH;
import static main.PropertiesStatic.xHide;

public abstract class AnimateEntity extends Entity
        implements Renderable {

    protected int numberSprite;


    public AnimateEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setCoordinatesRenderMap() {
        if (x < WIDTH / 2) {
            xHide = 0;
        } else if (x > WIDTH_TILE * TILE_SIZE * SCALE - WIDTH / 2) {
            xHide = WIDTH_TILE * TILE_SIZE * SCALE - WIDTH;
        } else {
            xHide = x - WIDTH / 2;
        }
    }

    public double distanceObject(Entity entity1, Entity entity2) {
        return Math.sqrt(Math.pow(entity1.getX() - entity2.getX(), 2) + Math.pow(entity1.getY() - entity2.getY(), 2));
    }

}
