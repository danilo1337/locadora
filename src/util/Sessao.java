package util;

/*
 * @author Hugo Henrique
 */

import entidade.Login;

public class Sessao {
    private static Sessao instance;
    private static Login login;

    private Sessao() {}

    public static Sessao getInstance() {
        if (instance == null)
            instance = new Sessao();
        return instance;
    }

    public Login getLogin() { return login; }

    public void setLogin(Login login) { Sessao.login = login; }
}
