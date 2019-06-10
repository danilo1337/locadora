package apresentacao;

import entidade.Filmes;
import entidade.LocacaoItem;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistencia.PLocacaoItem;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class FrmListarProdutosLocacao implements Initializable {

    @FXML
    private AnchorPane PaneDadosGerais;

    @FXML
    private Button btnEnviarDados;

    TableView<LocacaoItem> tabela = null;
    ObservableList<Filmes> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            gerarConfigTabelas();
            carregarTabela();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void ButtonEnviarDados(ActionEvent event) throws Exception {
        ObservableList<Filmes> list = FXCollections.observableArrayList();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/frmAlugar.fxml"));
        Parent tabelaUsadaParent = loader.load();
        Scene tabelaUsadaScene = new Scene(tabelaUsadaParent);
        FrmAlugar controller = loader.getController();

        //tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     /*   tabela.setOnMouseClicked((EventHandler<Event>) event1 -> {
            ObservableList<LocacaoItem> selectedItems =  tabela.getSelectionModel().getSelectedItems();
            for(LocacaoItem itens : selectedItems){
                selectedItems.add(itens);
                //controller.dadosProdutos(tabela.getSelectionModel().getSelectedItems());
                //System.out.println("selected item " + filme);
            }
        });*/


        //adiciona 1 de cada vez
        controller.dadosProdutos(tabela.getSelectionModel().getSelectedItems().iterator());
        Stage Window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Window.setScene(tabelaUsadaScene);
        Window.show();
    }

    private void gerarConfigTabelas(){
        tabela = new TableView<LocacaoItem>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //===TABELA===
        tabela.setPrefWidth(300);
        tabela.setPrefHeight(300);
        tabela.setTableMenuButtonVisible(true);
        PaneDadosGerais.getChildren().add(tabela);
    }

    private void carregarTabela() throws Exception {
        // Puxando as variaveis da classe Locacao para gerar Colunas
        //String colunas[] = new LocacaoItem().getColunas();
        String colunas[] = new String[]{"ID", "TITULO", "VALOR"};
        String nomeVariaveis[] = new String[]{"id", "filmes", "valor"};
        for (int i = 0; i < colunas.length; i++) {
            tabela.getColumns().add(new TableColumn<>(colunas[i]));
            tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
            tabela.getColumns().get(i).setStyle("-fx-alignment: CENTER;");
        }
        imprimirTable(new PLocacaoItem().listarProdutos().iterator());
       }

    private void imprimirTable(Iterator<LocacaoItem> LocItem){
        ObservableList<LocacaoItem> lista = FXCollections.observableArrayList();
        while (LocItem.hasNext()) {
            LocacaoItem locacaoItem = (LocacaoItem) LocItem.next();
            lista.add(locacaoItem);
        }
        tabela.setItems(lista);
    }

}
