package javafxtrabalhopoo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxtrabalhopoo.model.domain.Jogo;

public class JogoDao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    GeneroDao generoDao = new GeneroDao();

    public List<Jogo> listar() {
        String sql = "SELECT * FROM jogo";
        List<Jogo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            generoDao.setConnection(connection);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Jogo jogo = new Jogo();
                jogo.setIdJogo(resultado.getInt("id_jogo"));
                jogo.setIdGenero(resultado.getInt("id_genero"));
                jogo.setIdEvento(resultado.getInt("id_evento"));
                jogo.setIdUsuario(resultado.getInt("id_usuario"));
                jogo.setQtdVendida(resultado.getInt("qtd_vend_jogo"));
                jogo.setRestricaoIdade(resultado.getInt("restricao_idade"));
                jogo.setValor(resultado.getDouble("valor"));
                jogo.setTempoEstimado(resultado.getInt("tempo_estimado"));
                jogo.setNome(resultado.getString("nome"));
                jogo.setStatu(resultado.getString("statu_jogo").charAt(0));
                jogo.setDateCriacao(resultado.getDate("data_criacao"));
                jogo.setGeneroNome(generoDao.buscarNome(jogo.getIdGenero()));

                retorno.add(jogo);
            }
        } catch (Exception ex) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Jogo buscarId(int id) {
        String sql = "SELECT * FROM jogo WHERE id_jogo=?";
        Jogo jogo = new Jogo();
        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);
            generoDao.setConnection(connection);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {

                jogo.setIdJogo(resultado.getInt("id_jogo"));
                jogo.setIdGenero(resultado.getInt("id_genero"));
                jogo.setIdEvento(resultado.getInt("id_evento"));
                jogo.setIdUsuario(resultado.getInt("id_usuario"));
                jogo.setQtdVendida(resultado.getInt("qtd_vend_jogo"));
                jogo.setRestricaoIdade(resultado.getInt("restricao_idade"));
                jogo.setValor(resultado.getDouble("valor"));
                jogo.setTempoEstimado(resultado.getInt("tempo_estimado"));
                jogo.setNome(resultado.getString("nome"));
                jogo.setStatu(resultado.getString("statu_jogo").charAt(0));
                jogo.setDateCriacao(resultado.getDate("data_criacao"));
                jogo.setGeneroNome(generoDao.buscarNome(jogo.getIdGenero()));
            }
        } catch (SQLException e) {
            System.out.println("JogoDao > buscarId");
        }

        return jogo;
    }
    
    public void alterarQtd(int id, int qtd) {
        String sql = "UPDATE jogo SET qtd_vend_jogo=? WHERE id_jogo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, qtd);
            stmt.setInt(2, id);
            
            stmt.execute();
            System.out.println("Alterado");
            
        } catch (SQLException ex) {
            Logger.getLogger(JogoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
}
