package persistencia;

import entidade.Locacao;
import entidade.Locacao_item;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PLocacao_item {

    public void incluir(Locacao_item item, Connection cnn) throws SQLException {

        String sql = "INSERT INTO locacao_item (locacao_id, copia_id, data_devolucao, valor"
        + " VALUES (?,?,?,?,?)";

        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, item.getLocacao().getId());
        prd.setInt(2, item.getCopias().getId());
        prd.setDate(3,item.getData_devolucao());
        prd.setDouble(4, item.getValor());
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

            String sql = "SELECT LOCACAO_ITEM.ID, LOCACAO_ITEM.LOCACAO_ID, LOCACAO_ITEM.COPIA_ID, COPIAS.ID LOCACAO_ITEM.DATA_DEVOLUCAO, LOCACAO.ID,"
                +"LOCACAO_ITEM.VALOR, LOCACAO.DATA_PAGAMENTO, LOCACAO.FORMA_PAGAMENTO, LOCACAO.MULTA, LOCACAO.DATA_LOCACAO"
                +"COUNT (*) * LOCACAO_ITEM.VALOR AS VALORTOTAL"
                +"FROM LOCACAO_ITEM INNER JOIN LOCACAO"
                +"ON (LOCACAO_ITEM.LOCACAO_ID = LOCACAO.ID)"
                +"INNER JOIN COPIAS"
                +"ON (LOCACAO_ITEM.COPIAS_ID = COPIAS.ID)"
                +"GROUP BY LOCACAO_ITEM.ID, LOCACAO.ID";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        List<Locacao_item> lista = new ArrayList<>();

        while (rs.next()) {

            Locacao_item item = new Locacao_item();
            item.setId(rs.getInt("LOCACAO_ITEM.ID"));
            item.getLocacao().setId(rs.getInt("LOCACAO_ITEM.LOCACAO_ID"));
            item.setId(rs.getInt("LOCACAO_ITEM.COPIA_ID"));
            item.getCopias().setId(rs.getInt("COPIAS.ID"));
            item.setData_devolucao(rs.getDate("LOCACAO_ITEM.DATA_DEVOLUCAO"));
            item.getLocacao().setId(rs.getInt("LOCACAO.ID"));
            item.setValor(rs.getDouble("LOCACAO_ITEM.VALOR"));
            item.getLocacao().setData_pagamento(rs.getDate("LOCACAO.DATA_PAGAMENTO"));
            item.getLocacao().setForma_pagamento(rs.getString("LOCACAO.FORMA_PAGAMENTO"));
            item.getLocacao().setMulta(rs.getDouble("LOCACAO.MULTA"));
            item.getLocacao().setData_locacao(rs.getDate("LOCACAO.DATA_LOCACAO"));
            item.getLocacao().setValor_total(rs.getDouble("VALORTOTAL"));
            lista.add(item);
        }

        rs.close();
        cnn.close();
        return lista;
    }

    public List<Locacao_item> consultar(Locacao locacao) throws Exception {

        Connection cnn = Conexao.getConexao();
                String sql = "SELECT LOCACAO_ITEM.ID, LOCACAO_ITEM.COPIA_ID, COPIAS.ID, LOCACAO_ITEM.LOCACAO_ID, LOCACAO.ID,"
                + "LOCACAO_ITEM.VALOR, COPIAS.DISPONIVEL, COPIAS.RESERVADA, COPIAS.DISPONIVEL_VENDA,"
                + "COPIAS.DATA_RESERVA, COPIAS.DATA_COMPRA, COPIAS.DATA_VENDA,"
                + "COUNT (*) * LOCACAO_ITEM.VALOR AS VALORTOTAL"
                + "FROM LOCACAO_ITEM INNER JOIN COPIAS"
                + "ON (LOCACAO_ITEM.COPIA_ID = COPIAS.ID)"
                + "INNER JOIN LOCACAO"
                + "ON (LOCACAO_ITEM.LOCACAO_ID = LOCACAO.ID)"
                + "GROUP BY LOCACAO_ITEM.COPIA_ID, COPIAS.ID, LOCACAO_ITEM.LOCACAO_ID, LOCACAO.ID,"
                + "LOCACAO_ITEM.VALOR";

        PreparedStatement stm = cnn.prepareStatement(sql);
        stm.setInt(1, locacao.getId());
        ResultSet rs = stm.executeQuery();

        List<Locacao_item> listar = new ArrayList<>();

        while (rs.next()) {
            Locacao_item item = new Locacao_item();


            item.setId(rs.getInt("LOCACAO_ITEM.ID"));
            item.setId(rs.getInt("LOCACAO_ITEM.COPIA_ID"));
            item.getCopias().setId(rs.getInt("COPIAS.ID"));
            item.setId(rs.getInt("LOCACAO_ITEM.LOCACAO_ID"));
            item.getLocacao().setId(rs.getInt("LOCACAO.ID"));
            item.setValor(rs.getDouble("LOCACAO_ITEM.VALOR"));
            item.getCopias().setDisponivel(rs.getBoolean("COPIAS.DISPONIVEL"));
            item.getCopias().setReserva(rs.getBoolean("COPIAS.RESERVADA"));
            item.getCopias().setDisponivelVenda(rs.getBoolean("COPIAS.DISPONIVEL_VENDA"));
            item.getCopias().setDataReserva(rs.getDate("COPIAS.DATA_RESERVA"));
            item.getCopias().setDataCompra(rs.getDate("COPIAS.DATA_COMPRA"));
            item.getCopias().setDataVenda(rs.getDate("COPIAS.DATA_VENDA"));
            item.getLocacao().setValor_total(rs.getDouble("VALORTOTAL"));
            listar.add(item);
        }

        return listar;
    }
}
