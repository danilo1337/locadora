package padrao.adapter;

import java.sql.Date;
import java.text.ParseException;

import javafx.scene.control.DatePicker;

public interface IDataJFX {
	
	public Date formatarDatePicker(DatePicker parametro) throws ParseException;

	public String formatarData(DatePicker parametro);
	
	public Date formatarData(String parametro) throws ParseException;
	
	public DatePicker dataAtualFormatada() throws ParseException;
}
