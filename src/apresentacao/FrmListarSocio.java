package apresentacao;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pp_iterator.PessoalIterator;

public class FrmListarSocio implements Initializable {
	@FXML
	private TableView<Object> tabela;

	@FXML
	private Button btnConsultar;

    @FXML
    private Button btnLimpar;
    
	@FXML
	private ComboBox<?> cbTipos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		gerarColunas();
	}

    @FXML
    private void limpar(ActionEvent event) {
    	//Apaga as colunas da tabela
    	tabela.getItems().clear();
    }
	@FXML
	private void consultar(ActionEvent event) {
		try {
			PessoalIterator pessoalIterator = new PessoalIterator();

//			imprimirNaTabela(pessoalIterator.listagemComFila());
			imprimirNaTabela(pessoalIterator.listagemComPilha());
		} catch (Exception e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
			e.printStackTrace();
		}
	}

	private void imprimirNaTabela(Iterator<Pessoal> dados) throws Exception {
		ObservableList<Object> lista = FXCollections.observableArrayList();
		while (dados.hasNext()) {
			Pessoal pessoal = (Pessoal) dados.next();
			lista.add(pessoal);
		}
		
		tabela.setItems(lista);
	}

	private void gerarColunas() {
		// adiocionando as colunas
		String colunas[] = {"ID","NOME","SEXO","CPF","D.Nasc","TELEFONE","CELULAR","E-MAIL", "TIPO","LOCALIDADE"};
		String nomeVariaveis[] = {"id","nome_completo","sexo","cpf","data_nascimento","telefone","celular","email","tipo","endereco"};
		for (int i = 0; i < colunas.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
			tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
		}
	}


}
