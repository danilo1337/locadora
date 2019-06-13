package apresentacao;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import entidade.Filmes;
import entidade.Locacao;
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
import negocio.NPessoal;
import persistencia.PFilmes;
import persistencia.PLocacao;
import persistencia.PPessoal;
import util.pdf.PdfFilmes;
import util.pdf.PdfLocacao;
import util.pdf.PdfPessoal;

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
		try {
			String selecionado = (String) cbTipos.getSelectionModel().getSelectedItem();
			if (selecionado.isEmpty() || selecionado == null)
				throw new Exception("Escolha um tipo de relat�rio");

			Date date = new Date(new Date().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmss");
			String nomeDoPdf = "" + sdf.format(date);
			switch (selecionado) {
			case "Aluguel":
				//falta implementar
				break;
			case "Devolu��o":
				//falta implementar			
				break;
			case "Filme":
				PdfFilmes pdfF = new PdfFilmes(nomeDoPdf, new Filmes().getColunas(), new PFilmes().listar(new Filmes()));
				pdfF.gerarPersonalizado("Relat�rio de Filmes", "Relat�rio de todos os Filmes:");
				break;
			case "Loca��o":
				PdfLocacao pdfL = new PdfLocacao(nomeDoPdf, new Locacao().getColunas(), new PLocacao().listar2());
				pdfL.gerarPersonalizado("Relat�rio de Loca��o", "Relat�rio de todas as loca��es:");
				break;
			case"Pedidos":
				//falta implementar	
				break;
			case "Pessoal":
				PdfPessoal pdfP = new PdfPessoal(nomeDoPdf, new Pessoal().getColunas(), new NPessoal().listar());
				pdfP.gerarPersonalizado("Relat�rio de S�cios", "Relat�rio de todos os S�cios:");
				break;
			case"Reserva":
				//falta implementar	
				break;
			case"Venda":
				//falta implementar	
				break;
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
			e.printStackTrace();
		}
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
		//"Aluguel", "Devolu��o","Filme","Loca��o","Pedidos","Pessoal","Reserva","Venda"
		switch (selecionado) {
		case "Aluguel":
			//falta implementar
			break;
		case "Devolu��o":
			//falta implementar			
			break;
		case "Filme":
			colunas = new Filmes().getColunas();
			nomeVariaveis = new Filmes().getVariaveis();
			iterator = new PFilmes().listar(new Filmes()).iterator();
			break;
		case "Loca��o":
			colunas = new Locacao().getColunas();
			nomeVariaveis = new Locacao().getVariaveis();
			iterator = new PLocacao().listar2().iterator();
			break;
		case "Pedidos":
			colunas = new String[] { "1", "2" };
			nomeVariaveis = new String[] { "a", "b" };
			break;
		case "Pessoal":
			colunas = new Pessoal().getColunas();
			nomeVariaveis = new Pessoal().getVariaveis();
			iterator = new PPessoal().listar().iterator();
			break;
		case "Reserva":
			//falta implementar
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
		tabela.setTableMenuButtonVisible(true);
		paneDaTabela.getChildren().add(tabela);
	}

	private void gerarTipos() {
		ObservableList<Object> lista = FXCollections.observableArrayList();
		lista.addAll(Arrays.asList(
				new String[] {"Aluguel", "Devolu��o","Filme","Loca��o","Pedidos","Pessoal","Reserva"}
				));
		cbTipos.setItems(lista);
	}

}
