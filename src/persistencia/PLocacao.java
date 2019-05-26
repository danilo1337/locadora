package persistencia;

import entidade.Locacao_item;
import entidade.Locacao;

import java.sql.*;

public class PPedido {

    public void incluir(Locacao pedido) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);

        try {

            String sql = "INSERT INTO pedido (data, valor, id_pessoal) "
                    + "VALUES (now(),?,?)";

            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setDouble(1, pedido.getValorTotal());
            ps.setInt(2, pedido.getPessoal().getId());

            //gravo o pedido pai
            ps.execute();

            //Recupera a informação do código gerado no pedido
            // pai id id
            String sql2 = "SELECT currval('pedido_id_seq') as id";
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql2);

            if (rs.next()) {
                pedido.setId(rs.getInt("id"));
            }
            rs.close();

            //Percorrer a lista de itens
            //inserindo-as
            PItemPedido pItem = new PItemPedido();
            for (Locacao_item item : pedido.getListaItens()) {
                item.getPedido().setId(pedido.getId());
                //Gravo o item (filho)
                pItem.incluir(item, cnn);
            }

            cnn.commit();

        } catch (Exception e) {
            cnn.rollback();
        }
        cnn.close();
    }


    //testar alterar após dar o pull com entidades de filmes / copias
    public void alterar(Locacao pedido) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);
        try {

            //Passo 1 - Atualizar o pedido
            String sql = "UPDATE pedido "
                    + "SET data = now(), "
                    + "valor = ? WHERE id = ?";

            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setDouble(1, pedido.getValorTotal());
            ps.setInt(2, pedido.getId());
            ps.execute();

            //Passo 2 - Excluir todos os itens de um pedido
            PItemPedido pItem = new PItemPedido();
            pItem.excluirPorPedido(pedido.getId(), cnn);

            //Passo 3 - Incluir todos os itens do pedido
            for (Locacao_item item : pedido.getListaItens()) {
                item.getPedido().setId(pedido.getId());
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

    //testar após da o pull com entidades de filme / copia
    public void excluir(Locacao pedido) throws SQLException {
        Connection cnn = util.Conexao.getConexao();
        cnn.setAutoCommit(false);
        try {

            //Excluir todos os itens de um pedido
            new PItemPedido().excluirPorPedido(pedido.getId(), cnn);

            //Exclui o pedido
            String sql = "DELETE FROM pedido "
                    + " WHERE id = ?";

            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, pedido.getId());
            ps.execute();
            //Efetua a gravação no banco de dados
            cnn.commit();

        } catch (Exception e) {
            //Desfaz as alterações no banco de dados
            cnn.rollback();
        }
        cnn.close();
    }


}
