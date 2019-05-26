package entidade;

public class TipoFilme {

	private int id = 0;
	private String tipo = "";
	private double preco = 0;
	
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
	
	 public String toString() {
	        return String.format("%s - R$ %.2f", getTipo(), getPreco());
	    }
	
	
	
}
