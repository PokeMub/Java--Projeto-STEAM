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
public class ADMMenuCadastroController implements Initializable {

    @FXML
    private Button buttonFuncionario;
    @FXML
    private Button buttonProdutos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void clicarButtonFuncionario() throws IOException {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroFuncionario.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

       

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }
    
    @FXML
    public void clicarButtonProdutos() throws IOException {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuCadastroProduto.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

       

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }
    
}
