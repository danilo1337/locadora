package negocio;


import java.util.List;

import entidade.Genero;
import persistencia.PGenero;

public class NGenero {

	PGenero per;
	
	public NGenero() {
		per = new PGenero();
	}
	
	public List<Genero> listarGenero() throws Exception{
		return per.listarGenero();
	}
	
}
