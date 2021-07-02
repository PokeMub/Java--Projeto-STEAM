/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.domain.JogoComprado;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxtrabalhopoo.model.dao.JogoCompradoDao;
import javafxtrabalhopoo.model.dao.JogoDao;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Jogo;
import javafxtrabalhopoo.model.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class MenuUsuarioBibliotecaJogosController implements Initializable {

    @FXML
    private Button buttonJogar;
    @FXML
    private Button buttonReembolso;
    @FXML
    private TableView<JogoComprado> tableViewBiblioteca;
    @FXML
    private TableColumn<JogoComprado, String> tableColumnBibliotecaNome;
    @FXML
    private TableColumn<JogoComprado, String> tableColumnBibliotecaGenero;
    @FXML
    private TableColumn<JogoComprado, Integer> tableColumnBibliotecaHorasJogadas;
    
    private List<JogoComprado> listJogosComprados;
    private ObservableList<JogoComprado> observableListJogosComprados;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final JogoCompradoDao jogoCompradoDao = new JogoCompradoDao();
    private final JogoDao jogoDao = new JogoDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();
    
    
    private Usuario usuario;
    
    private Jogo jogo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jogoCompradoDao.setConnection(connection);
        jogoDao.setConnection(connection);
        usuarioDao.setConnection(connection);
                
    }

    public void carregarTableViewJogosComprados(int id) {

        listJogosComprados = jogoCompradoDao.listar(id);

        tableColumnBibliotecaNome.setCellValueFactory(new PropertyValueFactory<>("jogoNome"));
        tableColumnBibliotecaGenero.setCellValueFactory(new PropertyValueFactory<>("jogoGenero"));
        tableColumnBibliotecaHorasJogadas.setCellValueFactory(new PropertyValueFactory<>("qtdHoraJogada"));

        //listJogosComprados = jogoCompradoDao.listar();
        try {
            observableListJogosComprados = FXCollections.observableArrayList(listJogosComprados);
            tableViewBiblioteca.setItems(observableListJogosComprados);
        } catch (Exception e) {
            System.out.println("[Erro] RelatorioControle: " + e);
        }

    }
    
    @FXML
    public void clicarButtonJogar() throws IOException {
        JogoComprado jogo = tableViewBiblioteca.getSelectionModel().getSelectedItem();
        if (jogo!= null) {
            jogoCompradoDao.UpdateHoraJoga(jogo.getQtdHoraJogada()+1, jogo.getIdJogoComprado());
            carregarTableViewJogosComprados(getUsuario().getIdUsuario());
        }    
        
    }
    
    @FXML
    public void clicarReembolso() throws IOException {
        JogoComprado jogo = tableViewBiblioteca.getSelectionModel().getSelectedItem();
        if (jogo!= null ) {
            if (jogo.getQtdHoraJogada() < 2  ) {
                jogoCompradoDao.UpdateStatu_reembouso(jogo.getIdJogoComprado());
                usuarioDao.alterarCateira(getUsuario().getIdUsuario(), getUsuario().getValorCarteira()+jogo.getValorProduto());
                carregarTableViewJogosComprados(getUsuario().getIdUsuario());
            
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Indisponivel");
                alert.setHeaderText("Impossivel de realiza o reembolso");
                alert.setContentText("Ultrapassou o tempo máximo para de 2 horas para o reembolso");
                alert.show();
            }
        }
        
        
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        carregarTableViewJogosComprados(usuario.getIdUsuario());
    }

    /**
     * @return the jogo
     */
    public Jogo getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}
