package apresentacao;

import java.net.URL;
import java.util.Iterator;
import java.util.Observable;
import java.util.ResourceBundle;

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
import padrao.factory.Abreviatura;
import padrao.factory.AbreviaturaFactory;
import persistencia.PFilmes;

public class FrmCadCopia implements Initializable{
	@FXML
    private Tab mnuDadosPessoais;

    @FXML
    private TextField txtIdCopia;

    @FXML
    private TextField txtCodigo;

    @FXML
    private ComboBox<?> cbDisponibilidade;

    @FXML
    private Pane pane;

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
		btnNovo.setGraphic(new ImageView(new Image("/icones/save.png", 26, 26, false, false)));
		btnAlterar.setGraphic(new ImageView(new Image("/icones/edit.png", 26, 26, false, false)));
		btnExcluir.setGraphic(new ImageView(new Image("/icones/delete.png", 26, 26, false, false)));
		btnLimpar.setGraphic(new ImageView(new Image("/icones/clean.png", 26, 26, false, false)));
		btnBuscar.setGraphic(new ImageView(new Image("/icones/serach.png", 26, 26, false, false)));
		gerarConfigTabela();
		gerarTabela();
		
	}

	@FXML
    void alterar(ActionEvent event) {

    }

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void excluir(ActionEvent event) {

    }

    @FXML
    void limpar(ActionEvent event) {

    }

    @FXML
    void salvar(ActionEvent event) {

    }
    
	public void gerarTabela() {
		String colunas[] = {"ID","TÍTULO"};
		String nomeVariaveis[] = {"id","titulo"};
		for (int i = 0; i < nomeVariaveis.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
			tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
		}
		try {
			imprimirNaTabela(new PFilmes().listar(new Filmes()).iterator());
			tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> listnerTabela(newValue));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	private Object listnerTabela(Object newValue) {
		Filmes filme = null;
		if(newValue != null) {
			filme = (Filmes) newValue;
			txtIDTitulo.setText(filme.getId()+"");
			txtTitulo.setText(filme.getTitulo());
		}
		return filme;
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
		tabela.setPrefWidth(pane.getPrefWidth());
		tabela.setPrefHeight(pane.getPrefHeight());
		tabela.setTableMenuButtonVisible(true);
		pane.getChildren().add(tabela);
	}

}
