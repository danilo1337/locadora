package util;

import java.sql.Date;
import java.sql.SQLException;

import entidade.Endereco;
import entidade.Pessoal;
import persistencia.PEndereco;
import persistencia.PPessoal;

public class TPessoal {
	public static void main(String[] args) throws SQLException {
		Endereco e = new Endereco();
		e.setBairro("Vila");
		e.setCep("78744");
		e.setComplemento("ao lado do sei lá");
		e.setLocalidade("Goiânia");
		e.setLogradouro("Acacias");
		e.setUF("GO");
		new PEndereco().incluir(e);
		
		Pessoal p = new Pessoal();
		p.setCelular("92336927");
		p.setCpf("45678912398");
		Date d = new Date(new java.util.Date().getTime());
		p.setData_nascimento(d);
		p.setEndereco(e);
		p.setNome_completo("Danilo Ribeiro");
		p.setSexo("M");
		p.setTelefone_1("1154564");
		p.setTelefone_2("1564465");
		new PPessoal().incluir(p);
		
	}
}
