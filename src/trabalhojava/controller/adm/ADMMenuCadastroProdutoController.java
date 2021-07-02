/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.CadastroJogoDao;
import javafxtrabalhopoo.model.dao.JogoCompradoDao;
import javafxtrabalhopoo.model.dao.JogoDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Jogo;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class ADMMenuCadastroProdutoController implements Initializable {

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonApagar;
    @FXML
    private TableView<Jogo> tableViewProduto;
    @FXML
    private TableColumn<Jogo, String> tableColumnProdutoNome;
    @FXML
    private TableColumn<Jogo, Integer> tableColumnProdutoId;
    @FXML
    private TableColumn<Jogo, Double> tableColumnProdutoValor;
    @FXML
    private TableColumn<Jogo, String> tableColumnProdutoGenero;
    @FXML
    private TableColumn<Jogo, Integer> tableColumnProdutoQtd;

    private List<Jogo> listJogo;
    private ObservableList<Jogo> observableListJogo;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final JogoDao jogoDao = new JogoDao();
    private final JogoCompradoDao jogoCompradoDao = new JogoCompradoDao();
    private final CadastroJogoDao cadastroJogoDao = new CadastroJogoDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jogoDao.setConnection(connection);
        jogoCompradoDao.setConnection(connection);
        cadastroJogoDao.setConnection(connection);
        carregarTableViewProduto();
    }

    @FXML
    public void clicarButtonInserir() throws IOException {
        Jogo jogo = new Jogo();
        boolean buttonConfirmar = showADMMenuCadastroProdutoInserir(jogo);
        if (buttonConfirmar) {

            jogoDao.inserir(jogo);

            cadastroJogoDao.inserir(jogo.getIdUsuario(), jogoDao.buscaId(jogo.getNome()));
            carregarTableViewProduto();

        }
    }

    @FXML
    public void clicarButtonAlterar() throws IOException {
        Jogo jogo = tableViewProduto.getSelectionModel().getSelectedItem();
        if (jogo != null) {

            boolean buttonConfirmar = showADMMenuCadastroProdutoAlterar(jogo);
            if (buttonConfirmar) {

                jogoDao.alterar(jogo);

                carregarTableViewProduto();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um jogo na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void clicarButtonApagar() {
        Jogo jogo = tableViewProduto.getSelectionModel().getSelectedItem();
        if (jogo != null) {
            if (!jogoCompradoDao.existe(jogo)) {
                cadastroJogoDao.remover(jogo.getIdJogo());
                jogoDao.remover(jogo);
                carregarTableViewProduto();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Opção indisponivel, esse jogo esta na biblioteca de alguem, caso queira tira ele da loja altere o status de venda dele");
                alert.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um jogo na Tabela!");
            alert.show();
        }
    }

    public void carregarTableViewProduto() {
        listJogo = jogoDao.listar();
        tableColumnProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnProdutoId.setCellValueFactory(new PropertyValueFactory<>("idJogo"));
        tableColumnProdutoValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableColumnProdutoGenero.setCellValueFactory(new PropertyValueFactory<>("GeneroNome"));
        tableColumnProdutoQtd.setCellValueFactory(new PropertyValueFactory<>("qtdVendida"));

        observableListJogo = FXCollections.observableArrayList(listJogo);
        tableViewProduto.setItems(observableListJogo);
    }

    public boolean showADMMenuCadastroProdutoInserir(Jogo jogo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ADMMenuCadastroProdutoInserirController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroProdutoInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Jogo");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        ADMMenuCadastroProdutoInserirController controller = loader.getController();

        controller.setJogo(jogo);

        dialogStage.showAndWait();

        return controller.isButtonConfirmar();
    }

    public boolean showADMMenuCadastroProdutoAlterar(Jogo jogo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ADMMenuCadastroProdutoAlterarController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroProdutoAlterar.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Jogo");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        ADMMenuCadastroProdutoAlterarController controller = loader.getController();

        controller.setJogo(jogo);

        dialogStage.showAndWait();

        return controller.isButtonConfirmar();
    }

}
