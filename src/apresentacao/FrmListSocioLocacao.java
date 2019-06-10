package apresentacao;


import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import entidade.Locacao_item;
import entidade.Pessoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import negocio.NPessoal;
import padrao.factory.Abreviatura;
import padrao.factory.AbreviaturaFactory;
import padrao.iterator.PessoalIterator;
import persistencia.PPessoal;
import util.NovaCena;

import javax.swing.*;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class FrmListSocioLocacao implements Initializable {
    @FXML
    private AnchorPane PaneInterno;

    @FXML
    private AnchorPane PaneLista;

    TableView<Object> tabela = null;
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
        tabela = new TableView<Object>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabela.setPrefWidth(PaneLista.getPrefWidth());
        tabela.setPrefHeight(PaneLista.getPrefHeight());
        tabela.setTableMenuButtonVisible(true);
        PaneLista.getChildren().add(tabela);

    }

    private void carregarTabela() {
        // Puxando as variaveis da classe Locacao para gerar Colunas
        //String colunas[] = new Locacao_item().getColunas();

            String colunas[] = new String[]{"ID", "NOME", "CPF"};
            String nomeVariaveis[] = new String[]{"id", "nome_completo", "cpf"};
            for (int i = 0; i < colunas.length; i++) {
                tabela.getColumns().add(new TableColumn<>(colunas[i]));
                tabela.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
                tabela.getColumns().get(i).setStyle("-fx-alignment: CENTER;");
            }
        //tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> listnerTabela(newValue));

    }

    private void imprimirTable(Iterator<Pessoal> pessoas){
        ObservableList<Object> lista = FXCollections.observableArrayList();
        //try{
            //listaPessoas = FXCollections.observableArrayList();
           /* for (Pessoal socios : pessoas) {listaPessoas.add(socios);}
            tabela.setItems(listaPessoas);

        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
            while (pessoas.hasNext()) {
                Pessoal pessoal = (Pessoal) pessoas.next();
                lista.add(pessoal);
            }
            tabela.setItems(lista);
    }
/*
    private Object listnerTabela(Object newValue) {
        try {
            ObservableList<String> lista;
            //String id = tabela.getValueAt(indice, 0).toString();
            //Pessoal id = tabela.getValueAt(indice, 0).toString();
            tabela.getSelectionModel().getSelectedItem().toString();


            Pessoal pessoas = new NPessoal().consultar(id);

            FrmCadPedido janela = new FrmCadPedido(pnlPrincipal, cliente);
            pnlPrincipal.add(janela);
            janela.setVisible(true);
            this.dispose();

        } catch (Exception e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
        return pessoal;
    }*/



}
