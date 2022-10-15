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

import static main.settings.PropertiesConstant.HEIGHT;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.StatusGame.GAME_PLAY;
import static main.settings.StatusGame.status;

public class GameStart {
    private final double SCALE_BUTTON = 1.2;
    private GraphicsContext gc;
    Image backGroundImage;
    Image logo;
    Button startGameButton = new Button();
    Button trainingButton = new Button();
    Button levelGameButton = new Button();


    Button instructionButton = new Button();
    Button exitGameButton = new Button();
    int xRender = 0;
    public GameStart(Canvas canvas) throws FileNotFoundException {
        gc = canvas.getGraphicsContext2D();

        backGroundImage = new Image(new FileInputStream("res\\textures\\StartGame\\bg.png"));
        logo = new Image(new FileInputStream("res\\textures\\StartGame\\logo2.png"));

        setStartGameButton();
        setTrainingButton();
        setLevelGameButton();
        setInstructionButton();
        setExitGameButton();


        BombermanGame.root.getChildren().add(startGameButton);
        BombermanGame.root.getChildren().add(trainingButton);
        BombermanGame.root.getChildren().add(levelGameButton);
        BombermanGame.root.getChildren().add(instructionButton);
        BombermanGame.root.getChildren().add(exitGameButton);

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
        startGameButton.setText("Start Game");

        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                status = GAME_PLAY;
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

    public void setTrainingButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\training.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        trainingButton.setGraphic(img);
        trainingButton.setBackground(null);
        trainingButton.setLayoutX((WIDTH - image.getWidth()) / 2);
        trainingButton.setLayoutY((HEIGHT - image.getHeight()) / 2 - 25);
        trainingButton.setText("Training");

        trainingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                status = GAME_PLAY;
                removeButtonInRoot();
            }
        });

        trainingButton.setOnMouseEntered(e -> {
            trainingButton.setScaleX(SCALE_BUTTON);
            trainingButton.setScaleY(SCALE_BUTTON);
        });

        trainingButton.setOnMouseExited(e -> {
            trainingButton.setScaleX(1);
            trainingButton.setScaleY(1);
        });
    }

    public void setLevelGameButton() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\levelGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        levelGameButton.setGraphic(img);
        levelGameButton.setBackground(null);
        levelGameButton.setLayoutX((WIDTH - image.getWidth()) / 2);
        levelGameButton.setLayoutY((HEIGHT - image.getHeight()) / 2 +50);
        levelGameButton.setText("Level Game");

        levelGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                status = GAME_PLAY;
                removeButtonInRoot();
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

    public void setInstructionButton () throws FileNotFoundException {
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\instruction.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        instructionButton.setGraphic(img);
        instructionButton.setBackground(null);
        instructionButton.setLayoutX((WIDTH - image.getWidth()) / 2);
        instructionButton.setLayoutY((HEIGHT - image.getHeight()) / 2 + 125);
        instructionButton.setText("Instruction");

        instructionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                status = GAME_PLAY;
                removeButtonInRoot();
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
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\exitGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        exitGameButton.setGraphic(img);
        exitGameButton.setBackground(null);
        exitGameButton.setLayoutX((WIDTH - image.getWidth()) / 2);
        exitGameButton.setLayoutY((HEIGHT - image.getHeight()) / 2 + 200);
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
        BombermanGame.root.getChildren().remove(startGameButton);
        BombermanGame.root.getChildren().remove(trainingButton);
        BombermanGame.root.getChildren().remove(levelGameButton);

        BombermanGame.root.getChildren().remove(instructionButton);
        BombermanGame.root.getChildren().remove(exitGameButton);
    }

}
