package apresentacao;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;

import entidade.Copias;
import entidade.Filmes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import negocio.NCopias;
import persistencia.PFilmes;

public class FrmCadCopia implements Initializable {
	@FXML
	private Tab mnuDadosPessoais;

	@FXML
	private Pane pane;

	@FXML
	private TextField txtIdCopia;

	@FXML
	private TextField txtCodigo;

	@FXML
	private ComboBox<Boolean> cbDisponibilidade;

	@FXML
	private ComboBox<Boolean> cbReservada;

	@FXML
	private ComboBox<Boolean> cbVenda;

	@FXML
	private TextField txtIDTitulo;

	@FXML
	private TextField txtTitulo;

	@FXML
	private TextField txtConsultaCopia;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnLimpar;

	TableView<Object> tabela = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtIdCopia.setText("0");
		txtIDTitulo.setText("0");
		btnAlterar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setGraphic(new ImageView(new Image("/icones/save.png", 26, 26, false, false)));
		btnAlterar.setGraphic(new ImageView(new Image("/icones/edit.png", 26, 26, false, false)));
		btnExcluir.setGraphic(new ImageView(new Image("/icones/delete.png", 26, 26, false, false)));
		btnLimpar.setGraphic(new ImageView(new Image("/icones/clean.png", 26, 26, false, false)));
		btnBuscar.setGraphic(new ImageView(new Image("/icones/serach.png", 26, 26, false, false)));
		gerarConfigTabela();
		gerarTabela();
		gerarCB();

	}
	
	@FXML
	void salvar(ActionEvent event) {
		try {
			Copias copia = new Copias();
			copia.setId(Integer.parseInt(txtIdCopia.getText()));
			copia.setCodigoCopia(txtCodigo.getText());
			copia.setDisponivel(cbDisponibilidade.getSelectionModel().getSelectedItem());
			copia.setDisponivelVenda(cbVenda.getSelectionModel().getSelectedItem());
			copia.setReservada(cbReservada.getSelectionModel().getSelectedItem());
			copia.setFilmeId(Integer.parseInt(txtIDTitulo.getText()));
			new NCopias().salvar2(copia);
			if(copia.getId() > 0)
				new Alert(AlertType.INFORMATION, "Incluido com sucesso! N " + copia.getId()).show();
			limparTudo();
		} catch (SQLException e) {
			new Alert(AlertType.ERROR,e.getMessage()).show();
			e.printStackTrace();
		}
	}

	@FXML
	void alterar(ActionEvent event) {
		salvar(event);
		btnAlterar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(false);
		limparTudo();
	}

	@FXML
	void buscar(ActionEvent event) {
		Copias copias;
		try {
			copias = new NCopias().consultarGeral(txtConsultaCopia.getText());
			txtIdCopia.setText(copias.getId()+"");
			txtCodigo.setText(copias.getCodigoCopia());
			cbDisponibilidade.getSelectionModel().select(copias.getDisponivel());
			cbVenda.getSelectionModel().select(copias.getDisponivelVenda());
			cbReservada.getSelectionModel().select(copias.getReservada());
			txtIDTitulo.setText(copias.getFilmes().getId()+"");
			txtTitulo.setText(copias.getFilmes().getTitulo());
			if(copias.getId() > 0) {
			btnAlterar.setDisable(false);
			btnExcluir.setDisable(false);
			btnNovo.setDisable(true);
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage()).show();
			e.printStackTrace();
		}
	}



	@FXML
	void excluir(ActionEvent event) {
		try {
			new NCopias().excluir(Integer.parseInt(txtIdCopia.getText()));
			btnNovo.setDisable(false);
			btnAlterar.setDisable(true);
			btnExcluir.setDisable(true);
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage()).show();
			e.printStackTrace();
		}
	}

	@FXML
	void limpar(ActionEvent event) {
		limparTudo();
	}

	private void imprimirNaTabela(Iterator<?> dados) throws Exception {
		ObservableList<Object> lista = FXCollections.observableArrayList();
		while (dados.hasNext()) {
			Object pessoal = dados.next();
			lista.add(pessoal);
		}
		tabela.setItems(lista);
	}

	private Object listnerTabela(Object newValue) {
		Filmes filme = null;
		if (newValue != null) {
			filme = (Filmes) newValue;
			txtIDTitulo.setText(filme.getId() + "");
			txtTitulo.setText(filme.getTitulo());
		}
		return filme;
	}

	public void gerarTabela() {
		String colunas[] = { "ID", "TÍTULO" };
		String nomeVariaveis[] = { "id", "titulo" };
		for (int i = 0; i < nomeVariaveis.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
			tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
		}
		try {
			imprimirNaTabela(new PFilmes().listar(new Filmes()).iterator());
			tabela.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> listnerTabela(newValue));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void gerarConfigTabela() {
		tabela = new TableView<Object>();
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tabela.setPrefWidth(pane.getPrefWidth());
		tabela.setPrefHeight(pane.getPrefHeight());
		tabela.setTableMenuButtonVisible(true);
		pane.getChildren().add(tabela);
	}

	private void gerarCB() {
		ObservableList<Boolean> lista = FXCollections.observableArrayList();
		Boolean b[] = { new Boolean(true), new Boolean(false) };
		lista.addAll(Arrays.asList(b));
		cbDisponibilidade.setItems(lista);
		cbReservada.setItems(lista);
		cbVenda.setItems(lista);
	}

	private void limparTudo() {
		txtIdCopia.setText("0");
		txtCodigo.setText("");
		cbDisponibilidade.getSelectionModel().select(true);
		cbReservada.getSelectionModel().select(true);
		cbVenda.getSelectionModel().select(true);
		txtIDTitulo.setText("");
		txtTitulo.setText("");
        btnExcluir.setDisable(true);
        btnAlterar.setDisable(true);
        btnNovo.setDisable(false);
	}

}
