package apresentacao;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import entidade.Filmes;
import entidade.Genero;
import entidade.TipoFilme;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import negocio.NFilme;
import negocio.NGenero;
import negocio.NTipoFilme;

public class FrmCadFilme implements Initializable {

	@FXML
	private Tab mnuDadosPessoais;

	@FXML
	private ComboBox<String> Cb_FaixaEtaria;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtTituloFilme;

	@FXML
	private TextField txtAnoLancamento;

	@FXML
	private ComboBox<TipoFilme> Cb_Tipo;

	@FXML
	private ComboBox<Genero> Cb_Genero;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnLimpar;

	@FXML
	private TextField txtTitulo_consulta;

	@FXML
	private Button btnBuscar;

	@FXML
	private TextArea txaSinopse;

	ObservableList<String> lista;
	ObservableList<Genero> listaGen;
	ObservableList<TipoFilme> listaTipo;

	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			GerarFaixaEtaria();
			GerarGenero();
			GerarTipo();
			txtID.setText("0");
			btnNovo.setText("");
			btnAlterar.setText("");
			btnExcluir.setText("");
			btnLimpar.setText("");
			btnBuscar.setText("");
			btnNovo.setGraphic(new ImageView(new Image("/icones/save.png", 26, 26, false, false)));
			btnAlterar.setGraphic(new ImageView(new Image("/icones/edit.png", 26, 26, false, false)));
			btnExcluir.setGraphic(new ImageView(new Image("/icones/delete.png", 26, 26, false, false)));
			btnLimpar.setGraphic(new ImageView(new Image("/icones/clean.png", 26, 26, false, false)));
			btnBuscar.setGraphic(new ImageView(new Image("/icones/serach.png", 26, 26, false, false)));
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}

	}

	@FXML
	private void salvar(ActionEvent event) {
		try {
			Filmes filme = new Filmes();
			TipoFilme tipo = new TipoFilme();

			tipo.setId(tipo.getId());
			tipo.setPreco(tipo.getPreco());
			tipo.setTipo(tipo.getTipo());

			filme.setId(Integer.parseInt(txtID.getText()));
			filme.setTitulo(txtTituloFilme.getText());
			filme.setFaixaEtaria(Cb_FaixaEtaria.getSelectionModel().getSelectedItem());
			filme.setAnoLancamento(txtAnoLancamento.getText());
			filme.setGenero(String.valueOf(Cb_Genero.getSelectionModel().getSelectedItem()));
			filme.setSinopse(txaSinopse.getText());
			;
			filme.setTipo_id(Cb_Tipo.getValue());

			new NFilme().salvar(filme);
			new Alert(AlertType.ERROR, "Incluido com sucesso! N�" + filme.getId()).show();
			limparTudo();
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
	}

	@FXML
	private void alterar(ActionEvent event) {
		try {
			limparTudo();
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
	}

	@FXML
	private void buscar(ActionEvent event) {
		try {
			Filmes filme = new NFilme().consultar(txtTituloFilme.getText());
			txtID.setText(String.valueOf(filme.getId()));
			txtTituloFilme.setText(filme.getTitulo());
			txtAnoLancamento.setText(filme.getAnoLancamento());
			txaSinopse.setText(filme.getSinopse());

//			txtID.setText(filme.getId() + "");
//			id_endereco = pessoal.getEndereco().getId();

			String FaixaEtaria = filme.getFaixaEtaria();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(FaixaEtaria)) {
					Cb_FaixaEtaria.getSelectionModel().select(i);
					break;
				}
			}

			String TipoFilme = filme.getTitulo();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(TipoFilme)) {
					Cb_Tipo.getSelectionModel().select(i);
					break;
				}
			}

			String Genero = filme.getGenero();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(Genero)) {
					Cb_Genero.getSelectionModel().select(i);
					break;
				}
			}

		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
			e.printStackTrace();
		}
	}

	@FXML
	private void excluir(ActionEvent event) {
		try {
			Filmes filme = new Filmes();
			filme.setId(Integer.parseInt(txtID.getText()));

			new NFilme().excluir(filme);
			limparTudo();

		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
	}

	@FXML
	private void limpar(ActionEvent event) {
		try {
			limparTudo();

		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
	}

	private void limparTudo() {
		txtTituloFilme.setText("");
		txtAnoLancamento.setText("");
		txaSinopse.setText("");
		txtID.setText("0");
		Cb_FaixaEtaria.getSelectionModel().select(-1);
		Cb_Genero.getSelectionModel().select(-1);
		Cb_Tipo.getSelectionModel().select(-1);
	}

	private void GerarFaixaEtaria() {

		lista = FXCollections.observableArrayList();
		lista.add("L");
		lista.add("10");
		lista.add("12");
		lista.add("14");
		lista.add("16");
		lista.add("18");
		Cb_FaixaEtaria.setItems(lista);

	}

	private void GerarTipo() {
		try {
			listaTipo = FXCollections.observableArrayList();

			for (TipoFilme obj : new NTipoFilme().listarTipoFilme()) {
				listaTipo.add(obj);
				Cb_Tipo.setItems(listaTipo);
			}

		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}

	}

	private void GerarGenero() {
		try {
			listaGen = FXCollections.observableArrayList();
			for (Genero obj : new NGenero().listarGenero()) {

				listaGen.add(obj);
				Cb_Genero.setItems(listaGen);
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}

	}
}