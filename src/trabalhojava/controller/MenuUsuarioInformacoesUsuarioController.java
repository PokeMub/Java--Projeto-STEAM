
package trabalhojava.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection (connection);
        
        Usuario pessoa = new Usuario();
        pessoa.setIdUsuario(2);
        pessoa = usuarioDao.buscarId(pessoa);
        
        labelEmail.setText(pessoa.getEmail());
        labelNome.setText(pessoa.getNomeUsuario());
        
        /*
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
        */
       

        
    }
 
}
