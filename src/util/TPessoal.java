package util;

import entidade.Filmes;
import negocio.NFilme;

public class TPessoal {
	public static void main(String[] args) throws Exception {
//		Endereco e = new Endereco();
//		e.setBairro("Vila");
//		e.setCep("78744");
//		e.setComplemento("ao lado do sei l�");
//		e.setLocalidade("Goi�nia");
//		e.setLogradouro("Floripa");
//		e.setUF("GO");
//
//		
//		Pessoal p = new Pessoal();
//		p.setCelular("92336927");
//		p.setCpf("45678912398");
//		Date d = new Date(new java.util.Date().getTime());
//		p.setDataNascimento(d);
//		p.setEndereco(e);
//		p.setNomeCompleto("Danilo Alfredo");
//		p.setSexo("M");
//		p.setTelefone_1("1154564");
//		p.setTelefone_2("1564465");
////-------------------------------------------
//		new NPessoal().salvar(p);
//		System.out.println("ID GERADO: "+p.getId());
//		Pessoal k = new Pessoal();
//		Endereco z = new Endereco();
//		z.setId(6);
//		k.setId(6);
//		k.setEndereco(z);
//		new PPessoal().excluir(k);
////------------------------------------------
//		Pessoal pes = new Pessoal();
//		pes.setId(7);
//		pes = new PPessoal().consultar(pes);
//		String saida = "";
//		saida+= "Nome: "+pes.getNomeCompleto();
//		saida+= "\nUF: "+pes.getEndereco().getUF();
//		saida+= "\nLocalidade: "+pes.getEndereco().getLocalidade();
//		System.out.println(saida);
////------------------------------------------
//		
//		for (Pessoal pessoal : new PPessoal().listar()) {
//			String saida = "";
//			saida+= "Nome: "+pessoal.getNomeCompleto();
//			saida+="End id:"+pessoal.getEndereco().getId();
//			saida+= "\nUF: "+pessoal.getEndereco().getUF();
//			saida+= "\nLocalidade: "+pessoal.getEndereco().getLocalidade();
//			System.out.println(saida);
//			
//		}
		Filmes f = new NFilme().consultar("FImose NO PAU");
		System.out.println(f.getTitulo());
	}
}
