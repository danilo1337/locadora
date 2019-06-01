package apresentacao;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import persistencia.PPessoal;

public class FrmRelatorio implements Initializable {

	@FXML
	private Pane paneDaTabela;

	@FXML
	private ComboBox<Object> cbTipos;

	@FXML
	private DatePicker dateInicio;

	@FXML
	private DatePicker dateFinal;
	@FXML
	private Button btnListar;

	@FXML
	private Button btnPdf;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnFechar;
	// -------Variaveis
	TableView<Object> tabela = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnListar.setGraphic(new ImageView(new Image("/icones/table.png", 26, 26, false, false)));
		btnPdf.setGraphic(new ImageView(new Image("/icones/export_pdf.png", 26, 26, false, false)));
		btnLimpar.setGraphic(new ImageView(new Image("/icones/clean.png", 26, 26, false, false)));
		btnFechar.setGraphic(new ImageView(new Image("/icones/fechar.png", 26, 26, false, false)));
		gerarConfigTabela();
		gerarTipos();
	}

	@FXML
	void fechar(ActionEvent event) {

	}

	@FXML
	void gerarPdf(ActionEvent event) {

	}

	@FXML
	void limpar(ActionEvent event) {
		gerarConfigTabela();
	}

	@FXML
	void listar(ActionEvent event) {
		try {
			String selecionado = (String) cbTipos.getSelectionModel().getSelectedItem();
			gerarTabela(selecionado);
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
			e.printStackTrace();
		}
	}

	private void gerarTabela(String selecionado) throws Exception {
		gerarConfigTabela();
		String colunas[] = null;
		String nomeVariaveis[] = null;
		Iterator<?> iterator = null;
		switch (selecionado) {
		case "Pessoas":
			colunas = new Pessoal().getColunas();
			nomeVariaveis = new Pessoal().getVariaveis();
			iterator = new PPessoal().listar().iterator();
			break;
		case "Pedidos":
			colunas = new String[] { "1", "2" };
			nomeVariaveis = new String[] { "a", "b" };
			break;
		}
		
		for (int i = 0; i < colunas.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
			tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
		}
		
		imprimirNaTabela(iterator);
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
		paneDaTabela.getChildren().add(tabela);
	}

	private void gerarTipos() {
		ObservableList<Object> lista = FXCollections.observableArrayList();
		String tipos[] = { "Pessoas", "Pedidos" };
		for (String valores : tipos) {
			lista.add(valores);
		}
		cbTipos.setItems(lista);
	}

}
