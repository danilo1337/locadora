package padrao.iterator;

import java.util.ArrayList;
import java.util.Iterator;

import entidade.Locacao;
import persistencia.PLocacao;

public class LocacaoIterator {

    public LocacaoIterator() {
    }

    public Iterator<Locacao> listagemComArrayList() throws Exception {
        ArrayList<Locacao> arrayList = new ArrayList<>();
        arrayList.addAll(new PLocacao().listar2());
        return arrayList.iterator();
    }
}
