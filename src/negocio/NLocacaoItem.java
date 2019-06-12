package negocio;

import entidade.Locacao;
import entidade.LocacaoItem;
import persistencia.PLocacaoItem;

import java.util.List;

public class NLocacaoItem {

    private PLocacaoItem per;

    public NLocacaoItem() {
        per = new PLocacaoItem();
    }

    public List<LocacaoItem> listar() throws Exception {
        return per.listar();
    }

    public List<LocacaoItem> consultar(Locacao locacao)throws Exception{
        return per.consultar(locacao);
    }
}
