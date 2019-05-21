package persistencia;

import entidade.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Hugo Henrique
 */

public class PLogin {

	public Login login(String login, String senha) throws SQLException {
		String sql = "SELECT id, pessoal_id, login, senha FROM login WHERE login = ? AND senha = ?";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);

		ps.setString(1, login);
		ps.setString(2, senha);
		ResultSet rs = ps.executeQuery();
		return setRetornoLogin(cnn, rs);
	}

	public void incluir(Login login) throws SQLException {
		Connection cnn = util.Conexao.getConexao();
		cnn.setAutoCommit(false);
		try {
			String sql = "INSERT INTO login(pessoal_id, login, senha) VALUES (?,?,?)";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setInt(1, login.getPessoal_id());
			ps.setString(2, login.getLogin());
			ps.setString(3, login.getSenha());
			ps.execute();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		}
		cnn.close();
	}

	public void alterar(Login login) throws SQLException {
		Connection cnn = util.Conexao.getConexao();
		cnn.setAutoCommit(false);
		try {
			String sql = "UPDATE login SET senha = ? WHERE id = ?";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setString(1, login.getSenha());
			ps.setInt(2, login.getId());
			ps.execute();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		}
		cnn.close();
	}

	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM login WHERE id = ?";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
	}

	public Login consultar(int id) throws SQLException {
		String sql = "SELECT id, pessoal_id, login FROM login WHERE id = ?";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);

		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		return setRetornoLogin(cnn, rs);
	}

	public List<Login> listar() throws SQLException{
		String sql = "SELECT id, pessoal_id, login FROM login";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<Login>lista = new ArrayList<>();
		while(rs.next()) {
			Login retorno = new Login();
			retorno.setId(rs.getInt("id"));
			retorno.setPessoal_id(rs.getInt("pessoal_id"));
			retorno.setLogin(rs.getString("login"));
			lista.add(retorno);
		}
		rs.close();
		cnn.close();

		return lista;
	}

	public Login consultarLogin(String login) throws SQLException {
		String sql = "SELECT id, pessoal_id, login FROM login WHERE login = ?";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);

		ps.setString(1, login);
		ResultSet rs = ps.executeQuery();
		return setRetornoLogin(cnn, rs);
	}

	private Login setRetornoLogin(Connection cnn, ResultSet rs) throws SQLException {
		Login retorno = new Login();
		if(rs.next()) {
			retorno.setId(rs.getInt("id"));
			retorno.setPessoal_id(rs.getInt("pessoal_id"));
			retorno.setLogin(rs.getString("login"));
		}
		rs.close();
		cnn.close();
		return retorno;
	}
}
