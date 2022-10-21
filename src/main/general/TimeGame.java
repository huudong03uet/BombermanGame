package main.general;

import java.time.Clock;

import static main.settings.PropertiesStatic.timeRemain;

public class TimeGame {
    private Clock clock = Clock.systemDefaultZone();
    private long time;

    public TimeGame() {
        time = clock.millis();
    }
    public  int getTime() {
        long time_ = clock.millis();
        return (int) ((time_ - time) / 1000);
    }

    public void setTimeRemain() {
        timeRemain = 300 - getTime();
    }
    //start the thread and its ok
}
