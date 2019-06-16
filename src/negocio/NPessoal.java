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
		if(pessoal.getId() <= 0)
			throw new Exception("Não foi possível encontrar esse registro.");
		else
			per.excluir(pessoal);
	}

	public Pessoal consultar(Pessoal pessoal) throws Exception {
		return per.consultar(pessoal);
	}

	public List<Pessoal> listar() throws Exception {
		return per.listar();
	}
	
	public Pessoal consultarCpf(String cpf) throws Exception {
		if(!cpf.matches("-?\\d+(\\.\\d+)?"))
			throw new Exception("Digite apenas números!");
		if(cpf.length() > 11 || cpf.isEmpty())
			throw new Exception("A quantidade de digitos do CPF está incorreta!");
		return per.consultarCpf(cpf);
	}
}
