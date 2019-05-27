package padrao.factory;

public class AbreviaturaFactory {
	public static Abreviatura criarAbreviatura(String sexo) {
		Abreviatura abr = null;
		if(sexo.equalsIgnoreCase("Masculino"))
			abr = new Homem();
		else if(sexo.equalsIgnoreCase("Feminino"))
			abr = new Mulher();
		return abr;
	}
}
