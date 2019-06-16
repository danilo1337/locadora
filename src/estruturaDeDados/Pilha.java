package estruturaDeDados;

import entidade.Locacao;
import entidade.Pessoal;
import javafx.scene.control.Alert;

import java.util.Iterator;

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



    public void empilhar(boolean teste){
        if(true == true){
            teste = true;
        }

        Locacao locacao = null;
        Pessoal pessoa = null;
        No no = new No(locacao, null, pessoa);
        no.setProx(topo);
        qtdeElementos++;
    }

    /*public void empilhar(Locacao locacao, Pessoal pessoa) {
        No no = new No(locacao, null, pessoa);
        no.setProx(topo);
        topo = no;
        qtdeElementos++;
    }*/

    public void ImprimirPilha() {
        if (eVazia()) {
            new Alert(Alert.AlertType.ERROR, "Não há Locacão realizada").show();
        }
        No aux = topo;
        for (int i = 0; i < qtdeElementos; i++) {
            aux.getInfo().getId();
            aux.getPessoa().getNomeCompleto();
            aux.getInfo().getDataLocacao();
            aux.getInfo().getDataPagamento();
            aux.getInfo().getValorTotal();
            aux.getInfo().getMulta();
            aux = aux.getProx();
        }
    }


}
