package negocio;

import entidade.Locacao;
import persistencia.PLocacao;

import java.util.List;

public class NLocacao {

    private PLocacao per;

    public NLocacao(){
        per = new PLocacao();
    }

    public void salvar(Locacao parametro) throws Exception {



        if (parametro.getValor_total() == 0 && parametro.getListaItens() == null)
            throw new Exception("Por favor, Inserir algum produto");


        if (parametro.getListaItens() == null)
            throw new Exception("É necessário inserir um item");


        if (parametro.getId()== 0)
            new PLocacao().incluir(parametro);
         else
            new PLocacao().alterar(parametro);

    }

    public void excluir(Locacao locacao) throws Exception{
        per.excluir(locacao);
    }

    public Locacao consultar(int id) throws Exception {
        return per.consultar(id);
    }

    public List<Locacao> listar() throws Exception {
        return per.listar();
    }

//modified1


}
