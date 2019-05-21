package negocio;

import entidade.Login;
import persistencia.PLogin;
import java.sql.SQLException;
import java.util.List;

/*
 * @author Hugo Henrique
 */

public class NLogin {
	private PLogin per;

	public NLogin() { per = new PLogin(); }

	public Login logar(String login, String senha) throws Exception {
		return per.login(login, senha);
	}

	public void salvar(Login login) throws SQLException {
		if (login.getId() == 0)
			per.incluir(login);
		else
			per.alterar(login);
	}

	public void excluir(int id) throws Exception {
		per.excluir(id);
	}

	public Login consultar(int id) throws Exception {
		return per.consultar(id);
	}

	public Login consultarLogin(String login) throws Exception {
		return per.consultarLogin(login);
	}

	public List<Login> listar() throws Exception {
		return per.listar();
	}
}
