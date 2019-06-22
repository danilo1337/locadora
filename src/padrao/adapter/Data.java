package padrao.adapter;


import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Heuber
 */
public class Data implements IData{
    //Método para calculo da idade
    public int calcularIdade(Date dataNascimento) {

        Calendar dataCalendario = new GregorianCalendar();
        dataCalendario.setTime(dataNascimento);

        // Cria um objeto calendar com a data atual
        Calendar hoje = Calendar.getInstance();

        // Obtém a idade baseado no ano
        int idade = hoje.get(Calendar.YEAR) - dataCalendario.get(Calendar.YEAR);

        dataCalendario.add(Calendar.YEAR, idade);

        //se a data de hoje é antes da data de Nascimento, então diminui 1(um)
        if (hoje.before(dataCalendario)) {
            idade--;
        }

        return idade;

    }

    public String dataAtualFormatada() {
        DateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dataAtual = new java.util.Date();
        return formatar.format(dataAtual);
    }

    public String formatarData(Date parametro) {
        DateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        return formatar.format(parametro);
    }

    public Date formatarData(String parametro) throws ParseException {
        DateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        if (!parametro.isEmpty()) {
            return new java.sql.Date(formatar.parse(parametro).getTime());
        }
        return null;
    }
}
