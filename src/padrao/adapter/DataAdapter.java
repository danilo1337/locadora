package padrao.adapter;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;

public class DataAdapter implements IDataJFX{
	
	Data data = null;
	
	public DataAdapter() {
		data = new Data();
	}
	
	public DataAdapter(Data data) {
		this.data = data;
	}
	
	@Override
	public Date formatarData(String parametro) throws ParseException {
		return data.formatarData(parametro);
	}
	
	@Override
	public String formatarData(DatePicker parametro) {
		Date date = Date.valueOf(parametro.getValue());
		return data.formatarData(date);
	}
	
	@Override
	public Date formatarDatePicker(DatePicker parametro) throws ParseException {
		return data.formatarData(formatarData(parametro));
	}


	@Override
	public DatePicker dataAtualFormatada() throws ParseException {
		DatePicker dp = new DatePicker(LocalDate.now());
		dp.setPromptText(formatarData(dp));
		return dp;
	}

}
