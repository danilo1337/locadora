package apresentacao;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import entidade.Pessoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pp_iterator.PessoalIterator;
import pp_template.OrdenarPorCpf;
import pp_template.OrdenarPorData;
import pp_template.OrdenarPorNome;
import pp_template.OrdenarPorSexo;

public class FrmListarSocio implements Initializable {
	@FXML
	private TableView<Object> tabela;

	@FXML
	private Button btnConsultar;

	@FXML
	private Button btnLimpar;

	@FXML
	private ComboBox<String> cbTipos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gerarColunas();
		gerarTipos();
	}

	@FXML
	private void limpar(ActionEvent event) {
		// Apaga as colunas da tabela
		tabela.getItems().clear();
	}

	@FXML
	private void consultar(ActionEvent event) {
		try {
			PessoalIterator pessoalIterator = new PessoalIterator();
			
			String itemLista = (String) cbTipos.getSelectionModel().getSelectedItem();
			switch (itemLista) {
			case "Nome":
				imprimirNaTabela(new OrdenarPorNome().listagem());
				break;
			case "CPF":
				imprimirNaTabela(new OrdenarPorCpf().listagem());
				break;
			case "Sexo":
				imprimirNaTabela(new OrdenarPorSexo().listagem());
				break;
			case "Data":
				imprimirNaTabela(new OrdenarPorData().listagem());
				break;
			case "Lista":
				imprimirNaTabela(pessoalIterator.listagemComPilha());
				break;
			case "Pilha":
				imprimirNaTabela(pessoalIterator.listagemComPilha());
				System.out.println("PILHA");
				break;
			case "Fila":
				imprimirNaTabela(pessoalIterator.listagemComFila());
				System.out.println("fila");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
			e.printStackTrace();
		}
	}

	private void imprimirNaTabela(Iterator<Pessoal> dados) throws Exception {
		ObservableList<Object> lista = FXCollections.observableArrayList();
		while (dados.hasNext()) {
			Pessoal pessoal = (Pessoal) dados.next();
			lista.add(pessoal);
		}

		tabela.setItems(lista);
	}

	private void gerarColunas() {
		// adiocionando as colunas
		String colunas[] = { "ID", "NOME", "SEXO", "CPF", "D.Nasc", "TELEFONE", "CELULAR", "E-MAIL", "TIPO",
				"LOCALIDADE" };
		String nomeVariaveis[] = { "id", "nome_completo", "sexo", "cpf", "data_nascimento", "telefone", "celular",
				"email", "tipo", "endereco" };
		for (int i = 0; i < colunas.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
			tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
		}
	}

	public void gerarTipos() {
		ObservableList<String> lista = FXCollections.observableArrayList();
		String tipoListagem[] = { "Nome", "CPF", "Sexo", "Data", "Lista", "Pilha", "Fila" };
		for (int i = 0; i < tipoListagem.length; i++) {
			lista.add(tipoListagem[i]);
		}
		cbTipos.setItems(lista);
	}

}
