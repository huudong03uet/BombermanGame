package main.frameGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.BombermanGame;
import main.general.GeneralStatic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static main.map.MapGame.level;
import static main.settings.PropertiesConstant.HEIGHT;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.StatusGame.GAME_RESTART_LEVEL;
import static main.settings.StatusGame.status;

public class LevelGame {
    private final double SCALE_BUTTON = 1.2;
    private GraphicsContext gc;
    Image backGroundImage;
    Image logo;
    Button[] levelButton = new Button[5];
    Button gameVuiButton = new Button();

    Button backButton;
    int xRender = 0;

    public LevelGame(GraphicsContext canvas) throws FileNotFoundException {
        gc = canvas;

        backGroundImage = new Image(new FileInputStream("res\\textures\\StartGame\\bg.png"));
        logo = new Image(new FileInputStream("res\\textures\\StartGame\\logo2.png"));

        setLevelGameButton();
        setGameVuiButton();
        for (int i = 0; i < levelButton.length; i++) {
            BombermanGame.root.getChildren().add(levelButton[i]);
        }
    }


    public void startLoop() {
        gc.drawImage(backGroundImage, -xRender, 0, WIDTH, HEIGHT);
        gc.drawImage(backGroundImage, WIDTH - xRender, 0, WIDTH, HEIGHT);
        xRender++;
        System.out.println(1);
        if (xRender == WIDTH) xRender = 0;
        gc.drawImage(logo, (WIDTH - logo.getWidth()) / 2, 50);
    }


    public void setLevelGameButton() throws FileNotFoundException {
        for (int i = 0; i < 5; i++) {
            levelButton[i] = new Button();

            Image image = new Image(new FileInputStream("res\\textures\\StartGame\\levelGame.png"));
            levelButton[i].setGraphic(new ImageView(image));
            levelButton[i].setStyle("-fx-background-color: transparent;");
            levelButton[i].setText("Level " + (i + 1));
            levelButton[i].setLayoutX((WIDTH - image.getWidth()) / 2);
            levelButton[i].setLayoutY((HEIGHT - image.getHeight()) / 2 - 100 + i * 75);

            int finalI = i;
            levelButton[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    level = finalI + 1;
                    status = GAME_RESTART_LEVEL;
                    GeneralStatic.changeLevel(level);
                    removeButtonInRoot();

                }
            });

            levelButton[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    levelButton[finalI].setScaleX(SCALE_BUTTON);
                    levelButton[finalI].setScaleY(SCALE_BUTTON);
                }
            });

            levelButton[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    levelButton[finalI].setScaleX(1);
                    levelButton[finalI].setScaleY(1);
                }
            });

        }
    }

    public void setGameVuiButton() {
        FileInputStream input = null;
        try {
            input = new FileInputStream("res\\textures\\StartGame\\gameVui.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight() * 0.3);
        img.setFitWidth(image.getWidth() * 0.3);

        gameVuiButton.setGraphic(img);
        gameVuiButton.setBackground(null);
        gameVuiButton.setLayoutX((WIDTH - img.getFitWidth()) - 30);
        gameVuiButton.setLayoutY((HEIGHT - img.getFitHeight()) - 20);


        gameVuiButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                URL url = null;
                try {
                    url = new URL("https://www.gamevui.vn/");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    java.awt.Desktop.getDesktop().browse(url.toURI());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        gameVuiButton.setOnMouseEntered(e -> {
            gameVuiButton.setScaleX(SCALE_BUTTON);
            gameVuiButton.setScaleY(SCALE_BUTTON);
        });

        gameVuiButton.setOnMouseExited(e -> {
            gameVuiButton.setScaleX(1);
            gameVuiButton.setScaleY(1);
        });

        BombermanGame.root.getChildren().add(gameVuiButton);
    }


    public void removeButtonInRoot() {
        for (int i = 0; i < 5; i++) {
            BombermanGame.root.getChildren().remove(levelButton[i]);
            BombermanGame.root.getChildren().remove(gameVuiButton);
        }
    }
}
