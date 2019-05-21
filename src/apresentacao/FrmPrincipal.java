package apresentacao;

import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import entidade.Login;
import enums.PermissaoUsuario;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.NovaCena;
import util.Sessao;

public class FrmPrincipal implements Initializable{
    Sessao sessao = Sessao.getInstance();
    Stage stage;

    @FXML
    private StackPane paneInterno;

    @FXML
    private Menu mnuCadastrar;

    @FXML
    private MenuItem mnuTitulo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        Login usuario = sessao.getLogin();
        new Alert(Alert.AlertType.INFORMATION, "Bem vindo " + usuario.getUsuario()).show(); //So coloquei para teste.

        if(usuario.getPermissao() == PermissaoUsuario.USUARIO.getDescricao()){
            mnuCadastrar.setVisible(false);
        }
	}
	
    @FXML
    private void cadastrarCliente(ActionEvent event) {
    	paneInterno.getChildren().clear();
    	paneInterno.getChildren().add(new NovaCena().getNode("/fxml/frmCadSocio.fxml"));
    }

    @FXML
    private void listarSocio(ActionEvent event) {
    	paneInterno.getChildren().clear();
    	paneInterno.getChildren().add(new NovaCena().getNode("/fxml/frmListarSocio.fxml"));
    }
    @FXML
    private void cadastrarTitulo(ActionEvent event) {
    	paneInterno.getChildren().clear();
    	paneInterno.getChildren().add(new NovaCena().getNode("/fxml/frmCadTitulo.fxml"));
    }


    @FXML
    void mnuPedidos(ActionEvent event) {
        paneInterno.getChildren().clear();
        paneInterno.getChildren().add(new NovaCena().getNode("/fxml/frmPedidos.fxml"));
    }

    @FXML
    void logout(ActionEvent event) {
        sessao.setLogin(null);
        Main a = new Main();
        stage=(Stage) paneInterno.getScene().getWindow();
    	a.start(stage);
    }

    @FXML
    void sair(ActionEvent event) {
        sessao.setLogin(null);
	    System.exit(0);
    }
}
