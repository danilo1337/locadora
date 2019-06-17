package estruturaDeDados;

import entidade.Locacao;
import entidade.Pessoal;
import persistencia.PLocacao;

public class No {

    private Locacao info;
    private No prox;

    public No(Locacao info, No prox) {
        this.info = info;
        this.prox = prox;

    }

    public Locacao getInfo() {
        return info;
    }

    public void setInfo(Locacao info) {
        this.info = info;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }



}
