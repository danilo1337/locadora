package util;

import java.sql.SQLException;

import entidade.Pessoal;
import persistencia.PPessoal;

public class TPessoal {
	public static void main(String[] args) throws SQLException {
//		Endereco e = new Endereco();
//		e.setBairro("Vila");
//		e.setCep("78744");
//		e.setComplemento("ao lado do sei lá");
//		e.setLocalidade("Goiânia");
//		e.setLogradouro("Floripa");
//		e.setUF("GO");
//
//		
//		Pessoal p = new Pessoal();
//		p.setCelular("92336927");
//		p.setCpf("45678912398");
//		Date d = new Date(new java.util.Date().getTime());
//		p.setData_nascimento(d);
//		p.setEndereco(e);
//		p.setNome_completo("Danilo Alfredo");
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
//		saida+= "Nome: "+pes.getNome_completo();
//		saida+= "\nUF: "+pes.getEndereco().getUF();
//		saida+= "\nLocalidade: "+pes.getEndereco().getLocalidade();
//		System.out.println(saida);
////------------------------------------------
		
		for (Pessoal pessoal : new PPessoal().listar()) {
			String saida = "";
			saida+= "Nome: "+pessoal.getNome_completo();
			saida+= "\nUF: "+pessoal.getEndereco().getUF();
			saida+= "\nLocalidade: "+pessoal.getEndereco().getLocalidade();
			System.out.println(saida);
			
		}
	}
}
