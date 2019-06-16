package apresentacao;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javax.swing.JDesktopPane;

import entidade.Copias;
import entidade.Locacao;
import entidade.LocacaoItem;
import entidade.Pessoal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;
import negocio.NCopias;
import negocio.NLocacao;
import negocio.NPessoal;
import padrao.decorator.AnoFilme;
import padrao.decorator.BasicFilme;
import padrao.decorator.Filme;
import padrao.decorator.GeneroFilme;
import padrao.iterator.LocacaoIterator;


public class FrmAlugar implements Initializable {

    @FXML
    private Pane tPaneExit;

    @FXML
    private AnchorPane paneInterno;

    @FXML
    private TableView<LocacaoItem> listViewLista;

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
    private Label txtLabelCelular;

    @FXML
    private Label txtLabelSexo;

    @FXML
    private Label txtLabelEmail;

    @FXML
    private TextField txtCopiaId;

    @FXML
    private TextField txtSocioCpf;

    JDesktopPane principal;
    Locacao locacao;
    ObservableList<LocacaoItem> listaProdutos;
    Pessoal pessoal;
    List<LocacaoItem> arrayItens = new ArrayList<>();
    ObservableList<LocacaoItem> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            carregarTabela();
        } catch (Exception e) {
            e.printStackTrace();
        }
        btSalvar.setGraphic(new ImageView(new Image("/icones/save.png", 26, 26, false, false)));

        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([0-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        txtCopiaId.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, integerFilter));
        txtValorTotal.setEditable(false);
    }

    @FXML
    void VoltarAoPrincipal(ActionEvent event) throws IOException {
        tPaneExit.getChildren().clear();
    }

    @FXML
    void btnAdicionar(ActionEvent event) throws Exception {
        String codigoCopia = txtCopiaId.getText();
        if (!codigoCopia.isEmpty()) {
            NCopias nCopias = new NCopias();

            Copias copias = nCopias.consultarCopias(codigoCopia);

            if (copias.getId() != 0) {
                txtCopiaId.setText("");
                LocacaoItem locacaoItem = new LocacaoItem();
                locacaoItem.setCopias(copias);
                locacaoItem.setCodigoCopia(copias.getCodigoCopia());
                locacaoItem.setValor(copias.getFilmes().getTipoFilme().getPreco());
                locacaoItem.setTitulo(copias.getFilmes().getTitulo());
                locacaoItem.setFilmes(copias.getFilmes());
                listViewLista.getItems().add(locacaoItem);

                if (txtValorTotal.getText().isEmpty()) {
                    txtValorTotal.setText(String.valueOf(copias.getFilmes().getTipoFilme().getPreco()));
                } else {
                    double valorTotal = Double.parseDouble(txtValorTotal.getText());
                    txtValorTotal.setText(String.valueOf(valorTotal + copias.getFilmes().getTipoFilme().getPreco()));
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Copia não encontrada ou não disponivel.").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Campo copia vazio.").show();
        }
    }

    @FXML
    void btnInfo(ActionEvent event) {
        int index = listViewLista.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            LocacaoItem locacaoItem = listViewLista.getItems().get(index);

            Filme filme = new BasicFilme(locacaoItem.getTitulo());
            filme = new GeneroFilme(locacaoItem.getFilmes().getGenero() ,filme);
            filme = new AnoFilme(locacaoItem.getFilmes().getAnoLancamento(), filme);

            new Alert(Alert.AlertType.INFORMATION, filme.getInfo()).show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Selecionar uma copia para informações.").show();
        }
    }

    @FXML
    void btnExcluir(ActionEvent event) {
        int index = listViewLista.getSelectionModel().getSelectedIndex();
        LocacaoItem locacaoItem = listViewLista.getItems().get(index);

        if (!txtValorTotal.getText().isEmpty()) {
            double valorTotal = Double.parseDouble(txtValorTotal.getText());
            txtValorTotal.setText(String.valueOf(valorTotal - locacaoItem.getValor()));
        }
        listViewLista.getItems().remove(listViewLista.getSelectionModel().getSelectedIndex());
    }

    @FXML
    void btnLimpar(ActionEvent event) {
        limpar();
    }

    @FXML
    void btnPesquisarId(ActionEvent event) throws Exception {
        System.out.println(txtSocioCpf.getText());
        String cpf = txtSocioCpf.getText();
        if (!cpf.isEmpty()) {
            NPessoal nPessoal = new NPessoal();
            pessoal = nPessoal.consultarCpf(cpf);

            if (pessoal.getId() != 0) {
                txtSocioCpf.setText("");
                txtLabelId.setText("ID:        " + pessoal.getId());
                txtLabelNome.setText("NOME:     " + pessoal.getNomeCompleto());
                txtLabelCPF.setText("CPF:     " + pessoal.getCpf());
                txtLabelSexo.setText("SEXO:     " + pessoal.getSexo());
                txtLabelCelular.setText("CELULAR:     " + pessoal.getCelular());
                txtLabelEmail.setText("EMAIL:     " + pessoal.getEmail());
            } else {
                new Alert(Alert.AlertType.WARNING, "CPF não encontrado ou cliente inativo/bloqueado.").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Campo socio vazio.").show();
        }
    }


    @FXML
    void btnSalvar(ActionEvent event) throws Exception {
        lista = listViewLista.getItems();
        locacao = new Locacao(pessoal, lista);
        locacao.setValorTotal(Double.parseDouble(txtValorTotal.getText()));
        java.sql.Date sqlDate = new Date(System.currentTimeMillis());
        locacao.setDataLocacao(sqlDate);

        NLocacao nLocacao = new NLocacao();
        try {
            nLocacao.salvar(locacao);
            enviarDados();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Erro ao salvar a locação.").show();
        }
        new Alert(Alert.AlertType.INFORMATION, "Locação salva.").show();
        limpar();
    }

    private void limpar() {
        txtLabelId.setText("ID:");
        txtLabelCPF.setText("CPF:");
        txtLabelNome.setText("NOME:");
        txtLabelSexo.setText("SEXO:");
        txtLabelCelular.setText("CELULAR:");
        txtLabelEmail.setText("EMAIL:");
        listViewLista.getItems().clear();
        txtValorTotal.setText("");
    }

    private void carregarTabela() throws Exception {
        // Puxando as variaveis da classe Locacao para gerar Colunas
        String[] colunas = new LocacaoItem().getColunas();
        String[] nomeVariaveis = new LocacaoItem().getVariaveis();
        for (int i = 0; i < colunas.length; i++) {
            listViewLista.getColumns().add(new TableColumn<>(colunas[i]));
            listViewLista.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(nomeVariaveis[i]));
        }
    }

    public void enviarDados() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/frmListarHistorico.fxml"));
        Parent tabelaUsadaParent = loader.load();
        Scene  tabelaUsadaScene = new Scene(tabelaUsadaParent);
        FrmHistorico controller = loader.getController();
        Iterator<Locacao> pilhas;
        /*lista = listViewLista.getItems();
        locacao = new Locacao(pessoal, lista);*/
        //Precisa-se fazer este comando funcionar para jogar os dados para a outra tela ...
        //controller.dadosLocacao(listViewLista.getSelectionModel().getSelectedItem());
        controller.dadosLocacao(new LocacaoIterator().listagemComArrayList());



    }
}
