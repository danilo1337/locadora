package negocio;

import java.sql.SQLException;
import java.util.List;

import entidade.TipoFilme;
import persistencia.PTipoFilme;

public class NTipoFilme {

	
	PTipoFilme per;
	
	public NTipoFilme() {
		per = new PTipoFilme();
	}
	
	public List<TipoFilme> listarTipoFilme() throws SQLException{
		return per.listarTipoFilme();
	}
	
}