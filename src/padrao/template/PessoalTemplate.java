package pp_template;

import java.util.Iterator;
import java.util.List;

import entidade.Pessoal;
import negocio.NPessoal;

/**
 * @author Danilo
 *
 */

public abstract class PessoalTemplate {
	public abstract boolean ePrimeiro(Pessoal pessoa_1, Pessoal pessoa_2);

	public Iterator<Pessoal> listagem() throws Exception {
		try {
			List<Pessoal> lista = new NPessoal().listar(); 
			
			for (int i = 0; i < lista.size(); i++) {
				for (int j = i; j < lista.size(); j++) {
					if(!ePrimeiro(lista.get(i), lista.get(j))) {
						Pessoal temp = lista.get(j);
						lista.set(j, lista.get(i));
						lista.set(i, temp);
					}
				}
			}
			return lista.iterator();
		} catch (Exception e) {
			throw e;
		}
	}
}
