package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Copias;
import entidade.Filmes;
import entidade.TipoFilme;

/*
 * @author Hugo Henrique
 */

public class PCopias {

    public void incluir(Copias copias) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);
        try {
            String sql = "INSERT INTO copias(codigo_copia, filme_id, disponivel, reservada, disponivel_venda, data_reserva, data_venda) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, copias.getCodigoCopia());
            ps.setInt(2, copias.getFilmeId());
            ps.setBoolean(3, copias.getDisponivel());
            ps.setBoolean(4, copias.getReservada());
            ps.setBoolean(5, copias.getDisponivelVenda());
            ps.setDate(6, copias.getDataReserva());
            ps.setDate(7, copias.getDataVenda());
            ps.execute();
            cnn.commit();
        } catch (Exception e) {
            cnn.rollback();
            e.printStackTrace();
        }
        cnn.close();
    }

    public Copias consultar(int id) throws SQLException {
        String sql = "SELECT id, codigo_copia, filme_id, disponivel, reservada, disponivel_venda, data_reserva, data_venda FROM copias WHERE id = ?";
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return setRetornoCopias(cnn, rs);
    }

    public Copias consultarCodigo(String codigoCopia) throws SQLException {
        String sql = "SELECT copias.id as copia_id, codigo_copia, filme_id, disponivel, reservada, disponivel_venda, data_reserva, data_venda, \n" +
                "ano_lancamento, faixa_etaria, titulo, sinopse, genero, tipo_id, tipo, preco, preco_venda\n" +
                "FROM copias INNER JOIN filme on (copias.filme_id = filme.id) INNER JOIN tipo_filme on (filme.tipo_id = tipo_filme.id) WHERE codigo_copia = ? AND disponivel = true";
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);

        ps.setString(1, codigoCopia);
        ResultSet rs = ps.executeQuery();

        Copias retorno = new Copias();
        if (rs.next()) {
            retorno.setId(rs.getInt("copia_id"));
            retorno.setCodigoCopia(rs.getString("codigo_copia"));
            retorno.setFilmeId(rs.getInt("filme_id"));
            retorno.setDisponivel(rs.getBoolean("disponivel"));
            retorno.setReservada(rs.getBoolean("reservada"));
            retorno.setDisponivelVenda(rs.getBoolean("disponivel_venda"));
            retorno.setDataReserva(rs.getDate("data_reserva"));
            retorno.setDataVenda(rs.getDate("data_venda"));
            TipoFilme tipoFilme = new TipoFilme(rs.getInt("tipo_id"), rs.getString("tipo"),
                    rs.getDouble("preco"), rs.getDouble("preco_venda"));
            Filmes filmes = new Filmes(rs.getInt("filme_id"), rs.getString("ano_lancamento"), rs.getString("faixa_etaria"),
                    rs.getString("titulo"), rs.getString("sinopse"), rs.getString("genero"), tipoFilme);
            retorno.setFilmes(filmes);
        }
        rs.close();
        cnn.close();
        return retorno;
    }

    public void alterar(Copias copias) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);
        try {
            String sql = "UPDATE copias SET disponivel = ?, reservada =?, disponivel_venda =?, data_reserva=?, data_venda=?  WHERE id = ?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setBoolean(1, copias.getDisponivel());
            ps.setBoolean(2, copias.getReservada());
            ps.setBoolean(3, copias.getDisponivelVenda());
            ps.setDate(4, copias.getDataReserva());
            ps.setDate(5, copias.getDataVenda());
            ps.setInt(6, copias.getId());
            ps.execute();
            cnn.commit();
        } catch (Exception e) {
            cnn.rollback();
            e.printStackTrace();
        }
        cnn.close();
    }

    public void alterarLocacao(Copias copias, Connection cnn) throws SQLException {
        String sql = "UPDATE copias SET disponivel = ?, reservada =?, disponivel_venda =?, data_reserva=?, data_venda=?  WHERE id = ?";
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setBoolean(1, copias.getDisponivel());
        ps.setBoolean(2, copias.getReservada());
        ps.setBoolean(3, copias.getDisponivelVenda());
        ps.setDate(4, copias.getDataReserva());
        ps.setDate(5, copias.getDataVenda());
        ps.setInt(6, copias.getId());
        ps.execute();
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM copias WHERE id = ?";
        Connection cnn = util.Conexao.getConexao();
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }

    public List<Copias> listar(Copias param) throws SQLException {
        String sql = "SELECT"
        		+ " C.id,C.codigo_copia,"
        		+ " C.filme_id, C.disponivel,"
        		+ " C.reservada, C.disponivel_venda,"
        		+ " C.data_reserva, C.data_venda,"
        		+ " F.TITULO"
        		+ " FROM COPIAS C "
        		+ " INNER JOIN FILME F "
        		+ " ON C.FILME_ID = F.ID "
        		+ " WHERE 1 = 1 ";
        Connection cnn = util.Conexao.getConexao();
        if (param.getFilmes().getTitulo() != null) {
			if (!param.getFilmes().getTitulo().isEmpty()) {
				sql += " AND upper(F.TITULO) like ?";
			}
		}

		PreparedStatement prd = cnn.prepareStatement(sql);
		if (param.getFilmes().getTitulo() != null) {
            if (!param.getFilmes().getTitulo().isEmpty()) {
                prd.setString(1, "%" + param.getFilmes().getTitulo().toUpperCase() + "%");
            }
        }
        ResultSet rs = prd.executeQuery();

        List<Copias> lista = new ArrayList<>();
        while (rs.next()) {
            Copias retorno = new Copias();
            retorno.setId(rs.getInt("id"));
            retorno.setCodigoCopia(rs.getString("codigo_copia"));
            retorno.setFilmeId(rs.getInt("filme_id"));
            retorno.setDisponivel(rs.getBoolean("disponivel"));
            retorno.setReservada(rs.getBoolean("reservada"));
            retorno.setDisponivelVenda(rs.getBoolean("disponivel_venda"));
            retorno.setDataReserva(rs.getDate("data_reserva"));
            retorno.setDataVenda(rs.getDate("data_venda"));
            Filmes f = new Filmes();
            f.setId(rs.getInt("filme_id"));
            f.setTitulo(rs.getString("titulo"));
            retorno.setFilmes(f);
            lista.add(retorno);
        }
        rs.close();
        cnn.close();

        return lista;
    }

    private Copias setRetornoCopias(Connection cnn, ResultSet rs) throws SQLException {
        Copias retorno = new Copias();
        if (rs.next()) {
            retorno.setId(rs.getInt("id"));
            retorno.setCodigoCopia(rs.getString("codigo_copia"));
            retorno.setFilmeId(rs.getInt("filme_id"));
            retorno.setDisponivel(rs.getBoolean("disponivel"));
            retorno.setReservada(rs.getBoolean("reservada"));
            retorno.setDisponivelVenda(rs.getBoolean("disponivel_venda"));
            retorno.setDataReserva(rs.getDate("data_reserva"));
            retorno.setDataVenda(rs.getDate("data_venda"));
        }
        rs.close();
        cnn.close();
        return retorno;
    }
}
