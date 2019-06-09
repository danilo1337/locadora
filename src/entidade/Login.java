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
<<<<<<< HEAD
	//private int permissao = PermissaoUsuario.USUARIO.getDescricao();
	//private int situacao = SituacaoUsuario.ATIVO.getDescricao();
=======
>>>>>>> venda

	public Login() {}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public int getPessoal_id() { return pessoal_id; }
	public void setPessoal_id(int pessoal_id) { this.pessoal_id = pessoal_id; }
	public String getUsuario() { return usuario; }
	public void setUsuario(String usuario) { this.usuario = usuario; }
	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }
<<<<<<< HEAD
	//public int getPermissao() { return permissao; }
	//public void setPermissao(int permissao) { this.permissao = permissao; }
	//public int getSituacao() { return situacao; }
	//public void setSituacao(int situacao) { this.situacao = situacao; }
=======
>>>>>>> venda
}
