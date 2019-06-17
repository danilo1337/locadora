package apresentacao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

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
        Pilha p = new Pilha();
        List<Locacao> list = new ArrayList<>();
        for (Locacao l : new PLocacao().listar2()){
            p.empilhar(l);
        }
        while(!p.eVazia()){
        	Locacao locacao = p.listar().iterator().next();
            list.add(locacao);
        }
        imprimirNaTabela(list);
    }

    @FXML
    void limpar(ActionEvent event) {

    }

    private void gerarColunas() {
        // adiocionando as colunas
        String colunas[] = {"ID", "SOCIO", "DATA LOCACAO", "DATA PAGAMENTO", "VALOR TOTAL", "MULTA"};
        String nomeVariaveis[] = {"id", "pessoal", "dataLocacao", "dataPagamento", "valorTotal", "multa"};
        for (int i = 0; i < colunas.length; i++) {
            tabela.getColumns().add(new TableColumn<>(colunas[i]));
            tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
            tabela.getColumns().get(i).setStyle("-fx-alignment: CENTER;");
        }
    }

    private void imprimirNaTabela(List<Locacao> dados) throws Exception {
    	tabela.getItems().clear();
        tabela.getItems().addAll(dados);
    }
}
