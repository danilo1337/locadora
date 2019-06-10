package negocio;

import entidade.Locacao;
import entidade.Locacao_item;
import persistencia.PLocacao_item;

import java.util.List;

public class NLocacao_item {

    private PLocacao_item per;

    public NLocacao_item() {
        per = new PLocacao_item();
    }

    public List<Locacao_item> listar() throws Exception {
        return per.listar();
    }

    public List<Locacao_item> consultar(Locacao locacao)throws Exception{
        return per.consultar(locacao);
    }


    //modified
}
