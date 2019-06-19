package negocio;

import java.sql.SQLException;
import java.util.List;

import entidade.Copias;
import persistencia.PCopias;

/*
 * @author Hugo Henrique
 */

public class NCopias {
	private PCopias per;

	public NCopias() { per = new PCopias(); }

	public Copias consultarCopias(String codigoCopia) throws Exception {
		return per.consultarCodigo(codigoCopia);
	}

	public void salvar(Copias copias) throws SQLException {
		if (copias.getId() == 0)
			per.incluir(copias);
		else
			per.alterar(copias);
	}

	public void salvar2(Copias copias) throws SQLException {
		if (copias.getId() == 0)
			per.incluir2(copias);
		else
			per.alterar(copias);
	}
	
	public void excluir(int id) throws Exception {
		per.excluir(id);
	}

	public Copias consultar(int id) throws Exception {
		return per.consultar(id);
	}
	public Copias consultar(String codigoCopia) throws Exception {
		return per.consultarCodigo(codigoCopia);
	}
	public Copias consultarGeral(String codigoCopia) throws Exception {
		return per.consultarCodigoGeral(codigoCopia);
	}

	public List<Copias> listar(Copias param) throws Exception {
		return per.listar(param);
	}
}
