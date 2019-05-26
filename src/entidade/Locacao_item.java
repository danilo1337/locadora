package entidade;

public class ItemPedido {

    private int id;
    private double quantidade;
    private double valor;
    private Locacao pedido;
//    private Titulo titulo;
    //ItemPedido

    public ItemPedido() {
        pedido = new Locacao();
//        titulo = new Titulo();
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

    public Locacao getPedido() {
        return pedido;
    }

    public void setPedido(Locacao pedido) {
        this.pedido = pedido;
    }



}
