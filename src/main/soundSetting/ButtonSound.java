package main.soundSetting;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URISyntaxException;


public class ButtonSound {
    private Image buttonImg;
    private String text;
    private Font font;
    private Color color;

    private double x;
    private double y;
    private double width;
    private double height;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setW(double width) {
        this.width = width;
    }

    public void setH(double height) {
        this.height = height;
    }

    public double getW() {
        return width;
    }

    public double getH() {
        return height;
    }

    public ButtonSound(String text) {
        super();
        try {
            setX(0);
            setY(0);
            this.text = text;
            buttonImg = new Image(getClass().getResource("/muzik/Image/play9.png").toURI().toString());
            setW(buttonImg.getWidth());
            setH(buttonImg.getHeight());
            font = Font.font("Liberation Serif", FontWeight.BOLD, FontPosture.REGULAR, 50);
            color = Color.BLACK;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setButtonImg(String link) {
        try {
            buttonImg = new Image(getClass().getResource(link).toURI().toString());
            setW(buttonImg.getWidth());
            setH(buttonImg.getHeight());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(GraphicsContext render) {
        Text t = new Text(text);
        t.setFont(font);
        render.setFont(font);

        render.drawImage(buttonImg, x, y, getW(), getH());
        render.setFill(color);
        render.fillText(t.getText(), x + (getW() / 2), y);
        render.setFill(Color.WHITE);
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isClick(MouseEvent e) {
        return e.getX() > x && e.getX() < (x + getW())
                && e.getY() > y && e.getY() < (y + getH());
    }

    public void setXYWH(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

}
