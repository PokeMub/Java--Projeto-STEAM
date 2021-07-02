package trabalhojava.controller.adm;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.EventoDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Evento;

public class ADMMenuCadastroEventoInserirController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldValorDesconto;

    @FXML
    private ComboBox<String> comboBoxFormaDescont;

    @FXML
    private DatePicker DatePikerDataInicio;

    @FXML
    private DatePicker DatePikerDataFim;

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonVoltar;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final EventoDao eventoDao = new EventoDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventoDao.setConnection(connection);
        carregarFormaDescont();
        

    }

    public void buttonVoltar() {
        Stage stage = (Stage) buttonVoltar.getScene().getWindow();
        stage.close();
    }

    public void carregarFormaDescont() {
        comboBoxFormaDescont.getItems().addAll("%", "FLAT");
    }

    public void buttonInserirr() {


        Evento evento = new Evento();
        //evento.setStatu_evento("A".charAt(0));
        if (validarEntradaDeDados()) {
            evento.setNomeEvento(textFieldNome.getText());
            evento.setValorDesconto(Double.parseDouble(textFieldValorDesconto.getText()));
            evento.setFormaDesconto(comboBoxFormaDescont.getValue());
            evento.setDataInicio(java.sql.Date.valueOf(DatePikerDataInicio.getValue()));
            evento.setDataTermino(java.sql.Date.valueOf(DatePikerDataFim.getValue()));


            try {

                eventoDao.inserir(evento);
                buttonVoltar();

            } catch (Exception e) {
                System.out.println("Erro na inserção d evento: " + e);
            }
        }


    }

    private boolean validarEntradaDeDados() {
        String erroMessage = "";
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
            erroMessage += "Nome invalido \n";
        }
        try {
            if (textFieldValorDesconto.getText() == null || textFieldValorDesconto.getText().length() == 0) {
                erroMessage += "Valor invalido \n";
            } else if (Double.parseDouble(textFieldValorDesconto.getText()) < 0) {
                erroMessage += "Valor invalido \n";
            }
        } catch (Exception e) {
            erroMessage += "Utilizer ponto em vez de virgula no campo valor \n";
        }
        if ((DatePikerDataInicio.getValue() == null) || (DatePikerDataFim.getValue() == null)) {
            erroMessage += "Datas invalidas \n";

        } else {
            if (isDateValid(String.valueOf(DatePikerDataInicio.getValue())) || DatePikerDataInicio.getValue() == null) {
                erroMessage += "Data de incio invalido \n";
            }
            if (isDateValid(String.valueOf(DatePikerDataFim.getValue())) || DatePikerDataFim.getValue() == null) {
                erroMessage += "Data de temino invalido \n";
            }

            if (DatePikerDataInicio.getValue().compareTo(DatePikerDataFim.getValue()) >= 0) {
                erroMessage += "Data de inicio tem que se antes da data de termino \n";
            }
        }

        if (comboBoxFormaDescont.getValue()
                == null) {
            erroMessage += "Forma de desconto invalido \n";
        }

        if (erroMessage.length()
                == 0) {
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
