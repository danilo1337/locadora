package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidade.Filmes;
import util.Conexao;

public class PFilmes {
	Connection cnn;
	
	public PFilmes() {
		cnn = util.Conexao.getConexao();
	}

    public void incluir(Filmes filme) throws SQLException {
        String sql = "INSERT INTO filmes("
                + "	ano_lancamento,"
                + " faixa_etaria, titulo,"
                + " observacoes, genero)"
                + " VALUES ("
                + "?,?,?,?,?)";

        Connection cnn = Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setString(1, filme.getAnoLancamento());
        ps.setString(2, filme.getFaixaEtaria());
        ps.setString(3, filme.getTitulo());
        ps.setString(4, filme.getSinopse());
        ps.setString(5, filme.getGenero());

        ps.execute();

        //recuperar id gerado
        String sql2 = "SELECT currval('filme_id_seq') as id";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql2);

        if (rs.next()) {
            filme.setId(rs.getInt("id"));
        }
        rs.close();
        cnn.close();
    }

    public void alterar(Filmes filme) throws SQLException {
        String sql = "UPDATE filme SET"
                + "	ano_lancamento = ?,"
                + " faixa_etaria = ?, titulo = ?,"
                + " sinopse = ?"
                + "WHERE"
                + "id = ?";

        Connection cnn = Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setString(1, filme.getAnoLancamento());
        ps.setString(2, filme.getFaixaEtaria());
        ps.setString(3, filme.getTitulo());
        ps.setString(4, filme.getSinopse());
        ps.setInt(5, filme.getId());


        ps.execute();

        //recuperar id gerado
        String sql2 = "SELECT currval('filme_id_seq') as id";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql2);

        if (rs.next()) {
            filme.setId(rs.getInt("id"));
        }
        rs.close();
        cnn.close();

    }

    public void excluir(Filmes filme) throws SQLException {
        String sql = "DELETE FROM filme WHERE id = ?";
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setInt(1, filme.getId());
        ps.execute();
    }

    public Filmes consultar(String filme) throws SQLException {
        String sql = "SELECT * FROM filme WHERE id = ?";
        Connection cnn = Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);

        ps.setString(1, filme);

        ResultSet rs = ps.executeQuery();
        Filmes retorno = new Filmes();
        if (rs.next()) {
            retorno.setId(rs.getInt("id"));
            retorno.setAnoLancamento(rs.getString("ano_lancamento"));
            retorno.setFaixaEtaria(rs.getString("faixa_etaria"));
            retorno.setTitulo(rs.getString("titulo"));
            retorno.setSinopse(rs.getString("sinopse"));
            retorno.setGenero(rs.getString("genero"));
        }
        rs.close();
        cnn.close();
        return retorno;
    }
    
    public List<Filmes> listar() throws SQLException {
        String sql = "SELECT * FROM filme";
        
        Statement stm = cnn.createStatement();
        
        ResultSet rs = stm.executeQuery(sql);
        
        List<Filmes> lista = new ArrayList<>();
        
        while (rs.next()) {
            Filmes filme = new Filmes();
            filme.setId(rs.getInt("id"));
            filme.setAnoLancamento(rs.getString("ano_lancamento"));
            filme.setFaixaEtaria(rs.getString("faixa_etaria"));
            filme.setTitulo(rs.getString("titulo"));
            filme.setSinopse(rs.getString("sinopse"));
            filme.setGenero("genero");
            lista.add(filme);
        }
        rs.close();
        cnn.close();
        
        return lista;
    }
}
