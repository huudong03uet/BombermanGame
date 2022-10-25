package main.soundSetting;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private Clip clip;
    private String[] sound = new String[10];

    public Sound() {
        // sound[0] = "res\\muzik\\backGround2.wav";

        sound[0] = "res\\muzik\\backGround1.wav";
        sound[1] = "res\\muzik\\bomb_explosion.wav";
        sound[2] = "res\\muzik\\bomber_die.wav";
        sound[3] = "res\\muzik\\crash_wall.wav";
        sound[4] = "res\\muzik\\enemy_die.wav";
        sound[5] = "res\\muzik\\item.wav";
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound[i]).getAbsoluteFile());
            clip = AudioSystem.getClip();
        //    clip.open(audioInputStream);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");

        }
    }

    public void playMuzik(int i) {
        setFile(i);
     //   clip.start();
        clip.loop(-1);
    }

    public void isPlayMuzik(int i) {
        if(clip == null) {
            playMuzik(i);
        }
        if(!clip.isRunning()) {
            clip.stop();
             clip.start();
             clip.loop(-1);
        }
    }

    public void stop() {
        clip.stop();
    }

    public void playSE(int i) {
        setFile(i);
        clip.start();
    }
}
