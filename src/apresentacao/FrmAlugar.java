package apresentacao;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JDesktopPane;

import entidade.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.NCopias;
import negocio.NFilme;
import persistencia.PLocacao_item;
import util.NovaCena;


public class FrmAlugar implements Initializable {

    @FXML
    private Pane tPaneExit;

    @FXML
    private AnchorPane paneInterno;

    @FXML
    private TableView<Locacao_item> listViewLista;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btLimpar;

    @FXML
    private TextField txtValorTotal;

    @FXML
    private Label txtLabelId;

    @FXML
    private Label txtLabelNome;

    @FXML
    private Label txtLabelCPF;

    @FXML
    private TextField txtCopiaId;

    JDesktopPane principal;
    Locacao locacao;
    ObservableList<Locacao_item> listaProdutos;
    Pessoal pessoal;
    List<Locacao_item> arrayItens = new ArrayList<>();
    ObservableList<Locacao_item> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //txtFieldId.requestFocus();
        // carregarCombos();
        try {
            carregarTabela();
        } catch (Exception e) {
            e.printStackTrace();
        }
        btSalvar.setGraphic(new ImageView(new Image("/icones/save.png", 26, 26, false, false)));
        btExcluir.setGraphic(new ImageView(new Image("/icones/delete.png", 26, 26, false, false)));
        btLimpar.setGraphic(new ImageView(new Image("/icones/clean.png", 26, 26, false, false)));
    }

    @FXML
    void VoltarAoPrincipal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/principal.fxml"));
        Parent tabelaUsadaParent = loader.load();
        Scene  tabelaUsadaScene = new Scene(tabelaUsadaParent);
        Stage Window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Window.setScene(tabelaUsadaScene);
        Window.show();
    }

    @FXML
    void btnAdicionar(ActionEvent event) throws Exception {
        NCopias nCopias = new NCopias();

        Copias copias = nCopias.consultarCopias("931782");
        
        System.out.println(copias.getFilmeId());
    }

    @FXML
    void btnExcluir(ActionEvent event) {

    }

    @FXML
    void btnLimpar(ActionEvent event) {
        limpar();
    }

    @FXML
    void btnPesquisarId(ActionEvent event) throws Exception {
        paneInterno.getChildren().clear();
        paneInterno.getChildren().add(new NovaCena().getNode("/fxml/frmListarSociosLocacao.fxml"));
    }

    @FXML
    void btnPesquisarProduto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/frmListarProdutos.fxml"));
        Parent tabelaUsadaParent = loader.load();
        Scene  tabelaUsadaScene = new Scene(tabelaUsadaParent);
        Stage Window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Window.setScene(tabelaUsadaScene);
        Window.centerOnScreen();
        Window.show();
    }

    @FXML
    void btnSalvar(ActionEvent event) {

    }

    private void limpar() {
       txtLabelId.setText("ID");
       txtLabelCPF.setText("CPF");
       txtLabelNome.setText("NOME");
    }

    private void carregarTabela() throws Exception {
        // Puxando as variaveis da classe Locacao para gerar Colunas
        String colunas[] = new Locacao_item().getColunas();
        String nomeVariaveis[] = new Locacao_item().getVariaveis();
        for (int i = 0; i < colunas.length; i++) {
            listViewLista.getColumns().add(new TableColumn<>(colunas[i]));
            listViewLista.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
        }
    }

    private Pessoal socioSelected;
    public void dadosSocios(Pessoal socio){
        socioSelected = socio;
        txtLabelId.setText("ID:        "+(socioSelected.getId()));
        txtLabelNome.setText("NOME: "+socioSelected.getNome_completo());
        txtLabelCPF.setText("CPF:     "+socioSelected.getCpf());
    }

    private Iterator<Locacao_item> filmeSelected;
    public void dadosProdutos(Iterator<Locacao_item> LocItem){
        filmeSelected = LocItem;
        while(LocItem.hasNext()){
            Locacao_item locacao_item = (Locacao_item) LocItem.next();
            lista.add(locacao_item);
        }
        listViewLista.setItems(lista);
    }


}
