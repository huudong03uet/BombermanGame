package main.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import static main.PropertiesConstant.BOMBER_SPRITE;
import static main.PropertiesConstant.SPEED;

public abstract class CanMoveEntity extends AnimateEntity {
    private KeyCode keyCode = KeyCode.RIGHT;
    protected Image[][] imagesTwoWay = new Image[4][BOMBER_SPRITE];
    public CanMoveEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setDirection(KeyCode keyCode, char[][] mapGame) {
        setCoordinateAfterMove(keyCode);
        if(checkWallCollision(mapGame)) {
            setCoordinateAfterMoveReverse(keyCode);
        }
        setCoordinatesRenderMap();
    }

    public void setCoordinateAfterMoveReverse(KeyCode keyCode) {
        if(keyCode == KeyCode.RIGHT) {
            this.x -= SPEED;
        } else if(keyCode == KeyCode.LEFT) {
            this.x += SPEED;
        } else if(keyCode == KeyCode.UP) {
            this.y += SPEED;
        } else if(keyCode == KeyCode.DOWN) {
            this.y -= SPEED;
        }
    }

    public void setCoordinateAfterMove(KeyCode keyCode) {
        if(keyCode == KeyCode.RIGHT) {
            this.x += SPEED;
        } else if(keyCode == KeyCode.LEFT) {
            this.x -= SPEED;
        } else if(keyCode == KeyCode.UP) {
            this.y -= SPEED;
        } else if(keyCode == KeyCode.DOWN) {
            this.y += SPEED;
        }
    }

}
