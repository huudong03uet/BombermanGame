package main.frameGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.BombermanGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static main.settings.PropertiesConstant.HEIGHT;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.StatusGame.*;

public class GameOver {
    private final double SCALE_BUTTON = 1.2;
    private GraphicsContext gc;
    Image backGroundImage;
    Image logo;
    Button startMenuButton = new Button();
    Button exitGameButton = new Button();
    int xRender = 0;

    public GameOver(Canvas canvas) throws IOException {
        gc = canvas.getGraphicsContext2D();

        backGroundImage = new Image(Files.newInputStream(Paths.get("res\\textures\\StartGame\\bg.png")));
        logo = new Image(Files.newInputStream(Paths.get("res\\textures\\StartGame\\logo2.png")));
            setStartMenuButton();
            setExitGameButton();

            BombermanGame.root.getChildren().add(startMenuButton);
            BombermanGame.root.getChildren().add(exitGameButton);
    }

    public void startLoop() {
        gc.drawImage(backGroundImage, -xRender, 0, WIDTH, HEIGHT);
        gc.drawImage(backGroundImage, WIDTH - xRender, 0, WIDTH, HEIGHT);
        xRender++;
        if (xRender == WIDTH) xRender = 0;
        gc.drawImage(logo, (WIDTH - logo.getWidth()) / 2, 50);
    }


    public void setStartMenuButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\startGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());


        startMenuButton.setGraphic(img);
        startMenuButton.setBackground(null);
        startMenuButton.setLayoutX((WIDTH - image.getWidth()) / 2 - 150);
        startMenuButton.setLayoutY((HEIGHT - image.getHeight()) / 2 - 100);
        startMenuButton.setText("Menu");

        startMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                status = GAME_MENU;
                removeButtonInRoot();
            }
        });

        startMenuButton.setOnMouseEntered(e -> {
            startMenuButton.setScaleX(SCALE_BUTTON);
            startMenuButton.setScaleY(SCALE_BUTTON);
        });

        startMenuButton.setOnMouseExited(e -> {
            startMenuButton.setScaleX(1);
            startMenuButton.setScaleY(1);
        });
    }

    public void setExitGameButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\exitGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        exitGameButton.setGraphic(img);
        exitGameButton.setBackground(null);
        exitGameButton.setLayoutX((WIDTH - image.getWidth()) / 2 + 150);
        exitGameButton.setLayoutY((HEIGHT - image.getHeight()) / 2 - 100);
        exitGameButton.setText("Exit Game");

        exitGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });

        exitGameButton.setOnMouseEntered(e -> {
            exitGameButton.setScaleX(SCALE_BUTTON);
            exitGameButton.setScaleY(SCALE_BUTTON);
        });

        exitGameButton.setOnMouseExited(e -> {
            exitGameButton.setScaleX(1);
            exitGameButton.setScaleY(1);
        });
    }

    public void removeButtonInRoot() {
        BombermanGame.root.getChildren().remove(startMenuButton);
        BombermanGame.root.getChildren().remove(exitGameButton);
    }
}
