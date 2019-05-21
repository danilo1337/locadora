/**
 * 
 */
package entidade;

/**
 * @author Danilo
 *
 */
public class Endereco {
	private int id = 0;
	private String cep = "";
	private String logradouro = "";
	private String complemento = "";
	private String bairro = "";
	private String localidade = "";
	private String UF = "";
	
	public Endereco() {
		
	}
	
	@Override
	public String toString() {
		return cep;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
}
