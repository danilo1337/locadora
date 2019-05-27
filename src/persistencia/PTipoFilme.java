package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidade.Endereco;
import entidade.Filmes;
import entidade.TipoFilme;
import util.Conexao;

public class PTipoFilme {

Connection cnn;
	
	public  PTipoFilme() {
		cnn = util.Conexao.getConexao();
	}
	
		
	public TipoFilme consultar(TipoFilme tipo) throws SQLException {
        String sql = "SELECT * FROM tipo_filme WHERE id = ?";
        Connection cnn = Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);

        ps.setInt(1,tipo.getId());
        ResultSet rs = ps.executeQuery();
        TipoFilme retorno = new TipoFilme();
        if (rs.next()) {
            retorno.setId(rs.getInt("id"));
            retorno.setPreco(rs.getDouble("preco"));
            retorno.setTipo(rs.getString("tipo"));
        }
        rs.close();
        cnn.close();
		return retorno;
        
    }
	
	
	public List<TipoFilme> listarTipoFilme() throws SQLException {
        String sql = "SELECT * FROM tipo_filme";
        
        Statement stm = cnn.createStatement();
        
        ResultSet rs = stm.executeQuery(sql);
        
        List<TipoFilme> lista = new ArrayList<>();
        
        while (rs.next()) {
            TipoFilme tipo = new TipoFilme();
            tipo.setId(rs.getInt("id"));
            tipo.setTipo(rs.getString("tipo"));
            tipo.setPreco(rs.getDouble("preco"));
            
            lista.add(tipo);
        }
        rs.close();
        cnn.close();
        
        return lista;
    }

	public void incluir(TipoFilme tipo_id, Connection cnn2) throws SQLException {
		String sql ="INSERT INTO tipo_filme("
				+ "	id, tipo"
				+ " preco)"
				+ " VALUES ("
				+ "?,?,?)"; 
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, tipo_id.getId());
		ps.setString(2, tipo_id.getTipo());
		ps.setDouble(3, tipo_id.getPreco());
		
		ps.execute();
		
		//recuperar id gerado
        String sql2 = "SELECT currval('tipo_id_seq') as id";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql2);

        if (rs.next()) {
            tipo_id.setId(rs.getInt("id"));
        }
        rs.close();
		
	}
}
