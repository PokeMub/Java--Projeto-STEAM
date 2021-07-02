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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.EventoDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Evento;

public class ADMMenuCadastroEventoAlterarController implements Initializable {

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldValor;
    @FXML
    private ComboBox<String> comboBoxForma;
    @FXML
    private ComboBox<String> comboBoxStatus;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonVoltar;

    private Evento evento;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final EventoDao eventoDao = new EventoDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventoDao.setConnection(connection);
        carregarFormaDescont();
        carregarStatus();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
        textFieldNome.setText(evento.getNomeEvento());
        textFieldValor.setText(String.valueOf(evento.getValorDesconto()));
        comboBoxForma.setValue(evento.getFormaDesconto());
        comboBoxStatus.setValue(String.valueOf(evento.getStatu_evento()));
    }

    public void carregarFormaDescont() {
        comboBoxForma.getItems().addAll("%", "flat");
    }

    public void carregarStatus() {
        comboBoxStatus.getItems().addAll("A", "D");
    }

    public void buttonVoltar() {
        Stage stage = (Stage) buttonVoltar.getScene().getWindow();
        stage.close();
    }

    public void buttonAlterar() {
        if (validarEntradaDeDados()) {

            evento.setNomeEvento(textFieldNome.getText());
            evento.setValorDesconto(Double.parseDouble(textFieldValor.getText()));
            evento.setFormaDesconto(comboBoxForma.getValue());
            evento.setStatu_evento(comboBoxStatus.getValue().charAt(0));

            eventoDao.alterar(evento);
            buttonVoltar();
        }

    }

    private boolean validarEntradaDeDados() {
        String erroMessage = "";
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
            erroMessage += "Nome invalido \n";
        }
        try {
            if (textFieldValor.getText() == null || textFieldValor.getText().length() == 0) {
                erroMessage += "Valor invalido \n";
            } else if (Double.parseDouble(textFieldValor.getText()) < 0) {
                erroMessage += "Valor invalido \n";
            }
        } catch (Exception e) {
            erroMessage += "Utilizer ponto em vez de virgula no campo valor \n";
        }

        if (comboBoxForma.getValue() == null) {
            erroMessage += "Forma de desconto invalido \n";
        }
        
        if (comboBoxStatus.getValue() == null) {
            erroMessage += "Status invalido \n";
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

}
