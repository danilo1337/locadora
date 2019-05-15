package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NovaCena {
	
	Stage stage;
    Parent root;
    
	public Node getNode(String caminhoFxml) {
		  Node no = null;
		  try {
		     no = FXMLLoader.load(getClass().getResource(caminhoFxml));
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		return no;
	}
	
	/*Esse metodo abre uma nova cena sem fechar a anterior*/
	public void gerarNovaCena(String caminho) throws Exception {
		Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(caminho));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
	}
	
}
