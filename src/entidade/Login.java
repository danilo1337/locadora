package entidade;

import enums.PermissaoUsuario;
import enums.SituacaoUsuario;

/*
 * @author Hugo Henrique
 */
public class Login {
	private int id = 0;
	private int pessoal_id = 0;
	private String usuario = "";
	private String senha = "";

	public Login() {}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public int getPessoal_id() { return pessoal_id; }
	public void setPessoal_id(int pessoal_id) { this.pessoal_id = pessoal_id; }
	public String getUsuario() { return usuario; }
	public void setUsuario(String usuario) { this.usuario = usuario; }
	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }
}
