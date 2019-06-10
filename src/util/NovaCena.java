package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    
	    return localDate;
	}

	public static final LocalDate 	LOCAL_DATE (Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate localDate = LocalDate.parse(sdf.format(date),formatter);
	    
	    return localDate;
	}

}
