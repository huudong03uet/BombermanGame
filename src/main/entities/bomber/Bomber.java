package main.entities.bomber;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.entities.animateEntity;

import static main.PropertiesConstant.*;

public class Bomber extends animateEntity {


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    private void setCoordinateAfterMove(KeyCode keyCode) {
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

    private void setCoordinateAfterMoveReverse(KeyCode keyCode) {
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


    public void setDirectionBomber(KeyCode keyCode, char[][] mapGame) {
        setCoordinateAfterMove(keyCode);
        if(checkWallCollision(mapGame)) {
            setCoordinateAfterMoveReverse(keyCode);
        }
        setCoordinatesRenderMap();
    }
    @Override
    public void update() {

    }


}
