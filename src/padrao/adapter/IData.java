package padrao.adapter;

import java.sql.Date;
import java.text.ParseException;

public interface IData {

	public int calcularIdade(Date dataNascimento);

	public String dataAtualFormatada();

	public String formatarData(Date parametro);

	public Date formatarData(String parametro) throws ParseException;
}
