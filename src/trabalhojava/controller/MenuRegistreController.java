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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fabri
 */
public class MenuRegistreController implements Initializable {

    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonSair;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldSenha;
    @FXML
    private TextField textFieldConfirmarSenha;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCPF;
    @FXML
    private DatePicker textFieldData;
    @FXML
    private TextField textFieldTelefone;

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
        Stage stage = (Stage) buttonSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void clicaButtonLogin() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuLogin.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            clicaButtonSair();

        } catch (Exception e) {
            System.out.println("ERRO RegistroController: " + e);
        }

    }

}
