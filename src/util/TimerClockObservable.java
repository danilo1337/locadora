package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/*
 * @author Hugo Henrique
 */

public class TimerClockObservable extends Observable {
    private static final DateFormat CLOCK_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Timer timer;

    public String getTime() {
        return time;
    }

    private void setTime(String time) {
        this.time = time;
    }

    private String time;

    public void clock() {
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, 10000);
    }

    public void stop() {
    	timer.cancel();
    }

    class RemindTask extends TimerTask {
        public void run() {
            setTime(CLOCK_FORMAT.format(System.currentTimeMillis()));

            setChanged();
            notifyObservers();
            System.out.println(CLOCK_FORMAT.format(System.currentTimeMillis()));
        }
    }
}