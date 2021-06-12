/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafxtrabalhopoo.model.domain.Usuario;



public class MenuUsuarioInformacoesUsuarioControllerCarteira implements Initializable{
    @FXML
    private AnchorPane anchorPaneInformacaoUsuario;
    
    private Usuario usuario;
    //private String string;
    
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }
    
    
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    /*
    public void setString(String string){
        this.string = string;
    }; */
}
