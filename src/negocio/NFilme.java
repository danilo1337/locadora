package negocio;

import java.sql.SQLException;
import java.util.List;

import entidade.Filmes;
import persistencia.PFilmes;

public class NFilme {

	
	PFilmes per;

	public NFilme() {
		per = new PFilmes();
	}

	public void salvar(Filmes filme) throws SQLException {
		if (filme.getId() == 0)
			per.incluir(filme);
		else
			per.alterar(filme);
	}
	
	public void alterar(Filmes filme) throws SQLException {
		per.alterar(filme);
	}

	public void excluir(Filmes filme) throws Exception {
		per.excluir(filme);
	}

	public Filmes consultar(String filme) throws Exception {
		return per.consultar(filme);
	}
	
	public List<Filmes> Listar(Filmes param) throws SQLException{
		return per.listar(param);
	}
}
