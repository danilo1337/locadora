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
	private TextField txtTelefone;

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

	@FXML
	private ComboBox<String> cbTipo;
//------Objetos-----
	ObservableList<String> listaUF;
	ObservableList<String> listaTipo;
	int id_endereco = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			txtID.setText("0");
			gerarUF();
			gerarTipo();
			imagemNosBotoes();
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
			pessoal.setTelefone(txtTelefone.getText());
			pessoal.setCelular(txtCelular.getText());
			pessoal.setEmail(txtEmail.getText());
			pessoal.setEndereco(endereco);
			RadioButton rb = (RadioButton) grupoSexo.getSelectedToggle();
			pessoal.setSexo(rb.getText());
			
			String tipo = cbTipo.getSelectionModel().getSelectedItem();
			switch (tipo) {
			case "Gerente":
				pessoal.setTipo(1);
				break;
			case "Usuário":
				pessoal.setTipo(5);
				break;
			case "Atendente":
				pessoal.setTipo(2);
				break;
				
			default:
				pessoal.setTipo(5);
				break;
			}

			new NPessoal().salvar(pessoal);
			new Alert(AlertType.INFORMATION, "Incluido com sucesso! Nº" + pessoal.getId()).show();
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
			txtEmail.setText(pessoal.getEmail());// colocar e-mail no banco
			txtID.setText(pessoal.getId() + "");
			txtLocalidade.setText(pessoal.getEndereco().getLocalidade());
			txtLogradouro.setText(pessoal.getEndereco().getLogradouro());
			txtNome.setText(pessoal.getNome_completo());
			txtTelefone.setText(pessoal.getTelefone());
			dateNascimento.setValue(NovaCena.LOCAL_DATE(pessoal.getData_nascimento()));
			if (pessoal.getSexo().equals("Masculino"))
				raSexo_M.setSelected(true);
			else
				raSexo_F.setSelected(true);
			id_endereco = pessoal.getEndereco().getId();

			String uf = pessoal.getEndereco().getUF();
			for (int i = 0; i < listaUF.size(); i++) {
				if (listaUF.get(i).equals(uf)) {
					cbUF.getSelectionModel().select(i);
					break;
				}
			}
			//Pega o tipo descobre a descrição
			int tipo = pessoal.getTipo();
			String tipo_Descricao = "";
			if(tipo == 1)
				tipo_Descricao = "Gerente";
			else if (tipo == 2)
				tipo_Descricao = "Atendente";
			else if (tipo == 5)
				tipo_Descricao = "Usuário";
			//Seleciona o tipo segundo a descrição
			for (int i = 0; i < listaTipo.size(); i++) {
				if(listaTipo.get(i).equals(tipo_Descricao)) {
					cbTipo.getSelectionModel().select(i);
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

//---------------------Limpar Tela---------------------
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
		txtTelefone.setText("");

		dateNascimento.setValue(null);
		raSexo_F.setSelected(true);
		id_endereco = 0;
		gerarUF();
		gerarTipo();
		cbUF.getSelectionModel().select(0);
		cbTipo.getSelectionModel().select(0);
	}

//--------------------------Combobox--------------------------
	private void gerarUF() {
		listaUF = FXCollections.observableArrayList();
		String estados[] = { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB",
				"PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };
		for (int i = 0; i < estados.length; i++) {
			listaUF.add(estados[i]);
		}
		cbUF.setItems(listaUF);
	}

	private void gerarTipo() {
		listaTipo = FXCollections.observableArrayList();
		String tipos[] = { "Usuário", "Atendente", "Gerente" };
		for (int i = 0; i < tipos.length; i++) {
			listaTipo.add(tipos[i]);
		}
		cbTipo.setItems(listaTipo);
	}

//-------------------Imagem nos botões----------------------------
	private void imagemNosBotoes() {
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
	}

}
