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
    
    public Usuario buscarId(Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE id_usuario=?";
        Usuario retorno = new Usuario();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //stmt.setString(1, usuario.getNomeUsuario());
            stmt.setInt(1,usuario.getIdUsuario());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setNomeUsuario(resultado.getString("nome_usuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setStatus(resultado.getString("statu").charAt(0));
                usuario.setCpf(resultado.getString("cpf"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setDataNascimento(resultado.getDate("data_nasc"));
                usuario.setTelefone(resultado.getString("tel"));
                usuario.setValorCarteira(resultado.getFloat("valor_carteira"));
                usuario.setRestNome(resultado.getString("rest_nome"));
                
                retorno = usuario;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    
    
    public Usuario buscarEmail(Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE email=?";
        Usuario retorno = new Usuario();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());

            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setNomeUsuario(resultado.getString("nome_usuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setStatus(resultado.getString("statu").charAt(0));
                usuario.setCpf(resultado.getString("cpf"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setDataNascimento(resultado.getDate("data_nasc"));
                usuario.setTelefone(resultado.getString("tel"));
                usuario.setValorCarteira(resultado.getFloat("valor_carteira"));
                usuario.setRestNome(resultado.getString("rest_nome"));

                retorno = usuario;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public void alterarCateira(int id, double valor) {
        String sql = "UPDATE usuario SET valor_carteira=? WHERE id_usuario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, valor);
            stmt.setInt(2, id);
            
            stmt.execute();
            System.out.println("Alterado");
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
}
