package estruturaDeDados;

import entidade.Locacao;
import entidade.Pessoal;
import javafx.scene.control.Alert;
import persistencia.PLocacao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pilha {

    private int qtdeElementos;
    private No topo;

    public Pilha() {
        qtdeElementos = 0;
        topo = null;
    }
    public boolean eVazia() {
        return topo == null;
    }

    public void empilhar(Locacao locacao) throws Exception {
        No no = new No(locacao,null);
        no.setProx(topo);
        topo = no;
        qtdeElementos++;
    }


    public List<Locacao> listar(){
        if (eVazia()) {
            return null;
        }
        List<Locacao> list = new ArrayList<>();
        list.add(topo.getInfo());
        No aux = topo;
        topo = topo.getProx();
        aux.setProx(null);
        qtdeElementos--;
        return list;
    }

/*    public Locacao desempilhar() {
        if (eVazia()) {
            return null;
        }

        Locacao retorno = topo.getInfo();
        No aux = topo;
        topo = topo.getProx();
        aux.setProx(null);
        qtdeElementos--;
        return retorno;
    }*/

    public void ImprimirPilha() {
        if (eVazia()) {
            new Alert(Alert.AlertType.ERROR, "Não há Locação realizada").show();
        }
        System.out.println(listar().toString());
        No aux = topo;
        for (int i = 0; i < qtdeElementos; i++) {
            aux.getInfo().getId();
            aux.getInfo().getPessoal().getNomeCompleto();
            aux.getInfo().getDataLocacao();
            aux.getInfo().getDataPagamento();
            aux.getInfo().getValorTotal();
            aux.getInfo().getMulta();
            aux = aux.getProx();
        }
    }


}
