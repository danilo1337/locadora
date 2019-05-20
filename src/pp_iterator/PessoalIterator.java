package pp_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import entidade.Pessoal;
import persistencia.PPessoal;

public class PessoalIterator {

	public PessoalIterator() {

	}

	public Iterator listagemComLista() throws Exception {
		return new PPessoal().listar().iterator();
	}
	public Iterator listagemComArrayList() throws Exception {
		ArrayList<Pessoal> arrayList = new ArrayList<>();
		for (Pessoal pessoal :  new PPessoal().listar()) {
			arrayList.add(pessoal);
		}
		return arrayList.iterator();
	}
	
	public Iterator listagemComFila() throws Exception {
		Queue<Pessoal> fila = new LinkedList<>();
		for (Pessoal pessoal :  new PPessoal().listar()) {
			fila.add(pessoal);
		}
		return fila.iterator();
	}
	
	public Iterator listagemComPilha() throws Exception {
		Stack<Pessoal> pilha = new Stack<>();
		for (Pessoal pessoal :  new PPessoal().listar()) {
			pilha.push(pessoal);
		}
		return pilha.iterator();
	}
	
}
