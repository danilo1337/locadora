package persistencia;

import entidade.Locacao_item;
import entidade.Locacao;
import entidade.Pessoal;
import negocio.NLocacao_item;
import negocio.NPessoal;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PLocacao {

    public void incluir(Locacao locacao) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);

        try {

            String sql = "INSERT INTO "
                    +"locacao (pessoal_id, data_locacao, data_devolucao, data_pagamento, forma_pagamento, valor_total," +
                    " juros, multa, desconto) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = cnn.prepareStatement(sql);

            //passo os itens da sql para o statement
            ps.setInt(1, locacao.getPessoal().getId());
            ps.setDate(2, locacao.getData_locacao());
            ps.setDate(3, locacao.getData_devolucao());
            ps.setDate(4, locacao.getData_pagamento());
            ps.setString(5, locacao.getForma_pagamento());
            ps.setDouble(6, locacao.getValor_total());
            ps.setDouble(7, locacao.getJuros());
            ps.setDouble(8, locacao.getMulta());
            ps.setDouble(9, locacao.getDesconto());

            //gravo o locacao pai
            ps.execute();

            // pai id id
            // recuperar id gerado
            String sql2 = "SELECT currval('locacao_id_seq') as id";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql2);

            if (rs.next()) {
                locacao.setId(rs.getInt("id"));
            }
            rs.close();

            //Percorrer a lista de itens
            PLocacao_item pItem = new PLocacao_item();
            for (Locacao_item item : locacao.getListaItens()) {
                item.getLocacao().setId(locacao.getId());

                //Gravo o item (filho)
                pItem.incluir(item, cnn);
            }

            cnn.commit();

        } catch (Exception e) {
            cnn.rollback();
        }
        cnn.close();
    }


        public void alterar(Locacao locacao) throws SQLException {
        Connection cnn = Conexao.getConexao();
        cnn.setAutoCommit(false);

        try {

            //- Atualizar o pedido
            String sql = "UPDATE locacao "
                    + "SET data_locacao = ?, "
                    + "data_devolucao = ?, "
                    + "data_pagamento = ?, "
                    + "forma_pagamento = ?, "
                    + "valor_total = ?, "
                    + "juros = ?, "
                    + "multa = ?, "
                    + "desconto = ? "
                    + "WHERE id = ?";

            PreparedStatement ps = cnn.prepareStatement(sql);

            ps.setDate(1, locacao.getData_locacao());
            ps.setDate(2, locacao.getData_devolucao());
            ps.setDate(3, locacao.getData_pagamento());
            ps.setString(4, locacao.getForma_pagamento());
            ps.setDouble(5, locacao.getValor_total());
            ps.setDouble(6, locacao.getJuros());
            ps.setDouble(7, locacao.getMulta());
            ps.setDouble(8, locacao.getDesconto());
            ps.setInt(9, locacao.getId());
            ps.execute();

            //Excluir todos os itens de um pedido
            PLocacao_item pItem = new PLocacao_item();
            pItem.excluirPorPedido(locacao.getId(), cnn);

            //Incluir todos os itens do pedido
            for (Locacao_item item : locacao.getListaItens()) {
                item.getLocacao().setId(locacao.getId());
                pItem.incluir(item, cnn);
            }
            //Efetua a gravação no banco de dados
            cnn.commit();

        } catch (Exception e) {
            //Desfaz as alterações no banco de dados
            cnn.rollback();
        }
        cnn.close();
    }

    public void excluir(Locacao locacao) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);

        try {
            //Excluir todos os itens de um pedido
            new PLocacao_item().excluirPorPedido(locacao.getId(), cnn);

            //Exclui o pedido
            String sql = "DELETE FROM locacao "
                    + " WHERE id = ?";

            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, locacao.getId());
            ps.execute();

            //Efetua a gravação no banco de dados
            cnn.commit();

        } catch (Exception e) {
            //Desfaz as alterações no banco de dados
            cnn.rollback();
        }
        cnn.close();
    }

    public Locacao consultar(int id) throws SQLException {

        Connection cnn = Conexao.getConexao();
        cnn.setAutoCommit(false);
        Locacao locacao = new Locacao();
        //Select do que será consultado
        try {
            String sql = "SELECT id, pessoal_id, data_locacao, data_devolucao, data_pagamento, forma_pagamento, valor_total"
                    + " juros, multa, desconto"
                    + " FROM locacao "
                    + " WHERE id = ?";

            PreparedStatement stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                locacao.setId(rs.getInt("id"));
                locacao.setData_locacao(rs.getDate("data_locacao"));
                locacao.setData_locacao(rs.getDate("data_devolucao"));
                locacao.setData_locacao(rs.getDate("data_pagamento"));
                locacao.setForma_pagamento(rs.getString("forma_pagamento"));
                locacao.setValor_total(rs.getDouble("valor_total"));
                locacao.setJuros(rs.getDouble("juros"));
                locacao.setMulta(rs.getDouble("multa"));
                locacao.setDesconto(rs.getDouble("desconto"));
            }

            PLocacao_item pItem = new PLocacao_item();
            for (Locacao_item item : locacao.getListaItens()) {
                item.getLocacao().setId(locacao.getId());
                //Gravo o item (filho)
                pItem.incluir(item, cnn);
            }
            //Efetua a gravação no banco de dados
            cnn.commit();
            rs.close();

        } catch (Exception e) {
            //Desfaz as alterações no banco de dados
            cnn.rollback();
        }

        cnn.close();
        return locacao;
    }

    public List<Locacao> listar() throws Exception {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);

        String sql = "SELECT id, pessoal_id, data_locacao, data_devolucao, data_pagamento, forma_pagamento, valor_total"
                + " juros, multa, desconto"
                + " FROM locacao "
                + " WHERE id = ?";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        List<Locacao> lista = new ArrayList<>();

        while (rs.next()) {
            Pessoal pessoal = new NPessoal().consultar_cpf(rs.getString("pessoal_id"));
            Locacao locacao = new Locacao();
            locacao.setId(rs.getInt("id"));
            locacao.setData_locacao(rs.getDate("data_locacao"));
            locacao.setData_locacao(rs.getDate("data_devolucao"));
            locacao.setData_locacao(rs.getDate("data_pagamento"));
            locacao.setForma_pagamento(rs.getString("forma_pagamento"));
            locacao.setValor_total(rs.getDouble("valor_total"));
            locacao.setJuros(rs.getDouble("juros"));
            locacao.setMulta(rs.getDouble("multa"));
            locacao.setDesconto(rs.getDouble("desconto"));

            locacao.setListaItens(new NLocacao_item().listar());
            lista.add(locacao);
        }

        rs.close();
        cnn.close();
        return lista;
    }





//modified

}
