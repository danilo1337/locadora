package padrao.template;

import entidade.Pessoal;
/**
 * @author Danilo
 *
 */
public class OrdenarPorSexo extends PessoalTemplate{

	public OrdenarPorSexo() {
		super();
	}

	@Override
	public boolean ePrimeiro(Pessoal pessoa_1, Pessoal pessoa_2) {
		return pessoa_1.getSexo().compareToIgnoreCase(pessoa_2.getSexo()) <= 0;
	}

}
