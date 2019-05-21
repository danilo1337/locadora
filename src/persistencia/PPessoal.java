/**
 * 
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidade.Pessoal;

/**
 * @author Danilo
 *
 */
public class PPessoal {
	public void incluir(Pessoal pessoal) throws SQLException {
		Connection cnn = util.Conexao.getConexao();
		cnn.setAutoCommit(false);
		try {
			String sql = "INSERT INTO pessoal("
						+ "	nome_completo, sexo,"
						+ " cpf, data_nascimento,"
						+ " telefone , celular,"
						+ " endereco_id, email,"
						+ "	tipo)" + "VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setString(1, pessoal.getNome_completo());
			ps.setString(2, pessoal.getSexo());
			ps.setString(3, pessoal.getCpf());
			ps.setDate(4, pessoal.getData_nascimento());
			ps.setString(5, pessoal.getTelefone());
			ps.setString(6, pessoal.getCelular());
			new PEndereco().incluir(pessoal.getEndereco(), cnn);
			ps.setInt(7, pessoal.getEndereco().getId());
			ps.setString(8, pessoal.getEmail());
			ps.setString(9, pessoal.getTipo()+"");
			ps.execute();

			// recuperar id gerado
			String sql2 = "SELECT currval('pessoal_id_seq') as id";

			Statement st = cnn.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			if (rs.next()) {
				pessoal.setId(rs.getInt("id"));
			}

			rs.close();
			cnn.commit();

		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		}
		cnn.close();
	}

	public void alterar(Pessoal pessoal) throws SQLException {
		Connection cnn = util.Conexao.getConexao();
		cnn.setAutoCommit(false);
		try {
			String sql = "UPDATE pessoal SET"
					+ "nome_completo = ?, sexo = ?,"
					+ " cpf = ?, data_nascimento = ?,"
					+ " telefone = ?, celular = ?,"
					+ " endereco_id = ?, email = ? WHERE id = ?";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setString(1, pessoal.getNome_completo());
			ps.setString(2, pessoal.getSexo());
			ps.setString(3, pessoal.getCpf());
			ps.setDate(4, pessoal.getData_nascimento());
			ps.setString(5, pessoal.getTelefone());
			ps.setString(6, pessoal.getCelular());
			new PEndereco().incluir(pessoal.getEndereco(), cnn);
			ps.setInt(7, pessoal.getEndereco().getId());
			ps.setString(8, pessoal.getEmail());
			ps.setString(9, pessoal.getTipo()+"");
			ps.setInt(10, pessoal.getId());
			ps.execute();

			new PEndereco().alterar(pessoal.getEndereco(), cnn);
			
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		}
		cnn.close();
	}

	public void excluir(Pessoal pessoal) throws SQLException {
		String sql = "DELETE FROM pessoal WHERE id = ?";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, pessoal.getId());
		new PEndereco().excluir(pessoal.getEndereco(), cnn);
		ps.execute();
	}

	public Pessoal consultar(Pessoal pessoal) throws SQLException {
		String sql = "SELECT * FROM pessoal WHERE id = ?";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);
		
		ps.setInt(1, pessoal.getId());
		ResultSet rs = ps.executeQuery();
		Pessoal retorno = new Pessoal();
		if(rs.next()) {
			retorno.setId(rs.getInt("id"));
			retorno.setNome_completo(rs.getString("nome_completo"));
			retorno.setTipo(rs.getString("tipo").charAt(0));
			retorno.setSexo(rs.getString("sexo"));
			retorno.setCpf(rs.getString("cpf"));
			retorno.setData_nascimento(rs.getDate("data_nascimento"));
			retorno.setTelefone(rs.getString("telefone"));
			retorno.setCelular(rs.getString("celular"));
			retorno.setEmail(rs.getString("email"));
			retorno.getEndereco().setId(rs.getInt("endereco_id"));
			retorno.setEndereco(new PEndereco().consultar(retorno.getEndereco(), cnn));
		}
		rs.close();
		cnn.close();
		return retorno;
	}
	
	
	public List<Pessoal> listar() throws SQLException{
		String sql = "SELECT * FROM pessoal WHERE 1 = 1";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);
		
		
		ResultSet rs = ps.executeQuery();
		
		List<Pessoal>lista = new ArrayList<>();
		while(rs.next()) {
			Pessoal retorno = new Pessoal();
			retorno.setId(rs.getInt("id"));
			retorno.setNome_completo(rs.getString("nome_completo"));
			retorno.setTipo(rs.getString("tipo").charAt(0));
			retorno.setSexo(rs.getString("sexo"));
			retorno.setCpf(rs.getString("cpf"));
			retorno.setData_nascimento(rs.getDate("data_nascimento"));
			retorno.setTelefone(rs.getString("telefone"));
			retorno.setCelular(rs.getString("celular"));
			retorno.setEmail(rs.getString("email"));
			retorno.setEndereco(new PEndereco().consultar(retorno.getEndereco(), cnn));
			retorno.getEndereco().setId(rs.getInt("endereco_id"));
			lista.add(retorno);
		}
		rs.close();
		cnn.close();
		
		return lista;
	}
	
	public Pessoal consultar_cpf(String cpf) throws SQLException {
		String sql = "SELECT * FROM pessoal WHERE cpf = ?";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);
		
		ps.setString(1, cpf);
		ResultSet rs = ps.executeQuery();
		Pessoal retorno = new Pessoal();
		if(rs.next()) {
			retorno.setId(rs.getInt("id"));
			retorno.setNome_completo(rs.getString("nome_completo"));
			retorno.setTipo(rs.getString("tipo").charAt(0));
			retorno.setSexo(rs.getString("sexo"));
			retorno.setCpf(rs.getString("cpf"));
			retorno.setData_nascimento(rs.getDate("data_nascimento"));
			retorno.setTelefone(rs.getString("telefone"));
			retorno.setCelular(rs.getString("celular"));
			retorno.setEmail(rs.getString("email"));
			retorno.getEndereco().setId(rs.getInt("endereco_id"));
			retorno.setEndereco(new PEndereco().consultar(retorno.getEndereco(), cnn));
		}
		rs.close();
		cnn.close();
		return retorno;
	}
}
