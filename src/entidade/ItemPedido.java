package entidade;

public class ItemPedido {

    private int id;
    private double quantidade;
    private double valor;
    private Pedido pedido;
    private TesteTitulo titulo;

    public ItemPedido() {
        pedido = new Pedido();
        titulo = new TesteTitulo();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public TesteTitulo getTitulo() {
        return titulo;
    }

    public void setTitulo(TesteTitulo titulo) {
        this.titulo = titulo;
    }
}
