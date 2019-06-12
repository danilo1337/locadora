package entidade;

public class TipoFilme {

	private int id = 0;
	private String tipo = "";
	private double preco = 0;
	private double precoVenda = 0;

	public TipoFilme(int id, String tipo, double preco, double precoVenda) {
		this.id = id;
		this.tipo = tipo;
		this.preco = preco;
		this.precoVenda = precoVenda;
	}

	public TipoFilme(){
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.preco = precoVenda;
	}
	
	 public String toString() {
	        return String.format("%s - R$ %.2f", getTipo(), getPreco());
	    }
}
