package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidade.TipoFilme;

public class PTipoFilme {

Connection cnn;
	
	public  PTipoFilme() {
		cnn = util.Conexao.getConexao();
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
            
            lista.add(tipo);
        }
        rs.close();
        cnn.close();
        
        return lista;
    }
}
