package apresentacao;


import entidade.Pessoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import padrao.iterator.PessoalIterator;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class FrmListSocioLocacao implements Initializable {
    @FXML
    private AnchorPane paneInterno;

    @FXML
    private Pane paneGeral;

    @FXML
    private AnchorPane paneLista;

    @FXML
    private Button btnEnviarSocio;

    TableView<Pessoal> tabela = null;
    ObservableList<Object> listaPessoas;
    ObservableList<Pessoal> listaPessoas2;
    PessoalIterator pessoalIterator = new PessoalIterator();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gerarConfigTabela();
        carregarTabela();
        try {
            imprimirTable(pessoalIterator.listagemComLista());
        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void gerarConfigTabela() {
        tabela = new TableView<Pessoal>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabela.setPrefWidth(paneLista.getPrefWidth());
        tabela.setPrefHeight(paneLista.getPrefHeight());
        tabela.setTableMenuButtonVisible(true);
        paneLista.getChildren().add(tabela);
    }

    private void carregarTabela() {

        // Puxando as variaveis da classe Locacao para gerar Colunas
        //String colunas[] = new LocacaoItem().getColunas();

            String colunas[] = new String[]{"ID", "NOME", "CPF"};
            String nomeVariaveis[] = new String[]{"id", "nome_completo", "cpf"};
            for (int i = 0; i < colunas.length; i++) {
                tabela.getColumns().add(new TableColumn<>(colunas[i]));
                tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
                tabela.getColumns().get(i).setStyle("-fx-alignment: CENTER;");
            }
    }

    private void imprimirTable(Iterator<Pessoal> pessoas){
        ObservableList<Pessoal> lista = FXCollections.observableArrayList();
            while (pessoas.hasNext()) {
                Pessoal pessoal = (Pessoal) pessoas.next();
                lista.add(pessoal);
            }
            tabela.setItems(lista);
    }

    @FXML
    void buttonEnviarSocio(ActionEvent event) throws IOException {
        //Aqui carrega a tela
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/frmAlugar.fxml"));
        Parent tabelaUsadaParent = loader.load();
        Scene  tabelaUsadaScene = new Scene(tabelaUsadaParent);
        FrmAlugar controller = loader.getController();

        //Aqui envia os dadosSocios para a outra tela
//        controller.dadosSocios(tabela.getSelectionModel().getSelectedItem());

        //Aqui mostra a tela
        Stage Window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Window.setScene(tabelaUsadaScene);
        Window.show();
    }

}
