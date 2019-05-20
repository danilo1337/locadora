package negocio;

import entidade.Pedido;

public class NPedido {

    public void salvar(Pedido parametro) throws Exception {

        if (parametro.getId()== 0) {
            new persistencia.PPedido().incluir(parametro);
        } else {
            new persistencia.PPedido().alterar(parametro);
        }
    }
//isso precisa tambem
}
