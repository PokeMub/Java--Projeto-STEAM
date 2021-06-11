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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class ADMMenuCadastroFuncionarioController implements Initializable {

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void clicarButtonInserir() throws IOException {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroFuncionarioInserir.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

       

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }
    
    public void clicarButtonAlterar() throws IOException {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroFuncionarioAlterar.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

       

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }
    
}
