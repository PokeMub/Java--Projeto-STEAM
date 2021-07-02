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
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Evento;
import javafxtrabalhopoo.model.domain.Jogo;
import javafxtrabalhopoo.model.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class ADMMenuCadastroFuncionarioController implements Initializable {

    @FXML
    private TableView<Usuario> tableViewFuncionario;
    @FXML
    private TableColumn<Usuario, String> tableColumnNome;
    @FXML
    private TableColumn<Usuario, String> tableColumnCPF;

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;

    private List<Usuario> listRelatorio;
    private ObservableList<Usuario> observableListRelatorio;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection(connection);

        carregarTableFuncionario();
    }

    public void carregarTableFuncionario() {
        listRelatorio = usuarioDao.listFuncionario();

        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        try {
            observableListRelatorio = FXCollections.observableArrayList(listRelatorio);
            tableViewFuncionario.setItems(observableListRelatorio);
        } catch (Exception e) {
            System.out.println("[Erro] Funcionario: " + e);
        }
    }

    public void clicarButtonInserir() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ADMMenuCadastroEventoInserirController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroFuncionarioInserir.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro Funcionario");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        carregarTableFuncionario();
    }

    public void clicarButtonAlterar() throws IOException {

        try {
            Usuario usuario = tableViewFuncionario.getSelectionModel().getSelectedItem();
            if (usuario != null) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ADMMenuCadastroFuncionarioAlterarController.class.getResource("/trabalhojava/view/adm/ADMMenuCadastroFuncionarioAlterar.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Altera Evento");
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                ADMMenuCadastroFuncionarioAlterarController controller = loader.getController();

                controller.setUsuario(usuario);

                dialogStage.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um funcionario na Tabela!");
                alert.show();
            }
        } catch (IOException e) {
            System.out.println("ERRO Alterar Funcionario Controller: " + e);
        }
        carregarTableFuncionario();
    }

    public void clicarButtonApagar() {
        Usuario usuario = tableViewFuncionario.getSelectionModel().getSelectedItem();;

        if (usuario != null) {
            usuarioDao.remover(usuario);
            carregarTableFuncionario();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        }
    }

}
