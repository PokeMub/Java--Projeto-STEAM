
package trabalhojava.controller.adm;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.EventoDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Evento;


public class ADMMenuCadastroEventoVisualizarController implements Initializable {
   
    @FXML
    private Label labelNome;
    @FXML
    private Label labelStatuEvento;
    @FXML
    private Label labelFormaDesconto;
    @FXML
    private Label labelValorDesconto;
    @FXML
    private Label labelDataInicio;
    @FXML
    private Label labelDataTermino;
    @FXML
    private Button buttonvoltar;
    
    private Evento evento;
    /*
    private boolean buttonConfirmar = false;
    private Stage dialogStage;
    */
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final EventoDao eventoDao = new EventoDao();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  

    
    public void setEventoo(Evento evento){
        this.evento = evento;
            
        labelNome.setText(evento.getNomeEvento());
        
        labelStatuEvento.setText(String.valueOf(evento.getStatu_evento()));
        labelFormaDesconto.setText(evento.getFormaDesconto());
        labelValorDesconto.setText(String.valueOf(evento.getValorDesconto()));
        labelDataInicio.setText(String.valueOf(evento.getDataInicio()));
        labelDataTermino.setText(String.valueOf(evento.getDataTermino()));
        
    }
    
    
    public void clicaButtonSair() {
        Stage stage = (Stage) buttonvoltar.getScene().getWindow();
        stage.close();
    }
}
