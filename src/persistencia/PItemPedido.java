package persistencia;

import entidade.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PItemPedido {


    public void incluir(ItemPedido item, Connection cnn) throws SQLException {

        String sql = "INSERT INTO item_pedido(quantidade, valor, id_pedido"
                + ", id_copias) VALUES (?,?,?,?)";

        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setDouble(1, item.getQuantidade());
        prd.setDouble(2, item.getValor());
        prd.setInt(3, item.getPedido().getId());


        //prd.setInt(4, item.getTitulo().getId());
        prd.execute();
    }

    public void excluirPorPedido(int idPedido, Connection cnn) throws SQLException{
        String sql = "DELETE FROM item_pedido WHERE id_pedido = ?";

        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, idPedido);

        prd.execute();

    }

}
