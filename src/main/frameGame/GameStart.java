package main.frameGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import main.BombermanGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static main.settings.PropertiesConstant.HEIGHT;
import static main.settings.PropertiesConstant.WIDTH;
import static main.settings.StatusGame.GAME_PLAY;
import static main.settings.StatusGame.status;

public class GameStart {
    private GraphicsContext gc;
    Image backGroundImage;
    Image logo;
    Button startGameButton = new Button();
    int xRender = 0;
    public GameStart(Canvas canvas) throws FileNotFoundException {
        gc = canvas.getGraphicsContext2D();
        backGroundImage = new Image(new FileInputStream("res\\textures\\StartGame\\bg.png"));
        logo = new Image(new FileInputStream("res\\textures\\StartGame\\logo2.png"));

        setStartGameButton();
        setInstructionButton();

        BombermanGame.root.getChildren().add(startGameButton);
    }

    private void setInstructionButton() {
    }

    public void startLoop() {
        gc.drawImage(backGroundImage, -xRender, 0, WIDTH, HEIGHT);
        gc.drawImage(backGroundImage, WIDTH - xRender, 0, WIDTH, HEIGHT);
        xRender++;
        if (xRender == WIDTH) xRender = 0;

        gc.drawImage(logo, (WIDTH - logo.getWidth()) / 2, 50);
    }
    public TextAlignment setTextButton(String text) {
        TextAlignment textAlignment = TextAlignment.CENTER;
        return textAlignment;
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
        startGameButton.setTextAlignment(TextAlignment.CENTER);



        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                status = GAME_PLAY;
                removeButtonInRoot();
            }
        });

        startGameButton.setOnMouseEntered(e -> {
            startGameButton.setScaleX(1.1);
            startGameButton.setScaleY(1.1);
        });

        startGameButton.setOnMouseExited(e -> {
            startGameButton.setScaleX(1);
            startGameButton.setScaleY(1);
        });
    }


    public void removeButtonInRoot() {
        BombermanGame.root.getChildren().remove(startGameButton);
    }

    public void Sound() {
    }
}
