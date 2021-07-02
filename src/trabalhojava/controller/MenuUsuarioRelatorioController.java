/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxtrabalhopoo.model.dao.JogoCompradoDao;
import javafxtrabalhopoo.model.dao.JogoDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Jogo;
import javafxtrabalhopoo.model.domain.JogoComprado;
import javafxtrabalhopoo.model.domain.Usuario;



/**
 * FXML Controller class
 *
 * @author fabio
 */
public class MenuUsuarioRelatorioController implements Initializable {

    @FXML
    private TableView<JogoComprado> tableViewRelatorio;
    @FXML
    private TableColumn<JogoComprado, String> tableColoumnNome;
    @FXML
    private TableColumn<JogoComprado, String> tableColoumnGeneroJogo;
    @FXML
    private TableColumn<JogoComprado, Character> tableColoumnReembolso;
    @FXML
    private TableColumn<JogoComprado, Double> tableColoumnValor;
    @FXML
    private TableColumn<JogoComprado, Date> tableColoumnDataCompra;

    private List<JogoComprado> listJogosComprados;
    private ObservableList<JogoComprado> observableListJogosComprados;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final JogoCompradoDao jogoCompradoDao = new JogoCompradoDao();
    private final JogoDao jogoDao = new JogoDao();

    private Usuario usuario;
    
    private Jogo jogo;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        carregarTableViewJogosComprados(usuario.getIdUsuario());
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jogoCompradoDao.setConnection(connection);
        jogoDao.setConnection(connection);
        
        
    }

    public void carregarTableViewJogosComprados(int id) {

        listJogosComprados = jogoCompradoDao.listar(id);

        tableColoumnNome.setCellValueFactory(new PropertyValueFactory<>("jogoNome"));
        tableColoumnGeneroJogo.setCellValueFactory(new PropertyValueFactory<>("jogoGenero"));
        tableColoumnValor.setCellValueFactory(new PropertyValueFactory<>("valorProduto"));
        tableColoumnReembolso.setCellValueFactory(new PropertyValueFactory<>("statusReembolso"));
        tableColoumnDataCompra.setCellValueFactory(new PropertyValueFactory<>("dataCompra"));

        //listJogosComprados = jogoCompradoDao.listar();
        try {
            observableListJogosComprados = FXCollections.observableArrayList(listJogosComprados);
            tableViewRelatorio.setItems(observableListJogosComprados);
        } catch (Exception e) {
            System.out.println("[Erro] RelatorioControle: " + e);
        }

    }

}
