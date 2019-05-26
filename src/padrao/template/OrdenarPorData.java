package pp_template;

import entidade.Pessoal;

public class OrdenarPorData extends PessoalTemplate{
	public OrdenarPorData() {
		
	}
	@Override
	public boolean ePrimeiro(Pessoal pessoa_1, Pessoal pessoa_2) {
		return pessoa_1.getData_nascimento().compareTo(pessoa_2.getData_nascimento()) >= 0;
	}

}
