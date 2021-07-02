/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojava.controller;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Usuario;

/**
 * FXML Controller class
 *
 * @author fabri
 */
public class MenuRegistreController implements Initializable {

    @FXML
    private Button buttonLogin;
    @FXML
    private Button buttonSair;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldSenha;
    @FXML
    private TextField textFieldConfirmarSenha;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldSobrenome;
    @FXML
    private TextField textFieldCPF;
    @FXML
    private DatePicker textFieldData;
    @FXML
    private TextField textFieldTelefone;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection(connection);
    }

    @FXML
    public void clicaButtonSair() {
        Stage stage = (Stage) buttonSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void clicaButtonLogin() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/trabalhojava/view/MenuLogin.fxml"));

            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            clicaButtonSair();

        } catch (IOException e) {
            System.out.println("ERRO RegistroController: " + e);
        }

    }

    public void clicaButtonRegistro() {
        if (validarEntradaDeDados()) {
            Usuario usuario = new Usuario();
            usuario.setNomeUsuario(textFieldNome.getText());
            usuario.setRestNome(textFieldSobrenome.getText());
            usuario.setCpf(textFieldCPF.getText());
            usuario.setDataNascimento(java.sql.Date.valueOf(textFieldData.getValue()));
            usuario.setEmail(textFieldEmail.getText());
            usuario.setSenha(textFieldSenha.getText());
            usuario.setTelefone(textFieldTelefone.getText());
            usuario.setStatus('u');
            usuario.setValorCarteira(0);

            try {
                usuarioDao.inserirFunc(usuario);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Conta criada");
                alert.setHeaderText("Entre com sua conta");
                alert.show();
                clicaButtonLogin();

            } catch (Exception e) {
                System.out.println("Erro na inserção de usuario: " + e);
            }
        }
    }

    private boolean validarEntradaDeDados() {
        String erroMessage = "";
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
            erroMessage += "Nome invalido \n";
        }

        if (textFieldSobrenome.getText() == null || textFieldSobrenome.getText().length() == 0) {
            erroMessage += "Sobrenome invalido \n";
        }

        if (textFieldCPF.getText() == null || textFieldCPF.getText().length() == 0) {
            erroMessage += "CPF invalido \n";
        } else if (textFieldCPF.getText().length() != 11) {
            erroMessage += "CPF invalido \n";
        }

        if (textFieldSenha.getText() == null || textFieldSenha.getText().length() == 0) {
            erroMessage += "Senha invalido \n";
        } else if (textFieldConfirmarSenha.getText() == null || textFieldConfirmarSenha.getText().length() == 0) {
            erroMessage += "Senha invalido \n";
        } else if (!textFieldSenha.getText().equals(textFieldConfirmarSenha.getText())) {
            erroMessage += "Senha e Confirmar Senha diferentes \n";
        }

        if (textFieldData.getValue() == null) {
            erroMessage += "Datas invalidas \n";
        } else if (isDateValid(String.valueOf(textFieldData.getValue())) || textFieldData.getValue() == null) {
            erroMessage += "Data invalida \n";
        }

        if (!isValidEmailAddressRegex(textFieldEmail.getText())) {
            erroMessage += "Email invalida \n";
        } else if (usuarioDao.existe(textFieldEmail.getText())) {
            erroMessage += "Email já em uso \n";
        }

        if (textFieldTelefone.getText() == null || textFieldTelefone.getText().length() == 0) {
            erroMessage += "Telefone invalido \n";
        } else if (textFieldTelefone.getText().length() != 9) {
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
