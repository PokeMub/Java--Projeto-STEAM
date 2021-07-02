/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Usuario;

public class MenuUsuarioInformacoesUsuarioControllerCarteira implements Initializable {

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldCodigo;
    @FXML
    private TextField textFieldValor;
    @FXML
    private Button buttonAdicionar;

    private Usuario usuario;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection(connection);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuarioDao.buscarId(usuario);
    }

    public void clicarButtonAdicionar() {
        if (validarEntradaDeDados()) {
            usuarioDao.alterarCateira(usuario.getIdUsuario(), (usuario.getValorCarteira() + Double.parseDouble(textFieldValor.getText())));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Dinheiro Inseridor");
            alert.setHeaderText("Dinheiro Inseridor com sucessor");
            alert.show();
            sair();
        }
    }

    private boolean validarEntradaDeDados() {
        String erroMessage = "";
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
            erroMessage += "Nome invalido \n";
        }

        if (textFieldNumero.getText() == null || textFieldNumero.getText().length() == 0) {
            erroMessage += "Numero invalido \n";
        }
        if (textFieldCodigo.getText() == null || textFieldCodigo.getText().length() == 0) {
            erroMessage += "Codigo invalido \n";
        }

        try {
            if (textFieldValor.getText() == null || textFieldValor.getText().length() == 0) {
                erroMessage += "Valor invalido \n";
            } else if (Double.parseDouble(textFieldValor.getText()) < 0) {
                erroMessage += "Valor invalido \n";
            }
        } catch (Exception e) {
            erroMessage += "Utilizer ponto em vez de virgula no campo valor¹\n";
        }

        if (erroMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no inserir");
            alert.setHeaderText("Campo invalido, por favor, corrija...");
            alert.setContentText(erroMessage);
            alert.show();
            return false;
        }

    }
    
    public void sair() {
        Stage stage = (Stage) buttonAdicionar.getScene().getWindow();
        stage.close();
    }

}
