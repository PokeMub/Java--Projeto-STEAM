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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.GeneroDao;
import javafxtrabalhopoo.model.dao.JogoCompradoDao;
import javafxtrabalhopoo.model.dao.JogoDao;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Genero;
import javafxtrabalhopoo.model.domain.Jogo;
import javafxtrabalhopoo.model.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class MenuUsuarioLojaOnlineComprarController implements Initializable {

    @FXML
    private Label labelNome;
    @FXML
    private Label labelIdade;
    @FXML
    private Label labelValor;
    @FXML
    private Label labelTempo;
    @FXML
    private Label labelData;
    @FXML
    private Label labelGenero;
    @FXML
    private Label labelValorAntes;
    @FXML
    private Label labelValorDepois;
    @FXML
    private Button buttonConfimar;
    @FXML 
    private Button buttonSair;

    private Jogo jogo;
    private Usuario usuario;
    private double valorFinal;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final JogoDao jogoDao = new JogoDao();
    private final JogoCompradoDao jogoCompradoDao = new JogoCompradoDao();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection(connection);
        jogoDao.setConnection(connection);
        jogoCompradoDao.setConnection(connection);

    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setTela(Jogo jogo, Usuario usuario) {
        this.jogo = jogo;
        this.usuario = usuario;
        this.setValorFinal(usuario.getValorCarteira());

        labelNome.setText(jogo.getNome());
        labelIdade.setText(String.valueOf(jogo.getRestricaoIdade()));
        labelValor.setText(String.valueOf(jogo.getValorDesconto()));
        labelTempo.setText(String.valueOf(jogo.getTempoEstimado()));
        labelData.setText(String.valueOf(jogo.getDateCriacao()));
        labelGenero.setText(String.valueOf(jogo.getGeneroNome()));
        labelValorAntes.setText(String.valueOf(usuario.getValorCarteira()));
        labelValorDepois.setText(String.valueOf(usuario.getValorCarteira() - jogo.getValorDesconto()));
    }

    public Jogo getJogo() {
        return jogo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void clicaConfiamr(){
        double valor = getUsuario().getValorCarteira() - getJogo().getValorDesconto();
        usuarioDao.alterarCateira(getUsuario().getIdUsuario(), valor);
        jogoDao.alterarQtd(getJogo().getIdJogo(), getJogo().getQtdVendida() + 1);
        jogoCompradoDao.inserir(getUsuario().getIdUsuario(), getJogo().getIdJogo(), getJogo().getValorDesconto());
        
        this.setValorFinal(valor);
        
        clicaButtonSair();
        
    }
    
    public void clicaButtonSair() {
        Stage stage = (Stage) buttonSair.getScene().getWindow();
        stage.close();
    }

}
