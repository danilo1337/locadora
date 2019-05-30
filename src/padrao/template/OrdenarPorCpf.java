package padrao.template;

import entidade.Pessoal;

/**
 * @author Danilo
 *
 */
public class OrdenarPorCpf extends PessoalTemplate {

	public OrdenarPorCpf() {
		super();
	}

	@Override
	public boolean ePrimeiro(Pessoal pessoa_1, Pessoal pessoa_2) {
		return pessoa_1.getCpf().compareToIgnoreCase(pessoa_2.getCpf()) <= 0;
	}

}
