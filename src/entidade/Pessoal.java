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
	private String nomeCompleto = "";
	private String sexo = "";
	private String cpf = "";
	private java.sql.Date dataNascimento;
	private String telefone = "";
	private String celular = "";
	private String email = "";
	private int tipo = 5;
	private Endereco endereco;
	private int situacao = 1;

	public Pessoal() {
		dataNascimento = new Date(new java.util.Date().getTime());
		endereco = new Endereco();
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNomeCompleto() {return nomeCompleto;}
	public void setNomeCompleto(String nomeCompleto) {this.nomeCompleto = nomeCompleto;}
	public String getSexo() {return sexo;}
	public void setSexo(String sexo) {this.sexo = sexo;}
	public String getCpf() {return cpf;}
	public void setCpf(String cpf) {this.cpf = cpf;}
	public java.sql.Date getDataNascimento() {	return dataNascimento;}
	public void setDataNascimento(java.sql.Date dataNascimento) {this.dataNascimento = dataNascimento;}
	public String getCelular() {return celular;}
	public void setCelular(String celular) {this.celular = celular;}
	public Endereco getEndereco() {return endereco;}
	public void setEndereco(Endereco endereco) {this.endereco = endereco;}
	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public int getTipo() {return tipo;}
	public void setTipo(int tipo) {this.tipo = tipo;}
	public int getSituacao() {return situacao;}
	public void setSituacao(int situacao) {this.situacao = situacao;}
	
	public String[] getColunas() {
		return new String[]{ "ID", "NOME", "SEXO", "CPF", "D.Nasc", "TELEFONE", "CELULAR", "E-MAIL", "TIPO","SITUAÇÃO" };
	}
	public String[] getVariaveis() {
		return new String[]{ "id", "nomeCompleto", "sexo", "cpf", "dataNascimento", "telefone",
				"celular", "email", "tipo", "situacao" };
	}
	@Override
	public String toString() {
		return nomeCompleto;
	}

}
