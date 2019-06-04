package entidade;

/**
 * @author Felipe Karioka
 *
 */

public class Filmes {

	private int id = 0;
	private String anoLancamento = "";
	private String faixaEtaria = "";
	private String titulo = "";
	private String sinopse = "";
	private String genero = "";


	private TipoFilme tipo_id;
	
	public Filmes() {
		tipo_id = new TipoFilme();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public TipoFilme getTipo_id() {
		return tipo_id;
	}

	public void setTipo_id(TipoFilme tipo_id) {
		this.tipo_id = tipo_id;
	}

	@Override
	public String toString() {
		return this.titulo;
	}

}
