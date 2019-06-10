package apresentacao;

import entidade.Locacao_item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistencia.PLocacao_item;

import java.util.List;
import java.util.Queue;

public class teste {
    public static void main(String[] args) throws Exception {
        List<Locacao_item> listar = new PLocacao_item().listarProdutos();
        for (int i = 0; i < listar.size(); i++){
            System.out.println(listar.get(i).getFilmes().getTitulo());
        }
    }
}
