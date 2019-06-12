package entidade;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Locacao {

    private int id;
    private Date dataLocacao;
    private Date dataPagamento;
    private double valorTotal;
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

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
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
}
