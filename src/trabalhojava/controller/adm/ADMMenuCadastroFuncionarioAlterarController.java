package trabalhojava.controller.adm;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxtrabalhopoo.model.dao.UsuarioDao;
import javafxtrabalhopoo.model.database.Database;
import javafxtrabalhopoo.model.database.DatabaseFactory;
import javafxtrabalhopoo.model.domain.Usuario;
import static trabalhojava.controller.adm.ADMMenuCadastroFuncionarioInserirController.isDateValid;

public class ADMMenuCadastroFuncionarioAlterarController implements Initializable {

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonVoltar;

    private Usuario usuario;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao.setConnection(connection);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        textFieldNome.setText(usuario.getNomeUsuario().replaceAll("\\s+",""));
        textFieldTelefone.setText(usuario.getTelefone().replaceAll("\\s+",""));

    }

    public void buttonVoltar() {
        Stage stage = (Stage) buttonVoltar.getScene().getWindow();
        stage.close();
    }

    public void buttonAlterar() {
        if (validarEntradaDeDados()) {
            usuario.setNomeUsuario(textFieldNome.getText());
            usuario.setTelefone(textFieldTelefone.getText());

            usuarioDao.alterar(usuario);
            buttonVoltar();
        }

    }

    private boolean validarEntradaDeDados() {
        String erroMessage = "";
        if (textFieldNome.getText() == null || textFieldNome.getText().length() == 0) {
            erroMessage += "Nome invalido \n";
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

}
