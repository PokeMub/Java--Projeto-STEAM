/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxtrabalhopoo.model.domain.Genero;
import javafxtrabalhopoo.model.domain.Jogo;

public class GeneroDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Genero buscar(Jogo jogo) {
        String sql = "SELECT * FROM genero WHERE id_genero=?";
        Genero retorno = new Genero();
        Genero genero = new Genero();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, jogo.getIdGenero());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                genero.setIdGenero(resultado.getInt("id_genero"));
                genero.setNome(resultado.getString("nome_genero"));
                genero.setDescricao(resultado.getString("descricao"));
                retorno = genero;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public String buscarNome(int idGenero) {
        String sql = "SELECT * FROM genero WHERE id_genero=?";
        String retorno = "";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idGenero);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                retorno = resultado.getString("nome_genero");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Genero> list() throws SQLException {
        String sql = "SELECT * FROM genero";
        List<Genero> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Genero genero = new Genero();

                
                genero.setNome(resultado.getString("nome_genero"));
                genero.setDescricao(resultado.getString("descricao"));
                genero.setIdGenero(resultado.getInt("id_genero"));

                retorno.add(genero);
            }
        } catch (SQLException e) {
            Logger.getLogger(GeneroDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;

    }
    
    public void inserir(Genero genero) {
        String sql = "INSERT INTO genero(nome_genero, descricao) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, genero.getNome());
            stmt.setString(2, genero.getDescricao());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GeneroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
    }
    
    public boolean alterar(Genero genero) {
        String sql = "UPDATE genero SET nome_genero=?, descricao=? WHERE id_genero=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, genero.getNome());
            stmt.setString(2, genero.getDescricao());
            stmt.setInt(3, genero.getIdGenero());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
