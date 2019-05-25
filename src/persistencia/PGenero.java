package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidade.Genero;

public class PGenero {

	Connection cnn;
	
	public PGenero() {
		cnn = util.Conexao.getConexao();
	}
	
		
	public List<Genero> listarGenero() throws SQLException {
        String sql = "SELECT * FROM genero";
        
        Statement stm = cnn.createStatement();
        
        ResultSet rs = stm.executeQuery(sql);
        
        List<Genero> lista = new ArrayList<>();
        
        while (rs.next()) {
            Genero genero = new Genero();
            genero.setId(rs.getInt("id"));
            genero.setGenero(rs.getString("nome_genero"));
            
            lista.add(genero);
        }
        rs.close();
        cnn.close();
        
        return lista;
    }

}
