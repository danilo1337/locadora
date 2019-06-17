package apresentacao;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
	private Button btnGrafico;
	// -------Variaveis
	TableView<Object> tabela = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnGrafico.setDisable(true);
		btnListar.setGraphic(new ImageView(new Image("/icones/table.png", 26, 26, false, false)));
		btnPdf.setGraphic(new ImageView(new Image("/icones/export_pdf.png", 26, 26, false, false)));
		btnLimpar.setGraphic(new ImageView(new Image("/icones/clean.png", 26, 26, false, false)));
		btnGrafico.setGraphic(new ImageView(new Image("/icones/bar.png", 26, 26, false, false)));
		gerarConfigTabela();
		gerarTipos();
	}

	@FXML
	void grafico(ActionEvent event) {
		try {
			String selecionado = (String) cbTipos.getSelectionModel().getSelectedItem();
			switch (selecionado) {
				case "Diário":
					gerarBarCharDiario();
					break;
				case "Total":
					gerarBarTotal();
					break;
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@FXML
	void gerarPdf(ActionEvent event) {
		try {
			String selecionado = (String) cbTipos.getSelectionModel().getSelectedItem();
			if (selecionado.isEmpty() || selecionado == null)
				throw new Exception("Escolha um tipo de relatório");

			Date date = new Date(new Date().getTime());
			java.sql.Date data1 = validaData(dateInicio);
			java.sql.Date data2 = validaData(dateFinal);

			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmss");
			String nomeDoPdf = "" + sdf.format(date);
			switch (selecionado) {
			case "Filme":
				PdfFilmes pdfF = new PdfFilmes(nomeDoPdf, new Filmes().getColunas(),
						new PFilmes().listar(new Filmes()));
				pdfF.gerarPersonalizado("Relatório de Filmes", "Relatório de todos os Filmes:");
				break;
			case "Locação":
				PdfLocacao pdfL = new PdfLocacao(nomeDoPdf, new Locacao().getColunas(),
						new PLocacao().listar(data1, data2));
				pdfL.gerarPersonalizado("Relatório de Locação", "Relatório de todas as locações:");
				break;
			case "Pessoal":
				PdfPessoal pdfP = new PdfPessoal(nomeDoPdf, new Pessoal().getColunas(), new NPessoal().listar());
				pdfP.gerarPersonalizado("Relatório de Sócios", "Relatório de todos os Sócios:");
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

	private void gerarBarCharDiario() throws Exception {
		paneDaTabela.getChildren().clear();
		BarChart<String, Number> bar = new BarChart<>(new CategoryAxis(), new NumberAxis());
		bar.setPrefWidth(paneDaTabela.getPrefWidth());
		bar.setPrefHeight(paneDaTabela.getPrefHeight());
		paneDaTabela.getChildren().add(bar);
		Double valores[] = new PLocacao().listarDiario();
		XYChart.Series<String, Number> xy1 = new XYChart.Series<String, Number>();
		xy1.setName("Mínimo");
		xy1.getData().add(new XYChart.Data<String, Number>(valores[0] + "", valores[0]));
		XYChart.Series<String, Number> xy2 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> xy3 = new XYChart.Series<String, Number>();
		xy3.setName("Médio");
		xy3.getData().add(new XYChart.Data<String, Number>(valores[1] + "", valores[1]));
		xy2.setName("Máximo");
		xy2.getData().add(new XYChart.Data<String, Number>(valores[2] + "", valores[2]));
		bar.getData().add(xy1);
		bar.getData().add(xy2);
		bar.getData().add(xy3);
		bar.setTitle("RELATÓRIO DE VENDAS DIÁRIO" + "\n\tTOTAL: R$" + valores[3]);
	}
	private void gerarBarTotal() throws ParseException, SQLException {
		paneDaTabela.getChildren().clear();
		PieChart pie = new PieChart();
		pie.setPrefWidth(paneDaTabela.getPrefWidth());
		pie.setPrefHeight(paneDaTabela.getPrefHeight());
		paneDaTabela.getChildren().add(pie);
		paneDaTabela.getChildren().add(new ImageView(new Image("/icones/money.png", 50, 50, false, false)));
		java.sql.Date data1 = validaData(dateInicio);
		java.sql.Date data2 = validaData(dateFinal);
		Double total = new PLocacao().totalMensal(data1, data2);
    	PieChart.Data slice1 = new PieChart.Data("Total\nR$ "+total, total);
    	pie.getData().add(slice1);
	}
	private void gerarTabela(String selecionado) throws Exception {
		gerarConfigTabela();
		String colunas[] = null;
		String nomeVariaveis[] = null;
		Iterator<?> iterator = null;
		java.sql.Date data1;
		java.sql.Date data2;
		data1 = validaData(dateInicio);
		data2 = validaData(dateFinal);
		// "Aluguel",
		// "Devolução","Filme","Locação","Pedidos","Pessoal","Reserva","Venda"
		switch (selecionado) {
		case "Filme":
			colunas = new Filmes().getColunas();
			nomeVariaveis = new Filmes().getVariaveis();
			iterator = new PFilmes().listar(new Filmes()).iterator();
			break;
		case "Locação":
			colunas = new Locacao().getColunas();
			nomeVariaveis = new Locacao().getVariaveis();
			iterator = new PLocacao().listar(data1, data2).iterator();
			break;
		case "Pessoal":
			colunas = new Pessoal().getColunas();
			nomeVariaveis = new Pessoal().getVariaveis();
			iterator = new PPessoal().listar().iterator();
			break;
		}

		for (int i = 0; i < colunas.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
			tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
		}

		imprimirNaTabela(iterator);
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
		lista.addAll(Arrays.asList(new String[] { "Filme", "Locação", "Pessoal", "Diário", "Total" }));
		cbTipos.setItems(lista);
		cbTipos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> listnerCombobox(newValue));
	}

	private void imprimirNaTabela(Iterator<?> dados) throws Exception {
		ObservableList<Object> lista = FXCollections.observableArrayList();
		while (dados.hasNext()) {
			Object pessoal = dados.next();
			lista.add(pessoal);
		}
		tabela.setItems(lista);
	}

	private Object listnerCombobox(Object newValue) {
		String selecionado = (String) cbTipos.getSelectionModel().getSelectedItem();
		boolean editavel = true;
		switch (selecionado) {
		case "Filme":
			editavel = true;
			btnGrafico.setDisable(true);
			btnListar.setDisable(false);
			break;
		case "Locação":
			editavel = false;
			btnGrafico.setDisable(true);
			btnListar.setDisable(false);
			break;
		case "Pessoal":
			editavel = true;
			btnGrafico.setDisable(true);
			btnListar.setDisable(false);
			break;
		case "Diário":
			editavel = true;
			btnGrafico.setDisable(false);
			btnListar.setDisable(true);
			break;
		case "Total":
			editavel = false;
			btnGrafico.setDisable(false);
			btnListar.setDisable(true);
			break;
		}
		dateInicio.setDisable(editavel);
		dateFinal.setDisable(editavel);

		return newValue;
	}

	private java.sql.Date validaData(DatePicker datePicker) throws ParseException {
		java.sql.Date dataSql;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (dateInicio.getValue() == null || dateFinal.getValue() == null) {
			dataSql = new java.sql.Date(new java.util.Date().getTime());
		} else {
			dataSql = new java.sql.Date(
					sdf.parse(datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).getTime());
		}
		return dataSql;
	}

}
