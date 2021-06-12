
package trabalhojava.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Usuario;


public class MenuUsuarioInformacoesUsuarioController implements Initializable {

    @FXML
    private Label labelEmail;
    
    @FXML
    private Label labelSenha;
    
    @FXML
    private Label labelNome;
    
    @FXML
    private Label labelCpf;
    
    @FXML
    private Label labelData_de_nascimento;
    
    @FXML
    private Label labelTelefone;
    
    @FXML
    private Label labelStjo_points;
    
    @FXML
    private Button buttonAdicionar_points;
    
    private Usuario usuario;
    
    @FXML
    private AnchorPane anchorPaneInformacaoUsuario;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();
   
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }
    
    public void buttonInserirCarteira() throws IOException{
        System.out.println("lucas");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MenuUsuarioInformacoesUsuarioControllerCarteira.class.getResource("/trabalhojava/view/MenuUsuarioInformacoesUsuarioCarteira.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        MenuUsuarioInformacoesUsuarioControllerCarteira controller = loader.getController();
       
    
        Stage dialogStage = new Stage();
      
        dialogStage.setTitle("Colocar Dinheiro");
       
        Scene scene = new Scene(page);
       
        dialogStage.setScene(scene);
        
        controller.setUsuario(usuario);
        //controller.setString("joao");
       
        dialogStage.showAndWait();
        

    }
    
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        
        labelEmail.setText(usuario.getEmail());
        labelNome.setText(usuario.getNomeUsuario());
        labelSenha.setText(usuario.getSenha());
        labelCpf.setText(usuario.getCpf());
            
        labelData_de_nascimento.setText(String.valueOf(usuario.getDataNascimento()));
        
        labelTelefone.setText((usuario.getTelefone()));
        labelStjo_points.setText(String.valueOf(usuario.getValorCarteira()));
        
        
        
        /*
        usuarioDao.setConnection (connection);
        
       
        Usuario pessoa = new Usuario();
  
        pessoa.setEmail(usuario.getEmail());
 
        pessoa = usuarioDao.buscarEmail(pessoa);
        
        */
        
    }
    
   
 
}
