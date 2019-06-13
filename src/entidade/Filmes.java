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
	private TipoFilme tipoFilme;

	public Filmes(int id, String anoLancamento, String faixaEtaria, String titulo, String sinopse, String genero, TipoFilme tipoFilme) {
		this.id = id;
		this.anoLancamento = anoLancamento;
		this.faixaEtaria = faixaEtaria;
		this.titulo = titulo;
		this.sinopse = sinopse;
		this.genero = genero;
		this.tipoFilme = tipoFilme;
	}
	
	public Filmes() {
		tipoFilme = new TipoFilme();
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

	public TipoFilme getTipoFilme() {
		return tipoFilme;
	}

	public void setTipoFilme(TipoFilme tipoFilme) {
		this.tipoFilme = tipoFilme;
	}

	public String[] getColunas() {
		return new String[] {"ID","TÍTULO","GÊNERO","FAIXA-ETÁRIA","LANÇAMENTO"};
	}
	public String[] getVariaveis() {
		return new String[] {"id","titulo","genero","faixaEtaria","anoLancamento"};
	}
	
	@Override
	public String toString() {
		return this.titulo;
	}
}
