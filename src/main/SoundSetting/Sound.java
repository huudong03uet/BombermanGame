package main.SoundSetting;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sound {
    private String backGroundPath = "res\\muzik\\backGround.mp3";
    private Media muzikBackground ;
    private  MediaPlayer mediaPlayer = new MediaPlayer(muzikBackground);

    public Sound() {
        muzikBackground = new Media(new File(backGroundPath).toURI().toString());
        mediaPlayer = new MediaPlayer(muzikBackground);
    }

    public  void playBackGround () throws Exception{
        try{
            System.out.println(1);
            mediaPlayer.setAutoPlay(true);
        } catch (Exception e) {
            System.err.println(e.getCause());
        }

    }

}
