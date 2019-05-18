/**
 * 
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			String sql ="INSERT INTO pessoal("
					+ "	nome_completo," + 
					" sexo, cpf, data_nascimento," + 
					" telefone_1 , telefone_2," + 
					" celular, endereco_id)"
					+ "VALUES ("
					+ "?,?,?,?,?,?,?,?)"; 
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setString(1, pessoal.getNome_completo());
			ps.setString(2, pessoal.getSexo());
			ps.setString(3, pessoal.getCpf());
			ps.setDate(4, pessoal.getData_nascimento());
			ps.setString(5, pessoal.getTelefone_1());
			ps.setString(6, pessoal.getTelefone_2());
			ps.setString(7, pessoal.getCelular());
			ps.setInt(8, pessoal.getEndereco().getId());
			ps.execute();
			
			cnn.commit();
			
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		}
	}
}
