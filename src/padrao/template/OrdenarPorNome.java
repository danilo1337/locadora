package padrao.template;

import entidade.Pessoal;

/**
 * @author Danilo
 *
 */
public class OrdenarPorNome extends PessoalTemplate {

	public OrdenarPorNome() {
		super();
	}

	@Override
	public boolean ePrimeiro(Pessoal pessoa_1, Pessoal pessoa_2) {
		return pessoa_1.getNomeCompleto().compareToIgnoreCase(pessoa_2.getNomeCompleto()) <= 0;
	}

}
