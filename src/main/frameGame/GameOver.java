package main.frameGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.BombermanGame;
import main.general.ReadFileScore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static main.settings.PropertiesConstant.HEIGHT;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.PropertiesStatic.score;
import static main.settings.StatusGame.*;

public class GameOver {
    private final double SCALE_BUTTON = 1.2;
    private GraphicsContext gc;
    Image backGroundImage;
    Image logo;
    Image notify;
    Button startMenuButton = new Button();
    Button exitGameButton = new Button();
    int xRender = 0;
    ReadFileScore readFileScore = new ReadFileScore();
    ArrayList<Integer> listScore = new ArrayList<>();

    public GameOver(Canvas canvas) throws IOException {
        gc = canvas.getGraphicsContext2D();

        backGroundImage = new Image(Files.newInputStream(Paths.get("res\\textures\\StartGame\\bg.png")));
        logo = new Image(Files.newInputStream(Paths.get("res\\textures\\StartGame\\logo2.png")));
        notify = new Image(Files.newInputStream(Paths.get("res\\textures\\StartGame\\listScore.png")));
        setStartMenuButton();
        setExitGameButton();

        readFileScore.addScore(score);
        listScore = readFileScore.readScore();

        BombermanGame.root.getChildren().add(startMenuButton);
        BombermanGame.root.getChildren().add(exitGameButton);
    }

    public void startLoop() {
        gc.drawImage(backGroundImage, -xRender, 0, WIDTH, HEIGHT);
        gc.drawImage(backGroundImage, WIDTH - xRender, 0, WIDTH, HEIGHT);
        gc.drawImage(logo, (WIDTH - logo.getWidth()) / 2, 50);

        // render notify to center of screen
        gc.drawImage(notify, (WIDTH - notify.getWidth()) / 2, 200);
        drawScore();
        xRender++;
        if (xRender == WIDTH) xRender = 0;
        gc.drawImage(logo, (WIDTH - logo.getWidth()) / 2, 50);
    }

    private void drawScore() {
        // set text bold
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        gc.setFont(font);

        Text text = new Text("Your score: " + score);
        gc.fillText(text.getText(), (WIDTH - text.getLayoutBounds().getWidth()) / 2 - 25, 230);

        text = new Text("High score: ");
        gc.fillText(text.getText(), (WIDTH - text.getLayoutBounds().getWidth()) / 2 - 20, 268);

        for (int i = 0; i < 5 && i < listScore.size(); i++) {
            text = new Text("Top " + (i + 1) + ": " + listScore.get(i));
            gc.fillText(text.getText(), (WIDTH - text.getLayoutBounds().getWidth()) / 2 - 20, 305 + i * 38);
        }
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
        startMenuButton.setLayoutY(500);

        startMenuButton.setStyle("-fx-font-size: 20px; -fx-text-fill: WHITE; -fx-font-weight: bold;");
        startMenuButton.setContentDisplay(ContentDisplay.CENTER);
        startMenuButton.setText("Return Menu");

        startMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                status = GAME_SETTING_MENU;
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
        FileInputStream input = new FileInputStream("res\\textures\\StartGame\\startGame.png");
        Image image = new Image(input);
        ImageView img = new ImageView(image);
        img.setFitHeight(image.getHeight());
        img.setFitWidth(image.getWidth());

        exitGameButton.setGraphic(img);
        exitGameButton.setBackground(null);
        exitGameButton.setLayoutX((WIDTH - image.getWidth()) / 2 + 150);
        exitGameButton.setLayoutY(500);

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

    public void removeButtonInRoot() {
        BombermanGame.root.getChildren().remove(startMenuButton);
        BombermanGame.root.getChildren().remove(exitGameButton);
    }
}
