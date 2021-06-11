package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxtrabalhopoo.model.domain.Usuario;


public class UsuarioDao {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public Usuario buscar(Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE nome_usuario=?";
        Usuario retorno = new Usuario();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNomeUsuario());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
               
                usuario.setNomeUsuario(resultado.getString("nome_usuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setStatus(resultado.getString("statu").charAt(0));
                usuario.setCpf(resultado.getString("cpf"));
                usuario.setTelefone(resultado.getString("tel"));
                retorno = usuario;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
