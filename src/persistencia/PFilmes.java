package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidade.Filmes;
import util.Conexao;

public class PFilmes {

    public void incluir(Filmes filme) throws SQLException {
        String sql = "INSERT INTO filmes("
                + "	ano_lancamento,"
                + " faixa_etaria, titulo,"
                + " observacoes, genero_id)"
                + " VALUES ("
                + "?,?,?,?,?)";

        Connection cnn = Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setString(1, filme.getAnoLancamento());
        ps.setInt(2, filme.getFaixaEtaria());
        ps.setString(3, filme.getTitulo());
        ps.setString(4, filme.getObservacao());

        new PGenero().incluir(filme.getGenero(), cnn);

        ps.setInt(5, filme.getGenero().getId());

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
        String sql = "UPDATE endereco SET"
                + "	ano_lancamento = ?,"
                + " faixa_etaria = ?, titulo = ?,"
                + " observacoes = ?"
                + "WHERE"
                + "id = ?";

        Connection cnn = Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setString(1, filme.getAnoLancamento());
        ps.setInt(2, filme.getFaixaEtaria());
        ps.setString(3, filme.getTitulo());
        ps.setString(4, filme.getObservacao());
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

    public void excluir(Filmes filme, Connection cnn) throws SQLException {
        String sql = "DELETE FROM endereco WHERE id = ?";
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setInt(1, filme.getId());
        ps.execute();
    }

    public Filmes consultar(Filmes filme) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id = ?";
        Connection cnn = Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);

        ps.setInt(1, filme.getId());

        ResultSet rs = ps.executeQuery();
        Filmes retorno = new Filmes();
        if (rs.next()) {
            retorno.setId(rs.getInt("id"));
            retorno.setAnoLancamento(rs.getString("ano_lancamento"));
            retorno.setFaixaEtaria(rs.getInt("faixa_etaria"));
            retorno.setTitulo(rs.getString("titulo"));
            retorno.setObservacao(rs.getString("observacoes"));
            retorno.getGenero().setId(rs.getInt("genero_id"));
        }
        rs.close();
        cnn.close();
        return retorno;
    }
}
