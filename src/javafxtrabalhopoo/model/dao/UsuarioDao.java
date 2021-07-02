package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxtrabalhopoo.model.domain.Evento;
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
            stmt.setInt(1, usuario.getIdUsuario());
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

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public boolean existe(String email) {
        String sql = "SELECT * FROM usuario WHERE email=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, email);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public List<Usuario> listFuncionario() {
        String sql = "SELECT * FROM usuario WHERE statu='a'";
        List<Usuario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();

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

                retorno.add(usuario);
            }
        } catch (Exception e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public boolean inserirFunc(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome_usuario, rest_nome, cpf, data_nasc, senha, tel, statu, email, valor_carteira) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, usuario.getNomeUsuario());

            stmt.setString(2, usuario.getRestNome());

            stmt.setString(3, usuario.getCpf());

            stmt.setDate(4, (java.sql.Date) usuario.getDataNascimento());

            stmt.setString(5, usuario.getSenha());

            stmt.setString(6, usuario.getTelefone());

            stmt.setString(7, String.valueOf(usuario.getStatus()));

            stmt.setString(8, usuario.getEmail());

            stmt.setDouble(9, usuario.getValorCarteira());

            stmt.execute();
            return true;

        } catch (SQLException ex) {

            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome_usuario=?, tel=? WHERE id_usuario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getTelefone());
            stmt.setInt(3, usuario.getIdUsuario());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id_usuario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
