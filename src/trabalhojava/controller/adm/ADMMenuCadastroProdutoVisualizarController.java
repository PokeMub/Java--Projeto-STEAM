package trabalhojava.controller.adm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ADMMenuCadastroProdutoVisualizarController implements Initializable {
    
    
    @FXML
    private Label labelNome;
    
    @FXML
    private Label labelStatusEvento;
    
    @FXML
    private Label labelFormaDesconto;
    
    @FXML
    private Label labelValorDesconto;
    
    @FXML
    private Label labelDataInicio;
    
    @FXML 
    private Label labelDataTermino;
    
    @FXML
    private Button buttonVoltar;
    
    @FXML
    private AnchorPane anchorPaneADMMenuCadastroProdutoVisualizarController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
