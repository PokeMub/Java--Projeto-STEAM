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
import javafxtrabalhopoo.model.domain.Usuario;

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

    
    private Usuario usuario;
    /*
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
            
            System.out.println("usuario: "+ usuario.getEmail());
         
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuUsuarioInformacoesUsuarioController.class.getResource("/trabalhojava/view/MenuUsuarioInformacoesUsuario.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            MenuUsuarioInformacoesUsuarioController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Informações");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            controller.setUsuario(usuario);
            anchorPaneMenu.getChildren().setAll(page);
            //dialogStage.showAndWait(); apaga tudo q tive aver com essa linha
            
            
            
            
            //AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuarioInformacoesUsuario.fxml"));
            //anchorPaneMenu.getChildren().setAll(a);
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }

    @FXML
    public void clicarButtonJogos() throws IOException {
        try {
            System.out.println("usuario: "+ usuario.getEmail());
            System.out.println("usuarioId: "+ usuario.getIdUsuario());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuUsuarioBibliotecaJogosController.class.getResource("/trabalhojava/view/MenuUsuarioBibliotecaJogos.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            MenuUsuarioBibliotecaJogosController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Relatorio");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            controller.setUsuario(usuario);
            anchorPaneMenu.getChildren().setAll(page);
            //dialogStage.showAndWait();
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }

    @FXML
    public void clicarButtonRelatorio() throws IOException {
        try {
            System.out.println("usuario: "+ usuario.getEmail());
            System.out.println("usuarioId: "+ usuario.getIdUsuario());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuUsuarioRelatorioController.class.getResource("/trabalhojava/view/MenuUsuarioRelatorio.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            MenuUsuarioRelatorioController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Relatorio");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            controller.setUsuario(usuario);
            anchorPaneMenu.getChildren().setAll(page);
            //dialogStage.showAndWait();
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }
    
    @FXML
    public void clicaButtonLoja() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuUsuarioLojaOnlineController.class.getResource("/trabalhojava/view/MenuUsuarioLojaOnline.fxml"));
            //root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            MenuUsuarioLojaOnlineController controller = loader.getController();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Loja");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            controller.setUsuario(usuario);
            anchorPaneMenu.getChildren().setAll(page);
            //dialogStage.showAndWait();
        } catch (IOException e) {
            System.out.println("Erro no FXMLVBoxMainController " + e);
        }
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    
    
    
}

