package padrao.iterator;

import entidade.Locacao;
import entidade.Pessoal;
import persistencia.PLocacao;
import persistencia.PPessoal;

import java.util.ArrayList;
import java.util.Iterator;

public class LocacaoIterator {

    public LocacaoIterator() {
    }

    public Iterator<Locacao> listagemComArrayList() throws Exception {
        ArrayList<Locacao> arrayList = new ArrayList<>();
        arrayList.addAll(new PLocacao().listar());
        return arrayList.iterator();
    }
}
