package entidade;

public class Locacao_item {

    private int id;
    private double valor;

    private Locacao locacao;
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
}
