package estruturaDeDados;

import entidade.Locacao;
import entidade.Pessoal;

public class No {

    private Locacao info;
    private No prox;
    private Pessoal pessoa;

    public No(Locacao info, No prox, Pessoal pessoa) {
        this.info = info;
        this.prox = prox;
        this.pessoa = pessoa;
        
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

    public Pessoal getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoal pessoa) {
        this.pessoa = pessoa;
    }
}
