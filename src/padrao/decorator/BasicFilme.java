package padrao.decorator;

public class BasicFilme implements Filme {
    private String info;

    public BasicFilme(String info){
        this.info = info;
    }

    @Override
    public String getInfo() {
        return "Filme " + this.info;
    }
}
