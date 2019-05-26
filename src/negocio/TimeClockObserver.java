package negocio;

import util.TimerClockObservable;
import java.util.Observable;
import java.util.Observer;

public class TimeClockObserver implements Observer {
	Observable timerClock;

	public TimeClockObserver(Observable timerClock){
		this.timerClock = timerClock;
		timerClock.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof TimerClockObservable){
			System.out.println("TESTE OBSERVABLE " + ((TimerClockObservable) o).getTime());
		}
	}
}
