/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
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
import javafxtrabalhopoo.model.dao.JogoCompradoDao;
import javafxtrabalhopoo.model.dao.JogoDao;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Jogo;
import javafxtrabalhopoo.model.domain.JogoComprado;
import javafxtrabalhopoo.model.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class MenuUsuarioLojaOnlineController implements Initializable {

    @FXML
    private TableView<Jogo> tableViewJogo;
    @FXML
    private TableColumn<Jogo, String> tableColummJogoNome;
    @FXML
    private TableColumn<Jogo, String> tableColummJogoGenero;
    @FXML
    private TableColumn<Jogo, Double> tableColummJogoValor;
    @FXML
    private TableColumn<Jogo, Integer> tableColummJogoTempo;

    @FXML
    private Button buttonComprar;

    private Usuario usuario;

    private List<JogoComprado> listJogosComprados;

    private List<Jogo> listJogo;
    private ObservableList<Jogo> observableListJogo;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final JogoDao jogoDao = new JogoDao();
    private final JogoCompradoDao jogoCompradoDao = new JogoCompradoDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jogoCompradoDao.setConnection(connection);
        jogoDao.setConnection(connection);

        carregarTableViewJogo();

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

    }

    public void carregarTableViewJogo() {
        listJogo = jogoDao.listar();
        listJogo.removeIf(jg -> jg.getStatu() == 'N');

        tableColummJogoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColummJogoGenero.setCellValueFactory(new PropertyValueFactory<>("GeneroNome"));
        tableColummJogoValor.setCellValueFactory(new PropertyValueFactory<>("valorDesconto"));
        tableColummJogoTempo.setCellValueFactory(new PropertyValueFactory<>("tempoEstimado"));

        observableListJogo = FXCollections.observableArrayList(listJogo);
        tableViewJogo.setItems(observableListJogo);

    }

    @FXML
    public void clicarButtonComprar() throws IOException {
        listJogosComprados = jogoCompradoDao.listar(getUsuario().getIdUsuario());
        Jogo jogo = tableViewJogo.getSelectionModel().getSelectedItem();
        System.out.println("Desconto: " + jogo.getValorDesconto());

        ArrayList<Integer> ids = new ArrayList<>();

        for (JogoComprado jogoc : listJogosComprados) {
            ids.add(jogoc.getIdJogo());
        }

        if (jogo != null) {
            if (!ids.contains(jogo.getIdJogo())) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MenuUsuarioLojaOnlineComprarController.class.getResource("/trabalhojava/view/MenuUsuarioLojaOnlineComprar.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Compra Jogo");
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                MenuUsuarioLojaOnlineComprarController controller = loader.getController();

                Usuario usuario = getUsuario();

                controller.setTela(jogo, usuario);

                dialogStage.showAndWait();

                usuario.setValorCarteira(controller.getValorFinal());
                setUsuario(usuario);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Indisponivel");
                alert.setHeaderText("Impossivel de realiza a comprar");
                alert.setContentText("Jogo ja disponivel na biblioteca");
                alert.show();
            }

        }
    }

}
