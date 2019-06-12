package apresentacao;

import entidade.LocacaoItem;
import persistencia.PLocacaoItem;

import java.util.List;

public class teste {
    public static void main(String[] args) throws Exception {
        List<LocacaoItem> listar = new PLocacaoItem().listarProdutos();
        for (int i = 0; i < listar.size(); i++){
            System.out.println(listar.get(i).getFilmes().getTitulo());
        }
    }
}
