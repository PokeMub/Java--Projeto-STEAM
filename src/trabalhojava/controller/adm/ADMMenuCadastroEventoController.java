/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafxtrabalhopoo.model.dao.EventoDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Evento;
import javafxtrabalhopoo.model.domain.Jogo;
import trabalhojava.controller.adm.ADMMenuCadastroEventoVisualizarController;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class ADMMenuCadastroEventoController implements Initializable {

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonVisualizar;

    @FXML
    private Button buttonDeletar;

    @FXML
    private TableView<Evento> tableViewEvento;

    @FXML
    private TableColumn<Evento, String> tableColummEventoNome;

    @FXML
    private TableColumn<Evento, Float> tableColummEventoDescont;

    @FXML
    private TableColumn<Evento, Integer> tableColummEventoId;

    private List<Evento> listEvento;
    private ObservableList<Evento> observableListEvento;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final EventoDao eventoDao = new EventoDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventoDao.setConnection(connection);
        carregarInformacoes();
    }

    public void clicarButtonAlterar() throws IOException {
        try {
            Evento evento = tableViewEvento.getSelectionModel().getSelectedItem();
            if (evento != null) {
                if (evento.getIdEvento() == 3) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Impossivel altera evento porque ele e default");
                    alert.show();
                } else {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ADMMenuCadastroEventoAlterarController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroEventoAlterar.fxml"));
                    AnchorPane page = (AnchorPane) loader.load();

                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Altera Evento");
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);

                    ADMMenuCadastroEventoAlterarController controller = loader.getController();

                    controller.setEvento(evento);

                    dialogStage.showAndWait();
                    carregarInformacoes();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um evento na Tabela!");
                alert.show();
            }
        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }

    public void clicarButtonVisualizar() throws IOException {
        Evento evento = tableViewEvento.getSelectionModel().getSelectedItem();

        if (evento != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ADMMenuCadastroEventoVisualizarController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroEventoVisualizar.fxml"));
                AnchorPane page = (AnchorPane) loader.load();
                ADMMenuCadastroEventoVisualizarController controller = loader.getController();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Visualizar Evento");
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                controller.setEventoo(evento);
                dialogStage.showAndWait();
            } catch (IOException e) {
                System.out.println("ERRO RegistroController: " + e);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um campo na Tabela!");
            alert.show();
        }

    }

    public void carregarInformacoes() {
        tableColummEventoNome.setCellValueFactory(new PropertyValueFactory<>("nomeEvento"));
        tableColummEventoDescont.setCellValueFactory(new PropertyValueFactory<>("valorDesconto"));
        tableColummEventoId.setCellValueFactory(new PropertyValueFactory<>("idEvento"));
        listEvento = eventoDao.listar();
        observableListEvento = FXCollections.observableArrayList(listEvento);
        tableViewEvento.setItems(observableListEvento);

    }

    public void clicarButtonApagar() {
        Evento evento = tableViewEvento.getSelectionModel().getSelectedItem();
        Jogo jogo = new Jogo();

        if (evento != null) {
            if (evento.getIdEvento() == 3) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Impossivel excluir evento porque ele e default");
                alert.show();

            } else {
                eventoDao.alterarIdEventoJogo(jogo, evento.getIdEvento());
                eventoDao.remover(evento);
                carregarInformacoes();

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um evento na Tabela!");
            alert.show();
        }

    }

    public void clicarButtonInserir() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ADMMenuCadastroEventoInserirController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroEventoInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro Evento");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        carregarInformacoes();

    }

}
