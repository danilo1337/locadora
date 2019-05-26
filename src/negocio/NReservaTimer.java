package negocio;

import persistencia.PReserva;
import util.TimerClockObservable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;

public class NReservaTimer implements Observer {
	Observable timerClock;
	private PReserva per;

	public NReservaTimer(Observable timerClock){
		this.timerClock = timerClock;
		per = new PReserva();
		timerClock.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof TimerClockObservable){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String dataAtual = ((TimerClockObservable) o).getTime();
				Timestamp dataSql = new Timestamp(sdf.parse(dataAtual).getTime());
				per.cancelarReservasVencidas(dataSql);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
