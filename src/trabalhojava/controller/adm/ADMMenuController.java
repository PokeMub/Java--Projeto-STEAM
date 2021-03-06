/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class ADMMenuController implements Initializable {

    @FXML
    private Button buttonSair;
    @FXML
    private Button buttonCadastro;
    @FXML
    private Button buttonGraficos;
    @FXML
    private Button buttonRelatorio;
    @FXML
    private AnchorPane anchorPaneMenu;

    @FXML
    private AnchorPane anchorPane;

    private Usuario usuario;

    /*
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void clicaButtonSair() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuLogin.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            stage = (Stage) buttonSair.getScene().getWindow();
            stage.close();

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }

    @FXML
    public void clicarbuttonCadastro() throws IOException {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastro.fxml"));
            anchorPaneMenu.getChildren().setAll(a);
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }

    @FXML
    public void clicarbuttonGraficos() throws IOException {

        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuGraficosTodosJogos.fxml"));

            anchorPaneMenu.getChildren().setAll(a);

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }

        // AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuGraficosTodosJogos.fxml"));
        // anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void clicarbuttonRelatorio() throws IOException {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuRelatorioTodosJogos.fxml"));

            anchorPaneMenu.getChildren().setAll(a);

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
