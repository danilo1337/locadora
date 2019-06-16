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
//		else
//			per.alterar(login);
	}

	public void excluir(int id) throws Exception {
		per.excluir(id);
	}

	public Copias consultar(int id) throws Exception {
		return per.consultar(id);
	}

	public List<Copias> listar() throws Exception {
		return per.listar();
	}
}
