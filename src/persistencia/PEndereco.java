package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidade.Endereco;

public class PEndereco {
	public void incluir(Endereco endereco, Connection cnn) throws SQLException {
			String sql ="INSERT INTO endereco("
					+ "	cep,"
					+ " logradouro, complemento,"
					+ " bairro, localidade,"
					+ " uf)"
					+ " VALUES ("
					+ "?,?,?,?,?,?)"; 
			
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setString(1, endereco.getCep());
			ps.setString(2, endereco.getLogradouro());
			ps.setString(3, endereco.getComplemento());
			ps.setString(4, endereco.getBairro());
			ps.setString(5, endereco.getLocalidade());
			ps.setString(6, endereco.getUF());
			
			ps.execute();
			
			//recuperar id gerado
	        String sql2 = "SELECT currval('endereco_id_seq') as id";

	        Statement stm = cnn.createStatement();
	        ResultSet rs = stm.executeQuery(sql2);

	        if (rs.next()) {
	            endereco.setId(rs.getInt("id"));
	        }
	        rs.close();
	}
	public void alterar(Endereco endereco,Connection cnn) throws SQLException {
		String sql ="UPDATE endereco SET"
				+ "	cep = ?,"
				+ " logradouro = ?, complemento = ?,"
				+ " bairro = ?, localidade = ?,"
				+ " uf = ? WHERE id = ?";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setString(1, endereco.getCep());
		ps.setString(2, endereco.getLogradouro());
		ps.setString(3, endereco.getComplemento());
		ps.setString(4, endereco.getBairro());
		ps.setString(5, endereco.getLocalidade());
		ps.setString(6, endereco.getUF());
		ps.setInt(7, endereco.getId());
		
		ps.execute();
		
		//recuperar id gerado
        String sql2 = "SELECT currval('endereco_id_seq') as id";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql2);

        if (rs.next()) {
            endereco.setId(rs.getInt("id"));
        }
        rs.close();
		
	}
	
	public void excluir(Endereco endereco, Connection cnn) throws SQLException {
		String sql = "DELETE FROM endereco WHERE id = ?";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, endereco.getId());
		ps.execute();
	}
	
	public Endereco consultar(Endereco endereco, Connection cnn) throws SQLException {
		String sql = "SELECT * FROM endereco WHERE id = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		
		ps.setInt(1, endereco.getId());
		
		ResultSet rs = ps.executeQuery();
		Endereco retorno = new Endereco();
		if(rs.next()) {
			retorno.setId(rs.getInt("id"));
			retorno.setBairro(rs.getString("bairro"));
			retorno.setCep(rs.getString("cep"));
			retorno.setComplemento(rs.getString("complemento"));
			retorno.setLocalidade(rs.getString("localidade"));
			retorno.setLogradouro(rs.getString("logradouro"));
			retorno.setUF(rs.getString("uf"));
		}
		rs.close();
		return retorno;
	}
	
}
