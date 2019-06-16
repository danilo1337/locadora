package padrao.decorator;

public class GeneroFilme implements Filme {
    private String info;
    private Filme filme;

    public GeneroFilme(String info, Filme filme){
        this.filme = filme;
        this.info = info;
    }

    @Override
    public String getInfo() {
        return filme.getInfo() + " - " + this.info;
    }
}
