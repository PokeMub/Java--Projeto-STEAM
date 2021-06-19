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
}
