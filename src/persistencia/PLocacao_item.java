package persistencia;

import entidade.Locacao;
import entidade.Locacao_item;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PLocacao_item {

    public void incluir(Locacao_item item, Connection cnn) throws SQLException {

        String sql = "INSERT INTO locacao_item (locacao_id, copia_id, valor"
        + " VALUES (?,?,?,?)";

        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, item.getLocacao().getId());
        prd.setInt(2, item.getCopias().getId());
        prd.setDouble(3, item.getValor());
        prd.execute();

    }

    public void excluirPorPedido(int idLocacao, Connection cnn) throws SQLException{
        String sql = "DELETE FROM locacao_item WHERE locacao_id = ?";

        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, idLocacao);

        prd.execute();

    }

    public List<Locacao_item> listar() throws Exception {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);

            String sql = "SELECT LocItem.id, LocItem.locacao_id, LocItem.copia_id, LocItem.valor, Locacao.id, Locacao.data_locacao, "
                    + "Locacao.data_devolucao, Locacao.data_pagamento, Locacao.forma_pagamento, Locacao.valor_total, Locacao.juros, "
                    + "Locacao.multa, Locacao.desconto, COUNT (*) * LocItem.valor AS ValorTotal"
                    + "FROM locacao INNER JOIN locacao_item"
                    + "ON (LocItem.locacao_id = Locacao.id)"
                    + "WHERE 1 = 1";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        List<Locacao_item> lista = new ArrayList<>();

        while (rs.next()) {

            Locacao_item item = new Locacao_item();
            item.setId(rs.getInt("LocItem.id"));
            item.getLocacao().setId(rs.getInt("LocItem.locacao_id"));
            //Descomentar ao existir Entidade Copia
            //item.getLocacao().setId(rs.getInt("LocItem.copia_id"));
            item.setValor(rs.getDouble("LocItem.valor"));
            item.getLocacao().setId(rs.getInt("Locacao.id"));
            item.getLocacao().setData_locacao(rs.getDate("Locacao.data_locacao"));
            item.getLocacao().setData_devolucao(rs.getDate("Locacao.data_devolucao"));
            item.getLocacao().setData_pagamento(rs.getDate("Locacao.data_pagamento"));
            item.getLocacao().setForma_pagamento(rs.getString("Locacao.forma_pagamento"));
            item.getLocacao().setValor_total(rs.getDouble("ValorTotal"));
            item.getLocacao().setJuros(rs.getDouble("Locacao.juros"));
            item.getLocacao().setMulta(rs.getDouble("Locacao.multa"));
            item.getLocacao().setDesconto(rs.getDouble("Locacao.desconto"));
            lista.add(item);
        }

        rs.close();
        cnn.close();
        return lista;
    }

    public List<Locacao_item> consultar(Locacao locacao) throws Exception {

        Connection cnn = Conexao.getConexao();
        String sql = "SELECT LocItem.copia_id, LocItem.locacao_id, LocItem.valor, Copias.id, Copias.disponivel,"
                + " Copias.reservada, Copias.disponivel_venda, Copias.data_reserva, Copias.data_compra, Copias.data_venda, Locacao.id,"
                + " Locacao.valor_total, COUNT (*) * LocItem.valor AS ValorTotal"
                + " FROM locacao_item INNER JOIN copias"
                + " ON LocItem.copia_id = Copias_id"
                + " INNER JOIN locacao"
                + " ON LocItem_locacao_id = Locacao.id"
                + " WHERE LocItem.copias_id = ?";

        PreparedStatement stm = cnn.prepareStatement(sql);
        stm.setInt(1, locacao.getId());
        ResultSet rs = stm.executeQuery();

        List<Locacao_item> listar = new ArrayList<>();

        while (rs.next()) {
            Locacao_item item = new Locacao_item();

            item.setId(rs.getInt("LocItem.copia_id"));
            item.setValor(rs.getDouble("LocItem.valor"));
            item.getCopias().setId(rs.getInt("Copias.id"));
            item.getCopias().setDisponivel(rs.getBoolean("Copias.disponivel"));
            item.getCopias().setReserva(rs.getBoolean("Copias.reservada"));
            item.getCopias().setDisponivelVenda(rs.getBoolean("Copias.disponivel_venda"));
            item.getCopias().setDataReserva(rs.getDate("Copias.data_reserva"));
            item.getCopias().setDataCompra(rs.getDate("Copias.data_compra"));
            item.getCopias().setDataVenda(rs.getDate("Copias.data_venda"));
            item.getLocacao().setId(rs.getInt("Locacao.id"));
            item.getLocacao().setValor_total(rs.getDouble("ValorTotal"));
            listar.add(item);
        }

        return listar;
    }

    //submit again
}
