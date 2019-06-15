package apresentacao;

import entidade.Locacao;
import entidade.Pessoal;
import estruturaDeDados.Pilha;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import padrao.iterator.LocacaoIterator;
import persistencia.PLocacao;
import persistencia.PPessoal;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.itextpdf.text.html.simpleparser.ImageProvider;

public class FrmHistorico implements Initializable {

    @FXML
    private TableView<Object> tabela;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnLimpar;

    ObservableList<Object> listaProdutos;
    Pilha pilha = new Pilha();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gerarColunas();
    }

    @FXML
    void consultar(ActionEvent event) throws Exception {
//        dadosLocacao(new LocacaoIterator().listagemComArrayList());
    	
    }

    @FXML
    void limpar(ActionEvent event) {

    }

    private void gerarColunas() {
        // adiocionando as colunas
        String colunas[] = {"ID", "SÓCIO", "DATA LOCACAO", "DATA PAGAMENTO", "VALOR TOTAL", "MULTA"};
        String nomeVariaveis[] = {"id", "pessoal", "dataLocacao", "dataPagamento", "valorTotal", "multa"};
        for (int i = 0; i < colunas.length; i++) {
            tabela.getColumns().add(new TableColumn<>(colunas[i]));
            tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
            tabela.getColumns().get(i).setStyle("-fx-alignment: CENTER;");
        }
        //tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> listnerTabela(newValue));
    }

    //Aqui vou passar o Nó
  /*  private ArrayList<Pilha> locacao;
    public void dadosLocacao(ArrayList<Pilha> pilhas) throws Exception {
        Pilha pilha = new Pilha();
        locacao = pilhas;
        ArrayList<Locacao> arrayList = new ArrayList<>();
        arrayList.addAll(new PLocacao().listar());
        listaProdutos = FXCollections.observableArrayList();

        for (Locacao item : arrayList) {
            pilha.empilhar(listaProdutos.add(item));
        }
        tabela.setItems(listaProdutos);
    }*/

  Iterator<?> pilhas;
  public void dadosLocacao(Iterator<?> dados){
	  Locacao l = (Locacao)dados.next();
	  System.out.println(l);
      listaProdutos = FXCollections.observableArrayList();
      pilhas = dados;
      while(dados.hasNext()) {
    	  pilha.empilhar((Locacao)dados.next());
    	  listaProdutos.add(pilha);
//    	  listaProdutos.add(pilhas.next());
//    	  pilha.empilhar());
      }
//      ArrayList<Locacao> arrayList = new ArrayList<>();
//      for (Locacao item : arrayList) {
//          pilha.empilhar(listaProdutos.add(item));
//      }
      pilha.ImprimirPilha();
      tabela.setItems(listaProdutos);
  }
	
}
