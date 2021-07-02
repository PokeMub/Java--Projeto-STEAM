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
public class ADMMenuCadastroGeneroInserirController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldDescricao;
    @FXML
    private Button buttonAdicionar;
    @FXML   
    private Button buttonVoltar;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();    
    
    private final GeneroDao generoDao = new GeneroDao();
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generoDao.setConnection(connection);
        
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
    
    @FXML
    public void voltar(){
        Stage stage = (Stage) buttonVoltar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void adicionar(){
        
        if (validarEntradaDeDados()) {
            Genero genero = new Genero();
            genero.setNome(textFieldNome.getText());
            genero.setDescricao(textFieldDescricao.getText());
            generoDao.inserir(genero);
            voltar();
        }
    
    }
    
}
