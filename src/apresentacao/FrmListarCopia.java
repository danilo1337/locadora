package apresentacao;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import entidade.Copias;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import persistencia.PCopias;

public class FrmListarCopia implements Initializable {

	@FXML
	private Button btnConsultar;

	@FXML
	private Button btnLimpar;

	@FXML
	private TextField txtTitulo;

	@FXML
	private Pane paneDaTabela;

	// -------Variaveis
	TableView<Object> tabela = null;
	Copias copia = new Copias();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gerarConfigTabela();
		gerarTabela();
	}

	@FXML
	void consultar(ActionEvent event) {
		try {
			copia.getFilmes().setTitulo(txtTitulo.getText());
			Iterator<?> iterator = new PCopias().listar(copia).iterator();
			imprimirNaTabela(iterator);
			
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage()).show();
			e.printStackTrace();
		}
	}
    @FXML
    void listnerTxtFilme(KeyEvent event) {
    	try {
    	copia.getFilmes().setTitulo(txtTitulo.getText());
		Iterator<?> iterator = new PCopias().listar(copia).iterator();
			imprimirNaTabela(iterator);
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage()).show();
			e.printStackTrace();
		}
    }
	
	@FXML
	void limpar(ActionEvent event) {
		gerarConfigTabela();
		gerarTabela();
	}

	public void gerarTabela() {
		String colunas[] = new Copias().getColunas();
		String nomeVariaveis[] = new Copias().getVariaveis();
		for (int i = 0; i < nomeVariaveis.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
			tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
		}
	}

	private void imprimirNaTabela(Iterator<?> dados) throws Exception {
		ObservableList<Object> lista = FXCollections.observableArrayList();
		while (dados.hasNext()) {
			Object pessoal = dados.next();
			lista.add(pessoal);
		}
		tabela.setItems(lista);
	}

	private void gerarConfigTabela() {
		tabela = new TableView<Object>();
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tabela.setPrefWidth(paneDaTabela.getPrefWidth());
		tabela.setPrefHeight(paneDaTabela.getPrefHeight());
		tabela.setTableMenuButtonVisible(true);
		paneDaTabela.getChildren().add(tabela);
	}

}
