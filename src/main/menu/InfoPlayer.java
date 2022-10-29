package main.menu;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.graphics.Renderable;

import static main.map.MapGame.level;
import static main.settings.PropertiesConstant.TILE_SIZE;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.PropertiesStatic.*;

public class InfoPlayer implements Renderable {
    public InfoPlayer() {

    }

    @Override
    public void render(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, TILE_SIZE * 3);

        Text text = new Text();
        text.setFont(Font.font("Liberation Serif", FontWeight.BOLD, FontPosture.REGULAR, 15));
        gc.setFill(Color.WHITE);

        gc.setFont(text.getFont());

        gc.fillText("Level: " + level, 250, 30);
        gc.fillText("Time: " + timeRemain, 400, 30);
        gc.fillText("Score: " + score, 550, 30);
        gc.fillText("Lives: " + lifeBomber, 700, 30);
    }

    @Override
    public void update() {

    }

}