package entidade;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Locacao {

        private int id;
            private Date data_locacao;
            private Date data_pagamento;
            private String forma_pagamento;
            private double valor_total;
            private double juros;
            private double multa;
            private double desconto;
            private Pessoal pessoal;
            private List<LocacaoItem> listaItens;

        public Locacao() {
            pessoal = new Pessoal();
            listaItens = new ArrayList<>();
        }

    public Locacao(Pessoal pessoal, List<LocacaoItem> listaItens) {
        this.pessoal = pessoal;
        this.listaItens = listaItens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_locacao() {
        return data_locacao;
    }

    public void setData_locacao(Date data_locacao) {
        this.data_locacao = data_locacao;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Pessoal getPessoal() {
        return pessoal;
    }

    public void setPessoal(Pessoal pessoal) {
        this.pessoal = pessoal;
    }

    public List<LocacaoItem> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<LocacaoItem> listaItens) {
        this.listaItens = listaItens;
    }

    //modified

}
