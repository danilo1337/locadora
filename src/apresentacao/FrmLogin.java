package apresentacao;

import java.net.URL;
import java.util.ResourceBundle;

import entidade.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.NLogin;
import padrao.singleton.Sessao;

public class FrmLogin implements Initializable {
	Sessao sessao = Sessao.getInstance();

	@FXML
	private AnchorPane pane;

	@FXML
	private TextField txtLogin;

	@FXML
	private PasswordField txtSenha;


	@FXML
	private Button btnLogar;

	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		txtLogin.setText("ADMIN");
		txtSenha.setText("123");
	}

	@FXML
	private void logar(ActionEvent event) {
		try {
			NLogin nLogin = new NLogin();
			String loginTxt = txtLogin.getText().toLowerCase();
			String senhaTxt = util.Hash.codificar(txtSenha.getText());

			Login login = nLogin.logar(loginTxt, senhaTxt);

			if(login.getId() != 0){
				sessao.setLogin(login);
				abrirSistema(event);
			}else{
				new Alert(AlertType.ERROR, "Usuário ou Senha incorreto").show();
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}

	}

	private void abrirSistema(ActionEvent event) throws Exception {
		FXMLLoader loader;
		Parent root;
		Stage stage;
		if (event.getSource() == btnLogar) {
			stage = (Stage) btnLogar.getScene().getWindow();
			loader = new FXMLLoader(getClass().getResource("/fxml/principal.fxml"));
			root = loader.load();

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			stage.centerOnScreen();
			stage.setTitle("Locadora Altas Horas - Usuário: " + txtLogin.getText());
		} else
			throw new Exception("Não foi possível localizar a tela");
	}
}
