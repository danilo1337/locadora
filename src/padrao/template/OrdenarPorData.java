package padrao.template;

import entidade.Pessoal;

public class OrdenarPorData extends PessoalTemplate {
	public OrdenarPorData() {

	}

	@Override
	public boolean ePrimeiro(Pessoal pessoa_1, Pessoal pessoa_2) {
		return pessoa_1.getDataNascimento().compareTo(pessoa_2.getDataNascimento()) >= 0;
	}

}
