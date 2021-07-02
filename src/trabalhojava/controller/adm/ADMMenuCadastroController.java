/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.io.IOException;
import java.net.URL;
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
public class ADMMenuCadastroController implements Initializable {

    @FXML
    private Button buttonFuncionario;
    @FXML
    private Button buttonProdutos;
    @FXML
    private Button buttonEvento;
    @FXML
    private Button buttonGenero;
    @FXML
    private AnchorPane anchorPaneMenu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void clicarButtonFuncionario() throws IOException {

        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroFuncionario.fxml"));
            anchorPaneMenu.getChildren().setAll(a);

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }

    @FXML
    public void clicarButtonProdutos() throws IOException {

        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroProduto.fxml"));

            anchorPaneMenu.getChildren().setAll(a);

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }

    @FXML
    public void clicarButtonEvento() throws IOException {

        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroEvento.fxml"));

            anchorPaneMenu.getChildren().setAll(a);

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }

    @FXML
    public void clicarButtonGenero() throws IOException {

        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroGenero.fxml"));

            anchorPaneMenu.getChildren().setAll(a);

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }
}
