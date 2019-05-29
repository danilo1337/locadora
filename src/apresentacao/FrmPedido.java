package apresentacao;

import entidade.Filmes;
import entidade.Locacao;
import entidade.Locacao_item;
import entidade.Pessoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import negocio.NFilme;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class FrmPedido implements Initializable {

    @FXML
    private TitledPane frmFull;

    @FXML
    private AnchorPane testeExit;

    @FXML
    private TextField txtFieldId;

    @FXML
    private ComboBox<Filmes> comboProduto;

    @FXML
    private TextField txtFieldQnt;

    @FXML
    private ListView<?> listViewLista;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btLimpar;

    @FXML
    private Button btFechar;

    JDesktopPane principal;

    Locacao locacao;
    ObservableList<Filmes> listaProdutos;
    Pessoal pessoal;
    List<Locacao_item> arrayItens = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtFieldId.requestFocus();
        carregarCombos();

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
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/principal.fxml"));
        //fazer algo pra voltar pra tela

    }

    @FXML
    void btnLimpar(ActionEvent event) {
        limpar();
    }

    @FXML
    void btnPesquisarId(ActionEvent event) {

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

    private void carregarCombos() {

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
    }

    private void limpar(){
        txtFieldId.setText("");
        txtFieldQnt.setText("");
        comboProduto.getSelectionModel().select(-1);
    }

    private void carregarTabela(List<Locacao_item> itens){





    }



}
