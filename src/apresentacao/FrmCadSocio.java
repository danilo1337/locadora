package apresentacao;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import entidade.Endereco;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.LocalDateStringConverter;
import negocio.NPessoal;
import util.NovaCena;

public class FrmCadSocio implements Initializable {
	@FXML
	private TextField txtID;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCpf;

	@FXML
	private RadioButton raSexo_M;

	@FXML
	private ToggleGroup grupoSexo;

	@FXML
	private RadioButton raSexo_F;

	@FXML
	private DatePicker dateNascimento;

	@FXML
	private TextField txtTelefone_1;

	@FXML
	private TextField txtTelefone_2;

	@FXML
	private TextField txtCelular;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCep;

	@FXML
	private ComboBox<String> cbUF;

	@FXML
	private TextField txtLogradouro;

	@FXML
	private TextField txtLocalidade;

	@FXML
	private TextField txtComplemento;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnLimpar;

	@FXML
	private TextField txtCpf_consulta;

	@FXML
	private Button btnBuscar;

	@FXML
	private Tab mnuEndereco;

	@FXML
	private Tab mnuDadosPessoais;
//------Objetos-----
	ObservableList<String> lista;
	int id_endereco = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			gerarUF();
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
	private void novo(ActionEvent event) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date dataSql = null;
			java.util.Date dataUtil = null;
			Pessoal pessoal = new Pessoal();
			Endereco endereco = new Endereco();
			if (dateNascimento.getValue() == null) {
				dataSql = new java.sql.Date(new java.util.Date().getTime());
			} else {
				dataUtil = sdf.parse(dateNascimento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				dataSql = new java.sql.Date(dataUtil.getTime());
			}

			endereco.setId(id_endereco);
			endereco.setBairro(txtBairro.getText());
			endereco.setCep(txtCep.getText());
			endereco.setComplemento(txtComplemento.getText());
			endereco.setLocalidade(txtLocalidade.getText());
			endereco.setLogradouro(txtLogradouro.getText());
			endereco.setUF(cbUF.getSelectionModel().getSelectedItem());

			pessoal.setId(Integer.parseInt(txtID.getText()));
			pessoal.setCelular(txtCelular.getText());
			pessoal.setCpf(txtCpf.getText());
			pessoal.setData_nascimento(dataSql);
			pessoal.setNome_completo(txtNome.getText());
			RadioButton rb = (RadioButton) grupoSexo.getSelectedToggle();
			pessoal.setSexo(rb.getText());
			pessoal.setTelefone(txtTelefone_1.getText());
			pessoal.setCelular(txtCelular.getText());
			pessoal.setEndereco(endereco);

			new NPessoal().salvar(pessoal);
			new Alert(AlertType.ERROR, "Incluido com sucesso! Nº" + pessoal.getId()).show();
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
			Pessoal pessoal = new NPessoal().consultar_cpf(txtCpf_consulta.getText());
			txtBairro.setText(pessoal.getEndereco().getBairro());
			txtCelular.setText(pessoal.getCelular());
			txtCep.setText(pessoal.getEndereco().getCep());
			txtComplemento.setText(pessoal.getEndereco().getComplemento());
			txtCpf.setText(pessoal.getCpf());
			txtEmail.setText("");// colocar e-mail no banco
			txtID.setText(pessoal.getId() + "");
			txtLocalidade.setText(pessoal.getEndereco().getLocalidade());
			txtLogradouro.setText(pessoal.getEndereco().getLogradouro());
			txtNome.setText(pessoal.getNome_completo());
			txtTelefone_1.setText(pessoal.getTelefone());
			dateNascimento.setValue(NovaCena.LOCAL_DATE(pessoal.getData_nascimento()));
			if (pessoal.getSexo().equals("Masculino"))
				raSexo_M.setSelected(true);
			else
				raSexo_F.setSelected(true);
			id_endereco = pessoal.getEndereco().getId();

			String uf = pessoal.getEndereco().getUF();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(uf)) {
					cbUF.getSelectionModel().select(i);
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
			Pessoal pessoal = new Pessoal();
			pessoal.setId(Integer.parseInt(txtID.getText()));
			
			new NPessoal().excluir(pessoal);
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
		txtBairro.setText("");
		txtCelular.setText("");
		txtCep.setText("");
		txtComplemento.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
		txtID.setText("0");
		txtLocalidade.setText("");
		txtLogradouro.setText("");
		txtNome.setText("");
		txtTelefone_1.setText("");
		txtTelefone_2.setText("");
		dateNascimento.setValue(null);
		raSexo_F.setSelected(true);
		id_endereco = 0;
		gerarUF();
		cbUF.getSelectionModel().select(0);
	}

//--------------------------Combobox--------------------------
	private void gerarUF() {
		lista = FXCollections.observableArrayList();
		lista.add("AC");
		lista.add("AL");
		lista.add("AM");
		lista.add("AP");
		lista.add("BA");
		lista.add("CE");
		lista.add("DF");
		lista.add("ES");
		lista.add("GO");
		lista.add("MA");
		lista.add("MG");
		lista.add("MS");
		lista.add("MT");
		lista.add("PA");
		lista.add("PB");
		lista.add("PE");
		lista.add("PI");
		lista.add("PR");
		lista.add("RJ");
		lista.add("RN");
		lista.add("RO");
		lista.add("RR");
		lista.add("RS");
		lista.add("SC");
		lista.add("SE");
		lista.add("SP");
		lista.add("TO");
		cbUF.setItems(lista);

	}

}
