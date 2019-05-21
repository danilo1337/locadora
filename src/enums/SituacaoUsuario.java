package enums;

/*
 * @author Hugo Henrique
 */

public enum SituacaoUsuario {
    ATIVO(1), INATIVO(2), BLOQUEADO(3);

    private int descricao;

    SituacaoUsuario(int descricao) {
        this.descricao = descricao;
    }
    public int getDescricao() {
        return descricao;
    }
}
