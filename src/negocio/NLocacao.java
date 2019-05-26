package negocio;

import entidade.Locacao;
import persistencia.PLocacao;

public class NPedido {

    public void salvar(Locacao parametro) throws Exception {

        if (parametro.getId()== 0) {
            new PLocacao().incluir(parametro);
        } else {
            new PLocacao().alterar(parametro);
        }
    }
//isso precisa tambem
}
