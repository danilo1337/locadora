package apresentacao;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JDesktopPane;

import entidade.Filmes;
import entidade.Locacao;
import entidade.Locacao_item;
import entidade.Pessoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import negocio.NFilme;
import util.NovaCena;


public class FrmAlugar implements Initializable {

    @FXML
    private Pane tPaneExit;

    @FXML
    private AnchorPane PaneInterno;

    @FXML
    private TextField txtFieldId;

    @FXML
    private TableView<Locacao_item> listViewLista;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btLimpar;

    @FXML
    private Button btFechar;

    @FXML
    private TextField txtValorTotal;

    JDesktopPane principal;

    Locacao locacao;
    ObservableList<Locacao_item> listaProdutos;
    Pessoal pessoal;
    List<Locacao_item> arrayItens = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtFieldId.requestFocus();
       // carregarCombos();
        carregarTabela();

        btSalvar.setGraphic(new ImageView(new Image("/icones/save.png", 26, 26, false, false)));
        btExcluir.setGraphic(new ImageView(new Image( "/icones/delete.png", 26, 26, false, false)));
        btLimpar.setGraphic(new ImageView(new Image("/icones/clean.png", 26, 26, false, false)));
        btFechar.setGraphic(new ImageView(new Image("/icones/fechar.png", 26, 26,false,false)));
    }

    @FXML
    void btnAdicionar(ActionEvent event) {

    }

    @FXML
    void btnExcluir(ActionEvent event) {

    }

    @FXML
    void btnFechar(ActionEvent event) throws IOException {
    tPaneExit.getChildren().clear();
    }

    @FXML
    void btnLimpar(ActionEvent event) {
        limpar();
    }

    @FXML
    void btnPesquisarId(ActionEvent event) throws Exception {
        /*PaneInterno.getChildren().clear();
        PaneInterno.getChildren().add(new NovaCena().getNode("/fxml/frmListarSociosLocacao.fxml"));*/
        //new NovaCena().gerarNovaCena("/fxml/frmListarSociosLocacao.fxml");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/frmListarSociosLocacao.fxml"));
        PaneInterno.getChildren().setAll(pane);
    }

    @FXML
    void btnPesquisarProduto(ActionEvent event) {

    }

    @FXML
    void btnRetirar(ActionEvent event) {

    }

    @FXML
    void btnSalvar(ActionEvent event) {

    }

  /*  private void carregarCombos() {

        try {
            listaProdutos = FXCollections.observableArrayList();
            comboProduto.getSelectionModel().clearSelection();
            comboProduto.getSelectionModel().select(-1);
            for (Filmes filmes : new NFilme().Listar(new Filmes())){
                listaProdutos.add(filmes);
                comboProduto.setItems(listaProdutos);
           }

        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            e.printStackTrace();
        }
    }*/

    private void limpar(){
        txtFieldId.setText("");
    }

    private void carregarTabela() {
        // Puxando as variaveis da classe Locacao para gerar Colunas
        String colunas[] = new Locacao_item().getColunas();
        String nomeVariaveis[] = new Locacao_item().getVariaveis();
        for (int i = 0; i < colunas.length; i++) {
            listViewLista.getColumns().add(new TableColumn<>(colunas[i]));
            listViewLista.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
        }
    }

    private void imprimirTable(List<Locacao_item> itens){
        try{
            listaProdutos = FXCollections.observableArrayList();
            for (Locacao_item item : itens) {listaProdutos.add(item);}
            listViewLista.setItems(listaProdutos);

        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
