package apresentacao;

import java.net.URL;
import java.util.ResourceBundle;

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

public class FrmLogin implements Initializable{
	Stage stage;
    Parent root;
    
	@FXML
    private AnchorPane pane;
    
	@FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnLogar;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
    @FXML
    private void cadastrar(ActionEvent event) {

    }

    @FXML
    private void logar(ActionEvent event) {
    	try {

			gerarNovaCena(event);
			
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage()).show();
		}
    	
    }
    
    
	public void gerarNovaCena(ActionEvent event) throws Exception {
		if (event.getSource() == btnLogar) {
		stage = (Stage) btnLogar.getScene().getWindow();	 
        root = FXMLLoader.load(getClass().getResource("/fxml/principal.fxml"));
		}else
			throw new Exception("Não foi possível localizar a tela");
		
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
	}

}
