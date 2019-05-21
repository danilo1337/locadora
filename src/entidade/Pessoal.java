/**
 * 
 */
package entidade;

import java.sql.Date;

/**
 * @author Danilo
 *
 */
public class Pessoal {
	private int id = 0;
	private String nome_completo = "";
	private String sexo = "";
	private String cpf = "";
	private java.sql.Date data_nascimento = null;
	private String telefone = "";
	private String celular = "";
	private String email = "";
	private char tipo = ' ';
	private Endereco endereco = null;

	public Pessoal() {
		data_nascimento = new Date(new java.util.Date().getTime());
		endereco = new Endereco();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public java.sql.Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(java.sql.Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}


	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
}
