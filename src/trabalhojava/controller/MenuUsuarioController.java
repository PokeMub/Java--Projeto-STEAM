/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller;

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

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class MenuUsuarioController implements Initializable {

    @FXML
    private Button buttonSair;
    @FXML
    private Button buttonInformacoes;
    @FXML
    private Button buttonJogos;
    @FXML
    private Button buttonRelatorio;
    @FXML
    private Button buttonLoja;
    @FXML
    private AnchorPane anchorPaneMenu;

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
    public void clicarButtonInformacoes() throws IOException {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuarioInformacoesUsuario.fxml"));
            anchorPaneMenu.getChildren().setAll(a);
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }

    @FXML
    public void clicarButtonJogos() throws IOException {
        try {
            //Trocar o 'InformacoesUsuario.fxml' pelo endereço correto <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuarioBibliotecaJogos.fxml"));
            anchorPaneMenu.getChildren().setAll(a);
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }

    @FXML
    public void clicarButtonRelatorio() throws IOException {
        try {
            //Trocar o 'InformacoesUsuario.fxml' pelo endereço correto <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuarioRelatorio.fxml"));
            anchorPaneMenu.getChildren().setAll(a);
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }
    
    @FXML
    public void clicaButtonLoja() throws IOException {
        try {
            //Trocar o 'InformacoesUsuario.fxml' pelo endereço correto <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuarioLojaOnline.fxml"));
            anchorPaneMenu.getChildren().setAll(a);
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }
}