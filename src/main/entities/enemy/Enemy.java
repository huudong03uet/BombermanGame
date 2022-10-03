package main.entities.enemy;

import javafx.scene.image.Image;
import main.entities.CanMoveEntity;

public abstract class Enemy extends CanMoveEntity {
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }


    public abstract void updateSprite();

    public abstract void findCoordinatesRenderFromMap(char[][] mapGame);
}
