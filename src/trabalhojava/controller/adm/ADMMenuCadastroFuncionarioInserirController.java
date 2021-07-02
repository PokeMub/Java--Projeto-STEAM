package trabalhojava.controller.adm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Usuario;
import static trabalhojava.controller.adm.ADMMenuCadastroEventoInserirController.isDateValid;

public class ADMMenuCadastroFuncionarioInserirController implements Initializable {

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonVoltar;
    @FXML
    private TextField textname;
    @FXML
    private TextField textrestname;
    @FXML
    private TextField textcpf;
    @FXML
    private DatePicker datedata;
    @FXML
    private TextField textemail;
    @FXML
    private TextField textsenha;
    @FXML
    private TextField texttelefone;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection(connection);
        // TODO
    }

    public void clicarButtonVoltar() {
        Stage stage = (Stage) buttonVoltar.getScene().getWindow();
        stage.close();
    }

    public void clicarbuttonInserir() throws IOException {

        Usuario usuario = new Usuario();

        if (validarEntradaDeDados()) {
            
            usuario.setNomeUsuario(textname.getText());
            usuario.setRestNome(textrestname.getText());
            usuario.setCpf(textcpf.getText());
            usuario.setDataNascimento(java.sql.Date.valueOf(datedata.getValue()));
            usuario.setEmail(textemail.getText());
            usuario.setSenha(textsenha.getText());
            usuario.setTelefone(texttelefone.getText());
            usuario.setStatus('a');
            usuario.setValorCarteira(0);
                   
            try {
                usuarioDao.inserirFunc(usuario);
                clicarButtonVoltar();

            } catch (Exception e) {
                System.out.println("Erro na inserção de usuario: " + e);
            }
        }

    }

    private boolean validarEntradaDeDados() {
        String erroMessage = "";
        if (textname.getText() == null || textname.getText().length() == 0) {
            erroMessage += "Nome invalido \n";
        }

        if (textrestname.getText() == null || textrestname.getText().length() == 0) {
            erroMessage += "Sobrenome invalido \n";
        }
        
        if (textsenha.getText() == null || textsenha.getText().length() == 0) {
            erroMessage += "Senha invalido \n";
        }


        if (textcpf.getText() == null || textcpf.getText().length() == 0) {
            erroMessage += "CPF invalido \n";
        } else if (textcpf.getText().length() != 11) {
            erroMessage += "CPF invalido \n";
        }

        if (datedata.getValue() == null) {
            erroMessage += "Datas invalidas \n";
        } else if (isDateValid(String.valueOf(datedata.getValue())) || datedata.getValue() == null) {
            erroMessage += "Data invalida \n";
        }

        if (!isValidEmailAddressRegex(textemail.getText())) {
            erroMessage += "Email invalida \n";
        } else if (usuarioDao.existe(textemail.getText())) {
            erroMessage += "Email já em uso \n";
        }

        if (texttelefone.getText() == null || texttelefone.getText().length() == 0) {
            erroMessage += "Telefone invalido \n";
        } else if (texttelefone.getText().length() != 9) {
            erroMessage += "Telefone invalido \n";
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
    
    public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
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
