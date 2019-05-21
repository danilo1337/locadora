package enums;

/*
 * @author Hugo Henrique
 */

public enum PermissaoUsuario {
    GERENTE(1), ATENDENTE(2), USUARIO(5);

    private int descricao;

    PermissaoUsuario(int descricao) { this.descricao = descricao; }
    public int getDescricao() { return descricao; }
}
