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
		
	}

    @FXML
    private void limpar(ActionEvent event) {
    	//Apaga as colunas da tabela
    	tabela.getColumns().clear();
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

	private void imprimirNaTabela(Iterator dados) throws Exception {
		ObservableList<Object> lista = FXCollections.observableArrayList();
		gerarColunas();
		
//		for (Pessoal pessoal : new NPessoal().listar()) {
//			lista.add(pessoal);
//		}
		
		while (dados.hasNext()) {
			Pessoal pessoal = (Pessoal) dados.next();
			lista.add(pessoal);
		}
		
		tabela.setItems(lista);
	}

	private void gerarColunas() {
		// adiocionando as colunas
		String colunas[] = {"ID","NOME","SEXO","CPF","D.Nasc","TELEFONE","CELULAR","E-MAIL", "TIPO","LOCALIDADE"};
		for (int i = 0; i < colunas.length; i++) {
			tabela.getColumns().add(new TableColumn<>(colunas[i]));
		}
		//colunas para Endereco
		
		/*Aqui você coloca o nome das variaves para pegar os valores, é necessario estar com get set na classe*/
		tabela.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
		tabela.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome_completo"));
		tabela.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sexo"));
		tabela.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabela.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));
		tabela.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tabela.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("celular"));
		tabela.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("email"));
		tabela.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("tipo"));
		/*Vou descobri como colocar o objeto endereco*/
		tabela.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("endereco"));
		
	}


}
