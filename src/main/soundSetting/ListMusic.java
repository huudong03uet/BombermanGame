package main.soundSetting;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URISyntaxException;
import java.util.LinkedList;

import static main.settings.PropertiesConstant.*;

public class ListMusic {
    private static LinkedList<Media> media = null;
    private ButtonSound playLabel;
    private ButtonSound pauseLabel;
    private ButtonSound leftLabel;
    private ButtonSound rightLabel;
    private ButtonSound musicLabel;
    private ButtonSound volumeLabel;
    private ButtonSound volumeMuteLabel;
    private MediaPlayer mediaPlayer;
    private boolean playing;
    private int index;
    private String removeResource;

    public static void init() {
        if (media == null) {
            try {
                // TODO: add more music
                media = new LinkedList<>();
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/2_Phut_Hon.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/Levi_AMV.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/Hestisztd.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/No_Friends.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/I_Want_You_To_Know_Remix.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/Rise_Up.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/Plain_Jane.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/That_Girl_Remix.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/Lost_Sky.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/Warriyo.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/Ai_No.mp3").toURI().toString()));
                media.add(new Media(ListMusic.class.getResource("/muzik/Music/Nightcore_Centuries.mp3").toURI().toString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public ListMusic() {
        index = 0;
        try {
            init();

            removeResource = getClass().getResource("/muzik/Music/").toURI().toString();
            playLabel = new ButtonSound("");
            playLabel.setButtonImg("/muzik/Image/play.png");
            playLabel.setW(30);
            playLabel.setH(30);
            playLabel.setX(70);
            playLabel.setY(630);
            playLabel.setXYWH(70, 630, 30, 30);


            pauseLabel = new ButtonSound("");
            pauseLabel.setButtonImg("/muzik/Image/pause.png");
            pauseLabel.setXYWH(70, 630, 30, 30);


            volumeLabel = new ButtonSound("");
            volumeLabel.setButtonImg("/muzik/Image/volume.png");
            volumeLabel.setXYWH(170, 630, 30, 30);


            volumeMuteLabel = new ButtonSound("");
            volumeMuteLabel.setButtonImg("/muzik/Image/volume-mute.png");
            volumeMuteLabel.setXYWH(170, 630, 30, 30);


            leftLabel = new ButtonSound("");
            leftLabel.setButtonImg("/muzik/Image/left.png");
            leftLabel.setXYWH(20, 630, 30, 30);


            rightLabel = new ButtonSound("");
            rightLabel.setButtonImg("/muzik/Image/right.png");
            rightLabel.setXYWH(120, 630, 30, 30);


            musicLabel = new ButtonSound("");
            musicLabel.setColor(Color.WHITE);
            musicLabel.setFont(Font.font("Liberation Serif", FontWeight.BOLD, 25));
            musicLabel.setXYWH(220, 655, 0, 0);
            mediaPlayer = new MediaPlayer(media.get(index));

            createMusic();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void createMusic() {
        mediaPlayer = new MediaPlayer(media.get(index));
        setMusicLabel();
        mediaPlayer.setOnEndOfMedia(() -> {
            index++;
            if (index >= media.size()) {
                index = 0;
            }
            createMusic();
        });
        if (playing) {
            mediaPlayer.play();
        }
    }

    private void setMusicLabel() {
        musicLabel.setText(media.get(index).getSource().replace(removeResource, "").replace("_", " ").replace(".mp3", ""));
    }

    public void draw(GraphicsContext render) {
        render.setFill(Color.BLACK);
        render.fillRect(0, HEIGHT - TILE_SIZE * SCALE, 1000, TILE_SIZE * SCALE);

        if (!playing) {
            playLabel.draw(render);
        } else {
            pauseLabel.draw(render);
        }
        leftLabel.draw(render);
        rightLabel.draw(render);
        musicLabel.draw(render);
        if (mediaPlayer.getVolume() == 0) {
            volumeMuteLabel.draw(render);
        } else {
            volumeLabel.draw(render);
        }
    }

    public void click(MouseEvent e) {
        if (e == null) return;

        if (playLabel.isClick(e)) {
            if (!playing) {
                mediaPlayer.play();
                mediaPlayer.setVolume(0.5);
                playing = true;
            } else {
                mediaPlayer.pause();
                playing = false;
            }
        }
        if (leftLabel.isClick(e)) {
            index--;
            if (index < 0) {
                index = media.size() - 1;
            }
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            createMusic();
        }

        if (rightLabel.isClick(e)) {
            index++;
            if (index >= media.size()) {
                index = 0;
            }
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            createMusic();
        }

        if (volumeLabel.isClick(e) || volumeMuteLabel.isClick(e)) {
            if (mediaPlayer.getVolume() == 0) {
                mediaPlayer.setVolume(0.5);
            } else {
                mediaPlayer.setVolume(0);
            }
        }
    }
}
