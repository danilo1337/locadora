package apresentacao;

import entidade.Pedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class FrmPedido implements Initializable {



    @FXML
    private TextField txtFieldId;

    @FXML
    private ComboBox<?> comboProduto;

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
    Pedido pedido;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtFieldId.requestFocus();
    }


    @FXML
    void btnAdicionar(ActionEvent event) {

    }

    @FXML
    void btnExcluir(ActionEvent event) {

    }

    @FXML
    void btnFechar(ActionEvent event) {

    }

    @FXML
    void btnLimpar(ActionEvent event) {

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

    /*private void carregarCombos() {

        try {

            //Carregando produtos
            cmbProduto.removeAllItems();
            cmbProduto.addItem(new Produto(0, "Selecione..."));

            for (Produto produto : new NProduto().listar(new Produto())) {
                cmbProduto.addItem(produto);
            }

            //Carregando clientes
            cmbCliente.removeAllItems();
            cmbCliente.addItem(new Cliente(0, "Selecione..."));

            for (Cliente cliente : new NCliente().listar()) {
                cmbCliente.addItem(cliente);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.principal, e.getMessage());
        }

    }*/



}
