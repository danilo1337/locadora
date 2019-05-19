package negocio;

import java.sql.SQLException;
import java.util.List;

import entidade.Pessoal;
import persistencia.PPessoal;

public class NPessoal {
	PPessoal per;

	public NPessoal() {
		per = new PPessoal();
	}

	public void salvar(Pessoal pessoal) throws SQLException {
		if (pessoal.getId() == 0)
			per.incluir(pessoal);
		else
			per.alterar(pessoal);
	}

	public void excluir(Pessoal pessoal) throws Exception {
		per.excluir(pessoal);
	}

	public Pessoal consultar(Pessoal pessoal) throws Exception {
		return per.consultar(pessoal);
	}

	public List<Pessoal> listar() throws Exception {
		return per.listar();
	}
	
	public Pessoal consultar_cpf(String cpf) throws Exception {
		return per.consultar_cpf(cpf);
	}
	
}
