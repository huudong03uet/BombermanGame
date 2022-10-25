package main.general;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.map.MapGame;
import main.settings.PropertiesStatic;

import static main.settings.StatusGame.GAME_CHANGE_LEVEL;
import static main.settings.StatusGame.status;

public class GeneralStatic {
    public static void changeLevel(int n) {
        MapGame.level = n;
        if(n == 1) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 0;
        } else if(n == 2) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 1;
        } else if(n == 3) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 3;
        } else if(n == 4) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 3;
        } else if(n == 5) {
            PropertiesStatic.NUMBER_PASS_DEFAULT = 5;
        }
        status = GAME_CHANGE_LEVEL;
    }

    public static void renderLevelBackground(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // draw background black
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Text text = new Text();
        text.setFont(Font.font("Liberation Serif", FontWeight.BOLD, FontPosture.REGULAR, 50));
        gc.setFill(Color.WHITE);

        gc.setFont(text.getFont());
        // draw level to center of gc
        gc.fillText("Level " + MapGame.level, canvas.getWidth() / 2 - 75, canvas.getHeight() / 2);

        // sleep 1s

    }
}
