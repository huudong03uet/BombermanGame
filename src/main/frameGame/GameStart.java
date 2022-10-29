package main.frameGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.BombermanGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static main.settings.PropertiesConstant.HEIGHT;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.PropertiesStatic.score;
import static main.settings.StatusGame.*;

public class GameStart {
    private final double SCALE_BUTTON = 1.2;
    private GraphicsContext gc;
    Image backGroundImage;
    Image logo;
    Button startGameButton = new Button();
    Button survivalGame = new Button();
    Button levelGameButton = new Button();


    Button instructionButton = new Button();
    Button exitGameButton = new Button();
    Button gameVuiButton = new Button();
    int xRender = 0;

    public GameStart(Canvas canvas) throws FileNotFoundException {
        gc = canvas.getGraphicsContext2D();

        backGroundImage = new Image(new FileInputStream("res\\textures\\StartGame\\bg.png"));
        logo = new Image(new FileInputStream("res\\textures\\StartGame\\logo2.png"));

        setStartGameButton();
        setSurvivalButton();
        setLevelGameButton();
        setInstructionButton();
        setExitGameButton();
        setGameVuiButton();

        setButtonInRoot();
    }


    public void startLoop() {
        gc.drawImage(backGroundImage, -xRender, 0, WIDTH, HEIGHT);
        gc.drawImage(backGroundImage, WIDTH - xRender, 0, WIDTH, HEIGHT);
        xRender++;
        if (xRender == WIDTH) xRender = 0;

        gc.drawImage(logo, (WIDTH - logo.getWidth()) / 2, 50);
    }


    public void setStartGameButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\startGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());


        startGameButton.setGraphic(img);
        startGameButton.setBackground(null);
        startGameButton.setLayoutX((WIDTH - image.getWidth()) / 2);
        startGameButton.setLayoutY((HEIGHT - image.getHeight()) / 2 - 100);

        startGameButton.setStyle("-fx-font-size: 20px; -fx-text-fill: WHITE; -fx-font-weight: bold;");
        startGameButton.setContentDisplay(ContentDisplay.CENTER);
        startGameButton.setText("Start Game");

        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                isSurvival = false;
                status = GAME_CHANGE_LEVEL;
                score = 0;
                removeButtonInRoot();
            }
        });

        startGameButton.setOnMouseEntered(e -> {
            startGameButton.setScaleX(SCALE_BUTTON);
            startGameButton.setScaleY(SCALE_BUTTON);
        });

        startGameButton.setOnMouseExited(e -> {
            startGameButton.setScaleX(1);
            startGameButton.setScaleY(1);
        });
    }

    public void setButtonInRoot() {
        BombermanGame.root.getChildren().add(startGameButton);
        BombermanGame.root.getChildren().add(survivalGame);
        BombermanGame.root.getChildren().add(levelGameButton);
        BombermanGame.root.getChildren().add(instructionButton);
        BombermanGame.root.getChildren().add(exitGameButton);
        BombermanGame.root.getChildren().add(gameVuiButton);
    }

    public void setSurvivalButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\startGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        survivalGame.setGraphic(img);
        survivalGame.setBackground(null);
        survivalGame.setLayoutX((WIDTH - image.getWidth()) / 2);
        survivalGame.setLayoutY((HEIGHT - image.getHeight()) / 2 - 25);
        survivalGame.setStyle("-fx-font-size: 20px; -fx-text-fill: WHITE; -fx-font-weight: bold;");
        survivalGame.setContentDisplay(ContentDisplay.CENTER);
        survivalGame.setText("Survival");

        survivalGame.setOnAction(arg0 -> {
            // TODO Auto-generated method stub
            isSurvival = true;
            status = GAME_CHANGE_LEVEL;
            score = 0;
            removeButtonInRoot();
        });

        survivalGame.setOnMouseEntered(e -> {
            survivalGame.setScaleX(SCALE_BUTTON);
            survivalGame.setScaleY(SCALE_BUTTON);
        });

        survivalGame.setOnMouseExited(e -> {
            survivalGame.setScaleX(1);
            survivalGame.setScaleY(1);
        });
    }

    public void setLevelGameButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\startGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        levelGameButton.setGraphic(img);
        levelGameButton.setBackground(null);
        levelGameButton.setLayoutX((WIDTH - image.getWidth()) / 2);
        levelGameButton.setLayoutY((HEIGHT - image.getHeight()) / 2 + 50);
        levelGameButton.setStyle("-fx-font-size: 20px; -fx-text-fill: WHITE; -fx-font-weight: bold;");
        levelGameButton.setContentDisplay(ContentDisplay.CENTER);
        levelGameButton.setText("Level Game");

        levelGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                removeButtonInRoot();
                LevelGame levelGame;
                try {
                    levelGame = new LevelGame(gc);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                levelGame.startLoop();
            }
        });

        levelGameButton.setOnMouseEntered(e -> {
            levelGameButton.setScaleX(SCALE_BUTTON);
            levelGameButton.setScaleY(SCALE_BUTTON);
        });

        levelGameButton.setOnMouseExited(e -> {
            levelGameButton.setScaleX(1);
            levelGameButton.setScaleY(1);
        });
    }

    public void setInstructionButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\startGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        FileInputStream input1 = new FileInputStream("res\\instruction\\Instruction.png");
        Image imgIn = new Image(input1);

        instructionButton.setGraphic(img);
        instructionButton.setBackground(null);
        instructionButton.setLayoutX((WIDTH - image.getWidth()) / 2);
        instructionButton.setLayoutY((HEIGHT - image.getHeight()) / 2 + 125);
        instructionButton.setStyle("-fx-font-size: 20px; -fx-text-fill: WHITE; -fx-font-weight: bold;");
        instructionButton.setContentDisplay(ContentDisplay.CENTER);
        instructionButton.setText("Instruction");

        instructionButton.setOnAction(new EventHandler<ActionEvent>() {
            // TODO Auto-generated method stub
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                removeButtonInRoot();
                status = GAME_INSTRUCTION_SETTING;
            }

        });

        instructionButton.setOnMouseEntered(e -> {
            instructionButton.setScaleX(SCALE_BUTTON);
            instructionButton.setScaleY(SCALE_BUTTON);
        });

        instructionButton.setOnMouseExited(e -> {
            instructionButton.setScaleX(1);
            instructionButton.setScaleY(1);
        });
    }

    public void setExitGameButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\startGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        exitGameButton.setGraphic(img);
        exitGameButton.setBackground(null);
        exitGameButton.setLayoutX((WIDTH - image.getWidth()) / 2);
        exitGameButton.setLayoutY((HEIGHT - image.getHeight()) / 2 + 200);

        //set style text
        exitGameButton.setStyle("-fx-font-size: 20px; -fx-text-fill: WHITE; -fx-font-weight: bold;");
        exitGameButton.setContentDisplay(ContentDisplay.CENTER);
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

    public void setGameVuiButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\gameVui.png");
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
    }


    public void removeButtonInRoot() {
        BombermanGame.root.getChildren().remove(startGameButton);
        BombermanGame.root.getChildren().remove(survivalGame);
        BombermanGame.root.getChildren().remove(levelGameButton);

        BombermanGame.root.getChildren().remove(instructionButton);
        BombermanGame.root.getChildren().remove(exitGameButton);
        BombermanGame.root.getChildren().remove(gameVuiButton);
    }


}
