package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidade.Locacao;
import entidade.LocacaoItem;
import entidade.Pessoal;
import negocio.NLocacaoItem;
import negocio.NPessoal;
import util.Conexao;

public class PLocacao {

    public void incluir(Locacao locacao) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);

        try {

            String sql = "INSERT INTO "
                    +"locacao (pessoal_id, data_locacao, data_pagamento, forma_pagamento, valor_total," +
                    " juros, multa, desconto) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = cnn.prepareStatement(sql);

            //passo os itens da sql para o statement
            ps.setInt(1, locacao.getPessoal().getId());
            ps.setDate(2, locacao.getDataLocacao());
            ps.setDate(4, locacao.getDataPagamento());
            ps.setString(5, locacao.getForma_pagamento());
            ps.setDouble(6, locacao.getValorTotal());
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
            PLocacaoItem pItem = new PLocacaoItem();
            for (LocacaoItem item : locacao.getListaItens()) {
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
                    + "data_pagamento = ?, "
                    + "forma_pagamento = ?, "
                    + "valor_total = ?, "
                    + "juros = ?, "
                    + "multa = ?, "
                    + "desconto = ? "
                    + "WHERE id = ?";

            PreparedStatement ps = cnn.prepareStatement(sql);

            ps.setDate(1, locacao.getDataLocacao());
            ps.setDate(3, locacao.getDataPagamento());
            ps.setString(4, locacao.getForma_pagamento());
            ps.setDouble(5, locacao.getValorTotal());
            ps.setDouble(6, locacao.getJuros());
            ps.setDouble(7, locacao.getMulta());
            ps.setDouble(8, locacao.getDesconto());
            ps.setInt(9, locacao.getId());
            ps.execute();

            //Excluir todos os itens de um pedido
            PLocacaoItem pItem = new PLocacaoItem();
            pItem.excluirPorPedido(locacao.getId(), cnn);

            //Incluir todos os itens do pedido
            for (LocacaoItem item : locacao.getListaItens()) {
                item.getLocacao().setId(locacao.getId());
                pItem.incluir(item, cnn);
            }
            //Efetua a gravação no banco de dadosSocios
            cnn.commit();

        } catch (Exception e) {
            //Desfaz as alterações no banco de dadosSocios
            cnn.rollback();
        }
        cnn.close();
    }

    public void excluir(Locacao locacao) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);

        try {
            //Excluir todos os itens de um pedido
            new PLocacaoItem().excluirPorPedido(locacao.getId(), cnn);

            //Exclui o pedido
            String sql = "DELETE FROM locacao "
                    + " WHERE id = ?";

            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, locacao.getId());
            ps.execute();

            //Efetua a gravação no banco de dadosSocios
            cnn.commit();

        } catch (Exception e) {
            //Desfaz as alterações no banco de dadosSocios
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
            String sql = "SELECT id, pessoal_id, data_locacao, data_pagamento, forma_pagamento, valor_total"
                    + " juros, multa, desconto"
                    + " FROM locacao "
                    + " WHERE id = ?";

            PreparedStatement stm = cnn.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                locacao.setId(rs.getInt("id"));
                locacao.setDataLocacao(rs.getDate("data_locacao"));
                locacao.setDataLocacao(rs.getDate("data_pagamento"));
                locacao.setForma_pagamento(rs.getString("forma_pagamento"));
                locacao.setValorTotal(rs.getDouble("valor_total"));
                locacao.setJuros(rs.getDouble("juros"));
                locacao.setMulta(rs.getDouble("multa"));
                locacao.setDesconto(rs.getDouble("desconto"));
            }

            PLocacaoItem pItem = new PLocacaoItem();
            for (LocacaoItem item : locacao.getListaItens()) {
                item.getLocacao().setId(locacao.getId());
                //Gravo o item (filho)
                pItem.incluir(item, cnn);
            }
            //Efetua a gravação no banco de dadosSocios
            cnn.commit();
            rs.close();

        } catch (Exception e) {
            //Desfaz as alterações no banco de dadosSocios
            cnn.rollback();
        }

        cnn.close();
        return locacao;
    }

    public List<Locacao> listar() throws Exception {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);

        String sql = "SELECT id, pessoal_id, data_locacao, data_pagamento, forma_pagamento, valor_total"
                + " juros, multa, desconto"
                + " FROM locacao "
                + " WHERE id = ?";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        List<Locacao> lista = new ArrayList<>();

        while (rs.next()) {
            Pessoal pessoal = new NPessoal().consultarCpf(rs.getString("pessoal_id"));
            Locacao locacao = new Locacao();
            locacao.setId(rs.getInt("id"));
            locacao.setDataLocacao(rs.getDate("data_locacao"));
            locacao.setDataLocacao(rs.getDate("data_pagamento"));
            locacao.setForma_pagamento(rs.getString("forma_pagamento"));
            locacao.setValorTotal(rs.getDouble("valor_total"));
            locacao.setJuros(rs.getDouble("juros"));
            locacao.setMulta(rs.getDouble("multa"));
            locacao.setDesconto(rs.getDouble("desconto"));

            locacao.setListaItens(new NLocacaoItem().listar());
            lista.add(locacao);
        }

        rs.close();
        cnn.close();
        return lista;
    }





//modified

}
