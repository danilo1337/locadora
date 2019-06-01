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
import padrao.factory.Abreviatura;
import padrao.factory.AbreviaturaFactory;
import padrao.iterator.PessoalIterator;
import padrao.template.OrdenarPorCpf;
import padrao.template.OrdenarPorData;
import padrao.template.OrdenarPorNome;
import padrao.template.OrdenarPorSexo;

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
		String colunas[] = new Pessoal().getColunas();
		String nomeVariaveis[] = new Pessoal().getVariaveis();
		for (int i = 0; i < colunas.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
			tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
		}
		tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> listnerTabela(newValue));
	}

	private Object listnerTabela(Object newValue) {
		Pessoal pessoal = null;
		if(newValue != null) {
			pessoal = (Pessoal) newValue;
			Abreviatura abr = AbreviaturaFactory.criarAbreviatura(pessoal.getSexo());
			new Alert(AlertType.INFORMATION,abr.getAbreviatura()+" "+pessoal.getNome_completo()).show();;
			
		}
		return pessoal;
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
