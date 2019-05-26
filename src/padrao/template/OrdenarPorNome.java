/**
 * 
 */
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
		return pessoa_1.getNome_completo().compareToIgnoreCase(pessoa_2.getNome_completo()) <= 0;
	}

}
