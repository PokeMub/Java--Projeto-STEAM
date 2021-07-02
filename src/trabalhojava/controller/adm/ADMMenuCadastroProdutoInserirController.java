/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller.adm;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafxtrabalhopoo.model.dao.EventoDao;
import javafxtrabalhopoo.model.dao.GeneroDao;
import javafxtrabalhopoo.model.dao.JogoDao;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Evento;
import javafxtrabalhopoo.model.domain.Genero;
import javafxtrabalhopoo.model.domain.Jogo;
import javafxtrabalhopoo.model.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author fabio
 */
public class ADMMenuCadastroProdutoInserirController implements Initializable {

    @FXML
    private ComboBox<Integer> comboBoxIdade;
    @FXML
    private ComboBox<Genero> comboBoxGenero;
    @FXML
    private ComboBox<Usuario> comboBoxFuncionario;
    @FXML
    private ComboBox<Evento> comboBoxEvento;
    @FXML
    private DatePicker datePickerCriacao;
    @FXML
    private TextField textFieldValor;
    @FXML
    private TextField textFieldTempo;
    @FXML
    private TextField textFieldNome;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonVoltar;

    private boolean buttonConfirmar = false;
    private Jogo jogo;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final GeneroDao generoDao = new GeneroDao();
    private final EventoDao eventoDao = new EventoDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final JogoDao jogoDao = new JogoDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generoDao.setConnection(connection);
        eventoDao.setConnection(connection);
        usuarioDao.setConnection(connection);
        jogoDao.setConnection(connection);
        try {
            carregarGenero();
            carregarEvento();
            carregarFuncionario();
            carregarIdade();
        } catch (SQLException ex) {
            Logger.getLogger(ADMMenuCadastroProdutoInserirController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isButtonConfirmar() {
        return buttonConfirmar;
    }

    public void setButtonConfirmar(boolean buttonConfirmar) {
        this.buttonConfirmar = buttonConfirmar;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void carregarGenero() throws SQLException {
        List<Genero> listGenero = generoDao.list();
        ObservableList<Genero> observableListGenero;
        observableListGenero = FXCollections.observableArrayList(listGenero);

        Callback<ListView<Genero>, ListCell<Genero>> factory = lv -> new ListCell<Genero>() {

            @Override
            protected void updateItem(Genero item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNome());
            }

        };

        comboBoxGenero.setCellFactory(factory);
        comboBoxGenero.setButtonCell(factory.call(null));

        comboBoxGenero.setItems(observableListGenero);

//        for (Genero g: listGenero) {
//            comboBoxGenero.getItems().addAll(g.getNome());
//        }
    }

    public void carregarEvento() throws SQLException {
        List<Evento> listEvento = eventoDao.listar();

        ObservableList<Evento> observableListGenero;
        observableListGenero = FXCollections.observableArrayList(listEvento);

        Callback<ListView<Evento>, ListCell<Evento>> factory = lv -> new ListCell<Evento>() {

            @Override
            protected void updateItem(Evento item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNomeEvento());
            }

        };

        comboBoxEvento.setCellFactory(factory);
        comboBoxEvento.setButtonCell(factory.call(null));

        comboBoxEvento.setItems(observableListGenero);

//        for (Evento e : listEvento) {
//            comboBoxEvento.getItems().addAll(e.getNomeEvento());
//        }
    }

    public void carregarFuncionario() throws SQLException {
        List<Usuario> listEvento = usuarioDao.listFuncionario();

        ObservableList<Usuario> observableListGenero;
        observableListGenero = FXCollections.observableArrayList(listEvento);

        Callback<ListView<Usuario>, ListCell<Usuario>> factory = lv -> new ListCell<Usuario>() {

            @Override
            protected void updateItem(Usuario item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNomeUsuario());
            }

        };

        comboBoxFuncionario.setCellFactory(factory);
        comboBoxFuncionario.setButtonCell(factory.call(null));

        comboBoxFuncionario.setItems(observableListGenero);

//        for (Evento e : listEvento) {
//            comboBoxEvento.getItems().addAll(e.getNomeEvento());
//        }
    }

    public void carregarIdade() {
        comboBoxIdade.getItems().addAll(0, 8, 10, 12, 14, 16, 18);
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
        this.textFieldNome.setText(jogo.getNome());
        this.textFieldValor.setText(String.valueOf(jogo.getValor()));
        this.textFieldTempo.setText(String.valueOf(jogo.getTempoEstimado()));
    }

    public void buttonVoltar() {
        Stage stage = (Stage) buttonVoltar.getScene().getWindow();
        stage.close();
    }

    public void buttonInserir() {
        if (validarEntradaDeDados()) {
            jogo.setDateCriacao(java.sql.Date.valueOf(datePickerCriacao.getValue()));
            jogo.setIdGenero(comboBoxGenero.getValue().getIdGenero());
            jogo.setIdUsuario(comboBoxFuncionario.getValue().getIdUsuario());
            jogo.setIdEvento(comboBoxEvento.getValue().getIdEvento());
            jogo.setRestricaoIdade(comboBoxIdade.getValue());
            jogo.setNome(textFieldNome.getText());
            jogo.setTempoEstimado(Integer.parseInt(textFieldTempo.getText()));
            jogo.setValor(Double.parseDouble(textFieldValor.getText()));
            buttonConfirmar = true;
            buttonVoltar();
        }
    }

    private boolean validarEntradaDeDados() {
        String erroMessage = "";
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
            erroMessage += "Nome invalido \n";
        } else if (jogoDao.existe(textFieldNome.getText())) {
            erroMessage += "Nome ja em uso \n";
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

        try {
            if (textFieldTempo.getText() == null) {
                erroMessage += "Tempo invalido \n";
            } else if (Integer.parseInt(textFieldTempo.getText()) < 0) {
                erroMessage += "Tempo invalido \n";
            }
        } catch (Exception e) {
            erroMessage += "Tempo invalido \n";
        }

        if (datePickerCriacao.getValue() == null) {
            erroMessage += "Data invalido \n";
        } else if (isDateValid(String.valueOf(datePickerCriacao.getValue()))) {
            erroMessage += "Data invalido \n";
        }

        if (comboBoxIdade.getValue() == null) {
            erroMessage += "Idade invalido \n";
        }
        if (comboBoxFuncionario.getValue() == null) {
            erroMessage += "Funcionario invalido \n";
        }
        if (comboBoxGenero.getValue() == null) {
            erroMessage += "Genero invalido \n";
        }
        if (comboBoxEvento.getValue() == null) {
            erroMessage += "Evento invalido \n";
        }

        if (erroMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campo invalido, por favor, corrija...");
            alert.setContentText(erroMessage);
            alert.show();
            return false;
        }
    }

    public static boolean isDateValid(String strDate) {
        String dateFormat = "uuuu/MM/dd";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
