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

/**
 * FXML Controller class
 *
 * @author fabri
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
        try {
            usuarioDao.setConnection(connection);
            Usuario usuario = new Usuario();
            usuario.setNomeUsuario("Fabricio");
            usuario = usuarioDao.buscar(usuario);
            System.out.println("Nome: " + usuario.getNomeUsuario());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Status: " + usuario.getStatus());
        } catch (Exception e) {
            System.out.println("ERRO: " + e);
        }
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

        if ("123".equals(textFieldLogin.getText()) && "123".equals(textFieldSenha.getText())) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/adm/ADMMenu.fxml"));

                Stage stage = new Stage();

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();

                clicaButtonSair();

            } catch (IOException e) {
                System.out.println("ERRO LoginController: " + e);
            }
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuUsuario.fxml"));

                Stage stage = new Stage();

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();

                clicaButtonSair();

            } catch (IOException e) {
                System.out.println("ERRO LoginController: " + e);
            }
        }

    }

}
