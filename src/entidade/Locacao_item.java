package entidade;

import java.sql.Date;

public class Locacao_item {

    private int id;
    private String titulo;
    private double valor;
    private Date data_devolucao;
    private Locacao locacao;
    private Copias copias;
    private Filmes filmes;

    public Locacao_item() {
        locacao = new Locacao();
        filmes = new Filmes();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Filmes getFilmes() {
        return filmes;
    }

    public void setFilmes(Filmes filmes) {
        this.filmes = filmes;
    }

    public Copias getCopias() {
        return copias;
    }

    public void setCopias(Copias copias) {
        this.copias = copias;
    }

    public String[] getColunas() {
        return new String[]{ "ID", "VALOR", "TITULO"};
    }

    public String[] getVariaveis() {
        return new String[]{ "id", "valor", "titulo"};
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
