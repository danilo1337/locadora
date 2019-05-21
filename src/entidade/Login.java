package entidade;

/**
 * @author Hugo Henrique
 *
 */
public class Login {
	private int id = 0;
	private int pessoal_id = 0;
	private String login = "";
	private String senha = "";

	public Login() {}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public int getPessoal_id() { return pessoal_id; }
	public void setPessoal_id(int pessoal_id) { this.pessoal_id = pessoal_id; }
	public String getLogin() { return login; }
	public void setLogin(String login) { this.login = login; }
	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }

	//	@Override
//	public String toString() {
//		return localidade;
//	}
}
