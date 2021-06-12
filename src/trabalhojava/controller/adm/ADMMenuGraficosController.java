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
public class ADMMenuGraficosController implements Initializable {

    @FXML
    private Button buttonTodosJogos;
    @FXML
    private Button buttonGeneroJogos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void clicarButtonCategotia() throws IOException {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuGraficosGeneroJogos.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

       

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }
    
    @FXML
    public void clicarButtonTodos() throws IOException {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenuGraficosTodosJogos.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

       

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }
    }
    
}
