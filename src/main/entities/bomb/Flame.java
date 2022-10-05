package main.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.entities.AnimateEntity;

import static main.graphics.Sprite.*;
import static main.PropertiesConstant.*;

public class Flame extends AnimateEntity {
    protected int countFlame = 0;
    protected int Selection;
    protected Image[] imagesHorizontal = new Image[FLAME_HORIZONTAL_SPRITE];
    protected Image[] imageHorizontalLeft = new Image[FLAME_HORIZONTAL_LEFT];
    protected Image[] imageHorizontalRight = new Image[FLAME_HORIZONTAL_RIGHT];
    protected Image[] imagesVertical = new Image[FLAME_VERTICAL_SPRITE];
    protected Image[] imageVerticalUp = new Image[FLAME_VERTICAL_UP];
    protected Image[] imageVerticalDown = new Image[FLAME_VERTICAL_DOWN];

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Flame(int x, int y, int selection) {
        super(x, y, explosion_vertical.getFxImage(explosion_vertical.get_realWidth(), explosion_vertical.get_realHeight()));
        this.Selection = selection;
        //Horizontal

        imagesHorizontal[0] = explosion_horizontal.getFxImage(
                explosion_horizontal.get_realWidth(), explosion_horizontal.get_realHeight()
        );
        imagesHorizontal[1] = explosion_horizontal1.getFxImage(
                explosion_horizontal1.get_realWidth(), explosion_horizontal1.get_realHeight()
        );
        imagesHorizontal[2] = explosion_horizontal2.getFxImage(
                explosion_horizontal2.get_realWidth(), explosion_horizontal2.get_realHeight()
        );

        //Left

        imageHorizontalLeft[0] = explosion_horizontal_left_last.getFxImage(
                explosion_horizontal_left_last.get_realWidth(), explosion_horizontal_left_last.get_realHeight()
        );
        imageHorizontalLeft[1] = explosion_horizontal_left_last1.getFxImage(
                explosion_horizontal_left_last1.get_realWidth(), explosion_horizontal_left_last1.get_realHeight()
        );
        imageHorizontalLeft[2] = explosion_horizontal_left_last2.getFxImage(
                explosion_horizontal_left_last2.get_realWidth(), explosion_horizontal_left_last2.get_realHeight()
        );

        // Right

        imageHorizontalRight[0] = explosion_horizontal_right_last.getFxImage(
                explosion_horizontal_right_last.get_realWidth(), explosion_horizontal_right_last.get_realHeight()
        );
        imageHorizontalRight[1] = explosion_horizontal_right_last1.getFxImage(
                explosion_horizontal_right_last1.get_realWidth(), explosion_horizontal_right_last2.get_realHeight()
        );
        imageHorizontalRight[2] = explosion_horizontal_right_last2.getFxImage(
                explosion_horizontal_right_last2.get_realWidth(), explosion_horizontal_right_last2.get_realHeight()
        );

        // Vertical

        imagesVertical[0] = explosion_vertical.getFxImage(
                explosion_horizontal.get_realWidth(), explosion_horizontal.get_realHeight()
        );
        imagesVertical[1] = explosion_vertical1.getFxImage(
                explosion_vertical1.get_realWidth(), explosion_vertical1.get_realHeight()
        );
        imagesVertical[2] = explosion_vertical2.getFxImage(
                explosion_vertical2.get_realWidth(), explosion_vertical2.get_realHeight()
        );

        //Up
        imageVerticalUp[0] = explosion_vertical_top_last.getFxImage(
                explosion_vertical_top_last.get_realWidth(),explosion_vertical_top_last.get_realHeight()
        );
        imageVerticalUp[1] = explosion_vertical_top_last1.getFxImage(
                explosion_vertical_top_last1.get_realWidth(),explosion_vertical_top_last1.get_realHeight()
        );
        imageVerticalUp[2] = explosion_vertical_top_last2.getFxImage(
                explosion_vertical_top_last2.get_realWidth(),explosion_vertical_top_last2.get_realHeight()
        );

        //Down

        imageVerticalDown[0] = explosion_vertical_down_last.getFxImage(
                explosion_vertical_down_last.get_realWidth(), explosion_vertical_down_last.get_realHeight()
        );
        imageVerticalDown[1] = explosion_vertical_down_last1.getFxImage(
                explosion_vertical_down_last1.get_realWidth(), explosion_vertical_down_last1.get_realHeight()
        );
        imageVerticalDown[2] = explosion_vertical_down_last2.getFxImage(
                explosion_vertical_down_last2.get_realWidth(), explosion_vertical_down_last2.get_realHeight()
        );
    }

    @Override
    public void update() {

    }

    @Override
    public void render(GraphicsContext gc) {
        int indexAnimate = countFlame / ((FRAME_PER_SECOND) / FLAME_HORIZONTAL_SPRITE);
        if(Selection == 0) {
            img = imagesHorizontal[indexAnimate];
        } else if(Selection == 1) {
            img = imageHorizontalRight[indexAnimate];
        } else if(Selection == 2) {
            img = imageHorizontalLeft[indexAnimate];
        } else if (Selection == 3) {
            img = imagesVertical[indexAnimate];
        } else if (Selection == 4) {
            img = imageVerticalDown[indexAnimate];
        } else if (Selection == 5) {
            img = imageVerticalUp[indexAnimate];
        }
        super.render(gc);
        countFlame++;
        if (countFlame >= timeRemain) {
            countFlame = 0;
        }
    }
}
