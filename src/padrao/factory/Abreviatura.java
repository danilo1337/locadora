package padrao.factory;

public interface Abreviatura {
	String getAbreviatura();
}
class Homem implements Abreviatura{

	@Override
	public String getAbreviatura() {
		return "Sr.";
	}
}
class Mulher implements Abreviatura{

	@Override
	public String getAbreviatura() {
		return "Sr.ª";
	}
	
}