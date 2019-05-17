package apresentacao;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import entidade.Endereco;
import entidade.Pessoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FrmCadSocio implements Initializable {
	@FXML
	private TextField txtID;

	@FXML
	private TextField txtNome;

	@FXML
	private RadioButton raSexo_M;

	@FXML
	private ToggleGroup grupoSexo;

	@FXML
	private RadioButton raSexo_F;
	
	@FXML
    private TextField txtCpf;

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

//------Objetos-----
	Pessoal pessoal = new Pessoal();
	Endereco endereco = new Endereco();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gerarUF();
		
	}

	@FXML
	private void novo(ActionEvent event) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.sql.Date dataSql = null;
			java.util.Date dataUtil = null;
			if (dateNascimento.getValue() == null) {
				dataSql = new java.sql.Date(new java.util.Date().getTime());
			} else {
				dataUtil = sdf.parse(dateNascimento.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				dataSql = new java.sql.Date(dataUtil.getTime());
			}
			System.out.println(dataSql);
			pessoal.setCelular(txtCelular.getText());
			pessoal.setCpf(txtCpf.getText());
			pessoal.setData_nascimento(dataSql);
			pessoal.setNome_completo(txtNome.getText());
			
			endereco.setUF(cbUF.getSelectionModel().getSelectedItem());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	private void alterar(ActionEvent event) {

	}

	@FXML
	private void buscar(ActionEvent event) {

	}

	@FXML
	private void excluir(ActionEvent event) {

	}

	@FXML
	private void limpar(ActionEvent event) {

	}

//--------------------------Combobox--------------------------
	private void gerarUF() {
		ObservableList<String> lista = FXCollections.observableArrayList();
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
