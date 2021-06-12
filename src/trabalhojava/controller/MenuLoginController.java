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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Usuario;
import trabalhojava.controller.adm.ADMMenuController;

/**
 * FXML Controller class
 *
 * @author fabri
 * @author info
 */


public class MenuLoginController implements Initializable {

    @FXML
    private Button buttonRegistre;
    @FXML
    private Button buttonConecta;
    @FXML
    private Button buttonSair;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private TextField textFieldSenha;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    // o erro ocorre na linha de baixo \/ pois o valor que a linha de cima retornou não foi o q tinha q retona
    private final Connection connection = database.conectar();

    private final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Iniciando");

        /*teste se ta funcionado pode apaga depois \/\/ */
    }

    @FXML
    public void clicaButtonSair() {
        Stage stage = (Stage) buttonSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void clicaButtonRegistre() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuRegistre.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            clicaButtonSair();

        } catch (Exception e) {
            System.out.println("ERRO LoginController: " + e);
        }

    }

    @FXML
    public void clicaButtonConectar() throws IOException {

        try {

            usuarioDao.setConnection(connection);

            Usuario usuario = new Usuario();

            usuario.setEmail(textFieldLogin.getText());
            
            usuario = usuarioDao.buscarEmail(usuario);
            
            System.out.println(usuario.getEmail());
            System.out.println(usuario.getSenha());
            System.out.println(usuario.getStatus());
            System.out.println(""+ usuario.getTelefone());

            if ((usuario.getEmail().equals(textFieldLogin.getText())) && (usuario.getSenha().equals(textFieldSenha.getText()))) {
                try {

                    //Parent root = null;
                    FXMLLoader loader = new FXMLLoader();
                    
                    if (usuario.getStatus() == 'a') {
                        loader.setLocation(ADMMenuController.class.getResource("/trabalhojava/view/adm/ADMMenu.fxml"));
                        //root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenu.fxml"));
                        AnchorPane page = (AnchorPane) loader.load();
                        ADMMenuController controller = loader.getController();
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Compra Jogo");
                        Scene scene = new Scene(page);
                        
                        controller.setUsuario(usuario);

                        dialogStage.showAndWait();
                    dialogStage.showAndWait();

                    } else {
                        loader.setLocation(MenuUsuarioController.class.getResource("/trabalhojava/view/MenuUsuario.fxml"));
                        //root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuario.fxml"));
                        AnchorPane page = (AnchorPane) loader.load();
                        MenuUsuarioController controller = loader.getController();
                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Compra Jogo");
                        Scene scene = new Scene(page);
                        dialogStage.setScene(scene);
                         
                        controller.setUsuario(usuario);

                        dialogStage.showAndWait();

                    }

                    
                    
                    

                    
                
                     

                   
                    
                    
                    /*
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    clicaButtonSair();
                        */
                } catch (IOException e) {
                    System.out.println("ERRO LoginController: " + e);
                }
            } else {
                System.out.println("Conta Invalida");
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }

    }
    
    

}