package entidade;

public class ItemPedido {

    private int id;
    private double quantidade;
    private double valor;
    private Pedido pedido;
    private Titulo titulo;

    public ItemPedido() {
        pedido = new Pedido();
        titulo = new Titulo();
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

    public Titulo getProduto() {
        return titulo;
    }

    public void setProduto(Titulo titulo) {
        this.titulo = titulo;
    }

}
