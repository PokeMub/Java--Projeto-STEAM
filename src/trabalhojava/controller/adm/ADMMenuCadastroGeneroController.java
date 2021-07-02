/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafxtrabalhopoo.model.dao.GeneroDao;
//import javafxtrabalhopoo.model.dao.JogoDao;
//import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Genero;
import javafxtrabalhopoo.model.domain.Usuario;
//import javafxtrabalhopoo.model.domain.JogoComprado;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class ADMMenuCadastroGeneroController implements Initializable {

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private TableView<Genero> tableViewGenero;
    @FXML
    private TableColumn<Genero, String> tableColumnGeneroNome;
    @FXML
    private TableColumn<Genero, String> tableColumnGeneroDescricao;
    @FXML
    private TableColumn<Genero, Integer> tableColumnGeneroID;

    private List<Genero> listGeneros;
    private ObservableList<Genero> observableListGeneros;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final GeneroDao generoDao = new GeneroDao();

    private Genero genero;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generoDao.setConnection(connection);
        try {
            carregarTableViewJogosComprados();
        } catch (SQLException ex) {
            Logger.getLogger(ADMMenuCadastroGeneroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void carregarTableViewJogosComprados() throws SQLException {
        listGeneros = generoDao.list();

        tableColumnGeneroNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnGeneroDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableColumnGeneroID.setCellValueFactory(new PropertyValueFactory<>("idGenero"));

        //listJogosComprados = jogoCompradoDao.listar();
        try {
            observableListGeneros = FXCollections.observableArrayList(listGeneros);
            tableViewGenero.setItems(observableListGeneros);
        } catch (Exception e) {
            System.out.println("[Erro] RelatorioControle: " + e);
        }

    }

    public void clicarButtonInserir() throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ADMMenuCadastroGeneroInserirController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroGeneroInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro Funcionario");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        carregarTableViewJogosComprados();

    }

    public void clicarButtonAlterar() throws IOException, SQLException {

        //try {
        //  Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroGeneroAlterar.fxml"));
        Genero genero = tableViewGenero.getSelectionModel().getSelectedItem();
        try {

            if (genero != null) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ADMMenuCadastroGeneroAlterarController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroGeneroAlterar.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Altera Evento");
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                ADMMenuCadastroGeneroAlterarController controller = loader.getController();

                controller.setGenero(genero);

                dialogStage.showAndWait();
                carregarTableViewJogosComprados();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um funcionario na Tabela!");
                alert.show();
            }
        } catch (IOException e) {
            System.out.println("ERRO Alterar Funcionario Controller: " + e);
        }
    }

}
