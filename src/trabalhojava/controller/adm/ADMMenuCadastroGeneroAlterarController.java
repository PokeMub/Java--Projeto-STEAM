/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.GeneroDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Genero;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class ADMMenuCadastroGeneroAlterarController implements Initializable {

    

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldDescricao;
    @FXML
    private Button ButtonAlterar;
    @FXML   
    private Button ButtonVoltar;
    
    private Genero genero;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();    
    
    private final GeneroDao generoDao = new GeneroDao();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generoDao.setConnection(connection);
    }

    /**
     * @return the genero
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
        this.textFieldNome.setText(genero.getNome());
        this.textFieldDescricao.setText(genero.getDescricao());
    }

    public void ButtonAlterar() {
        if (validarEntradaDeDados()) {
            genero.setNome(textFieldNome.getText());
            genero.setDescricao(textFieldDescricao.getText());
            generoDao.alterar(genero);
            ButtonVoltar();
            
        }
        
    }
    
    public void ButtonVoltar() {
        Stage stage = (Stage) ButtonVoltar.getScene().getWindow();
        stage.close();
    }
    
            private boolean validarEntradaDeDados() {
        String erroMessage = "";
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
            erroMessage += "Nome invalido \n";
        }

        if (textFieldDescricao.getText() == null || textFieldDescricao.getText().length() == 0) {
            erroMessage += "Descricao invalido \n";
        }

        if (erroMessage.length()
                == 0) {
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
    
    
    
}
