package main.soundSetting;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import static main.settings.PropertiesStatic.*;

public class Sound {
    private static Clip[] clip = new Clip[10];
    private String[] sound = new String[10];
    long clipTimePosition;

    public Sound() {
        // sound[0] = "res\\muzik\\backGround2.wav";
      //  sound[0] = "res\\muzik\\backGround1.wav";
        sound[1] = "res\\muzik\\bomb_explosion.wav";
        sound[2] = "res\\muzik\\bomber_die.wav";
        sound[3] = "res\\muzik\\crash_wall.wav";
        sound[4] = "res\\muzik\\enemy_die.wav";
        sound[5] = "res\\muzik\\item.wav";
        clipTimePosition = 0;
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound[i]).getAbsoluteFile());
            clip[i] = AudioSystem.getClip();
            clip[i].open(audioInputStream);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }

    public void playMuzik(int i) {
      //  clip[i].start();
     //   clip[i].loop(-1);
        isPlayingMuzik = true;
    }

    public void isPlayMuzik(int i) {
        if(clip[0] != null) {
            return;
        }
        setFile(0);
        playMuzik(i);
    }

    public void stop() {
        clip[0].stop();
        isPlayingMuzik = false;
    }

    public void pause() {
        clipTimePosition = clip[0].getMicrosecondPosition();
        stop();
    }

    public void resume() {
        clip[0].setMicrosecondPosition(clipTimePosition);
        clip[0].loop(-1);
        clip[0].start();
        isPlayingMuzik = true;
    }

    public void playSE(int i) {
        setFile(i);
        if (isPlayingSound) {
            clip[i].start();
            clip[i].start();
        }
    }
}
