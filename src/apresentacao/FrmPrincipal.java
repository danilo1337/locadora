package apresentacao;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import entidade.Pedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.NovaCena;

import javax.swing.*;

public class FrmPrincipal implements Initializable{
	Stage stage;
	Parent root;




    @FXML
    private StackPane paneInterno;

    @FXML
    private MenuItem mnuTitulo;
    
    @FXML
    private MenuItem mnuTelaLogin;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
    void telaLogin(ActionEvent event) {
    	
    	Main a = new Main();
    	a.start(stage);
    }

    @FXML
    void telaSair(ActionEvent event) {
    	System.exit(0);
    }


}
